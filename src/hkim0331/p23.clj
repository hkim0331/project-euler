(ns hkim0331.p23
  (:require
    [hkim0331.p12 :refer [proper-divisors]]))


(defn abundant?
  [n]
  (< n (reduce + (proper-divisors n))))


(defn abundant-numbers-under
  [n]
  (filter abundant? (range 1 (+ 1 n))))


;; 6965 個ある

(defn p23
  [n]
  (- (reduce + (range (+ 1 n)))
     (let [an (abundant-numbers-under n)
           s (atom #{})]
       (doseq [x an y an]
         (let [v (min (+ x y) n)]
           (swap! s conj v)))
       (reduce + @s))))


;; (time (p23 28123))
;; "Elapsed time: 17000.534095 msec"

(defn p23-improve
  [n]
  (- (reduce + (range (+ 1 n)))
     (let [an (abundant-numbers-under n)]
       (reduce +
               (set
                 (for [x an y an]
                   (min (+ x y) n)))))))


;; (time (p23-improve 28123))
;; "Elapsed time: 5849.054534 msecs"

(defn abundant-vector
  [n]
  (vec (filter abundant? (range 1 (+ 1 n)))))


(defn p23-vector
  [n]
  (- (reduce + (range (+ 1 n)))
     (let [av (abundant-vector n)
           len (count av)]
       (reduce +
               (set
                 (for [x (range len) y (range 0 (+ 1 x))]
                   (min (+ (av x) (av y)) n)))))))


;; (time (p23-vector 28123))
;; "Elapsed time: 2930.930966 msecs"
;; 4179871
