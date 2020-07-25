;Find the sum of the digits in the number 100!
(ns hkim0331.p20)

(defn- f [n]
  (apply *(range 1N (+ 1 n))))

(defn p20 [n]
  (apply + (map #(Integer. %) (re-seq #"\d" (str (f n))))))

(= 27 (p20 10))
(p20 100)