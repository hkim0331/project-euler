(ns hkim0331.p17
  (:require [hkim0331.p16 :refer [c->i]]
            [clojure.string :as str]))

; If the numbers 1 to 5 are written out in words: one, two,
; three, four, five, then there are 3 + 3 + 5 + 4 + 4 = 19
; letters used in total.

; If all the numbers from 1 to 1000 (one thousand) inclusive
; were written out in words, how many letters would be used?

(def word
  {1 "one"
   2 "two"
   3 "three"
   4 "four"
   5 "five"
   6 "six"
   7 "seven"
   8 "eight"
   9 "nine"
   10 "ten"
   11 "eleven"
   12 "twelve"
   13 "thirteen"
   14 "fourteen"
   15 "fifteen"
   16 "sixteen"
   17 "seventeen"
   18 "eighteen"
   19 "nineteen"
   20 "twenty"
   30 "thirty"
   40 "forty"
   50 "fifty"
   60 "sixty"
   70 "seventy"
   80 "eighty"
   90 "ninety"
   100 "handred"
   1000 "one thausand"})


(defn- aux
  ([d0]
   (word d0))

  ([d1 d0]
   (if (= d1 1)
       (word (+ (* d1 10) d0))
       (str (word (* d1 10))
            " "
            (word d0))))
  ([d2 d1 d0]
   (if (and (zero? d1) (zero? d0))
     (str (word d2)
          " "
          (word 100))
     (str (word d2)
          " "
          (word 100)
          " and "
          (aux d1 d0))))
  ([d3 d2 d1 d0]
   (word 1000)))

(defn in-word [n]
   (apply aux (map c->i (-> n str seq))))

(defn p17-aux
  ([n m]
   (map in-word (range n (+ m 1))))
  ([n]
   (p17-aux 1 n)))

(count (str/replace (apply str (p17-aux 1000)) #"\s" ""))

