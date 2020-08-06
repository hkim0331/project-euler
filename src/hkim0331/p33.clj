(ns hkim0331.p33)

;;; under construction


; There are exactly four non-trivial examples of this type of
; fraction, less than one in value, and containing two digits in
; the numerator and denominator.

; If the product of these four fractions is given in its lowest
; common terms, find the value of the denominator.

;; policy: 整数である分子、分母を文字列に分解し共通する文字をキャンセルする。
;; その結果、元の分数と同じになるのを残せばどうだ？

; (defn f [x y]
;   (let [x x y y]
;    (prn x y)))

(defn- member? [el coll]
  (cond
   (empty? coll) false
   (= el (first coll)) true
   :else (member? el (rest coll))))

(member? :d [:a :b :c])

(defn- common [s1 s2 ret]
 (cond
  (empty? s1) ret
  (member? (first s1) s2)
  (common (rest s1) s2 (conj ret (first s1)))
  :else (common (rest s1) s2 ret)))

(common [] (seq "45") [])
(common (seq "123") (seq "456") [])
(common (seq "123") (seq "2134") [])

; 最初の要素だけ削除
(defn- c- [s c]
 (cond
  (empty? s) []
  (member? (first s) c) (rest s)
  :else (cons (first s) (c- (rest s) c))))

(c- [\1 \2 \2 \3] \3)
(c- [\1 \2 \2 \3] \2)
(c- [\1 \2 \2 \3] \4)

(defn- cancel [x y]
 (let [sx (seq (str x))
       sy (seq (str y))
       sc (common sx sy [])]
    (prn sc)
    [(c- sx sc) (c- sy sc)]))

(cancel 123 234)

(for [y (range 10 100) (x (range 10 y))]
  (let [[cx xy] (cancel x y)]))