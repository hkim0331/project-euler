(ns hkim0331.rk5)

; 0 ~ 9 の乱数を 100 万個発生させ、それを集合に変換する make-set

; racket では 55msec でやれる(mbp2)。
; (time (make-set data))
; cpu time: 55 real time: 55 gc time: 0
; '(3 4 9 1 7 0 5 8 2 6)

; 同じのを clojure で、同じ PC で実行する。


(defn rk5 [n]
 (set (take n (repeatedly (fn [] (int (rand 10)))))))

; (time (rk5 1000000))
; "Elapsed time: 387.68838 msecs"
; #{0 7 1 4 6 3 2 9 5 8}

; 残念、10 倍近く遅いやんけ。
; しかし、rkt のは 100 万個のリストを作るのを時間に入れてない。

(def rands
  (doall (take 1000000
          (repeatedly (fn [] (int (rand 10)))))))

; こうしても3倍近くかかる。racket は速い。
; (time (set rands))
; "Elapsed time: 147.020301 msecs"
; #{0 7 1 4 6 3 2 9 5 8}