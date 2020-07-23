(ns hkim0331.p14)
;;longest collatz sequences under one million

;;need improvements

(defn c [n]
  (if (even? n)
    (/ n 2)
    (+ (* 3 n) 1)))

(defn cs [n ret]
  (if (= n 1)
      (conj ret 1)
      (cs (c n) (conj ret n))))

(defn collatz-seq [n]
  (cs n []))

;(collatz-seq 13)

(def collatz-len
  (fn [n]
    (count (cs n []))))

(def collatz-len-memo
  (memoize (fn [n]
              (count (cs n [])))))
;(collatz-seq-memo 13)

(defn p14 [n]
  (loop [i 1 max 0 at 0]
    (if (> i n)
        [max at]
        (let [len (collatz-len-memo i)]
          (if (> len max)
            (recur (inc i) len i)
            (recur (inc i) max at))))))

(time (p14 1000000))