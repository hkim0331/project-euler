
// p92.clj を C で書き直し。
// 30分で仕上げる予定が 31 分かかった。
// clojure で 20 秒だが、C では 1 秒。
//こんなもんだろ。

#include <stdio.h>
#include <stdlib.h>

int sq (int n) {
  return n*n;
}

int once(int n) {
  char s[256];
  char *p;
  int ret;

  sprintf(s,"%d", n);
//  printf("s=%s\n", s);

  ret = 0;
  for (p = s; *p != (char)NULL; p++) {
    ret += sq(*p - '0');
  }
  return ret;
}

void check(int *memo, int n) {
  int i;
  for (i=0;i<n;i++) {
    printf("%d ",memo[i]);
  }
}

int p92(int n) {
  int memo[568];
  int i;
  int ret;

  //p92-aux の機能をここに

  memo[0]=0; // no use
  for (i=1; i<568; i++) {
    int m = once(i);
    for (;;) {
      if (m==1) {
        memo[i]=0;
        break;
      } else if (m==89) {
        memo[i]=1;
        break;
      }
      m = once(m);
    }
  }

//  check(memo, 568);

  ret = 0;
  for (i=1; i< n; i++) {
    int m = once(i);
//    printf("i, m: %d, %d", i, m);
    ret += memo[m];
//    printf(" ret: %d\n", ret);
  }
  return ret;
}

int main(int argc, char *argv[]) {
  if (argc==1) {
    fprintf(stderr, "usage: ./a.out <integer>\n");
    exit(1);
  }
  int n = atoi(argv[1]);
  printf("%d\n", p92(n));
  return 0;
}

//% time ./a.out 10000000
//8581146
//./a.out 10000000  1.04s user 0.00s system 99% cpu 1.040 total