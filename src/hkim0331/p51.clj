(ns hkim0331.p51
  (:require [clojure.math.combinatorics :as combo]
            [hkim0331.misc :refer [prime? primes]]))

; By replacing the 1st digit of the 2-digit number *3, it turns out that six of the nine possible values: 13, 23, 43, 53, 73, and 83, are all prime.
; By replacing the 3rd and 4th digits of 56**3 with the same digit, this 5-digit number is the first example having seven primes among the ten generated numbers, yielding the family: 56003, 56113, 56333, 56443, 56663, 56773, and 56993. Consequently 56003, being the first member of this family, is the smallest prime with this property.
; Find the smallest prime which, by replacing part of the number (not necessarily adjacent digits) with the same digit, is part of an eight prime value family.

(defn replace-digits [i c digits]
  "char のシーケンス digits の i 番目を char c に変えたコレクションを返す"
  (if (zero? i)
    (cons c (rest digits))
    (cons (first digits)
          (replace-digits (- i 1)
                          c
                          (rest digits)))))

(replace-digits 2 \2 [\1 \2 \3])

(defn replace-two-with [n number]
  (let [digits (seq (str number))
        c (first (seq (str n)))]
    (for [[i j] (combo/combinations (range (count digits)) 2)]
      (Integer. (apply str (replace-digits j c (replace-digits i c digits)))))))

(replace-two-with 3 12345)

(defn replace-two-digits [number]
  (flatten (map #(replace-two-with % number) (range 0 10))))

(filter #(< 56000 % 57000)
        (filter prime? (replace-two-digits 56003)))

(first
 (filter
  (fn [x] (= 7 #(count (filter prime? x))))
  (map replace-two-digits primes)))

