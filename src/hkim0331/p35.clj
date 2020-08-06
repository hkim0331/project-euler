(ns hkim0331.p35
  (:require [hkim0331.p7 :refer [prime? next-prime]]))

; The number, 197, is called a circular prime because all
; rotations of the digits: 197, 971, and 719, are themselves
; prime.
; There are thirteen such primes below 100:
; 2, 3, 5, 7, 11, 13, 17, 31, 37, 71, 73, 79, and 97.
; How many circular primes are there below one million?

(def primes (take-while #(< % 1000000) (iterate next-prime 2)))

;(take 10 primes)

(defn rotate [n]
 (let [s (str n)]
   (Integer. (apply str (concat (subs s 1) (subs s 0 1))))))

;(rotate 123)
; (take 6 (iterate rotate 900019))
; (900019 199 991 919 199 991)

(defn include-zero? [n]
  (re-find #"0" (str n)))

;(include-zero? "991")

(defn circular-prime? [n]
  (if (include-zero? n)
    false
    (let [numbers (take (count (str n)) (iterate rotate n))]
      (every? prime? numbers))))

;(filter circular-prime? primes)

; (time (count (filter circular-prime? primes)))
; "Elapsed time: 2116.457248 msecs"
; 55