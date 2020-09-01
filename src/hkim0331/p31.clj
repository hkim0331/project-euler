(ns hkim0331.p31)

; There are eight coins in general circulation:
;
; 1p, 2p, 5p, 10p, 20p, 50p, £1 (100p), and £2 (200p).
;
; How many different ways can £2 be made using any number of coins?

; 組み合わせの問題。L99 の combination の応用。


(def coins [1 2 5 10 20 50 100 200])

(defn changes [amount coins]
  (cond
    (empty? coins) 0
    (< amount 0) 0
    (= 0 amount) 1
    :else (+
            (changes (- amount (first coins)) coins)
            (changes amount (rest coins)))))

; (time (changes 200 coins))
; "Elapsed time: 541.916607 msecs"
;73682

; コインの並び順を逆にすると4倍速くなる。
; (time (changes 200 (reverse coins)))
; "Elapsed time: 148.58423 msecs"
; 73682

; 無理やり、解を表示する。
; atom, swap!, list を使うあたり、方法的にはイケてない。
; (defn changes-aux [amount coins ret]
;   (cond
;     (empty? coins) []
;     (< amount 0) []
;     (= 0 amount) (swap! answer conj ret)
;     :else (list
;             (changes-aux (- amount (first coins))
;                          coins
;                          (conj ret (first coins)))
;             (changes-aux amount (rest coins) ret))))

; (defn p31-coins [amount coins]
;   (changes-aux amount coins [])
;   @answer)

; ;; やめとこう。
; ; (def answer (atom []))
; ; (p31-coins 80 coins)
