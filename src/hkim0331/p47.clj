(ns hkim0331.p47
  (:require
    [hkim0331.misc :refer [prime-factors prime-factors-2]]))


;; The first two consecutive numbers to have two distinct prime factors are:

;; 14 = 2 × 7
;; 15 = 3 × 5

;; The first three consecutive numbers to have three distinct prime factors are:

;; 644 = 2² × 7 × 23
;; 645 = 3 × 5 × 43
;; 646 = 2 × 17 × 19.

;; Find the first four consecutive integers to have four distinct prime factors each. What is the first of these numbers?

;; distinct prime factors count
(defn dpfc
  [n]
  (count (set (prime-factors n))))


(defn p47
  [n]
  (loop [i 2 c 0]
    (if (= c n)
      (- i n)
      (recur (inc i)
             (if (= n (dpfc i))
               (inc c)
               0)))))


;; (= 14  (p47 2))
;; (= 644 (p47 3))


;; (time (p47 4))
;; "Elapsed time: 27140.529867 msecs"
;; 134043
