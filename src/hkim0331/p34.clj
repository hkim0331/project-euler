(ns hkim0331.p34
  (:require [hkim0331.p25 :refer [power]]))

; 145 is a curious number, as 1! + 4! + 5! = 1 + 24 + 120 = 145.
; Find the sum of all numbers which are equal to the sum of the
; factorial of their digits.
; Note: As 1! = 1 and 2! = 2 are not sums they are not included.

(defn factorial [n]
  (apply * (range 1 (+ n 1))))

;(factorial 6)

(def f9 (factorial 9))

;(take-while #(> (* f9 %) (power 10 %)) (iterate inc 1))
;(1 2 3 4 5 6)

; n > 7 だと (* n 9!) < 10^7, 追いつかない。
; 9! * 7 までを探せばいい。

(defn integers [n]
 (map #(- (int %) (int \0)) (seq (str n))))

;(integers 345)

(defn p34? [n]
  (= n (reduce  + (map factorial (integers n)))))

;(p34? 145)

; (time (reduce + (filter p34? (range 3 (* 7 f9)))))
; "Elapsed time: 10236.472246 msecs"
; 40730
