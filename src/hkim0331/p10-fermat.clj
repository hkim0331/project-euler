(ns hkim0331.p10-fermat
  (:require [hkim0331.p7 :refer [prime?]]))

(defn sq [n]
  (* n n))

(defn power [b e]
  (cond
    (zero? e) 1N
    (even? e) (sq (power b (/ e 2)))
    :else (* b (power b (- e 1)))))

(defn power-mod [b e n]
  (cond
    (zero? e) 1N
    (even? e) (rem (sq (power-mod b (/ e 2) n)) n)
    :else (rem (* (rem b n)
                  (power-mod b (- e 1) n)) n)))

(defn pprime? [n]
 (= 1 (power-mod 2 (- n 1) n)))

(pprime? 7)
(pprime? 11)
(pprime? 15)
(filter pprime? (range 3 100 2))
(filter prime? (range 3 100 2))

(prime? 561)
(pprime? 561)


(time (count (filter prime?  (range 3 2000000))))
(time (count (filter pprime? (range 3 2000000))))

(power-mod 2 96 97)
