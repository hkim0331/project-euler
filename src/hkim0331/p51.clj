(ns hkim0331.p51
  (:require
    [clojure.math.combinatorics :as combo]
    [hkim0331.misc :refer [prime? primes]]))


;; By replacing the 1st digit of the 2-digit number *3, it turns out that six of the nine possible values: 13, 23, 43, 53, 73, and 83, are all prime.
;; By replacing the 3rd and 4th digits of 56**3 with the same digit, this 5-digit number is the first example having seven primes among the ten generated numbers, yielding the family: 56003, 56113, 56333, 56443, 56663, 56773, and 56993. Consequently 56003, being the first member of this family, is the smallest prime with this property.
;; Find the smallest prime which, by replacing part of the number (not necessarily adjacent digits) with the same digit, is part of an eight prime value family.

;; 一桁めはブランクにできない。
;; 0~9 の10通りのうち、出来上がりが素数になりそうなのが 1,3,7 しかない。
;; 素数を8つ作れない。
;;
;; 全ての桁の和が3の倍数になったら、その数は素数でないので、
;; ブランクの数は3の倍数じゃないといけない。
;;
;; 4 桁だったら ***1, ***3, ***7 の *** に 111~999 を入れて8つ素数になるものを探せばいい。
;; 次は 5 桁。****n の4つの位置から3つを選んで、000~999 入れて8つ素数になるものを探す。
;; その次は 6 桁。
;; その次は 7 桁。
;; この作戦で。

;; 4桁数チェック

(filter prime?
        (for [x [111 222 333 444 555 666 777 888 999]
              y [1 3 7]]
          (+ (* x 10) y)))


;; (1117 2221 3331 4441 4447 5557 6661 8887)

;; 全部で8つしか素数は見つからないし、
;; 見つかった最初の二つは異なる。
;; 4桁の素数ひとつから8つの素数を作ることはできない。

;; (combo/combinations [0 1 2 3] 3)
;; ((0 1 2) (0 1 3) (0 2 3) (1 2 3))

(defn replace-digit
  "文字列 s の位置 pos を n に変えた文字列を返す。"
  [s pos n]
  (str (subs s 0 pos) n (subs s (+ 1 pos))))


(defn rd-aux
  [s pos n]
  (if (empty? pos)
    s
    (recur (replace-digit s (first pos) n) (rest pos) n)))


(defn replace-digits
  "整数 num の複数の位置 positions を n に変えた整数を返す"
  [num positions n]
  (Integer. (rd-aux (str num) positions n)))


;; (replace-digits 1234567 [0 1 2] 6)
;; 6664567

(defn same-digits?
  "整数n とm は同じ桁数を持つ"
  [n m]
  (= (count (seq (str n))) (count (seq (str m)))))


(defn make-numbers
  "整数 num の複数の位置 positions を 0 ~ 9 で埋めた
  整数 10 個のコレクションを返す。
  ただしそれらの整数は num と同じ桁数を持つ。"
  [num positions]
  (filter (partial same-digits? num)
          (map (fn [n] (replace-digits num positions n))
               (range 10))))


(defn eight-primes?
  [num positions]
  (when (= 8 (count-primes num positions))
    num))


(defn count-primes
  [num positions]
  (count (filter prime? (make-numbers num positions))))


;; (make-numbers 12345 [0 1 2])
;; (45 11145 22245 33345 44445 55545 66645 77745 88845 99945)

(defn p51-5
  []
  (let [positions (combo/combinations [0 1 2 3] 3)
        numbers (take-while #(< % 100000)
                            (drop-while #(< % 10000) primes))]
    (first
      (for [n numbers p positions]
        (when (eight-primes? n p)
          (make-numbers n p))))))


;; (time (p51-5))
;; "Elapsed time: 0.562668 msecs"
;; nil

(defn p51-6
  []
  (let [positions (combo/combinations [0 1 2 3 4] 3)
        numbers (take-while #(< % 1000000)
                            (drop-while #(< % 100000) primes))]
    (first
      (drop-while nil?
                  (for [n numbers p positions]
                    (when (eight-primes? n p)
                      (make-numbers n p)))))))


;; (time (p51-6))
;; "Elapsed time: 628.631908 msecs"
;; (121313 222323 323333 424343 525353 626363 727373 828383 929393)
