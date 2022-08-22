(ns hkim0331.p27
  (:require
    [hkim0331.p7 :refer [prime?]]))


;; n^2 + an + b, |a| <1000 and |b|<=1000
;; n=0 からはじめて、最も多く素数を連続する a, b  の積。


(defn- qd
  [a b]
  (take-while
    (comp prime? #(+ (* % (+ % a)) b))
    (iterate inc 0)))


;; n(n+a) + b, n=0 の時、b が素数でなければ題意を満たさない。
(defn p27
  [n]
  (reduce
    (fn [a b] (if (> (a 1) (b 1)) a b))
    (for [a (range (+ (- 0 n) 1) n)
          b (filter prime? (range 3 n))]
      [(* a b) (count (qd a b))])))


;; (time (p27 1000))
;; "Elapsed time: 1333.047395 msecs"
;; [-59231 71]
