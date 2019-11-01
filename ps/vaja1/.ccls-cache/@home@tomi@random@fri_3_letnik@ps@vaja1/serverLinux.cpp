/*H********************************************************************************
* Ime datoteke: serverLinux.cpp
*
* Opis:
*		Enostaven stre�nik, ki zmore sprejeti le enega klienta naenkrat.
*		Stre�nik sprejme klientove podatke in jih v nespremenjeni obliki po�lje
*		nazaj klientu - odmev.
*
*H*/

//Vklju�imo ustrezna zaglavja
#include<stdio.h>
#include<sys/socket.h>
#include<netinet/in.h>
#include<unistd.h>
#include<pthread.h>
/*
Definiramo vrata (port) na katerem bo stre�nik poslu�al
in velikost medponilnika za sprejemanje in po�iljanje podatkov
*/
#define PORT 1053
#define BUFFER_SIZE 256
#define MAX_CONNECTIONS 3

int sockets[MAX_CONNECTIONS], connCount = 0;

pthread_mutex_t lock;

void* talk(void *arg) {
	int iResult, clientSock = *(int*)arg;
	char buff[BUFFER_SIZE];
	printf("\nNew thread: %d\n", clientSock);

	//Postrezi povezanemu klientu
	do{

		//Sprejmi podatke
		iResult = recv(clientSock, buff, BUFFER_SIZE, 0);
		if (iResult > 0) {
			printf("Bytes received: %d\n", iResult);

			//Vrni prejete podatke po�iljatelju
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
		else{
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

int main(int argc, char **argv){
	// A je problem, če mamo mutex destroy v main klicu, vsi threadi so pa detached?

	//Spremenjlivka za preverjane izhodnega statusa funkcij
	int iResult;

	for (int i = 0; i < MAX_CONNECTIONS; i++) sockets[i] = -2;

	/*
	Ustvarimo nov vti�, ki bo poslu�al
	in sprejemal nove kliente preko TCP/IP protokola
	*/
	int listener=socket(AF_INET, SOCK_STREAM, 0);
	if (listener == -1) {
		printf("Error creating socket\n");
		return 1;
	}

	//Nastavimo vrata in mre�ni naslov vti�a
	sockaddr_in  listenerConf;
	listenerConf.sin_port=htons(PORT);
	listenerConf.sin_family=AF_INET;
	listenerConf.sin_addr.s_addr=INADDR_ANY;

	//Vti� pove�emo z ustreznimi vrati
	iResult = bind( listener, (sockaddr *)&listenerConf, sizeof(listenerConf));
	if (iResult == -1) {
		printf("Bind failed\n");
		close(listener);
		return 1;
	}

	//Za�nemo poslu�ati
	if ( listen( listener, 5 ) == -1 ) {
		printf( "Listen failed\n");
		close(listener);
		return 1;
	}

	//Definiramo nov vti� in medpomnilik
	int clientSock;
	
	/*
	V zanki sprejemamo nove povezave
	in jih stre�emo (najve� eno naenkrat)
	*/
	while (1)
	{
		//Sprejmi povezavo in ustvari nov vti�
		clientSock = accept(listener,NULL,NULL);
		if (clientSock == -1) {
			printf("Accept failed\n");
			close(listener);
			return 1;
		}

		if (connCount >= MAX_CONNECTIONS) {
			close(clientSock);
			continue;
		}

		// Create thread
		pthread_t new_thread;

		pthread_mutex_lock(&lock);
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

	//Po�istimo vse vti�e
	close(listener);

	return 0;
}
