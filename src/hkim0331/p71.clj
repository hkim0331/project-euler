(ns hkim0331.p71)

; Consider the fraction, n/d, where n and d are positive integers. If n<d and HCF(n,d)=1, it is called a reduced proper fraction.
; If we list the set of reduced proper fractions for d ≤ 8 in ascending order of size, we get:

; 1/8, 1/7, 1/6, 1/5, 1/4, 2/7, 1/3, 3/8, 2/5, 3/7, 1/2, 4/7, 3/5, 5/8, 2/3, 5/7, 3/4, 4/5, 5/6, 6/7, 7/8

; It can be seen that 2/5 is the fraction immediately to the left of 3/7.
; By listing the set of reduced proper fractions for d ≤ 1,000,000 in ascending order of size, find the numerator of the fraction immediately to the left of 3/7.

;; 3/7 から 1/1,000,000 ずつ引いた数で 6桁/6桁の分数になる最初の数。
;; 分母7桁の解は
;; 299999/1000000
;; なのでそれと大小比べればいいだろ。

(def m 1/1000000)

(defn both6?
 "分数 f の分子、分母はともに7桁未満か？"
 [f]
 (and (< (numerator f)   1000000)
      (< (denominator f) 1000000)))

;(both6? 1/2000000)

(defn p71
 []
 (first
  (filter both6?
    (map #(- 3/7 (* m %)) (iterate inc 1)))))

(p71)
; (time (p71))
; "Elapsed time: 0.504017 msecs"
; 374993/875000
; true
; ダメ！甘かったか。