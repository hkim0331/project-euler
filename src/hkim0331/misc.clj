(ns hkim0331.misc)

(defn sq [n]
  (* n n))

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

;;; prime factors.
;;; map で呼ぶと返ってこない。

(defn- pf-aux [n d ret]
    (cond
      (= n 1) ret
      (zero? (mod n d)) (recur (/ n d) d (conj ret d))
      :else (recur n (+ 1 d) ret)))

(defn prime-factors [n]
  "整数 n の素因数分解"
  (if (= n 1)
    1
    (doall(pf-aux n 2 []))))

;;other definition、 これは遅いか。
; (defn prime-factors-2 [n]
;   (filter #(zero? (mod n %))
;      (take-while #(< % n) primes)))



(defn phi
 "Euler's totient (phi) function"
 [n]
 (case n
  1 1
  (reduce * n
    (map #(- 1 (/ 1 %))
         (->> n
              prime-factors
              (group-by identity)
              keys)))))


;; power, power-mod

(defn power [b e]
    (cond
      (zero? e) 1N
      (even? e) (sq (power b (/ e 2)))
      :else (* b (power b (- e 1)))))

(defn power-mod [b e m]
  (cond
    (zero? e) 1N
    (even? e) (mod (sq (power-mod b (/ e 2) m)) m)
    :else (mod (* (mod b m)
                  (power-mod b (- e 1) m)) m)))

;;; pandigital

(defn pandigital? [n]
  (let [p (seq (str n))
        d (take (count p) (seq (apply str (range 1 10))))]
   (= (set p) (set d))))

;;
