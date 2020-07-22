; Even Fibonacci numbers
; Each new term in the Fibonacci sequence is generated by
; adding the previous two terms. By starting with 1 and 2,
; the first 10 terms will be:
;
; 1, 2, 3, 5, 8, 13, 21, 34, 55, 89, ...
;
; By considering the terms in the Fibonacci sequence whose
; values do not exceed four million, find the sum of the
; even-valued terms.

(define fibo-aux
  (lambda (n xs)
    (if (< n (car xs))
      (cdr xs)
      (fibo-aux n (cons (+ (car xs) (cadr xs)) xs)))))

(define fibo-under-n
  (lambda (n)
    (fibo-aux n '(2 1))))

(fibo-under-n 100)

(filter even? (range 10))

(define p2
  (lambda (n)
    (apply + (filter even? (fibo-under-n n)))))

(time (p2 4000000))