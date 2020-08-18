(ns hkim0331.p54
  (:require [clojure.string :as str]
            [hkim0331.misc :refer [power]]))

; In the card game poker, a hand consists of five cards and are ranked, from lowest to highest, in the following way:

; High Card: Highest value card.
; One Pair: Two cards of the same value.
; Two Pairs: Two different pairs.
; Three of a Kind: Three cards of the same value.
; Straight: All cards are consecutive values.
; Flush: All cards of the same suit.
; Full House: Three of a kind and a pair.
; Four of a Kind: Four cards of the same value.
; Straight Flush: All cards are consecutive values of same suit.
; Royal Flush: Ten, Jack, Queen, King, Ace, in same suit.
; The cards are valued in the order:
; 2, 3, 4, 5, 6, 7, 8, 9, 10, Jack, Queen, King, Ace.

; If two players have the same ranked hands then the rank made up of the highest value wins; for example, a pair of eights beats a pair of fives (see example 1 below). But if two ranks tie, for example, both players have a pair of queens, then highest cards in each hand are compared (see example 4 below); if the highest cards tie then the next highest cards are compared, and so on.

; Consider the following five hands dealt to two players:

; Hand     Player 1     Player 2     Winner
; 1     5H 5C 6S 7S KD
; Pair of Fives
;    2C 3S 8S 8D TD
; Pair of Eights
;    Player 2
; 2     5D 8C 9S JS AC
; Highest card Ace
;    2C 5C 7D 8S QH
; Highest card Queen
;    Player 1
; 3     2D 9C AS AH AC
; Three Aces
;    3D 6D 7D TD QD
; Flush with Diamonds
;    Player 2
; 4     4D 6S 9H QH QC
; Pair of Queens
; Highest card Nine
;    3D 6D 7H QD QS
; Pair of Queens
; Highest card Seven
;    Player 1
; 5     2H 2D 4C 4D 4S
; Full House
; With Three Fours
;    3C 3D 3S 9S 9D
; Full House
; with Three Threes
;    Player 1
; The file, poker.txt, contains one-thousand random hands dealt to two players. Each line of the file contains ten cards (separated by a single space): the first five are Player 1's cards and the last five are Player 2's cards. You can assume that all hands are valid (no invalid characters or repeated cards), each player's hand is in no specific order, and in each hand there is a clear winner.
; How many hands does Player 1 win?


;; ポリシー：ひたすら分類し、スコアを数える。

(defn to-number [[c s]]
 (case c
  \A [14 s]
  \K [13 s]
  \Q [12 s]
  \J [11 s]
  \T [10 s]
  [(- (int c) (int \0)) s]))

;; 8C TS KC 9H 4S 7D 2S 5D 3S AC を
;; (([8 \C] [10 \S] [13 \C] [9 \H] [4 \S])
;;  ([7 \D] [2 \S] [5 \D] [3 \S] [14 \C])]))
;; に変換して読み込む。

