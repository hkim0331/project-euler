(ns hkim0331.p57)


;; It is possible to show that the square root of two can be expressed as an infinite continued fraction.
;; By expanding this for the first four iterations, we get:
;; The next three expansions are
;; ,
;; , and
;; , but the eighth expansion,
;; , is the first example where the number of digits in the numerator exceeds the number of digits in the denominator.
;; In the first one-thousand expansions, how many fractions contain a numerator with more digits than the denominator?

;; f(n+1) = 1+1/(1+f(n))

(def sqrt2 (take 1000 (iterate #(+ 1 (/ (+ 1 %))) (/ 3 2))))


;; (numerator (/ 2 3))
;; 2
;; (denominator (/ 2 3))
;; 3

(defn number-of-digits
  [n]
  (count (seq (str n))))


;; (number-of-digits 123)
;; 3

(defn p57
  []
  (count
    (filter #(> (number-of-digits (numerator %))
                (number-of-digits (denominator %)))
            sqrt2)))


;; (time (p57))
;; "Elapsed time: 102.16413 msecs"
;; 153
