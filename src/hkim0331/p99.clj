(ns hkim0331.p99
  (:require
    [clojure.string :as str]))


;; Comparing two numbers written in index form like 2^11 and 3^7 is not difficult, as any calculator would confirm that 2^11 = 2048 < 3^7 = 2187.

;; However, confirming that 632382^518061 > 519432^525806 would be much more difficult, as both numbers contain over three million digits.

;; Using base_exp.txt (right click and 'Save Link/Target As...'), a 22K text file containing one thousand lines with a base/exponent pair on each line, determine which line number has the greatest numerical value.

;; NOTE: The first two lines in the file represent the numbers in the example given above.


(defn my-max
  "v1, v2 の大きい方を返す"
  [[i1 v1] [i2 v2]]
  (if (< v1 v2)
    [i2 v2]
    [i1 v1]))


(let [lines (map #(str/split % #",")
                 (str/split (slurp "resources/p099_base_exp.txt") #"\n"))
      numbers (map
                (fn [[bs es]] (* (Integer. es) (Math/log (Integer. bs))))
                lines)
      v (map-indexed vector numbers)]
  (reduce my-max v))


;; [708 6919995.552420337]
;; インデックスが 0 から始まっているので答えは 709.
