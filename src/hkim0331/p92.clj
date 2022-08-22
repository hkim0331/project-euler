(ns hkim0331.p92)


;; A number chain is created by continuously adding the square of the digits in a number to form a new number until it has been seen before.

;; For example,

;; 44 → 32 → 13 → 10 → 1 → 1
;; 85 → 89 → 145 → 42 → 20 → 4 → 16 → 37 → 58 → 89

;; Therefore any chain that arrives at 1 or 89 will become stuck in an endless loop. What is most amazing is that EVERY starting number will eventually arrive at 1 or 89.

;; How many starting numbers below ten million will arrive at 89?


(defn c->i
  "coarse char to integer"
  [c]
  (- (int c) (int \0)))


(defn p92-once
  "2回使うので、関数にしておく"
  [n]
  (reduce + (map #(* % %) (map c->i (seq (str n))))))


(defn p92-aux
  "引数 n が 1 または 89 になるまで p92 の操作を繰り返す。"
  [n]
  (loop [n n]
    (let [m (p92-once n)]
      ;;    (prn m)
      (case m
        1 0
        89 1
        (recur m)))))


;; (p92-aux 44)
;; 0
;; (p92-aux 85)
;; 1

;; 9,999,999-> 81+81+81+81+81+81=567 が変換後最大数。

(def p92-memo
  (memoize (fn [n] (p92-aux n))))


;; メモする。
(map p92-memo (range 1 568))


;; 行ってみよう。
(defn p92
  "n までのうちに 89 で止まるものはいくつか？"
  [n]
  (reduce +
          (map #(p92-memo (p92-once %)) (range 1 n))))


;; (time (p92 10000000))
;; "Elapsed time: 21076.890513 msecs"
;; 8581146
