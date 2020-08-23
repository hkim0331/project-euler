(ns hkim0331.p60
  (:require [clojure.math.combinatorics :as combo]
            [hkim0331.misc :refer [primes prime?]]))

; The primes 3, 7, 109, and 673, are quite remarkable. By taking any two primes and concatenating them in any order the result will always be prime. For example, taking 7 and 109, both 7109 and 1097 are prime. The sum of these four primes, 792, represents the lowest sum for a set of four primes with this property.

; Find the lowest sum for a set of five primes for which any two primes concatenate to produce another prime.


;; 700 以下の素数からの 4 ペアで p60 を満足するペアが見つかるかどうかをテストする。
;; 見つかるとすればそれは #{3 7 109 673} のはずだ。

(defn cc?
  "引数の [n1 n2] から作った整数 n1n2, n2n1 がともに素数か?"
  [[n1 n2]]
  (let [s1 (str n1)
        s2 (str n2)]
    (and (prime? (Integer. (str s1 s2)))
         (prime? (Integer. (str s2 s1))))))

(defn p60?
  [numbers]
  (every? cc? (combo/combinations numbers 2)))

;(p60? [3 7 109 673])
;true

(def primes-700 (take-while #(< % 700) primes))

; (time
;  (first
;    (drop-while (complement p60?) (combo/combinations primes-700 4))))
; "Elapsed time: 1383.185882 msecs"
; (3 7 109 673)
; 遅い。

; 試しに、
(def primes-10000 (take-while #(< % 10000) primes))
(count primes-10000)
;1229

; (time
;   (first
;     (drop-while (complement p60?) (combo/combinations primes-10000 5))))

; いつまで待っても答えが返らない。10^12 回のループだからな。当たり前。
; 何か根本的に違うアルゴリズムを考えないと。
; C で書いてみるか。

;関数一発でゴン方式をやめてみる。

(def pairs (combo/combinations primes-10000 2))

(count pairs)
;754606

;片方に2があったら素数にならない
(def pairs-2 (remove #(or (= 2 (first %)) (= 2 (second %))) pairs))

;片方に5があったら素数にならない
(def pairs-25 (remove #(or (= 5 (first %)) (= 5 (second %))) pairs-2))

;足して3の倍数は素数にならない
(def pairs-253 (remove #(zero? (mod (+ (first %) (second %)) 3)) pairs-25))

(count pairs-253)
;376386
;半分には減ったがまだまだ。

;cc? でフィルタ。
(def pairs-253-cc (filter cc? pairs-253))
(count pairs-253-cc)
;18176

;二つを選んで、ジョインし、3の倍数になるものを消した上で cc? でフィルタ。
(def pairs-253-cc-4-3-cc
  (filter cc?
    (remove #(zero? (mod (+ (first %) (second %)) 3))
            (map (partial apply into)
                 (combo/combinations pairs-253-cc 2)))))

;これやっぱりダメ。C[18176,2] が爆弾だ。
;深さ優先サーチで最初の解を探そう。

