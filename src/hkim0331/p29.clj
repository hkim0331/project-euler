(ns hkim0331.p29
  (:require
    [hkim0331.p25 :refer [power]]))


;; How many distinct terms are in the sequence generated by ab for 2 ≤ a ≤ 100 and 2 ≤ b ≤ 100?

;; brute force
(defn p29
  [n]
  (into #{}
        (for [a (range 2 (+ n 1))
              b (range 2 (+ n 1))]
          (power a b))))


;; (= 15 (count (p29 5)))
;; (time (count (p29 100)))
;; "Elapsed time: 138.88439 msecs"
;; 9183
