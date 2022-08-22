(ns hkim0331.p42)


;; The nth term of the sequence of triangle numbers is given by, tn = ½n(n+1) so the first ten triangle numbers are:

;; 1, 3, 6, 10, 15, 21, 28, 36, 45, 55, ...

;; By converting each letter in a word to a number corresponding to its alphabetical position and adding these values we form a word value. For example, the word value for SKY is 19 + 11 + 25 = 55 = t10. If the word value is a triangle number then we shall call the word a triangle word.

;; Using words.txt (right click and 'Save Link/Target As...'), a 16K text file containing nearly two-thousand common English words, how many are triangle words?

(defn c->i
  [c]
  (+ 1 (- (int c) (int \A))))


(defn word-value
  [w]
  (reduce + (map c->i (seq w))))


(def words
  (clojure.string/split
    (clojure.string/replace
      (slurp "resources/p042_words.txt")
      #"\""
      "") #","))


;; ださい...
;; (reduce max (map word-value words))
;; 192

(def triangle-numbers
  (take-while (partial > 192)
              (map (fn [n] (/ (* n (+ n 1)) 2))
                   (iterate inc 1))))


(defn tn?
  [n]
  (some (partial = n) triangle-numbers))


;; (tn? 11)

;; (time (count (filter tn? (map word-value words))))
;; "Elapsed time: 11.176778 msecs"
;; 162
