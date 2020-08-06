(ns hkim0331.p33
  (:require [clojure.string :as str]))

; There are exactly four non-trivial examples of 
; this type of fraction, less than one in value,
; and containing two digits in the numerator and 
; denominator.

; If the product of these four fractions is given
; in its lowest common terms, find the value of
; the denominator.


(defn common [sx sy ret])
  (cond
    (empty? sx) ret
    (member? (first sx) sy)
    (common (rest sx) sy (conj ret (first sx)))
    :else (common (rest sx) sy ret)))

(common (seq (str 123))
        (seq (str 345)) [])

(defn c- [sx sc])

(defn cancel [x y]
  (let [sx (seq (str x))
        sy (seq (str y))
        sc (common sx sy []))]
    (c- sx sc))


(defn p33? [[x y]]
  (= (/ x y) 
     (/ (cancel x y) (cancel y x))

(defn p33 []
  (filter p33?
    (for [y (range 10 100) x (range 10 y)]
      [x y])))