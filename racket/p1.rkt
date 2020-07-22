#lang racket

;; Multiple of 3 and 5
;; Find the sum of all the multiples of 3 or 5 below 1000.

(define p1
  (lambda (n)
    (apply +
      (filter
        (lambda (x) (or (zero? (modulo x 3))
                        (zero? (modulo x 5))))
        (range n)))))
(p1 10)
(p1 1000)

