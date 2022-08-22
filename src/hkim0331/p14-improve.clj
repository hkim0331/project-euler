(ns hkim0331.p14-improve)


;; longest collatz sequences under one million

(defn collatz
  [n]
  (cond
    (= n 1) [1]
    (even? n) (conj (collatz (/ n 2)) n)
    :else (conj (collatz (+ (* 3 n) 1)) n)))


(def collatz-memo (memoize collatz))
(def collatz-count (memoize (comp count collatz-memo)))


(defn p14
  [n]
  (loop [i 1 max 1 at 1]
    (if (> i n)
      [max at]
      (let [c (collatz-count i)]
        (if (> c max)
          (recur (inc i) c i)
          (recur (inc i) max at))))))


(time (p14 1000000))


;; (time (p14 1000000))
;; "Elapsed time: 9602.715124 msecs"
;; [525 837799]

;; memoize が効くのは2度目から？
;; (time (p14 1000000))
;; "Elapsed time: 728.39655 msecs"
;; [525 837799]
;; (time (p14 1000000))
;; "Elapsed time: 731.976205 msecs"
;; [525 837799]

(defn collatz-count
  [n]
  (cond
    (= n 1) 1
    (even? n) (+ 1 (collatz-count (/ n 2)))
    :else (+ 2 (collatz-count (/ (+ 1 (* 3 n)) 2)))))


(defn p14-count
  [n]
  (apply max (map collatz-count (range (/ n 2) n))))


;; (time (p14-count 1000000))
;; "Elapsed time: 3734.746499 msecs"
;; 525
