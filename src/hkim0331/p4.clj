(ns hkim0331.p4)
; A palindromic number reads the same both ways.
; The largest palindrome made from the product of two 2-digit
; numbers is 9009 = 91 × 99.

; Find the largest palindrome made from the product of two 3-digit
; numbers.



;; ここを改良してみよう
;(defn reverse-string [s]
; (apply str (reverse s)))
; (defn palindrome-number? [n]
;  (let [s (str n)]
;    (= s (reverse-string s))))
(defn palindrome-number? [n]
  (let [s (seq (str n))]
    (= s (reverse s))))

(defn find-largest-palindrome-number [xs ys]
  (apply max
    (flatten
     (for [x xs]
       (filter palindrome-number?
         (for [y ys]
           (* x y)))))))

(time (find-largest-palindrome-number
        (range 100 1000)
        (range 100 1000)))

(defn flpn [from to]
  (apply max
    (flatten
      (for [x (range from to)]
        (filter palindrome-number?
          (for [y (range from x)]
            (* x y)))))))

(time (flpn 100 1000))
;; 378msec
(time (flpn 900 1000))
;; 5msec


(defn flp2 [from to]
  (let [p (atom (set []))]
    ;(swap! p conj "start")
    (dorun
       (for [x (range from to)
             y (range from x)
             :let [z (* x y)]
             :when (palindrome-number? z)]
         (swap! p conj z)))
    (apply max @p)))


(flp2 10 100)
;; => 9009

(time (flp2 100 1000))
;; 378msec
(time (flp2 900 1000))
;; 5msec

(defn flp3 [from to]
  (apply max
    (for [x (range from to)
          y (range from x)
          :when (palindrome-number? (* x y))]
      (* x y))))

(time (flp3 100 1000))
;; 288msec
(time (flp3 900 1000))
;; 4msec

