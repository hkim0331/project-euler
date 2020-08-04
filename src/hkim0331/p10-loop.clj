  (:require [hkim0331.p7 :refer [next-prime]])

(defn p10 [n]
  (loop [ret 2 p 3]
    (if (> p n)
      ret
      (recur (+ ret p) (next-prime p)))))

(time (p10 2000000))
