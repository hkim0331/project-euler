(ns hkim0331.p9)
; A Pythagorean triplet is a set of three natural numbers,
; a < b < c, for which,
;
; a^2 + b^2 = c^2
; For example, 3^2 + 4^2 = 9 + 16 = 25 = 5^2.
;
; There exists exactly one Pythagorean triplet for which
; a + b + c = 1000.
; Find the product abc.

(defn sq [x]
  (* x x))

(defn p9 [n]
  (for [x (range 1 n)
        y (range x n)
        :let [z (- n (+ x y))]
        :when (and (pos? z)
                   (= (+ (sq x) (sq y)) (sq z)))]
      [x y z]))

; (time (reduce * (first (p9 1000))))
; "Elapsed time: 42.445076 msecs"
; 31875000