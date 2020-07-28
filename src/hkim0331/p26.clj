(ns hkim0331.p26)

(defn- rc [n m ret]
  (let [q (quot n m) r (rem n m)]
    (if (= 1 r)
      (str ret q)
      (rc (* 10 r) m (str ret q)))))

(defn recurring-cycle [n]
  (rc 10 n ""))

; (recurring-cycle 3)
; ; (recurring-cycle 6)
; (recurring-cycle 11)
; (recurring-cycle 17)
; (recurring-cycle 21)

(defn- max-length [a b]
  (if (> (count (a 1)) (count (b 1)))
    a
    b))

(defn p26 [n]
  (reduce max-length
    (map
      (fn [x] [x (recurring-cycle x)])
      (filter
         #(and (not= 0 (rem % 2))
               (not= 0 (rem % 5)))
         (range 2 n)))))

(time (p26 1000))
;"Elapsed time: 30.993335 msecs"