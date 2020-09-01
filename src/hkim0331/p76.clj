(ns hkim0331.p76
  (:require [hkim0331.p31 :refer [changes]]))

; It is possible to write five as a sum in exactly six different ways:

; 4 + 1
; 3 + 2
; 3 + 1 + 1
; 2 + 2 + 1
; 2 + 1 + 1 + 1
; 1 + 1 + 1 + 1 + 1

; How many different ways can one hundred be written as a sum of at least two positive integers?

(defn p76
 "amount を2つ以上の整数の和として表す方法はいくつあるか？"
 [amount]
 (changes amount (reverse (range 1 amount))))

; (p76 5)
; 6
; (time (p76 100))
; "Elapsed time: 81410.616392 msecs"
; 190569291

;; スピードアップする。

;(defn changes [amount coins]
;  (cond
;    (empty? coins) 0
;    (< amount 0) 0
;    (= 0 amount) 1
;    :else (+
;            (changes (- amount (first coins)) coins)
;            (changes amount (rest coins)))))

;; memoize. 正しく使えてないか？

(def mem
 (memoize (fn [a c] (changes a c))))

; (time (mem 100 (reverse (range 1 99))))
; "Elapsed time: 82579.55197 msecs"
; 190569290
; (time (mem 100 (reverse (range 1 99))))
; "Elapsed time: 0.071259 msecs"
; 190569290

(time (mem 100 (range 1 99)))