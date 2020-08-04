(ns hkim0331.p28)

(defn- aux [n]
  (if (= n 1)
    1
    (+ (* 4 n n) (* -6 n) 6)))

(defn p28 [n]
 (apply + (map aux (range 1 (+ n 1) 2))))

; (p28 3)
; (time (p28 1001))
; "Elapsed time: 0.83626 msecs"
; 669171001