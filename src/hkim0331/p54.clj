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

(defn to-number [[c s]]
 (case c
  \A [14 s]
  \K [13 s]
  \Q [12 s]
  \J [11 s]
  \T [10 s]
  [(- (int c) (int \0)) s]))


(def pokers
 (map (partial partition 5)
   (map (partial map to-number)
    (map (partial map seq)
     (map #(str/split % #"\s")
       (str/split (slurp "resources/p054_poker.txt") #"\n"))))))


(defn numbers [hand]
  (map first hand))

(defn max-number [hand]
  (apply max (numbers hand)))

(defn max2-number [hand])
  (sort (numbers hand))


(defn suits [hand]
  (map second hand))


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
  (apply min (numbers hand)))

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

;;
;; full house
;;

(defn full-house? [hand]
  (= 2 (count (group-by identity (numbers hand)))))

(full-house? [[1 \H] [1 \C] [1 \D] [2 \S] [2 \S]])
(full-house? [[1 \H] [1 \C] [2 \D] [2 \S] [3 \S]])

;;
;; three cards
;;

(defn three-cards? [hand]
  (some #(= 3 (count %))
    (vals (group-by identity (numbers hand)))))

(three-cards? [[1 \H] [1 \C] [2 \D] [2 \S] [1 \S]])
(three-cards? [[1 \H] [1 \C] [2 \D] [2 \S] [3 \S]])

;;
;; two pairs
;;

(defn two-pairs? [hand]
  (= 3 (count (vals (group-by identity (numbers hand))))))

(two-pairs? [[1 \H] [1 \C] [2 \D] [2 \S] [3 \S]])
(two-pairs? [[1 \H] [1 \C] [2 \D] [4 \S] [3 \S]])

;;
;; one pair?
;;
(defn one-pair? [hand]
  (= 4 (count (vals (group-by identity (numbers hand))))))

(one-pair? [[1 \H] [1 \C] [2 \D] [4 \S] [3 \S]])
(one-pair? [[1 \H] [5 \C] [2 \D] [4 \S] [3 \S]])

;;
;; high-card
;;

(defn high-card [hand]
  (apply max (numbers hand)))

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
  (* (pow 9) (royal-straight-flush hand))
  
  (straight-flush? hand) 
  (* (pow 8) (straight-flush hand))
  
  (four-cards? hand) 
  (* (pow 7) (four-cards hand))

  (full-house? hand)
  (* (pow 6) (full-house hand))
  
  (flush? hand) 
  (* (pow 5) (flush hand))

  (straight? hand)
  (* (pow 4) (straight hand))

  (three-cards? hand) 
  (* (pow 3) (three-cards hand))

  (two-pairs? hand)
  (* (pow 2) (two-pair hand))

  (one-pair? hand)
  (* (pow 1) (one-pair hand))

  :else (high-card hands)))