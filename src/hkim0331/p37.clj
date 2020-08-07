(ns hkim0331.p37
  (:require [hkim0331.misc :refer [primes prime?]]))

; The number 3797 has an interesting property. Being prime
; itself, it is possible to continuously remove digits from
; left to right,
; and remain prime at each stage: 3797, 797, 97, and 7.
; Similarly we can work from right to left: 3797, 379, 37,
;  and 3.)

; Find the sum of the only eleven primes that are both
; truncatable
; from left to right and right to left.

; NOTE: 2, 3, 5, and 7 are not considered to be truncatable
; primes.

; why eleven? --- eleven from the smallest except 2,3,5 and 7.


; 1st
; (defn tr-aux [coll]
;  (take-while #(not (empty? %)) (iterate rest coll)))

; 2nd
; (defn tr-aux [coll]
;  (take-while (comp not empty?) (iterate rest coll)))

; 3rd
(defn tr-aux [coll]
 (take-while (complement empty?) (iterate rest coll)))

(defn truncatables [n]
  (let [coll (seq (str n))]
    (map #(Integer. (apply str %))
         (concat (tr-aux coll)
                 (rest (map reverse (tr-aux (reverse coll))))))))

;(truncatables 3797)

;(every? prime? (truncatables 3797))

; (take 15
;   (map first
;      (filter #(every? prime? %)
;              (map truncatables primes))))

(defn p37 [n]
  (take n
    (map first
         (filter #(every? prime? %)
                 (map truncatables primes)))))

;(time (- (reduce + (p37 15)) (+ 2 3 5 7)))
; "Elapsed time: 1026.486697 msecs"
; 748317
