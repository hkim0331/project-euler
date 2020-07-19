; 2520 is the smallest number that can be divided
; by each of the numbers
; from 1 to 10 without any remainder.
; What is the smallest positive number that is
; evenly divisible by all of
; the numbers from 1 to 20?

(define gcd
  (lambda (x y)
    (if (zero? y)
      x
      (gcd y (modulo x y)))))

(define lcm
  (lambda (x y)
    (/ (* x y) (gcd x y))))

(define inject
  (lambda (f i v)
    (if (empty? v)
      i
      (inject f (f i (car v)) (cdr v)))))

(define my-max
  (lambda (x y)
    (if (< x y)
      y
      x)))

(define p5
  (lambda (n)
    (inject lcm 1 (range 1 (+ 1 n)))))

(p5 10)
; 2520
(p5 20)
; 232792560
