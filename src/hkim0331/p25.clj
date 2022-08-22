(ns hkim0331.p25)


(defn fibo
  []
  (map
    first
    (iterate (fn [[a b]] [b (+ a b)]) [1N 1N])))


(take 10 (fibo))


;; not desctururing bind
(defn fibo-index
  []
  (map
    first
    (iterate (fn [[as bs]]
               [bs [(inc (first bs))
                    (+ (as 1) (bs 1))]])
             [[1 1N] [2 1N]])))


(defn- sq
  [n]
  (* n n))


(defn power
  [base n]
  (cond
    (zero? n) 1N
    (even? n) (sq (power base (/ n 2)))
    :else (* base (power base (- n 1)))))


(defn p25
  [n]
  (first
    (drop-while (fn [v] (< (v 1) (power 10 n)))
                (fibo-index))))


;; (time (p25 999))
;; Elapsed time: 527.719401 msecs

;; これをループで書き直すとどうなるか？
(defn p25+
  [n]
  (let [lmt (power 10 n)]
    (loop [a 1N b 1N n 3]
      (if (> (+ a b) lmt)
        n
        (recur b (+ a b) (inc n))))))


;; (time (p25+ 999))
;; "Elapsed time: 20.137173 msecs"
;; 4782

