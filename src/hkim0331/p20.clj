;Find the sum of the digits in the number 100!
(ns hkim0331.p20)

(defn- f [n]
  (apply *(range 1N (+ 1 n))))

(defn p20 [n]
  (apply + (map #(Integer. %) (re-seq #"\d" (str (f n))))))

; (= 27 (p20 10))
; (time (p20 100))
; "Elapsed time: 2.517672 msecs"
; 648