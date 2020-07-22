#lang racket

(define f-aux
  (lambda (n m ret)
    (cond
      ((< n m) ret)
      ((zero? (modulo n m)) (f-aux (/ n m) m (cons m ret)))
      (else (f-aux n (+ m 2) ret)))))

(define factor
  (lambda (n)
    (f-aux n 3 '())))

(factor 13195)

(define p3
  (lambda (n)
    (apply max (factor n))))

(p3 13195)

(time (p3 600851475143))
