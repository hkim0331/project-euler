(ns hkim0331.p10
  (:require [hkim0331.p7 :refer [prime? next-prime]]))

;The sum of the primes below 10 is 2 + 3 + 5 + 7 = 17.
;Find the sum of all the primes below two million.

(defn p10 [n]
  (take-while
    (partial > n)
    (iterate next-prime 2)))

;(reduce + (p10 10))
; 17

(def ^:dynamic *TM* 2000000)

; (time (reduce + (p10 *TM*)))
; "Elapsed time: 5790.05082 msecs"
; 142913828922
;
; 6 秒か。need improve!

(defn sieve [init rng]
  (let [mx (Math/sqrt (last rng))]
    (loop [ret init xs rng]
      (if (< mx (first ret))
        (into ret xs)
        (recur (cons (first xs) ret)
               (remove #(zero? (rem % (first xs))) xs))))))

;(time (reduce + (sieve [2] (range 3 *TM* 2))))
;"Elapsed time: 4370.125309 msecs")
;142913828922
;
; 1 秒短縮。

(defn primes-under-sqrt [n]
  (filter prime? (range 2 (+ (Math/sqrt n) 1))))

(defn divide-by? [primes n]
  (cond
    (empty? primes) false
    (zero? (rem n (first primes))) true
    :else (recur (rest primes) n)))

(defn p10' [n]
  (let [primes (primes-under-sqrt n)]
    (+ (reduce + primes)
       (reduce + (remove #(divide-by? primes %) (range 3 n 2))))))

;(time (p10' 2000000))
; "Elapsed time: 2373.084627 msecs"
; 142913828922
;
; さらに倍速。この辺で。

(defn p10'' [n]
  (let [primes (primes-under-sqrt n)]
    (+ (apply + primes)
       (apply + (remove #(divide-by? primes %) (range 3 n 2))))))

;(time (p10'' 2000000)
; "Elapsed time: 2108.059204 msecs")
;142913828922
;もうちょっとで 2 秒切るな。
