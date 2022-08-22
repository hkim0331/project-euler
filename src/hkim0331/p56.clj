(ns hkim0331.p56
  (:require
    [hkim0331.misc :refer [power]]))


;; A googol (10^100) is a massive number: one followed by one-hundred zeros; 100^100 is almost unimaginably large: one followed by two-hundred zeros. Despite their size, the sum of the digits in each number is only 1.

;; Considering natural numbers of the form, a^b, where a, b < 100, what is the maximum digital sum?

;; stupid, OK?

(defn to-int
  [c]
  (- (int c) (int \0)))


;; (to-int \4)

(defn p56
  []
  (apply max
         (for [a (range 100) b (range 100)]
           (reduce +
                   (map to-int (seq (str (power a b))))))))


;; (time (p56))
;; "Elapsed time: 217.049367 msecs"
;; 972
