(ns hkim0331.p52
  (:require [hkim0331.misc :refer [power]]))

; It can be seen that the number, 125874, and its double, 251748, contain exactly the same digits, but in a different order.
; Find the smallest positive integer, x, such that 2x, 3x, 4x, 5x, and 6x, contain the same digits.

(defn same-digits? [x y]
  (let [sx (sort (seq (str x)))
        sy (sort (seq (str y)))]
     (= sx sy)))

(defn p52' [n]
  (let [b (power 10 n)]
    (for [i (range b (+ b (- b 1)))]
      (when (same-digits? i (* 2 i))
        i))))

(first (drop-while nil? (p52' 5)))

(defn p52 [n]
  (let [b (power 10 n)]
    (first
      (drop-while nil?
        (for [i (range b (+ b (- b 1)))]
          (when (and
                  (same-digits? i (* 2 i))
                  (same-digits? i (* 3 i))
                  (same-digits? i (* 4 i))
                  (same-digits? i (* 5 i))
                  (same-digits? i (* 6 i)))
            i))))))

(time
  (first
    (drop-while nil?
                (map p52 (iterate inc 1)))))
"Elapsed time: 109.517357 msecs"
; 142857N

