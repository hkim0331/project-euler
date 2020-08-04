#lang racket

; a^b + b^2 = c^2, a + b + c = 1000
; what is (* a b c)

(define p9
  (lambda ()
    (let ((ret '()))
      (for ((a (range 1 1000)))
        (for ((b (range 1 a)))
          (let ((c (- 1000 (+ a b))))
            (when (and (< 0 c)
                       (= (+ (* a a) (* b b)) (* c c)))
              (set! ret (cons (* a b c) ret))))))
      ret)))

(time (p9))
