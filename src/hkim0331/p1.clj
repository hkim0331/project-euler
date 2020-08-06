(ns hkim0331.p1)

;; Multiple of 3 and 5
;; Find the sum of all the multiples of 3 or 5 below 1000.

(defn p1 [n]
  (take-while
    (partial > n)
    (filter
      (fn [x] (or (zero? (rem x 3))
                  (zero? (rem x 5))))
      (iterate inc 0))))

(defn p1' [n]
  (take-while
    (partial > n)
    (filter
      #(or (zero? (rem % 3))
           (zero? (rem % 5)))
      (iterate inc 1))))

(range 1 20)
(defn p1'' [n]
  (filter
      #(or (zero? (rem % 3))
           (zero? (rem % 5)))
      (range n)))

;((juxt p1 p1' p1'') 20)

; (time (reduce + (p1'' 1000)))
; "Elapsed time: 0.544521 msecs"
; 233168
