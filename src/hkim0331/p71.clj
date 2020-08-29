(ns hkim0331.p71)

(defn floor
  "n を超えない最大の整数"
  [n]
  (int (Math/floor n)))

(defn p71
  "1..limit までの整数を分母分子に使って
   goal を超えない最大の分数 p/q を作る"
  [limit goal]
  (let [n (numerator goal)
        d (denominator goal)]
    (loop [q 1 ret 0/1]
      (if (<= q limit)
        (let [p (floor (/ (- (* n q) 1) d))]
           (recur (inc q) (max ret (/ p q))))
        ret))))

;(p71 8 3/7)

; (time(p71 1000000 3/7))
; "Elapsed time: 964.300299 msecs"
; 428570/999997
