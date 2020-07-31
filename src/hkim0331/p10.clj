(ns hkim0331.p10
  (:require [hkim0331.p7 :refer [next-prime]]))

;The sum of the primes below 10 is 2 + 3 + 5 + 7 = 17.
;Find the sum of all the primes below two million.

(defn p10 [n]
  (reduce +
    (take-while
      (partial > n)
      (iterate next-prime 2))))

;(p10 10)
; 17

; 6 秒か。need improve!
; (time (p10 2000000))
; "Elapsed time: 5790.05082 msecs"
; 142913828922

(time (p10 200000))



(defn sieve [lst]
  (loop [primes [] l lst]
    (if (empty? l)
      primes
      (recur (conj primes (first lst))
             (remove
               #(zero? (rem % (first lst)))
               (rest lst))))))

(sieve (range 3 10 2))
