#lang racket

(require math/number-theory)

(define p72
  (lambda (n)
    (apply + (map totient (range 2 (+ n 1))))))

;(time (p72 1000000))
;cpu time: 12092 real time: 12195 gc time: 607
;303963552391
