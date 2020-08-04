(ns hkim0331.p16)

; 2^15 = 32768 and the sum of its digits is 3 + 2 + 7 + 6 + 8 = 26.
; What is the sum of the digits of the number 2^1000?
(defn sq [n]
  (* n n))

(defn power2 [n]
  (cond
    (zero? n) 1N
    (even? n) (sq (power2 (/ n 2)))
    :else (* 2 (power2 (- n 1)))))

(defn c->i [c]
  (- (int c) (int \0)))

(defn p16 [n]
 (apply +
   (map c->i (-> n power2 str seq))))

(p16 15)

; (time (p16 1000))
; "Elapsed time: 0.654789 msecs"
; 1366