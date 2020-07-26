(ns hkim0331.p10
  (:require [hkim0331.p7 :refer [next-prime]]))

;The sum of the primes below 10 is 2 + 3 + 5 + 7 = 17.
;Find the sum of all the primes below two million.

(take 10 (iterate next-prime 2))

(take-while (partial > 10)
            (iterate next-prime 2))

(defn p10 [n]
  (reduce +
    (take-while
      (partial > n)
      (iterate next-prime 2))))

(p10 10)
; 17
(time (p10 2000000))
; "Elapsed time: 6789.642204 msecs"


