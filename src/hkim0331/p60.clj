(ns hkim0331.p60
  (:require [clojure.math.combinatorics :as combo]
            [hkim0331.misc :refer [primes prime?]]))

; The primes 3, 7, 109, and 673, are quite remarkable. By taking any two primes and concatenating them in any order the result will always be prime. For example, taking 7 and 109, both 7109 and 1097 are prime. The sum of these four primes, 792, represents the lowest sum for a set of four primes with this property.

; Find the lowest sum for a set of five primes for which any two primes concatenate to produce another prime.


;; 700 以下の素数からの 4 ペアで p60 を満足するペアが見つかるかどうかをテストする。
;; 見つかるとすればそれは #{3 7 109 673} のはずだ。

(defn cc
  [[n1 n2]]
  (let [s1 (str n1)
        s2 (str n2)]
    (and (prime? (Integer. (str s1 s2)))
         (prime? (Integer. (str s2 s1))))))

(defn p60?
  [numbers]
  (every? true? (map cc (combo/combinations numbers 2))))

;(p60? [3 7 109 673])
;true

(def primes-700 (take-while #(< % 700) primes))

;(take 20 primes-700)

; (time
;  (first
;    (drop-while (complement p60?) (combo/combinations primes-700 4))))
; "Elapsed time: 1383.185882 msecs"
; (3 7 109 673)
; 遅い。

; 試しに、
(def primes-10000 (take-while #(< % 10000) primes))

; (time
;   (first
;     (drop-while (complement p60?) (combo/combinations primes-10000 5))))

; いつまで待っても答えが返らない。10^12 回のループだからな。当たり前。
; 何か根本的に違うアルゴリズムを考えないと。
; C で書いてみるか。


