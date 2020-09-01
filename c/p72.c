#include <stdio.h>
#include <stdlib.h>

long p72(int n) {

  // must be long.
  long* phi = (long*)malloc(sizeof(long)*(n+1));
  int i, j;

  for (i=0; i<=n; i++) {
    phi[i] = i;
  }

  for (i=2; i<=n; i++) {
    if (phi[i]==i) {
      for (j=i; j<=n; j+=i) {
        phi[j] = (phi[j]*(i-1))/i;
        //        ^^^^^^^^^^^^ since this may exceed int.
      }}}

  long ret = 0;
  for (i=2; i<=n; i++) {
    ret += phi[i];
  }
  return ret;
}

int main(int argc, char *argv[]) {
  if (argc != 2) {
    printf("usage: a.out <n>\n");
    exit(-1);
  }

  int n = atoi(argv[1]);
  printf("%ld\n", p72(n));
  return 0;
}