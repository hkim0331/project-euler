(ns hkim0331.p46
  (:require
    [hkim0331.misc :refer [prime?]]))


;; It was proposed by Christian Goldbach that every odd composite number can be written as the sum of a prime and twice a square.

;; 9 = 7 + 2×1^2
;; 15 = 7 + 2×2^2
;; 21 = 3 + 2×3^2
;; 25 = 7 + 2×3^2
;; 27 = 19 + 2×2^2
;; 33 = 31 + 2×1^2

;; It turns out that the conjecture was false.

;; What is the smallest odd composite that cannot be written as the sum of a prime and twice a square?

(defn twice-square-under
  [n]
  "n 以下の 2*prime^2 を返す。"
  (take-while #(< % n)
              (map #(* 2 % %) (iterate inc 1))))


;; 1st version,
;; not-prime-odds を lazy-seq としてあらかじめ定義する。

(defn goldbach
  [n]
  "goldbach 性を持てば true, そうでなければ n を返す。"
  (if (some prime?
            (map #(- n %) (twice-square-under n)))
    true
    n))


(def not-prime-odds
  "奇数かつ素数でない数の lazy-seq"
  (filter (complement prime?)
          (iterate (partial + 2) 3)))


;; 奇数かつ素数でない数に goldbach をマップ、
;; 最初の true でない要素。
;; (time
;;  (first
;;    (drop-while true?
;;                (map goldbach not-prime-odds))))
;; "Elapsed time: 4.83602 msecs"
;; 5777

;; 2nd version.

(defn goldbach2
  [n]
  "n が goldbach 性を持つか？
   持つなら true、そうでない時 n を返す。"
  (or (even? n)
      (prime? n)
      (some prime?
            (map #(- n %) (twice-square-under n)))
      n))


;; lazy-seq 3,4,5,... に goldbach2 をマップし、
;; 最初の true でない要素を返す。
;; (time
;;  (first
;;   (drop-while true?
;;     (map goldbach2 (iterate inc 3)))))
;; "Elapsed time: 6.486162 msecs"
;; 5777



