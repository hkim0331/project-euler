(ns hkim0331.p97
  (:require
    [hkim0331.misc :refer [power-mod]]))


;; The first known prime found to exceed one million digits was discovered in 1999, and is a Mersenne prime of the form 2^6972593−1; it contains exactly 2,098,960 digits. Subsequently other Mersenne primes, of the form 2^p−1, have been found which contain more digits.

;; However, in 2004 there was found a massive non-Mersenne prime which contains 2,357,207 digits: 28433×2^7830457+1.

;; Find the last ten digits of this prime number.

;; power-mod は hkim0331.misc で定義していたが、
;; 学生のお勉強のため、ここにコピー。
(defn power-mod
  [b e m]
  (cond
    (zero? e) 1N
    (even? e) (mod (sq (power-mod b (/ e 2) m)) m)
    :else (mod (* (mod b m)
                  (power-mod b (- e 1) m)) m)))


(def d 10000000000)

(mod (+ (* 28433 (power-mod 2 7830457 d)) 1) d)


;; (time (mod (+ (* 28433 (power-mod 2 7830457 d)) 1) d)
;; "Elapsed time: 0.354426 msecs"
;; 8739992577N
