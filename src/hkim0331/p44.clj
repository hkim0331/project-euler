(ns hkim0331.p44
  (:require
    [clojure.math.combinatorics :as combo]
    [clojure.set :as set]))


;; penta numbers are generated by the formula, Pn=n(3n−1)/2. The first ten penta numbers are:

;; 1, 5, 12, 22, 35, 51, 70, 92, 117, 145, ...

;; It can be seen that P4 + P7 = 22 + 70 = 92 = P8. However, their difference, 70 − 22 = 48, is not penta.

;; Find the pair of penta numbers, Pj and Pk, for which their sum and difference are penta and D = |Pk − Pj| is minimised; what is the value of D?


(defn penta
  [n]
  (/ (* n (- (* 3 n) 1))  2))


;; (map penta (range 1 10))

(defn p44
  [n]
  (let [s (set (map penta (range 1 n)))]
    (filter
      (fn [[p1 p2]] (and (s p1) (s p2)))
      (for [k (range 1 n) j (range (+ 1 k) n)]
        (let [pj (penta j) pk (penta k)]
          [(- pj pk) (+ pj pk)])))))


;; いくつまで調べればいいのかわからん。
;; この方法だと、解の全域性が保証できない。
;; 3000 以上に解が存在しないことを証明できないと。

;; (p44 10)
;; ()
;; (p44 100)
;; ()
;; (p44 1000)
;; ()
;; (time (p44 4000))
;; "Elapsed time: 0.596519 msecs"
;; ([5482660 8602840])

;; combinations 使うと5倍遅くなった。
;; 答えは引いて、足し前の pentagonal numbers.

(defn abs
  [x]
  (max x (- 0 x)))


(defn p44'
  [n]
  (let [s (set (map penta (range 1 n)))]
    (filter
      (fn [[pj pk]]
        (and (s (abs (- pj pk))) (s (+ pj pk))))
      (combo/combinations s 2))))


;; (time (p44' 3000))
;; "Elapsed time: 2.512814 msecs"
;; ((1560090 7042750))
