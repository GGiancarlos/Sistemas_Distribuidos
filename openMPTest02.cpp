
#include <omp.h>
#include <stdio.h>

int main (int argc, char *argv[])
{
    double res[1000];
    #pragma omp parallel for
    for(int i = 0; i < 1000; i++)
    {
        res[i] = i*i;
        printf("value: %d\n", i);
    }


}

