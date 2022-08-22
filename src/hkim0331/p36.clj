(ns hkim0331.p36
  (:require
    [hkim0331.p4 :refer [palindrome-number?]]))


;; The decimal number, 585 = 1001001001(2) , is palindromic
;; in both bases.

;; Find the sum of all numbers, less than one million, which are
;; palindromic in base 10 and base 2.

;; (Please note that the palindromic number, in either base, may)
;; not include leading zeros.

;; 先頭にゼロを許さないので、奇数に限る。

(def pal-10 (filter palindrome-number? (range 1 1000000 2)))


(defn base-aux
  [n num ret]
  (if (zero? num)
    (reverse ret)
    (base-aux n (quot num n) (conj ret (rem num n)))))


(defn base
  [n num]
  (if (zero? num)
    [0]
    (base-aux n num [])))


;; (base 2 16)
;; (base 2 999999)

(defn pal-n
  [n num]
  (let [bn (base n num)]
    (= bn (reverse bn))))


;; (pal-n 2 585)

;; (time (reduce + (filter #(pal-n 2 %) pal-10)))
;; "Elapsed time: 510.9467 msecs"
;; 872187
