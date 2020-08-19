(ns hkim0331.p43
  (:require [clojure.math.combinatorics :as combo]
            [clojure.set :as set]))

; The number, 1406357289, is a 0 to 9 pandigital number because it is made up of each of the digits 0 to 9 in some order, but it also has a rather interesting sub-string divisibility property.

; Let d1 be the 1st digit, d2 be the 2nd digit, and so on. In this way, we note the following:

; d2d3d4=406 is divisible by 2
; d3d4d5=063 is divisible by 3
; d4d5d6=635 is divisible by 5
; d5d6d7=357 is divisible by 7
; d6d7d8=572 is divisible by 11
; d7d8d9=728 is divisible by 13
; d8d9d10=289 is divisible by 17
; Find the sum of all 0 to 9 pandigital numbers with this property.

;d1=1,
;d3=2,4,6,8,0,
;d6=0,5

;1**(24680)*(05)****

;2,2
(defn d234? [[d1 d2 d3 d4 d5 d6 d7 d8 d9 d0]]
  (even? d4))

;3,3
(defn d345? [[d1 d2 d3 d4 d5 d6 d7 d8 d9 d0]]
  (zero? (rem (Integer. (str d3 d4 d5)) 3)))

;4,5
(defn d456? [[d1 d2 d3 d4 d5 d6 d7 d8 d9 d0]]
  (or (= d6 0) (= d6 5)))

;5,7
(defn d567? [[d1 d2 d3 d4 d5 d6 d7 d8 d9 d0]]
  (and (or (= d6 0) (= d6 5))
       (zero? (rem (+ (* 100 d5) (* 10 d6) d7) 7))))
;6,11
(defn d678? [[d1 d2 d3 d4 d5 d6 d7 d8 d9 d0]]
  (and (or (= d6 0) (= d6 5))
       (zero? (rem (+ (* 100 d6) (* 10 d7) d8) 11))))

;7,13
(defn d789? [[d1 d2 d3 d4 d5 d6 d7 d8 d9 d0]]
  (zero? (rem (Integer. (str d7 d8 d9)) 13)))

;8,17
(defn d890? [[d1 d2 d3 d4 d5 d6 d7 d8 d9 d0]]
  (zero? (rem (Integer. (str d8 d9 d0)) 17)))

;(take 10 (filter d890? (combo/permutations [0 2 3 4 5 6 7 8 9])))

; (time
;  (reduce +
;   (map #(Long. (apply str %))
;        (filter (fn [p] (and (d456? p)
;                             (d234? p)
;                             (d345? p)
;                             (d567? p)
;                             (d678? p)
;                             (d789? p)
;                             (d890? p)))
;         (combo/permutations [0 1 2 3 4 5 6 7 8 9])))))
; "Elapsed time: 2197.713187 msecs"
; 16695334890