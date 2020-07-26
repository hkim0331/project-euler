(ns hkim0331.p23
  (:require [hkim0331.p12 :refer [proper-divisors]]))

(defn abundant? [n]
  (< n (reduce + (proper-divisors n))))

(defn abundant-numbers-under [n]
  (filter abundant? (range 1 (+ 1 n))))

(defn p23 [n]
  (- (reduce + (range (+ 1 n)))
     (let [an (abundant-numbers-under n)
           s (atom #{})]
       (doseq [x an y an]
         (let [v (min (+ x y) n)]
           (swap! s conj v)))
       (reduce + @s))))

(time (p23 28123))
;Elapsed time: 17000.534095 msec