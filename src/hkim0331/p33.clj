(ns hkim0331.p33
  (:require
    [clojure.string :as str]))


;; There are exactly four non-trivial examples of
;; this type of fraction, less than one in value,
;; and containing two digits in the numerator and
;; denominator.

;; If the product of these four fractions is given
;; in its lowest common terms, find the value of
;; the denominator.


(defn member?
  [a coll]
  (cond
    (empty? coll) false
    (= (first coll) a) true
    :else (member? a (rest coll))))


(defn common
  [sx sy ret]
  (cond
    (empty? sx) ret
    (member? (first sx) sy)
    (common (rest sx) sy (conj ret (first sx)))
    :else
    (common (rest sx) sy ret)))


;; (common (seq (str 1234))
;;         (seq (str 345)) [])

(defn rember
  [coll a]
  (cond
    (empty? coll) []
    (= (first coll) a) (rest coll)
    :else
    (cons (first coll) (rember (rest coll) a))))


;; (rember (range 10) 5)

(defn c-
  [sx sc]
  (if	(empty? sc)
    sx
    (c- (rember sx (first sc)) (rest sc))))


;; (c- [\1 \2 \3] [\2 \3])

(defn cancel
  [x y]
  (let [sx (seq (str x))
        sy (seq (str y))
        sc (common sx sy [])
        ret (apply str (c- sx sc))]
    (if (empty? ret)
      1
      (Integer. ret))))


;; (cancel 1235 234)
;; (cancel 234 1235)
;; (cancel 24 42)
;; (cancel 19 20)
;; (cancel 20 21)
;; (cancel 21 20)

(defn p33?
  [[x y]]
  (and (not= 0 (cancel y x))
       (not= x (cancel x y))
       (not= 0 (rem x 10))
       (= (/ x y) (/ (cancel x y) (cancel y x)))))


;; (p33? [24 42])

(defn p33
  []
  (filter p33?
          (for [y (range 10 100) x (range 10 y)]
            [x y])))


;; (p33)

;; (time (let [ret (p33)]
;; 			(/ (reduce * (map first ret))
;; 			   (reduce * (map second ret)))))
;; "Elapsed time: 42.703823 msecs"
;; 1/100