(def pokers
 (map (partial partition 5)
   (map (partial map to-number)
    (map (partial map seq)
     (map #(str/split % #"\s")
       (str/split (slurp "resources/p054_poker.txt") #"\n"))))))

(def p1 (first pokers))
(def p11 (first  p1))
(def p12 (second p1))

(def weights
 (iterate #(/ % 100) 1.0))

(take 5 weights)

(defn numbers [hand]
  (map first hand))

(defn suits [hand]
  (map second hand))

(defn max-number [hand]
  (apply max (numbers hand)))

(defn max2-numbers [hand]
  (reduce +
     (map * weights
           (sort > (numbers hand)))))

(def hand1 (ffirst pokers))
(def hand2 (second (first pokers)))

;;
;; straight
;;

(defn straight? [hand]
  (every? (partial = 1)
    (map #(- (second %) (first %))
      (partition 2 1 (sort (numbers hand))))))

(defn straight [hand]
  (apply max (numbers hand)))

(straight? [[1 \a] [2 \a] [3 \b] [4 \c] [5 \d]])
(straight  [[1 \a] [2 \a] [3 \b] [4 \c] [5 \d]])


;;
;; flush
;;

(defn flush? [hand]
 (apply = (map second hand)))

(defn flush [hand]
 (apply max (numbers hand)))

(flush? [[1 \a] [3 \a] [4 \a] [9 \a]])
(flush  [[1 \a] [3 \a] [4 \a] [9 \a]])

;;
;; straight flush
;;

(defn straight-flush? [hand]
  (and (straight? hand)
       (flush? hand)))

(defn straight-flush [hand]
 (apply max (numbers hand)))

(straight-flush? [[9 \H] [10 \H] [11 \H]  [12 \H] [8 \H]])
(straight-flush  [[9 \H] [10 \H] [11 \H]  [12 \H] [8 \H]])

;;
;;royal-straight-flush
;;
(defn royal-straight-flush? [hand]
 (and (straight-flush? hand)
      (= 10 (apply min (map first hand)))))

(defn royal-straight-flush [hand]
  (apply max (numbers hand)))

(royal-straight-flush? [[12 \H] [10 \H] [11 \H]  [13 \H] [14 \H]])
(royal-straight-flush? [[12 \H] [10 \H] [11 \H]  [13 \C] [14 \H]])
(royal-straight-flush  [[12 \H] [10 \H] [11 \H]  [13 \H] [14 \H]])

;;
;; four-cards

(defn four-cards? [hand]
  (some #(= 4 (count %))
    (vals (group-by identity (numbers hand)))))

(four-cards? [[1 \H] [1 \C] [1 \D] [2 \S] [1 \S]])
(four-cards? [[1 \H] [1 \C] [1 \D] [2 \S] [3 \S]])

(defn four-cards [hand]
  (let [vals (group-by identity (numbers hand))]
    (+ (ffirst vals)
       (* 0.01 (first (second vals))))))

(four-cards [[1 \H] [1 \C] [1 \D] [2 \S] [1 \S]])

;;
;; full house
;;

(defn full-house? [hand]
  (= 2 (count (group-by identity (numbers hand)))))

(full-house? [[1 \H] [1 \C] [1 \D] [2 \S] [2 \S]])
(full-house? [[1 \H] [1 \C] [2 \D] [2 \S] [3 \S]])

; bug.
; (defn full-house [hand]
;   (let [vals (group-by identity (numbers hand))]
;     (+ (ffirst vals)
;        (* 0.01 (first (second vals))))))

(defn full-house [hand]
  (let [{g1 c1 g2 c2} (group-by identity (numbers hand))]
    (prn g1 c1 g2 c2)))


(full-house [[1 \H] [1 \C] [2 \D] [2 \S] [2 \S]])

;;
;; three cards
;;

(defn three-cards? [hand]
  (some #(= 3 (count %))
    (vals (group-by identity (numbers hand)))))

(three-cards? [[1 \H] [1 \C] [2 \D] [2 \S] [1 \S]])
(three-cards? [[1 \H] [1 \C] [2 \D] [2 \S] [3 \S]])

(defn three-cards [hand]
  (let [vals (group-by identity (numbers hand))]
   (+ (ffirst vals)
      (reduce +
        (map * [0.01 0.0001]
               (map first (rest vals)))))))

(three-cards [[1 \H] [1 \C] [2 \D] [2 \S] [2 \S]])

;;
;; two pairs
;;

(defn two-pairs? [hand]
  (= 3 (count (vals (group-by identity (numbers hand))))))

(two-pairs? [[1 \H] [1 \C] [2 \D] [2 \S] [3 \S]])
(two-pairs? [[1 \H] [1 \C] [2 \D] [4 \S] [3 \S]])
(two-pairs? [[5 \C] [14 \D][5 \D] [14 \C][9 \C]])


(defn two-pairs [hand]
 (let [vals (group-by identity (numbers hand))]
   (+
     (reduce +
         (map * [1 0.001]
                (sort > (map first (take 2 vals)))))
     (* 0.00001 (first (last vals))))))

(two-pairs [[1 \H] [1 \C] [2 \D] [2 \S] [3 \S]])
(two-pairs [[5 \C] [14 \D][5 \D] [14 \C][9 \C]])
;;
;; one pair?
;;
(defn one-pair? [hand]
  (= 4 (count (vals (group-by identity (numbers hand))))))

(one-pair? [[1 \H] [1 \C] [2 \D] [4 \S] [3 \S]])
(one-pair? [[1 \H] [5 \C] [2 \D] [4 \S] [3 \S]])

(defn one-pair [hand]
  (let [vals (group-by identity (numbers hand))]
   (+ (ffirst vals)
      (reduce +
        (map * weights
               (sort > (map first (rest vals))))))))

(one-pair [[1 \H] [1 \C] [2 \D] [4 \S] [3 \S]])
;;
;; high-card
;;

(defn high-card [hand]
  (reduce +
    (map * weights
           (sort > (map first hand)))))

(high-card [[1 \H] [14 \C] [2 \D] [4 \S] [3 \S]])
(high-card [[1 \H] [13 \C] [2 \D] [4 \S] [3 \S]])

;;
;; score of a hand
;;

(defn pow [n]
  (power 10 n))

(defn score [hand]
 (cond
  (royal-straight-flush? hand)
  (* (pow 18) (max-number hand))

  (straight-flush? hand)
  (* (pow 16) (max-number hand))

  (four-cards? hand)
  (* (pow 14) (four-cards hand))

  (full-house? hand)
  (* (pow 12) (full-house hand))

  (flush? hand)
  (* (pow 10) (max-number hand))

  (straight? hand)
  (* (pow 8) (max-number hand))

  (three-cards? hand)
  (* (pow 6) (three-cards hand))

  (two-pairs? hand)
  (* (pow 4) (two-pairs hand))

  (one-pair? hand)
  (* (pow 2) (one-pair hand))

  :else (high-card hand)))

(time (count
        (filter true?
          (for [[p1 p2] pokers]
             (> (score p1) (score p2))))))
