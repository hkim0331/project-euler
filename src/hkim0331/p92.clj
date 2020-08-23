(ns hkim0331.p92)

; A number chain is created by continuously adding the square of the digits in a number to form a new number until it has been seen before.

; For example,

; 44 → 32 → 13 → 10 → 1 → 1
; 85 → 89 → 145 → 42 → 20 → 4 → 16 → 37 → 58 → 89

; Therefore any chain that arrives at 1 or 89 will become stuck in an endless loop. What is most amazing is that EVERY starting number will eventually arrive at 1 or 89.

; How many starting numbers below ten million will arrive at 89?


; misc?
(defn c->i
  "coarse char to i"
  [c]
  (- (int c) (int \0)))

(defn h89-new?
 "if n arrives 89, return true"
 [n]
; (prn n)
 (cond
  (= 89 n) true
  (= 1 n) false
  :else
  (let [digits (map c->i (seq (str n)))]
    (recur (reduce + (map #(* % %) digits))))))

;(h89-new? 100)

(defn nc
 "repeat adding the square of the digits
  until it has been seen before."
 [n]
 (cond
   (h89 n) true
   (h01 n) false
   (h89-new? n) (do
                  (assoc! h89 n true)
                  true)
   :else (do
           (assoc! h01 n true)
           false)))

(def h89 (transient {}))
(def h01 (transient {}))

(defn p92 [n]
		(let [c 0]
		  (loop [i 1]
						(when (< i n)
				    (when (nc i)
			    		(inc c)))
				(recur (inc i)))
    [c (count h89) (count h01)]))

(time (p92 1000))


