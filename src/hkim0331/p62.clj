(ns hkim0331.p62)

; The cube, 41063625 (345^3), can be permuted to produce two other cubes: 56623104 (384^3) and 66430125 (405^3). In fact, 41063625 is the smallest cube which has exactly three permutations of its digits which are also cube.

; Find the smallest cube for which exactly five permutations of its digits are cube.

(defn char> [c1 c2]
  (> (int c1) (int c2)))

; first version.
;
; not returning key of the hash,
; must return lowest integer which produces the hash key(cubic number).
;
; (defn p62' [n h]
;   (loop [i 300 hsh h]
;     (let [cube (* i i i)
;           digits (apply str (sort char> (seq (str cube))))
;           maybe-count (hsh digits)]
;       (cond
;       		(nil? maybe-count)	(recur (inc i) (assoc hsh digits 1))
;         (= maybe-count (- n 1)) [i cube]
;       		:else	(recur (inc i) (update hsh digits inc))))))

; (p62' 3 {})
;; [405 66430125]
;; correct, but must return 41063625, first cube of the set.


(defn p62 [n hsh]
  (loop [i 300 h hsh]
    (let [cube (* i i i)
          digits (apply str (sort char> (seq (str cube))))]
      (if (= (- n 1) (count (h digits)))
        (conj (h digits) cube)
        (recur (inc i) (update h digits #(conj % cube)))))))

;(p62 3 {})
; (66430125 56623104 41063625)
; correct.

; (time (p62 5 {}))
; "Elapsed time: 27.587507 msecs"
; (589323567104 569310543872 373559126408 352045367981 127035954683))
