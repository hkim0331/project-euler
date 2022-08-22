(ns hkim0331.p50
  (:require
    [hkim0331.misc :refer [primes prime?]]))


;; The prime 41, can be written as the sum of six consecutive primes:
;; 41 = 2 + 3 + 5 + 7 + 11 + 13
;; This is the longest sum of consecutive primes that adds to a prime below one-hundred.
;; The longest sum of consecutive primes below one-thousand that adds to a prime, contains 21 terms, and is equal to 953.
;; Which prime, below one-million, can be written as the sum of the most consecutive primes?

(def p50-primes
  (take-while #(< % 1000000) primes))


;; (filter prime? (map (partial reduce +) (partition 21 1 p50-primes)))
;; 953

;; (count p50-primes)
;; 78498

;; 21以上の長さを持つ。

;; 小さい方から何個足したら100万を超えてしまうか？
;; (reduce + (take 500 primes))
;; (reduce + (take 546 primes))
;; (reduce + (take 547 primes))
;; reduce + (take 550 primes)
;; 546 がリストの最長、これより長いと100万超す。

(defn p50?
  [ret]
  (and (< ret 1000000)
       (prime? ret)))


(defn p50
  []
  (first
    (drop-while empty?
                (for [n (range 546 21 -1)]
                  (filter p50?
                          (map (partial reduce +) (partition n 1 p50-primes)))))))


;; (map (partial reduce +) (partition 543 1 p50-primes))

;; (time (p50))
;; "Elapsed time: 20646.626756 msecs"
;;  (997651)

(defn p50-improve
  []
  (first
    (drop-while empty?
                (for [n (range 546 21 -1)]
                  (filter p50?
                          (take-while (partial > 1000000)
                                      (map (partial reduce +) (partition n 1 p50-primes))))))))


;; (time (p50-improve))
;; "Elapsed time: 2.152974 msecs"
;; (997651)
