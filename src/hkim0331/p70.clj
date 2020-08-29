(ns hkim0331.p70
  (:require [hkim0331.misc :refer [primes]]))

; Euler's Totient function, φ(n) [sometimes called the phi function], is used to determine the number of positive numbers less than or equal to n which are relatively prime to n. For example, as 1, 2, 4, 5, 7, and 8, are all less than nine and relatively prime to nine, φ(9)=6.
; The number 1 is considered to be relatively prime to every positive number, so φ(1)=1.
; Interestingly, φ(87109)=79180, and it can be seen that 87109 is a permutation of 79180.
; Find the value of n, 1 < n < 10^7, for which φ(n) is a permutation of n and the ratio n/φ(n) produces a minimum.

;; phi(n) が n の並び違いになるためには
;; phi(n) と同じ数字を n も持つ必要があるが、
;; p が素数の時、phi(p) = p-1
;; 素数の最終桁（1桁目）は 1,3,7 なので、
;; 素材がどうしても違ってくる。なので素数は p70 の解ではない。

;; n が偶数か奇数かと言えば奇数。分母となる phi(n) が n が偶数だと小さくなる。
;; 同じ論法で n は素数。しかし、単独の素数ではない（上）。
;; 二つの素数 p,q の掛け算になるような n が p70 の解だろう。

;; これをどうプログラムするか？
;; 0<p<q<r<sの時、全部素数、
;; pq/phi(p)phi(q) > rs/phi(r)phi(s)

;;1,000,000< pq < 10,000,000 のうち、条件を満たす最も大きな pq を探す、でいいか？

;(Math/sqrt 10000000)
;3162.2776601683795

(def pq
 (take-while #(< % 3126)
   (drop-while #(< % 1000) primes)))

(count pq)
(take 10 pq)

; 大きな組みがいいんだから、ひっくり返しておく。
(def pq-rev (reverse pq))
(take 10 pq-rev)

(defn permute=?
  "n, m は数字の並びだけが違う数か？"
  [n m]
  (= (set (seq (str n)))
     (set (seq (str n)))))
;(permute=? 123 321)

(defn p70
 []
 (first
  (drop-while nil?
    (for [p pq-rev q pq-rev]
      (when (permute=? (* p q) (* (- p 1) (- q 1)))
        [(* p q) p q])))))

; (time (p70))
; "Elapsed time: 0.579608 msecs"
; 9840769
; 赤ペケ。最初に見つかる数が最大とは限らないか？

