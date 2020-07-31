(ns hkim0331.p5)
; 2520 is the smallest number that can be divided by each of the numbers
; from 1 to 10 without any remainder.
; What is the smallest positive number that is evenly divisible by all of
; the numbers from 1 to 20?

; 答えは (* 2520 11 13 2 17 19)

(defn gcd [x y]
  (if (zero? y)
      x
      (gcd y (rem x y))))

(defn lcm [x y]
  (/ (* x y) (gcd x y)))

(defn p5 [n]
   (reduce lcm 2 (range 3 (+ n 1))))

;(p5 10)
; 2520

; (time (p5 20))
; "Elapsed time: 0.166023 msecs"
; 232792560


; under constrution
; もっとスピードアップできないか？
; 素因数分解して、
;
; (defn- factor-aux [n m ret]
;   (cond
;     (= n 1) ret
;     (zero? (rem n m)) (recur (/ n m) m (conj ret m))
;     :else (recur n (+ m 1) ret)))

; (defn factor [n]
;   (factor-aux n 2 []))