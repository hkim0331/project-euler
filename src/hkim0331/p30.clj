(ns hkim0331.p30
  (:require [hkim0331.p25 :refer [power]]))

; Find the sum of all the numbers that can be written as the sum of fifth powers of their digits.

; 整数 n が題意を判定する述語の定義は簡単。

(defn c->i [c]
 (- (int c) (int \0)))

;(c->i \5)

(defn p30? [n]
  (fn [x]
    (=  x (apply +
            (map
              (fn [y] (power (c->i y) n))
              (seq (str x)))))))

; ((p30? 4) 1634)
; ((p30? 4) 8208)
; ((p30? 4) 9474)

; 何桁の数まで探せばいいか？が問題。
;(* 6 (power 9 5))

; brute force
; (time
;   (apply +
;          (filter (p30? 5)
;                  (range 2 (* 6 (power 9 5))))))
; "Elapsed time: 917.405476 msecs"
; 443839