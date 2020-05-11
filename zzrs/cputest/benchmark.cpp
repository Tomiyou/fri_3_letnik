#include <iostream>
#include <ctime>
#include <ratio>
#include <chrono>
#include <iomanip>

template <class PodatkovnaStruktura>
void factorialTEST(PodatkovnaStruktura startPoint)
{
    int N = 500;

    for(int i=0;i<N;i++)
    {
        PodatkovnaStruktura n = startPoint, f = 1;

        for (int c = 1; c <= n; c++)
            f = f * c;

        n++;
    }
}

template <class PodatkovnaStruktura>
bool isPrime(PodatkovnaStruktura n)
{
    if (n <= 1)
        return false;

    for (int i = 2; i < n; i++)
        if (n % i == 0)
            return false;

    return true;
}

template <class PodatkovnaStruktura>
void primeTEST(PodatkovnaStruktura startPoint)
{
    long N = 5000;

    for(long i=(long)startPoint;i<startPoint+N;i++)
    {
        isPrime(i);
    }
}


double timeDiff(clock_t startTime, clock_t endTime)
{
    return ((double)(endTime - startTime))/ CLOCKS_PER_SEC;
}


int main ()
{
    using namespace std::chrono;

    duration<double, std::milli> time_elapsed;

    /*int factorialTEST1 = 10050000;
    long factorialTEST2 = 10050000;
    float factorialTEST3 = 10050000;
    double  factorialTEST4 = 10050000;

    int primeTEST1 = 10050000;
    long primeTEST2 = 10050000;
    float primeTEST3 = 10050000;
    double  primeTEST4 = 10050000;*/

    int factorialTEST1 = 1005000;
    long factorialTEST2 = 1005000;
    float factorialTEST3 = 1005000;
    double  factorialTEST4 = 1005000;

    int primeTEST1 = 1005000;
    long primeTEST2 = 1005000;
    float primeTEST3 = 1005000;
    double  primeTEST4 = 1005000;

    high_resolution_clock::time_point programStart = high_resolution_clock::now();

    std::cout <<std::fixed<< "TESTIRANJE z int:" << std::endl;
    high_resolution_clock::time_point primeStart = high_resolution_clock::now();
    primeTEST(primeTEST1);
    high_resolution_clock::time_point primeEnd = high_resolution_clock::now();
    time_elapsed = primeEnd - primeStart;
    std::cout <<std::fixed<< "Prime TEST elapsed time: " << (time_elapsed.count()/1000) << " seconds." << std::endl;


    high_resolution_clock::time_point factStart = high_resolution_clock::now();
    factorialTEST(factorialTEST1);
    high_resolution_clock::time_point factEnd = high_resolution_clock::now();
    time_elapsed = factEnd - factStart;
    std::cout <<std::fixed<< "Factorial TEST elapsed time: " << (time_elapsed.count()/1000) << " seconds." << std::endl<< std::endl;


    std::cout <<std::fixed<< "TESTIRANJE z long:" << std::endl;
    primeStart = high_resolution_clock::now();
    primeTEST(primeTEST2);
    primeEnd = high_resolution_clock::now();
    time_elapsed = primeEnd - primeStart;
    std::cout <<std::fixed<< "Prime TEST elapsed time: " << (time_elapsed.count()/1000) << " seconds." << std::endl;


    factStart = high_resolution_clock::now();
    factorialTEST(factorialTEST2);
    factEnd = high_resolution_clock::now();
    time_elapsed = factEnd - factStart;
    std::cout <<std::fixed<< "Factorial TEST elapsed time: " << (time_elapsed.count()/1000) << " seconds." << std::endl<< std::endl;



    std::cout <<std::fixed<< "TESTIRANJE z float:" << std::endl;
    primeStart = high_resolution_clock::now();
    primeTEST(primeTEST3);
    primeEnd = high_resolution_clock::now();
    time_elapsed = primeEnd - primeStart;
    std::cout <<std::fixed<< "Prime TEST elapsed time: " << (time_elapsed.count()/1000) << " seconds." << std::endl;


    factStart = high_resolution_clock::now();
    factorialTEST(factorialTEST3);
    factEnd = high_resolution_clock::now();
    time_elapsed = factEnd - factStart;
    std::cout <<std::fixed<< "Factorial TEST elapsed time: " << (time_elapsed.count()/1000) << " seconds." << std::endl<< std::endl;



    std::cout <<std::fixed<< "TESTIRANJE z double:" << std::endl;
    primeStart = high_resolution_clock::now();
    primeTEST(primeTEST4);
    primeEnd = high_resolution_clock::now();
    time_elapsed = primeEnd - primeStart;
    std::cout <<std::fixed<< "Prime TEST elapsed time: " << (time_elapsed.count()/1000) << " seconds." << std::endl;


    factStart = high_resolution_clock::now();
    factorialTEST(factorialTEST4);
    factEnd = high_resolution_clock::now();
    time_elapsed = factEnd - factStart;
    std::cout <<std::fixed<< "Factorial TEST elapsed time: " << (time_elapsed.count()/1000) << " seconds." << std::endl<< std::endl;


    high_resolution_clock::time_point programEnd = high_resolution_clock::now();
    time_elapsed = programEnd - programStart;
    std::cout <<std::fixed<< "Program total elapsed time: " << (time_elapsed.count()/1000) << " seconds." << std::endl;

    return 0;
}
