(ns hkim0331.p53
  (:require
    [clojure.math.combinatorics :as combo]))


;; There are exactly ten ways of selecting three from five, 12345:
;; 123, 124, 125, 134, 135, 145, 234, 235, 245, and 345
;; In combinatorics, we use the notation,
;; c[5,3]=10
;; In general,
;; c[n,r] n!/r!(n-r)!, where , , and .
;; It is not until n=23,
;; that a value exceeds one-million:
;; n[23,10] = 1144066.
;; How many, not necessarily distinct, values of
;; c[n,r]>one milliton?

;; (combo/count-combinations [1 2 3 4 5] 3)
;; (combo/count-combinations (range 23) 10)


(for [n (range 10) r (range 10)]
  (< 10 (combo/count-combinations (range n) r)))


;; (time
;;  (reduce +
;;   (for [n (range 1 101) r (range 1 101)]
;;     (if (< 1000000 (combo/count-combinations (range n) r))
;;       1
;;       0))))
;; "Elapsed time: 271.596242 msecs")
;; 4075

;; (time
;;  (count
;;    (filter (partial < 1000000)
;;      (for [n (range 1 101) r (range 1 101)]
;;        (combo/count-combinations (range n) r)))))
;; "Elapsed time: 223.071685 msecs"
;; 4075

(defn c
  [n r]
  (if (= r 0) 0
      (/ (reduce * (range (+ n (* -1 r) 1N) (+ n 1)))
         (reduce * (range 1N (+ r 1))))))


;; (time
;;   (count
;;    (filter (partial < 1000000)
;;            (for [n (range 0 101) r (range 0 101)]
;;              (c n r)))))
;; "Elapsed time: 82.881866 msecs"
;; 4075
