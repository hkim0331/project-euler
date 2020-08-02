(ns hkim0331.p14-improve)

(defn collatz-count [n]
  (cond
    (= n 1) 1
    (even? n) (+ 1 (collatz-count (/ n 2)))
    :else (+ 2 (collatz-count (/ (+ 1 (* 3 n)) 2)))))

(defn p14-count [n]
  (apply max (map collatz-count (range (/ n 2) n))))

; (time (p14-count 1000000))
; "Elapsed time: 3734.746499 msecs"
; 525
