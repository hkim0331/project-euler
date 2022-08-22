(ns hkim0331.p59
  (:require
    [clojure.string :as str]))


;;
;; under construction
;;

;; Each character on a computer is assigned a unique code and the preferred standard is ASCII (American Standard Code for Information Interchange). For example, uppercase A = 65, asterisk (*) = 42, and lowercase k = 107.

;; A modern encryption method is to take a text file, convert the bytes to ASCII, then XOR each byte with a given value, taken from a secret key. The advantage with the XOR function is that using the same encryption key on the cipher text, restores the plain text; for example, 65 XOR 42 = 107, then 107 XOR 42 = 65.

;; For unbreakable encryption, the key is the same length as the plain text message, and the key is made up of random bytes. The user would keep the encrypted message and the encryption key in different locations, and without both "halves", it is impossible to decrypt the message.

;; Unfortunately, this method is impractical for most users, so the modified method is to use a password as a key. If the password is shorter than the message, which is likely, the key is repeated cyclically throughout the message. The balance for this method is using a sufficiently long password key for security, but short enough to be memorable.

;; Your task has been made easy, as the encryption key consists of three lower case characters. Using p059_cipher.txt (right click and 'Save Link/Target As...'), a file containing the encrypted ASCII codes, and the knowledge that the plain text must contain common English words, decrypt the message and find the sum of the ASCII values in the original text.


;; 準備
;; 普通の英文に含まれる文字の分布
;; 予想は 'e', 'a' が多い。

(defn cc-aux
  [cs m]
  (if (empty? cs)
    m
    (cc-aux (rest cs)
            (update m (first cs) inc))))


(defn sort-by-value
  [m]
  (into (sorted-map-by
          (fn [key1 key2]
            (compare (get m key2)
                     (get m key1))))
        m))


(defn char-counts
  [file]
  "How many characters can be found in the file?"
  (let [cs (seq (slurp file))
        ks (set cs)
        m  (zipmap ks (repeat 0))]
    (sort-by-value (cc-aux cs m))))


;; (char-counts "resources/p059.txt")
;; {\space 259,
;;  \e 157,
;;  \t 114,
;;  \a 87,
;;  \s 82,
;;  \n 73,
;;  \o 69,
;;  \i 68,
;;  \h 64, \c 44, \d 41, \l 32, \p 29, \y 28, \u 26, \g 23, \f 22, \k 20, \. 15, \b 12, \I 11, \newline 9, \A 8, \v 7, \S 6, \C 5, \O 4, \( 3, \" 2, \* 1))

;; 多いのは \space, \e, \t, \a ... の順。


;; 問題文を確認。
;; \A = 65
;; \* = 42
;; \k = 107

;; (int \A)
;; 65
;; (int \*)
;; 42
;; (int \k)
;; 107

;; (int \space)
;; 32
;; (int \e)
;; 101
;; (int \t)
;; 116

;; 出現回数の多い3文字のアスキーコードは、
;; \space = 32
;; \e = 101
;; \t = 116

;; 秘密キー(?)は英小文字3文字
;; \a...\z = 97 ... 122

;; (int \a)
;; 97
;; (int \z)
;; 122

;; XOR の性質

;; (bit-xor 65 42)
;; 107
;; (bit-xor 107 42)
;; 65


;; 問題文を読む。

(def cipher
  (as-> "resources/p059_cipher.txt" $
        (slurp $)
        (str/split $ #",")
        (map #(Integer. %) $)))


;; (take 10 cipher)
;; (36 22 80 0 0 4 23 25 19 17)

;; 出現頻度
;; cipher は長さ 3 のキーで xor されているから、
;; mod 3 で周期的に切り取った部分文字列中の文字出現頻度も
;; 一般の英文の出現頻度を反映するものになるはず。

;; misc 行き？
(defn transpose
  [col]
  (apply map list col))


(def cipher3
  (transpose (partition 3 cipher)))


(def c0 (first cipher3))
(def c1 (second cipher3))
(def c2 (nth cipher3 2))


(defn int-counts
  [col]
  "How many integers can be found in the list?"
  (let [ks (set col)
        m  (zipmap ks (repeat 0))]
    (sort-by-value (cc-aux col m))))


;; (int-counts c0)
;; {69 86, 0 62, 12 34, 17 32, 22 28, 10 24, 13 22, 4 21, 23 20, 16 16, 8 15, 11 13, 1 11, 3 10, 6 8, 21 7, 7 5, 81 4, 28 3, 20 2, 74 1}

;; (int-counts c1)
;; {88 77, 29 60, 12 31, 11 30, 10 28, 23 23, 17 22, 22 19, 25 17, 21 16, 30 14, 13 11, 27 10, 28 9, 20 8, 26 7, 1 5, 0 4, 77 3, 15 2, 74 1}

;; (int-counts c2)
;; {80 103, 21 42, 4 40, 2 29, 3 26, 25 25, 24 21, 17 19, 19 15, 28 13, 20 12, 0 9, 29 8, 70 7, 92 6, 65 5, 7 4, 1 3, 72 2, 62 1}


;;
;;  ここからは推理  ;;
;;


;; c0 の取り出しの上位3は 69, 0, 12
;; これらは \space(32), \e(101), \t(114)

;; (map (partial bit-xor 101) [69 0 12])
;; (32 101 105) => (\space \e \i)

;; k0 = \e に違いない。


;; c1 の上位3つは 88, 29, 12
;; (bit-xor 88 32)
;; 120
;; (bit-xor 29 101)
;; 120

;; ピンポン！120 が k1.
;; (char 120)
;; \x
;; k1 = \x


;; c2 の上位3つは 80, 21, 4
;; c1 と同様にやってみる。頻度分布がだいぶ違う点に注意。
;; (bit-xor 80 32)
;; 112
;; (bit-xor 21 101)
;; 112

;; よかった。
;; (char 112)
;; \p

;; 暗号化キーは、
;; [k0 k1 k2] = [e x p] = [101 120 112]

(def p0 (map #(char (bit-xor 101 %)) c0))
(def p1 (map #(char (bit-xor 120 %)) c1))
(def p2 (map #(char (bit-xor 112 %)) c2))

(def p (apply str (interleave p0 p1 p2)))
p
"An extract taken from the introduction of one of Euler's most celebrated papers, \"De summis serierum reciprocarum\" [On the sums of series of reciprocals]: I have recently found, quite unexpectedly, an elegant expression for the entire sum of this series 1 + 1/4 + 1/9 + 1/16 + etc., which depends on the quadrature of the circle, so that if the true sum of this series is obtained, from it at once the quadrature of the circle follows. Namely, I have found that the sum of this series is a sixth part of the square of the perimeter of the circle whose diameter is 1; or by putting the sum of this series equal to s, it has the ratio sqrt(6) multiplied by s to 1 of the perimeter to the diameter. I will soon show that the sum of this series to be approximately 1.644934066842264364; and from multiplying this number by six, and then taking the square root, the number 3.141592653589793238 is indeed produced, which expresses the perimeter of a circle whose diameter is 1. Following again the same steps by which I had arrived at this sum, I have discovered that the sum of the series 1 + 1/16 + 1/81 + 1/256 + 1/625 + etc. also depends on the quadrature of the circle. Namely, the sum of this multiplied by 90 gives the biquadrate (fourth power) of the circumference of the perimeter of a circle whose diameter is 1. And by similar reasoning I have likewise been able to determine the sums of the subsequent series in which the exponents are even numbers."


;; (time (reduce + (map int (interleave p0 p1 p2))))
;; "Elapsed time: 1.944121 msecs"
;; 129448
;; この time はまったく意味がない
