(ns hkim0331.p14)
;;longest collatz sequences under one million

;;need improvements

(defn collatz [n]
  (if (even? n)
    (/ n 2)
    (+ (* 3 n) 1)))

(defn collatz-seq [n]
   (take-while #(not= % 1) (iterate collatz n)))

;(collatz-seq 13)

(defn collatz-len [n]
  (+ 1 (count (collatz-seq n))))

(defn p14 [n]
  (loop [i 1 max 0 at 0]
    (if (> i n)
        [max at]
        (let [len (collatz-len i)]
          (if (> len max)
            (recur (inc i) len i)
            (recur (inc i) max at))))))


;(time (p14 1000000))
;'"Elapsed time:  42943.663339 msecs"
; [525 837799]

;;need improve!

(def collatz-memo (memoize collatz-len))

(defn p14-memo [n]
  (loop [i 1 max 0 at 0]
    (if (> i n)
        [max at]
        (let [len (collatz-memo i)]
          (if (> len max)
            (recur (inc i) len i)
            (recur (inc i) max at))))))

(time (p14-memo 1000000))
; "Elapsed time: 43612.379248 msecs"
; (time (p14-memo 1000000))
; "Elapsed time: 822.398522 msecs"

(defn collatz-count [n]
  (cond
    (= n 1) 1
    (even? n) (+ 1 (collatz-count (/ n 2)))
    :else (+ 2 (collatz-count (/ (+ 1 (* 3 n)) 2)))))

(defn p14-count [n]
  (loop [i (/ n 2) max 0 at 0]
    (if (> i n)
        [max at]
        (let [len (collatz-count i)]
          (if (> len max)
            (recur (inc i) len i)
            (recur (inc i) max at))))))

;(time (p14-count 1000000))
; "Elapsed time: 4070.739315 msecs"
; [525 837799]

(def cc-mem (memoize collatz-count))

(defn p14-mem [n]
  (loop [i (/ n 2) max 0 at 0]
    (if (> i n)
        [max at]
        (let [len (cc-mem i)]
          (if (> len max)
            (recur (inc i) len i)
            (recur (inc i) max at))))))

(time (p14-mem 1000000))