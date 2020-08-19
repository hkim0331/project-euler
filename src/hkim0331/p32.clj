(ns hkim0331.p32)

;;; under construction

; Find the sum of all products whose multiplicand/
; multiplier/product identity can be written as a 1
; through 9 pandigital.

; HINT: Some products can be obtained in more than one
;  way so be sure to only include it once in your sum.

;; あとは積が9桁になる整数 x y のペアの範囲だが。
;; ヒントをどうするか？

; x y とその積で pandigital となるか？
(defn pandigital-product? [[x y]]
  (let [sx (seq (str x))
        sy (seq (str y))
        sz (seq (str (* x y)))
        cc (concat sx sy sz)]
   (and
     (= 9 (count cc))
     (= #{\1 \2 \3 \4 \5 \6 \7 \8 \9} (into #{} cc)))))

; (pandigital? [39 186])
; (pandigital? [40 187])

; 述語は定義できた。あとは x,y の範囲。

; 3桁x3桁だと一番小さくても 100x100=10000で4桁。
; 3+3+4 = 10 でまずい。
; なので 2桁x3桁か、1桁x4桁を調べる。

(defn p32 []
  (let [l1 (filter pandigital-product?
              (for [x (range 10 100) y (range 100 1000)]
                [x y]))
        l2 (filter pandigital-product?
              (for [x (range 1 10) y (range 1000 10000)]
                [x y]))]
    (concat l1 l2)))

; (time
;     (reduce +
;         (into #{} (map (fn ([[a b]] (* a b))) (p32)))))
; "Elapsed time: 441.294927 msecs"
; 45228