(ns hkim0331.p3)

; The prime factors of 13195 are 5, 7, 13 and 29.
; What is the largest prime factor of the number 600851475143 ?

(defn f-aux [n m ret]
  (cond
    (< n m) ret
    (zero? (rem n m)) (recur (/ n m) m (conj ret m))
    :else (recur n (+ m 2) ret)))

(defn factor [n]
  (f-aux n 3 []))

(factor 13195)

(defn p3 [n]
  (apply max (factor n)))

(p3 13195)

(p3 600851475143)

(time (p3 600851475143))