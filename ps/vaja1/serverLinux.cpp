/*H********************************************************************************
* Ime datoteke: serverLinux.cpp
*
* Opis:
*		Enostaven stre�nik, ki zmore sprejeti le enega klienta naenkrat.
*		Stre�nik sprejme klientove podatke in jih v nespremenjeni obliki po�lje
*		nazaj klientu - odmev.
*
*H*/

// Vključimo ustrezna zaglavja
#include <stdio.h>
#include <sys/socket.h>
#include <netinet/in.h>
#include <unistd.h>
#include <pthread.h>
/*
Definiramo vrata (port) na katerem bo strežnik poslušal
in velikost medponilnika za sprejemanje in pošiljanje podatkov
*/
#define PORT 1053
#define BUFFER_SIZE 256
#define MAX_CONNECTIONS 3

int sockets[MAX_CONNECTIONS], connCount = 0;

pthread_mutex_t lock;

void* talk (void *arg) {
	int iResult, clientSock = *(int*)arg;
	char buff[BUFFER_SIZE];
	printf("\nNew thread: %d\n", clientSock);

	// Postrezi povezanemu klientu
	do {

		// Sprejmi podatke
		iResult = recv(clientSock, buff, BUFFER_SIZE, 0);
		if (iResult > 0) {
			printf("Bytes received: %d\n", iResult);

			// Vrni prejete podatke pošiljatelju
			// iResult = send(clientSock, test, 11, 0 );
			iResult = send(clientSock, buff, iResult, 0 );
			if (iResult == -1) {
				printf("send failed!\n");
				close(clientSock);
				break;
			}
			printf("Bytes sent: %d\n", iResult);
		} 
		else if (iResult == 0)
			printf("Connection closing...\n");
		else {
			printf("recv failed!\n");
			close(clientSock);
			break;
		}

	} while (iResult > 0);

	close(clientSock);
	pthread_mutex_lock(&lock);
	*(int*)arg = -2;
	connCount--;
	pthread_mutex_unlock(&lock);

	return 0;
}

int main(int argc, char **argv) {
	// A je problem, če mamo mutex destroy v main klicu, vsi threadi so pa detached?
	pthread_mutex_init(&lock, NULL);

	// Spremenjlivka za preverjane izhodnega statusa funkcij
	int iResult;

	for (int i = 0; i < MAX_CONNECTIONS; i++) sockets[i] = -2;

	/*
	Ustvarimo nov vtič, ki bo poslušal
	in sprejemal nove kliente preko TCP/IP protokola
	*/
	int listener=socket(AF_INET, SOCK_STREAM, 0);
	if (listener == -1) {
		printf("Error creating socket\n");
		return 1;
	}

	// Nastavimo vrata in mrežni naslov vtiča
	sockaddr_in  listenerConf;
	listenerConf.sin_port = htons(PORT);
	listenerConf.sin_family = AF_INET;
	listenerConf.sin_addr.s_addr = INADDR_ANY;

	// Vtič povežemo z ustreznimi vrati
	iResult = bind( listener, (sockaddr *)&listenerConf, sizeof(listenerConf));
	if (iResult == -1) {
		printf("Bind failed\n");
		close(listener);
		return 1;
	}

	// Začnemo poslušati
	if ( listen( listener, 5 ) == -1 ) {
		printf( "Listen failed\n");
		close(listener);
		return 1;
	}

	// Definiramo nov vtič in medpomnilik
	int clientSock;
	
	/*
	V zanki sprejemamo nove povezave
	in jih strežemo (največ eno naenkrat)
	*/
	while (1)
	{
		// Sprejmi povezavo in ustvari nov vtič
		clientSock = accept(listener, NULL, NULL);
		if (clientSock == -1) {
			printf("Accept failed\n");
			close(listener);
			return 1;
		}

		pthread_mutex_lock(&lock);

		// Check if maximum connection count reached
		if (connCount >= MAX_CONNECTIONS) {
			pthread_mutex_unlock(&lock);
			close(clientSock);
			continue;
		}

		// Create thread
		pthread_t new_thread;
		for (int i = 0; i < MAX_CONNECTIONS; i++) {
			if (sockets[i] == -2) {
				// this array entry is available
				sockets[i] = clientSock;
				printf("\nMain thread: %d %d\n", i, sockets[i]);
				pthread_create(&new_thread, NULL, talk, (void*)&sockets[i]);
				pthread_detach(new_thread);
				connCount++;

				break;
			}	
		}

		pthread_mutex_unlock(&lock);

	}

	// Počistimo vse vtiče
	close(listener);
	pthread_mutex_destroy(&lock);

	return 0;
}
