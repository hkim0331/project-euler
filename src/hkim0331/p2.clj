(ns hkim0331.p2)


;; Even Fibonacci numbers
;; Each new term in the Fibonacci sequence is generated by adding the previous
;; two terms. By starting with 1 and 2, the first 10 terms will be:
;;
;; 1, 2, 3, 5, 8, 13, 21, 34, 55, 89, ...
;;
;; By considering the terms in the Fibonacci sequence whose values do not exceed
;; four million, find the sum of the even-valued terms.)

(defn lazy-seq-fibo
  ([]
   (concat [1 2] (lazy-seq-fibo 1N 2N)))
  ([a b]
   (let [n (+ a b)]
     (lazy-seq
       (cons n (lazy-seq-fibo b n))))))


;; (take 10 (lazy-seq-fibo))

;; (take-while (partial > 4000)
;;             (filter even? (lazy-seq-fibo)))

(defn p2
  [n]
  (reduce +
          (take-while (partial > n)
                      (filter even? (lazy-seq-fibo)))))


(def four-million 4000000)


;; (time (p2 four-million))
;; "Elapsed time: 0.413659 msecs"
;; 4613732N

(defn fibo
  []
  (map
    first
    (iterate (fn [[a b]] [b (+ a b)]) [1 2])))


(defn p2'
  [n]
  (reduce +
          (take-while (partial > n)
                      (filter even? (fibo)))))


;; (time (p2' four-million))
;; "Elapsed time: 0.206818 msecs"
;; 4613732

;; how about not lazy

(defn- f-aux
  [n xs]
  (if (< n (first xs))
    (rest xs)
    (f-aux n (cons (+ (first xs) (second xs)) xs))))


(defn fibo-cons
  [n]
  (f-aux n '(2N 1N)))


;; (time
;;   (reduce +
;;     (filter even? (fibo-cons 4000000))))
;; "Elapsed time: 0.159481 msecs"
;; 4613732
