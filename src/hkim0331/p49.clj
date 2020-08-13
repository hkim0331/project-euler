(ns hkim0331.p49)
; The arithmetic sequence, 1487, 4817, 8147, in which each of the terms increases by 3330, is unusual in two ways: (i) each of the three terms are prime, and, (ii) each of the 4-digit numbers are permutations of one another.

; There are no arithmetic sequences made up of three 1-, 2-, or 3-digit primes, exhibiting this property, but there is one other 4-digit increasing sequence.

; What 12-digit number do you form by concatenating the three terms in this sequence?

;;numbers は 1,000 以上 10,000 以下の素数
(def numbers (take-while
                #(< % 10000)
                (drop-while #(< % 999) primes)))

;(first numbers)
;1009
;(count numbers)
;1061

;こっちじゃねーだろ。
(filter #(and (prime? (+ 3330 %)) (prime? (+ 6660 %))) numbers)
(count *1)
;149


;1061 -> 0116 に写像する
(defn- v [n]
  (Integer. (apply str (sort (seq (str n))))))

;(v 1061)

(def g (group-by v numbers))
;(count g)
;336
;(vals g)

(first (vals g))
(filter #(< 2 (count %)) (vals g))
(count *1)
;174


(defn- same-interval? [col]
  (apply =
        (map #(- (second %) (first %)) (partition 2 1 col))))


(filter same-interval? (filter #(= 3  (count %)) (vals g)))


;(- 9629 6299)
;(- 6299 2969)
