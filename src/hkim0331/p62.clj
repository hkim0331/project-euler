(ns hkim0331.p62)

; The cube, 41063625 (345^3), can be permuted to produce two other cubes: 56623104 (384^3) and 66430125 (405^3). In fact, 41063625 is the smallest cube which has exactly three permutations of its digits which are also cube.

; Find the smallest cube for which exactly five permutations of its digits are cube.

(defn char> [c1 c2]
  (> (int c1) (int c2)))


(defn p62 [n h]
  (loop [i 300 hsh h]
;    (prn i hsh)
    (let [cube (* i i i)
          digits (apply str (sort char> (seq (str cube))))
          maybe-count (hsh digits)]
;      (prn cube maybe-count digits)
      (cond
      		(nil? maybe-count)
      		(recur (inc i) (assoc hsh digits 1))

      		(< maybe-count n)
      		(recur (inc i) (update hsh digits inc))

      		:else [cube, hsh]))))


; (p62 3)
; 8120601000
; 41063625
; 違う。(