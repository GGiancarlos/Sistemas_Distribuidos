
#include <pthread.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#define MAX_THREADS     10

pthread_t tabla_thr[MAX_THREADS];

typedef struct {
  int id;
  char *cadena;
} thr_param_t;
thr_param_t param[MAX_THREADS];

void *funcion_thr(thr_param_t *p)
{

  printf("%s %d\n", p->cadena, p->id);

  pthread_exit(&(p->id));
}
int main(void)
{
  int i, *res;

  printf("Creando threads...\n");
  for (i=0; i<MAX_THREADS; i++) {
    param[i].cadena = strdup("Hola, soy el thread");
    param[i].id     = i;
    pthread_create(&tabla_thr[i], NULL, (void *)&funcion_thr,
     (void *)&param[i]);
  }

  printf("Threads creados. Esperando que terminen...\n");
  for (i=0; i<MAX_THREADS; i++) {
    pthread_join(tabla_thr[i], (void *)&res);
    printf("El thread %d devolvio el valor %d\n", i, *res);
  }

  printf("Todos los threads finalizados. Adios!\n");
  return 0;
}
