(ns hkim0331.p48
  (:require
    [hkim0331.misc :refer [power power-mod]]))


;; The series, 1^1 + 2^2 + 3^3 + ... + 10^10 = 10405071317.
;; Find the last ten digits of the series, 1^1 + 2^2 + 3^3 + ... + 1000^1000.

(defn p48
  [n]
  (reduce + (map #(power % %) (range 1 (+ 1 n)))))


;; (= (p48 10) 10405071317)

;; まずはbrute force

;; (time
;;   (mod
;;     (reduce +
;;             (map #(power % %) (range 1 1001)))
;;     (power 10 10)))
;; Elapsed time: 9.030658 msecs"
;; 9110846700N

;; 正解は power-mod 使うんだろ。
;; defined in hkim0331.misc,
;;
;; (defn sq [n]
;;   (* n n))
;;
;; (defn power-mod [b e m]
;;   (cond
;;     (zero? e) 1
;;     (even? e) (mod (sq (power-mod b (/ e 2) m)) m)
;;     :else (mod (* (mod b m)
;;                   (power-mod b (- e 1) m)) m)))

(defn pm-p48
  [n m]
  (mod
    (reduce +
            (map #(power-mod % % m) (range 1 (+ 1 n))))
    m))


;; (time (pm-p48 1000 (power 10 10)))
;; "Elapsed time: 4.129514 msecs"
;; 9110846700N
