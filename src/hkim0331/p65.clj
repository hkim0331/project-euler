(ns hkim0331.p65)

(defn e
 "convergence of e."
 [n]
 (if (= 1 (mod n 3))
     (* 2 (+ 1 (quot n 3)))
     1))

;;(take 20 (map e (iterate inc 1)))

(defn cf [col]
  "continued fraction of col. recursive definition."
  (if (empty? col)
     0
     (/ 1 (+ (first col) (cf (rest col))))))

;(cf (map e (range 9)))

(defn c->i
  "coarse char c into integer."
  [c]
  (- (int c) (int \0)))

;(c->i \3)

(+ 2 (cf (map e (range 99))))

(defn p65
  [n]
  (let [ret (+ 2 (cf (map e (range n))))]
    (->> ret
        numerator
        str
        seq
        (map c->i)
        (reduce +))))

; (time (p65 99))
; "Elapsed time: 1.294114 msecs"
; 272
