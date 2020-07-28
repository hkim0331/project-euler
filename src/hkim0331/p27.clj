(ns hkim0331.p27
  (:require [hkim0331.p7 :refer [prime?]]))

; (defn- q0 [a b]
;   (take-while
;      (fn [n] (prime? (+ (* n (+ n a)) b)))
;      (iterate inc 0)))
;(q0 1 41)

; (defn- q1 [a b]
;   (take-while
;      #(prime? (+ (* % (+ % a)) b))
;      (iterate inc 0)))
;(q1 1 41)

(defn- qd [a b]
  (take-while
    (comp prime? #(+ (* % (+ % a)) b))
    (iterate inc 0)))

(defn- my-max [a b]
  (if (> (a 1) (b 1))
    a
    b))

(defn p27 [n]
  (reduce my-max
    (for [a (range (- 0 n -1) n)
          b (filter prime? (range 3 n))]
      [(* a b) (count (qd a b))])))

; (time (p27 1000))
; "Elapsed time: 756.954731 msecs"
; [-59231 71]