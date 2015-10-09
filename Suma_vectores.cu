
_global_ void Suma_vectores(float *c, float *a, float *b, int N)
{
	int idx = blockIdx.x *blocDim.x + threadIdx.x;
	if (idx < N)
	{
		c[idx] = a[idx] + b[idx];
	}
}