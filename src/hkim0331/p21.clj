(ns hkim0331.p21
  (:require [hkim0331.p12 :refer [divisors]]))

; Evaluate the sum of all the amicable numbers under 10000.

(defn amicable? [n]
  (let [a (- (reduce + (divisors n)) n)]
    (and (not= a n) (= n (- (reduce + (divisors a)) a)))))

; (time (reduce + (filter amicable? (range 2 10000))))
; "Elapsed time: 112.621106 msecs"
; 31626
