(ns hkim0331.p49
  (:require
    [hkim0331.misc :refer [primes prime?]]))


;; The arithmetic sequence, 1487, 4817, 8147, in which each of the terms increases by 3330, is unusual in two ways: (i) each of the three terms are prime, and, (ii) each of the 4-digit numbers are permutations of one another.

;; There are no arithmetic sequences made up of three 1-, 2-, or 3-digit primes, exhibiting this property, but there is one other 4-digit increasing sequence.

;; What 12-digit number do you form by concatenating the three terms in this sequence?

;; numbers は 1,000 以上 10,000 以下の素数
(def ps
  (set (take-while #(< % 10000)
                   (drop-while #(< % 1000) primes))))


;; reverseは先頭にゼロが来ないように。
(defn digits
  [x]
  (Integer. (apply str (reverse (sort (seq (str x)))))))


;; (digits 432)
;; (digits 10613)

(defn same-digits?
  [x y z]
  (= (digits x) (digits y) (digits z)))


(defn p49
  []
  (for [x ps
        y ps
        :when (< x y)
        :let [z (+ y (- y x))]
        :when (prime? z)
        :when (same-digits? x y z)]
    [x y z]))


;; (time (p49))
;; "Elapsed time: 0.133542 msecs"
;; ([1487 4817 8147] [2969 6299 9629])
