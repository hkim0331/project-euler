(ns hkim0331.p63
  (:require
    [hkim0331.misc :refer [power]]))


;; The 5-digit number, 16807=7^5, is also a fifth power. Similarly, the 9-digit number, 134217728=8^9, is a ninth power.
;; How many n-digit positive integers exist which are also an nth power?

(defn digits
  [n]
  (if (= n 0) 0
      (+ 1 (digits (quot n 10)))))


(digits (power 9 19))


;; 19
(digits (power 9 20))


;; 20
(digits (power 9 21))


;; 21
(digits (power 9 22))


;; 21
(digits (power 9 23))


;; 22

(digits (power 10 10))


;; 11

(reduce +
        (for [x (range 10) y (range 22)]
          (if (= (digits (power x y)) y) 1 0)))


;; (time
;;   (reduce +
;;     (for [x (range 10) y (range 22)]
;;       (if (= (digits (power x y)) y) 1 0))))
;; "Elapsed time: 1.335592 msecs"
;; 49
