(ns hkim0331.p72
  (:require
    [hkim0331.misc :refer [prime-factors factor-integer]]))


;; Consider the fraction, n/d, where n and d are positive integers. If n<d and HCF(n,d)=1, it is called a reduced proper fraction.
;; If we list the set of reduced proper fractions for d ≤ 8 in ascending order of size, we get:

;; 1/8, 1/7, 1/6, 1/5, 1/4, 2/7, 1/3, 3/8, 2/5, 3/7, 1/2, 4/7, 3/5, 5/8, 2/3, 5/7, 3/4, 4/5, 5/6, 6/7, 7/8

;; It can be seen that there are 21 elements in this set.
;; How many elements would be contained in the set of reduced proper fractions for d ≤ 1,000,000?

;; 1st version. 力ずくで。

(defn p72-brain-damaged
  "n/d の数, d <= m"
  [m]
  (count
    (set
      (for [d (range 1 (+ 1 m)) n (range 1 d)]
        (/ n d)))))


;; (p72-brain-damaged 8)
;; 21

;; (time (p72-brain-damaged 1000))
;; "Elapsed time: 6175.924348 msecs"
;; 304191

;; 終わらない。
;; (time (p72-brain-damaged 1000000))

;; 工夫がいる。phi() だろ。

(defn phi
  "Euler's totient (phi) function"
  [n]
  (case n
    1 1
    (reduce * n
            (map #(- 1 (/ 1 %))
                 (->> n
                      prime-factors
                      (group-by identity)
                      keys)))))


(defn p72
  [n]
  (reduce + (map phi (range 2 (+ n 1)))))


;; 問題の1/10のサイズでやってみる
;; (time (p72 100000))
;; "Elapsed time: 31060.187689 msecs"
;; 3039650753N
;; まだ最適化が必要。


;; clojure らしく factor-integer を定義、
;; 単独では小さい数字(1000~4000)では
;; prime-factors よりも20倍以上高速という結果は
;; 得られた。

;; (time (prime-factors 12345678))
;; "Elapsed time: 0.165514 msecs"
;; (time (count (map prime-factors  (range 10000 40000))))
;; "Elapsed time: 1161.236942 msecs"
;; (factor-integer 12345678)
;; (time (count (map factor-integer (range 10000 40000))))
;; "Elapsed time: 3.364862 msecs"

(defn phi
  [n]
  (if (= n 1)
    1
    (reduce * n (map #(- 1 (/ 1 %))
                     (factor-integer n)))))


;; しかし、本番の1/10 のサイズで、
;; (time (p72 100000))
;; "Elapsed time: 88874.477775 msecs"
;; 3039660345N
;; 逆に遅くなってしまった。

;; m,n が互いに素の時、phi(mn)=phi(m)phi(n)を使おう。
;; n が偶数の時に最適化の余地がある。

(def phi-memo
  (memoize (fn [n] (phi n))))


(defn phi
  "Euler's totient (phi) function"
  [n]
  (cond
    (< n 3) 1
    (and (even? n) (odd? (/ n 2))) (phi-memo (/ n 2))
    :else (reduce * n
                  (map #(- 1 (/ 1 %))
                       (->> n
                            prime-factors
                            (group-by identity)
                            keys)))))


;; (defn p72
;;  [n]
;;  (reduce + (map phi-memo (range 2 (+ n 1)))))

;; (p72 8)

;; (time (p72 100000))
;; "Elapsed time: 25240.824226 msecs"
;; 3039650753N
;; ほとんど効かない。1割くらい？こんなんじゃダメ。
