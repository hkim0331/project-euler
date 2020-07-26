(ns hkim0331.p24
  (:require [clojure.math.combinatorics :as combo]))

;;(first (drop 3 (combo/permutations [0 1 2])))

(time (first (drop 999999 (combo/permutations [0 1 2 3 4 5 6 7 8 9]))))

;2783915460