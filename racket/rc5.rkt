#lang racket

(define (r-aux r n ret)
  (if (zero? n)
      ret
      (r-aux r (- n 1) (cons (random r) ret))))
(define (randoms r n)
  (r-aux r n '()))

(define data (randoms 10 1000000))

(define (make-set xs)
  (define (aux xs ret)
    (cond
      ((null? xs) ret)
      ((member? (car xs) (cdr xs)) (aux (cdr xs) ret))
      (else (aux (cdr xs) (cons (car xs) ret)))))
  (define (member? a xs)
    (cond
      ((null? xs) #f)
      ((eq? a (car xs)) #t)
      (else (member? a (cdr xs)))))
  (aux xs '()))

; (time (make-set data))
; cpu time: 55 real time: 55 gc time: 0
; '(3 4 9 1 7 0 5 8 2 6)