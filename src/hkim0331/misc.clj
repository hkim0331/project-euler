(ns hkim0331.misc)

;; prime number sequence. from 'Programming Clojure', p69.

(def primes
  (concat
   [2 3 5 7]
   (lazy-seq
    (let [primes-from
          (fn primes-from [n [f & r]]
            (if (some #(zero? (rem n %))
                      (take-while #(<= (* % %) n) primes))
              (recur (+ n f) r)
              (lazy-seq (cons n (primes-from (+ n f) r)))))
          wheel (cycle [2 4 2 4 6 2 6 4 2 4 6 6 2 6  4  2
                        6 4 6 8 4 2 4 2 4 8 6 4 6 2  4  6
                        2 6 6 4 2 4 6 2 6 4 2 4 2 10 2 10])]
      (primes-from 11 wheel)))))

(defn- prime?-aux [n m]
  (cond
  		(< n (* m m)) true
  		(zero? (rem n m)) false
  		:else (recur n (+ m 2))))

(defn prime? [n]
		(cond
				(< n 3) (= n 2)
				(even? n) false
				:else (prime?-aux n 3)))

(defn sq [n]
		(* n n))

(defn power [b e]
		(cond
				(zero? e) 1
		  (even? e) (sq (power b (/ e 2)))
		  :else (* b (power b (- e 1)))))

; (take 3 (range 10))
; エラーにならない。
; (0 1 2)

(defn pandigital? [n]
  (let [p (seq (str n))
        d (take (count p) (seq (apply str (range 1 10))))]
   (= (set p) (set d))))


