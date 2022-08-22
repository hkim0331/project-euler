(ns hkim0331.p38)


;; Take the number 192 and multiply it by each of 1, 2, and 3:

;; 192 × 1 = 192
;; 192 × 2 = 384
;; 192 × 3 = 576

;; By concatenating each product we get the 1 to 9 pandigital,
;; 192384576. We will call 192384576 the concatenated product of
;; 192 and (1,2,3)

;; The same can be achieved by starting with 9 and multiplying by
;; 1, 2, 3, 4, and 5, giving the pandigital, 918273645,
;; which is the concatenated product of 9 and (1,2,3,4,5).

;; What is the largest 1 to 9 pandigital 9-digit number that can
;; be formed as the concatenated product of an integer with
;; (1,2, ... , n) where n > 1?

(defn pandigital?
  [n]
  (let [digits (seq (str n))]
    (and (= 9 (count digits))
         (= #{\1 \2 \3 \4 \5 \6 \7 \8 \9}
            (set digits)))))


(defn make-p38
  [n m]
  (Integer.
    (apply
      str
      (map (partial * n) (range 1 (+ m 1))))))


;; (pandigital? (make-p38 9 5))

;; case base = 1 digit, (1)(2)(2)(2)(2)
(pandigital? 918273645)


;; case base = 2  digits, (2)(2)(2)(3)
;; base * 4 >= 100, base > 25
;; base * 3 <= 99, base < 33
(def b2
  (filter pandigital?
          (map #(make-p38 % 4) (range 25 33))))


;; case base 3 digits, (3)(3)(3)
;; 100 <= base < 333
(def b3
  (filter pandigital?
          (map #(make-p38 % 3) (range 100 333))))


;; case base 4digits, (4)(5)
(def b4
  (filter pandigital?
          (map #(make-p38 % 2) (range 5000 10000))))


;; (time (apply max (concat b2 b3 b4)))
;; "Elapsed time: 0.076174 msecs"
;; 932718654
;; このtime はインチキ。b2, b3, b4 の計算が外。
