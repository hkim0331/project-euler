#lang racket

(define collatz-count
  (lambda (n)
    (cond
      ((= n 1) 1)
      ((even? n) (+ 1 (collatz-count (/ n 2))))
      (else (+ 2 (collatz-count (/ (+ 1 (* 3 n)) 2)))))))

(define p14-count
  (lambda (n)
    (apply max (map collatz-count (range (/ n 2) n)))))

; (time (p14-count 1000000))
; > cpu time: 2460 real time: 2500 gc time: 161
; 525

(require memoize)

(define/memo (collatz-memo n)
  (cond
    ((= n 1) 1)
    ((even? n) (+ 1 (collatz-memo (/ n 2))))
    (else (+ 2 (collatz-memo (/ (+ 1 (* 3 n)) 2))))))

(collatz-memo 10)

(define p14-memo
  (lambda (n)
    (apply max (map collatz-memo (range (/ n 2) n)))))

; (time (p14-memo 1000000))
; > cpu time: 1174 real time: 1194 gc time: 465
; 525
