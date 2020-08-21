(ns hkim0331.p59
  (:require [clojure.string :as str]))

;;;
;;; under construction
;;;

; Each character on a computer is assigned a unique code and the preferred standard is ASCII (American Standard Code for Information Interchange). For example, uppercase A = 65, asterisk (*) = 42, and lowercase k = 107.

; A modern encryption method is to take a text file, convert the bytes to ASCII, then XOR each byte with a given value, taken from a secret key. The advantage with the XOR function is that using the same encryption key on the cipher text, restores the plain text; for example, 65 XOR 42 = 107, then 107 XOR 42 = 65.

; For unbreakable encryption, the key is the same length as the plain text message, and the key is made up of random bytes. The user would keep the encrypted message and the encryption key in different locations, and without both "halves", it is impossible to decrypt the message.

; Unfortunately, this method is impractical for most users, so the modified method is to use a password as a key. If the password is shorter than the message, which is likely, the key is repeated cyclically throughout the message. The balance for this method is using a sufficiently long password key for security, but short enough to be memorable.

; Your task has been made easy, as the encryption key consists of three lower case characters. Using p059_cipher.txt (right click and 'Save Link/Target As...'), a file containing the encrypted ASCII codes, and the knowledge that the plain text must contain common English words, decrypt the message and find the sum of the ASCII values in the original text.

;;;
;;; preparation
;;;

(defn cc-aux [cs m]
  (if (empty? cs)
    m
    (cc-aux (rest cs)
            (update m (first cs) inc))))

(defn sort-by-value [m]
  (into (sorted-map-by
          (fn [key1 key2]
            (compare (get m key2)
                     (get m key1))))
        m))

(defn char-counts [file]
  "How many characters can be found in the file?"
  (let [cs (seq (slurp file))
        ks (set cs)
        m  (zipmap ks (repeat 0))]
     (sort-by-value (cc-aux cs m))))

;(char-counts "resources/p059.txt")
; {\space 259,
;  \e 157,
;  \t 114,
;  \a 87,
;  \s 82,
;  \n 73,
;  \o 69,
;  \i 68,
;  \h 64, \c 44, \d 41, \l 32, \p 29, \y 28, \u 26, \g 23, \f 22, \k 20, \. 15, \b 12, \I 11, \newline 9, \A 8, \v 7, \S 6, \C 5, \O 4, \( 3, \" 2, \* 1))

;; 多いのは スペース, e, t

;; ASCII codes

;(int \A)
;65
;(int \*)
;42
;(int \k)
;107
(int \space)
32
(int \e)
101
(int \t)
116

;; secret key's range
(int \a)
97
(int \z)
122

;; XOR

;(bit-xor 65 42)
;107
;(bit-xor 107 42)
;65
;;;

;; slurp は一本の文字列として読み込む。
;; ',' で区切り、
;; 整数に直す。(xor しやすいように。)

(def cipher
  (as-> "resources/p059_cipher.txt" $
    (slurp $)
    (str/split $ #",")
    (map #(Integer. %) $)))

;(take 10 cipher)
;(36 22 80 0 0 4 23 25 19 17)

;; frequencies

(defn int-counts [col]
  "How many integers can be found in the list?"
  (let [ks (set col)
        m  (zipmap ks (repeat 0))]
     (prn ks)
     (sort-by-value (cc-aux col m))))

(int-counts cipher)
; (int-counts cipher)
; {80 107,
;  69 86,
;  88 77,
;  0 75,
;  17 73,
;  29 70,
;  21 65, 4 61, 22 56, 10 52, 23 46, 11 43, 25 42, 16 38, 3 36, 13 33, 2 31, 30 26, 28 25, 31 24, 20 22, 24 21, 9 20, 1 19, 84 16, 5 15, 6 11, 27 10, 65 9, 83 8, 70 7, 92 6, 75 5, 86 4, 72 3, 74 2, 62 1

(for [x [32 101 116 92] y [80 69 88 0]]
  (bit-xor x y))
(112 101 120 32 53 32 61 101 36 49 44 116 12 25 4 92)

;キーは 92 101 112 120?

