(ns hkim0331.p79
  (:require [clojure.string :as str]
            [clojure.math.combinatorics :as combo]))

; A common security method used for online banking is to ask the user for three random characters from a passcode. For example, if the passcode was 531278, they may ask for the 2nd, 3rd, and 5th characters; the expected reply would be: 317.

; The text file, keylog.txt, contains fifty successful login attempts.

; Given that the three characters are always asked for in order, analyse the file so as to determine the shortest possible secret passcode of unknown length.


;; これも深さ優先探索だ。

(def rules
  (apply sorted-set (str/split (slurp "resources/p079_keylog.txt") #"\n")))
;#{"129" "160" "162" "168" "180" "289" "290" "316" "318" "319" "362" "368" "380" "389" "620" "629" "680" "689" "690" "710" "716" "718" "719" "720" "728" "729" "731" "736" "760" "762" "769" "790" "890"}

;;紙と鉛筆でやりました。これでは行数を全く稼げない。
;73162890

;;どうアルゴリズミックにやるかな？
;;33個のルールを満足する最小の数字？
;;文字は8種類。4と5が出てこない。
;;なので、12367890 の順列で探す。

(defn p?
  "整数 ns 中に長さ 3 の文字列 s3 中に含まれる数字がその順番に出現していたら
   true を返す。"
  [ns s3]
  (let [re (re-pattern (str ".*" (subs s3 0 1)
                            ".*" (subs s3 1 2)
                            ".*" (subs s3 2 3)
                            ".*"))]
    (re-matches re ns)))

; (p? "12345" "425")
; (p? "12345" "135")
; (p? "73162891" "129")

; (when (every? (fn [r] (p? "73162890" r)) rules)
;   "yes")

(defn p79
 []
 (first
  (drop-while nil?
   (for [n (combo/permutations [\1 \2 \3 \6 \7 \8 \9 \0])]
     (let [ns (apply str n)]
       (when (every? (fn [r] (p? ns r)) rules)
         ns))))))

; (time (p79))
; "Elapsed time: 73.53406 msecs"
; "73162890"