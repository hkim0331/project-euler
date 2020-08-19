(ns hkim0331.p41
  (:require [hkim0331.misc :refer [pandigital? prime? power]]
            [clojure.math.combinatorics :refer [permutations]]))

; We shall say that an n-digit number is pandigital if it makes use of
; all the digits 1 to n exactly once. For example, 2143 is a 4-digit
; pandigital and is also prime.
; What is the largest n-digit pandigital prime that exists?

; 大きい方から探して、最初に見つかる素数かつ pandigital が見つかればそれ。

(defn p41? [n]
  (if (and (prime? n) (pandigital? n))
    n
    false))

;(p41? 2143)

(defn p41
 ([]
  (p41 (- (power 10 9) 1)))
 ([n]
  (cond
    (< n 0) nil
    (even? n) (recur (- n 1))
    :else (or (p41? n) (recur (- n 2))))))

; too slow, improve!
;(time (p41 87654321))

(defn- drop- [x coll]
  (cond
   (empty? coll) []
   (= x (first coll)) (rest coll)
   :else (cons (first coll) (drop- x (rest coll)))))

;(drop- 3 '(1 2 3))

(defn permute [coll]
  (if (empty? coll)
    [[]]
    (for [x coll y (permute (drop- x coll))]
      (cons x y))))

(defn permu [coll]
  (map #(Integer. (apply str %)) (permute coll)))

;(first (filter prime? (permu [4 3 2 1])))

(defn p41-improve [digits]
 (first (filter prime? (permu (reverse (range 1 (+ 1 digits)))))))

; (time (or (p41-improve 9) (p41-improve 8) (p41-improve 7)))
; "Elapsed time: 1804.726231 msecs"
; 7652413

(defn permu-2 [coll]
  (map #(Integer. (apply str %)) (permutations coll)))

;(permu-2 (range 1 4))

(defn p41-improve-2 [digits]
 (first (filter prime? (permu-2 (reverse (range 1 (+ 1 digits)))))))

;(permu-2 (reverse (range 1 (+ 1 4))))

;(p41-improve-2 7)

; (time (or (p41-improve-2 9) (p41-improve-2 8) (p41-improve-2 7)))
; "Elapsed time: 1256.620328 msecs"
; 7652413
;
; ライブラリ使っても劇的に速くはならない。
