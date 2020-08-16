(ns hkim0331.p53
  (:require [clojure.math.combinatorics :as combo]))

; There are exactly ten ways of selecting three from five, 12345:
; 123, 124, 125, 134, 135, 145, 234, 235, 245, and 345
; In combinatorics, we use the notation,
; c[5,3]=10
; In general,
; c[n,r] n!/r!(n-r)!, where , , and .
; It is not until n=23,
; that a value exceeds one-million:
; n[23,10] = 1144066.
; How many, not necessarily distinct, values of
; c[n,r]>one milliton?

; (combo/count-combinations [1 2 3 4 5] 3)
; (combo/count-combinations (range 23) 10)


(for [n (range 10) r (range 10)]
  (if (< 10 (combo/count-combinations (range n) r))
    1
    0))

; (time
;  (reduce +
;   (for [n (range 101) r (range 101)]
;    (if (< 1000000 (combo/count-combinations (range n) r))
;      1
;      0))))
; "Elapsed time: 271.596242 msecs"
; 4075