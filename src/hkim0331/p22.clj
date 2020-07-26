(ns hkim0331.p22
  (:require [clojure.string :as str]))

;; ダブルクオートをはぎ取り、
;; コンマでスプリット
;; そして並べ替え

(def names
  (-> (slurp "resources/p022_names.txt")
      (str/replace #"\"" "")
      (str/split #",")
      (sort)))

(defn- value [c]
  (+ 1 (- (int c) (int \A))))

(defn worth [word]
  (reduce + (map value (seq word))))

;(= 53 (worth "COLIN"))

(defn p22 [names]
  (map
    (fn [v] (* (v 0) (v 1)))
    (map-indexed
      (fn [idx itm] [idx (worth itm)])
      ;; インデックスを一つずらす。
      (cons "DUMMY" names))))

(reduce + (p22 names))
