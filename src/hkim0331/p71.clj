(ns hkim0331.p71)

; Consider the fraction, n/d, where n and d are positive integers. If n<d and HCF(n,d)=1, it is called a reduced proper fraction.

; If we list the set of reduced proper fractions for d ≤ 8 in ascending order of size, we get:

; 1/8, 1/7, 1/6, 1/5, 1/4, 2/7, 1/3, 3/8, 2/5, 3/7, 1/2, 4/7, 3/5, 5/8, 2/3, 5/7, 3/4, 4/5, 5/6, 6/7, 7/8

; It can be seen that 2/5 is the fraction immediately to the left of 3/7.

; By listing the set of reduced proper fractions for d ≤ 1,000,000 in ascending order of size, find the numerator of the fraction immediately to the left of 3/7.


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
