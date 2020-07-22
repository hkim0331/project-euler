(ns hkim0331.p7)

; By listing the first six prime numbers: 2, 3, 5, 7, 11, and 13,
; we can see that the 6th prime is 13.
; What is the 10001st prime number?

(defn- prime?-aux [n]
  (nil? (some
          #(zero? (rem n %))
          (range 3 (+ 1 (Math/sqrt n)) 2))))

(defn prime? [n]
  (cond
    (< n 3) (= n 2)
    (even? n) false
    :else (prime?-aux n)))

(defn- np [n]
  (let [p (+ n 2)]
    (if (prime? p)
        p
        (recur p))))

(defn next-prime [n]
  (if (< n 3)
      3
      (np n)))
(defn nth-prime [n]
  (last (take n (iterate next-prime 2))))

;(nth-prime 6)
;13

(time  (last (take 10001 (iterate next-prime 2))))
;"Elapsed time: 218.497036 msecs"
;104743
