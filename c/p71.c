/*
Consider the fraction, n/d, where n and d are positive integers. If n<d and HCF(n,d)=1, it is called a reduced proper fraction.
If we list the set of reduced proper fractions for d ≤ 8 in ascending order of size, we get:

1/8, 1/7, 1/6, 1/5, 1/4, 2/7, 1/3, 3/8, 2/5, 3/7, 1/2, 4/7, 3/5, 5/8, 2/3, 5/7, 3/4, 4/5, 5/6, 6/7, 7/8

It can be seen that 2/5 is the fraction immediately to the left of 3/7.
By listing the set of reduced proper fractions for d ≤ 1,000,000 in ascending order of size, find the numerator of the fraction immediately to the left of 3/7.
*/

/*

上限3/7の他、下限を設け、C でループすればいけるだろう作戦は失敗。

*/
#include <stdio.h>
#include <stdlib.h>

/*

1<= n,d <=m, HCF(n,d)=1 について、
n/d が 3/7 よりもジャスト小さい一組を求める。
その数は 2/7 よりも大きいだろう作戦。

*/

int hcf(int x, int y) {
  if (y==0) {
    return x;
  } else {
    return hcf(y, x%y);
  }
}

int p71(int m) {
  int n,d;
  for (d=1; d<=m; d++) {
    for (n=1; n<d; n++) {
      if (hcf(d,n) != 1) {
        continue;
      }
      float v = (float)n/d;
      // 42857, 100000 は n=100000 の実験で得たもの。ズル。
      if (42857.0/100000 < v && v < 3.0/7) {
        printf("%f %d %d\n",v,n,d);
      } else if (3.0/7 <= (float)n/d) {
        break;
      }
    }
  }
  return 1;
}

int main(int argc, char *argv[]) {
  if (argc == 1) {
    printf("usage: ./a.out <n> | sort | tail -1\n");
    exit(-1);
  }
  int n = atoi(argv[1]);
  return p71(n);
}
