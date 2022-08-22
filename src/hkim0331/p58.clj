(ns hkim0331.p58
  (:require
    [hkim0331.misc :refer [prime? primes]]))


;; Starting with 1 and spiralling anticlockwise in the following way, a square spiral with side length 7 is formed.

;; 37 36 35 34 33 32 31
;; 38 17 16 15 14 13 30
;; 39 18  5  4  3 12 29
;; 40 19  6  1  2 11 28
;; 41 20  7  8  9 10 27
;; 42 21 22 23 24 25 26
;; 43 44 45 46 47 48 49

;; It is interesting to note that the odd squares lie along the bottom right diagonal, but what is more interesting is that 8 out of the 13 numbers lying along both diagonals are prime; that is, a ratio of 8/13 ≈ 62%.

;; If one complete new layer is wrapped around the spiral above, a square spiral with side length 9 will be formed. If this process is continued, what is the side length of the square spiral for which the ratio of primes along both diagonals first falls below 10%?


(defn corners
  "辺の長さ n の左回り正方形における各隅の整数のリスト"
  [n]
  (if (even? n)
    (throw (Exception. "argument must be odd number."))
    (let [m (- n 2)]
      (conj [(+ (* m (+ m 1)) 1)
             (+ (* m (+ m 2)) 2)
             (+ (* m (+ m 3)) 3)
             (* n n)]))))


;; 問題読み間違った。
;; (defn f [n]
;;   (if (< n 9)
;;     [8 13]
;;     (map +
;;       (f (- n 2))
;;       [(count (filter prime? (corners n)))
;;        (count (filter prime? (range (+ 1 (sq (- n 2)))
;;                                     (+ 1 (sq n)))))])))

;; 対角線に並ぶ素数の数か！
;; memoize の使い方も間違っていた。
(def d-primes
  (memoize
    (fn [n]
      (cond
        (< n 7) (throw "n must be grater than or equal 7.")
        (= n 7) 8
        (even? n) (throw " n must be a odd number.")
        :else  (+ (d-primes (- n 2))
                  (count (filter prime? (corners n))))))))


;; (time (last (take 1000 (map #(/ (d-primes %) (- (* 2 %) 1.0))
;;                             (iterate (partial + 2) 7)))))

(defn p58
  []
  (first
    (drop-while #(< 0.1 (/ (d-primes %) (- (* 2 %) 1.0)))
                (iterate (partial + 2) 7))))


;; (time (p58))
;; "Elapsed time: 2223.48658 msecs"
;; 26241

;; 2度目は memoize が効くぞ。
;; (time (p58))
;; "Elapsed time: 9.605271 msecs"
;; 26241
