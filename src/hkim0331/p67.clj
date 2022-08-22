(ns hkim0331.p67
  (:require
    [hkim0331.p18 :refer [p18 to-ints]]))


;; By starting at the top of the triangle below and moving to adjacent numbers on the row below, the maximum total from top to bottom is 23.

;; 3
;; 7 4
;; 2 4 6
;; 8 5 9 3

;; That is, 3 + 7 + 4 + 9 = 23.

;; Find the maximum total from top to bottom in triangle.txt (right click and 'Save Link/Target As...'), a 15K text file containing a triangle with one-hundred rows.


;; すでにこれは p18 で解いているはず。
;; p18の問題と同じ形式に p067_triangle.txt を読めればいい。
;; 関数を定義するまでもないが、、、

(defn p67
  [file]
  (p18 (to-ints (slurp file))))


(p67 "resources/p067_triangle.txt")


;; (time (p18 triangle))
;; "Elapsed time: 6.385822 msecs"
;; 7273
