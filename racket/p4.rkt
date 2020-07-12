#lang racket

(define string-reverse
  (lambda (s)
    (list->string (reverse (string->list s)))))

(define palindrome-number?
  (lambda (n)
    (let ((s (format "~s" n)))
      (string=? s (string-reverse s)))))

(define find-largest-palindrome
  (lambda (from to)
    (apply max
      (flatten
        (for/list ([x (range from to)])
          (filter palindrome-number?
            (for/list ([y (range from x)])
              (* x y))))))))

(time (find-largest-palindrome 100 1000))
;;> cpu time: 5 real time: 5 gc time: 0
;;906609

