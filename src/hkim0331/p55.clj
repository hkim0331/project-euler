(ns hkim0331.p55)


;; If we take 47, reverse and add, 47 + 74 = 121, which is palindromic.
;; Not all numbers produce palindromes so quickly. For example,
;;
;; 349 + 943 = 1292,
;; 1292 + 2921 = 4213
;; 4213 + 3124 = 7337
;;
;; That is, 349 took three iterations to arrive at a palindrome.
;; Although no one has proved it yet, it is thought that some numbers, like 196, never produce a palindrome. A number that never forms a palindrome through the reverse and add process is called a Lychrel number. Due to the theoretical nature of these numbers, and for the purpose of this problem, we shall assume that a number is Lychrel until proven otherwise. In addition you are given that for every number below ten-thousand, it will either (i) become a palindrome in less than fifty iterations, or, (ii) no one, with all the computing power that exists, has managed so far to map it to a palindrome. In fact, 10677 is the first number to be shown to require over fifty iterations before producing a palindrome: 4668731596684224866951378664 (53 iterations, 28-digits).
;; Surprisingly, there are palindromic numbers that are themselves Lychrel numbers; the first example is 4994.
;; How many Lychrel numbers are there below ten-thousand?
;; NOTE: Wording was modified slightly on 24 April 2007 to emphasise the theoretical nature of Lychrel numbers.

(defn str->int-aux
  [s n]
  (if (empty? s)
    n
    (str->int-aux (subs s 1)
                  (+ (* 10 n) (Integer. (subs s 0 1))))))


(defn str->int
  [s]
  (str->int-aux s 0N))


(defn rev
  [s]
  (apply str (reverse s)))


(defn palindrome?
  [s]
  (= s (rev s)))


(defn iter
  [s]
  (str (+' (str->int s) (str->int (rev s)))))


(defn lychel-aux
  [s c]
  (cond
    (zero? c) s
    (palindrome? s) false
    :else	(lychel-aux (iter s) (- c 1))))


;; 最初の数それ自身が palindrome である数を lychel 数でないと
;; 誤認しないために、一度回し、(- c 1) する。

(defn lychel?
  ([n] (lychel? n 50))
  ([n c] (lychel-aux (iter (str n)) (- c 1))))


;; (time (count (filter #(lychel? % 50) (range 10000))))
;; "Elapsed time: 321.486518 msecs"
;; 249
