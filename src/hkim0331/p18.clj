(ns hkim0331.p18
  (:require [clojure.string :refer [split]]))

(def test-triangle
 "3
  7 4
  2 4 6
  8 5 9 3")

(def p18-triangle
 "75
  95 64
  17 47 82
  18 35 87 10
  20 04 82 47 65
  19 01 23 75 03 34
  88 02 77 73 07 63 67
  99 65 04 28 06 16 70 92
  41 41 26 56 83 40 80 70 33
  41 48 72 33 47 32 37 16 94 29
  53 71 44 65 25 43 91 52 97 51 14
  70 11 33 28 77 73 17 78 39 68 17 57
  91 71 52 38 17 14 91 43 58 50 27 29 48
  63 66 04 68 89 53 67 30 73 16 69 87 40 31
  04 62 98 27 23 09 70 98 73 93 38 53 60 04 23")

(defn- integers [v]
  (map #(Integer. %) v))

(defn to-ints [tri]
  (->> (split tri #"\n")
       (map #(re-seq #"\d+" %))
       (map integers)))

(def q-test (to-ints test-triangle))

(def q18 (to-ints p18-triangle))

(def third (comp second rest))
(def rrest (comp rest rest))
(def fsecond (comp first second))

(defn- p18-aux [a]
  (cond
    (empty? a) 0
    (empty? (second a)) (ffirst a)
    (empty? (third a)) (+ (apply max (first a)) (fsecond a))
    :else
      (let [f (map #(apply max %) (partition 2 1 (first a)))
            s (second a)]
        (recur (cons (map + f s) (rrest a))))))

(defn p18 [tri]
  (p18-aux (reverse tri)))

; (p18 [])
; (p18 [[1]])
; (p18 [[1] [3 4]])
; (p18 q-test)

; (time (p18 q18)
;  "Elapsed time: 0.498027 msecs")
; 1074

