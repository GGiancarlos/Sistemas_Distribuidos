/*
	La salida del programa deberia de ser:
	Hello World! My rank is 0
	Hello World! My rank is 1
	Hello World! My rank is 2
	Hello World! My rank is 3
	Hello World! My rank is 4
	Hello World! My rank is 5
	Hello World! My rank is 6
	Hello World! My rank is 7
	Hello World! My rank is 8
	Hello World! My rank is 9
*/

#include "mpi.h"
#include <iostream>

using namespace std;

int main(int argc, char** argv)
{
	int baton = 1;
	int max;
	int myRank;
	MPI_Status status;

	MPI_Init(&argc, &argv);
	MPI_Comm_rank(MPI_COMM_WORLD, &myRank);
	MPI_Comm_size(MPI_COMM_WORLD, &max);
	if(myRank == 0)
	{
		cout << "Hello World! My rank is 0" << endl;
		MPI_Send(&baton, 1, MPI_INT, 1, 1, MPI_COMM_WORLD);
	}
	else
	{
		MPI_Recv(&baton, 1, MPI_INT, myRank-1, 1, MPI_COMM_WORLD, &status);
		cout << "Hello World! My rank is " << myRank << endl;
		if(myRank < max-1)
			MPI_Send(&baton, 1, MPI_INT, myRank+1, 1, MPI_COMM_WORLD);
	}

	MPI_Finalize();
}
