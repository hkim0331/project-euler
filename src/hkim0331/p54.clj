(ns hkim0331.p54
  (:require
    [clojure.string :as str]
    [hkim0331.misc :refer [power]]))


;; In the card game poker, a hand consists of five cards and are ranked, from lowest to highest, in the following way:

;; High Card: Highest value card.
;; One Pair: Two cards of the same value.
;; Two Pairs: Two different pairs.
;; Three of a Kind: Three cards of the same value.
;; Straight: All cards are consecutive values.
;; Flush: All cards of the same suit.
;; Full House: Three of a kind and a pair.
;; Four of a Kind: Four cards of the same value.
;; Straight Flush: All cards are consecutive values of same suit.
;; Royal Flush: Ten, Jack, Queen, King, Ace, in same suit.
;; The cards are valued in the order:
;; 2, 3, 4, 5, 6, 7, 8, 9, 10, Jack, Queen, King, Ace.

;; If two players have the same ranked hands then the rank made up of the highest value wins; for example, a pair of eights beats a pair of fives (see example 1 below). But if two ranks tie, for example, both players have a pair of queens, then highest cards in each hand are compared (see example 4 below); if the highest cards tie then the next highest cards are compared, and so on.

;; Consider the following five hands dealt to two players:

;; Hand     Player 1     Player 2     Winner
;; 1     5H 5C 6S 7S KD
;; Pair of Fives
;;    2C 3S 8S 8D TD
;; Pair of Eights
;;    Player 2
;; 2     5D 8C 9S JS AC
;; Highest card Ace
;;    2C 5C 7D 8S QH
;; Highest card Queen
;;    Player 1
;; 3     2D 9C AS AH AC
;; Three Aces
;;    3D 6D 7D TD QD
;; Flush with Diamonds
;;    Player 2
;; 4     4D 6S 9H QH QC
;; Pair of Queens
;; Highest card Nine
;;    3D 6D 7H QD QS
;; Pair of Queens
;; Highest card Seven
;;    Player 1
;; 5     2H 2D 4C 4D 4S
;; Full House
;; With Three Fours
;;    3C 3D 3S 9S 9D
;; Full House
;; with Three Threes
;;    Player 1
;; The file, poker.txt, contains one-thousand random hands dealt to two players. Each line of the file contains ten cards (separated by a single space): the first five are Player 1's cards and the last five are Player 2's cards. You can assume that all hands are valid (no invalid characters or repeated cards), each player's hand is in no specific order, and in each hand there is a clear winner.
;; How many hands does Player 1 win?


;; ポリシー：ひたすら分類し、スコアを数える。

(defn to-number
  [[c s]]
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


(defn numbers
  [hand]
  (map first hand))


(defn suits
  [hand]
  (map second hand))


;;
;; straight
;;

(defn straight
  [hand]
  (let [ordered (sort (numbers hand))]
    (when (every? (partial = 1)
                  (map #(- (second %) (first %))
                       (partition 2 1 ordered)))
      (last ordered))))


;; (straight [[6 \a] [2 \a] [3 \b] [4 \c] [5 \d]])
;; (score [[6 \a] [2 \a] [3 \b] [4 \c] [5 \d]])

;; (score [[7 \a] [2 \a] [3 \b] [4 \c] [5 \d]])


;;
;; flush
;;

(defn flush
  [hand]
  (when (apply = (map second hand))
    (apply max (numbers hand))))


;; (flush [[1 \a] [3 \a] [4 \a] [9 \a]])
;; (flush [[1 \a] [3 \a] [4 \b] [9 \a]])
;; (flush [[6 \H] [2 \H] [8 \H] [13 \H] [4 \H]])
;; (score [[6 \H] [2 \H] [8 \H] [13 \H] [4 \H]])
;;
;; straight flush
;;

(defn straight-flush
  [hand]
  (when (and (straight? hand)
             (flush? hand))
    (apply max (numbers hand))))


;; (straight-flush [[9 \H] [10 \H] [11 \H]  [12 \H] [8 \H]])
;; (straight-flush [[9 \H] [10 \H] [11 \H]  [12 \H] [8 \W]])

;;
;; royal-straight-flush
;;
(defn royal-straight-flush
  [hand]
  (when (and (straight-flush? hand)
             (= 10 (apply min (map first hand))))
    (apply max (numbers hand))))


;; (royal-straight-flush [[12 \H] [10 \H] [11 \H]  [13 \H] [14 \H]])
;; (royal-straight-flush [[12 \H] [10 \H] [11 \H]  [13 \C] [14 \H]])

;;
;; four-cards
;;

(defn four-cards
  [hand]
  (let [gs (group-by identity (numbers hand))
        vs (vals gs)]
    (when (some #(= 4 (count %)) vs)
      (let [[v0 v1] vs]
        (if (= 1 (count v0))
          (+ (first v1) (/ (first v0) 100.))
          (+ (first v0) (/ (first v1) 100.)))))))


;; (four-cards [[1 \H] [1 \C] [1 \D] [2 \S] [1 \S]])
;; (four-cards [[1 \H] [1 \C] [1 \D] [2 \S] [3 \S]])
;; (four-cards [[2 \H] [1 \C] [2 \D] [2 \S] [2 \S]])

;;
;; full house
;;

(defn full-house
  [hand]
  (let [gs (group-by identity (numbers hand))]
    (when (= 2 (count gs))
      (let [[v0 v1] (vals gs)]
        (if (= 2 (count v0))
          (+ (first v1) (/ (first v0) 100.))
          (+ (first v0) (/ (first v1) 100.)))))))


;; (full-house [[1 \H] [1 \C] [1 \D] [2 \S] [2 \S]])
;; (full-house [[1 \H] [1 \C] [2 \D] [2 \S] [2 \S]])
;; (full-house [[1 \H] [1 \C] [3 \D] [2 \S] [2 \S]])

;;
;; three cards
;;

(defn three-cards
  [hand]
  (let [gs (group-by identity (numbers hand))]
    (when (some #(= 3 (count %)) (vals gs))
      (let [[k0 k1 k2] (keys gs)]
        (cond
          (= 3 (count (gs k0)))
          (+ k0 (/ (max k1 k2) 100.) (/ (min k1 k2) 10000.))
          (= 3 (count (gs k1)))
          (+ k1 (/ (max k2 k0) 100.) (/ (min k2 k0) 10000.))
          :else
          (+ k2 (/ (max k0 k1) 100.) (/ (min k0 k1) 10000.)))))))


;; (three-cards [[1 \H] [1 \C] [2 \D] [3 \S] [1 \S]])
;; (three-cards [[1 \H] [1 \C] [2 \D] [2 \S] [3 \S]])
;; (three-cards [[14 \H] [1 \C] [2 \D] [2 \S] [2 \S]])

;;
;; two pairs
;;

;; bug
(defn two-pairs
  [hand]
  (let [gs (group-by identity (numbers hand))]
    ;;    (prn gs)
    (when (= 3 (count gs))
      (let [[k0 k1 k2] (keys gs)]
        (cond
          (= 1 (count (gs k0)))
          (+ (max k1 k2) (/ (min k1 k2) 100.) (/ k0 10000.))
          (= 1 (count (gs k1)))
          (+ (max k2 k0) (/ (min k2 k0) 100.) (/ k1 10000.))
          :else
          (+ (max k0 k1) (/ (min k0 k1) 100.) (/ k2 10000.)))))))


;; (two-pairs [[1 \H] [1 \C] [2 \D] [2 \S] [3 \S]])
;; (two-pairs [[1 \H] [1 \C] [2 \D] [4 \S] [3 \S]])
;; (two-pairs [[5 \C] [14 \D][5 \D] [14 \C][9 \C]])

;; (two-pairs [[7 \H] [2 \C] [3 \D] [4 \S] [5 \S]])
;; (score [[7 \H] [2 \C] [3 \D] [4 \S] [5 \S]])

;;
;; one pair
;;

(defn one-pair
  [hand]
  (let [gs (group-by identity (numbers hand))]
    (when (= 4 (count gs))
      (let [temp (sort > (keys gs))
            k (first (filter #(= 2 (count (gs %))) temp))
            ks (filter (partial = k) temp)]
        (reduce + k (map / ks (iterate (partial * 100) 100.)))))))


;; (one-pair [[1 \H] [1 \C] [2 \D] [4 \S] [3 \S]])
;; (one-pair [[1 \H] [5 \C] [2 \D] [4 \S] [3 \S]])

;;
;; high-card
;;

(defn high-card
  [hand]
  (let [nums (sort > (numbers hand))]
    ;;    (prn nums)
    (reduce +
            (map /
                 (sort > (numbers hand))
                 (iterate (partial * 100) 1.)))))


;; (high-card [[1 \H] [14 \C] [2 \D] [4 \S] [3 \S]])
;; (high-card [[1 \H] [13 \C] [2 \D] [4 \S] [3 \S]])
;; (high-card [[7 \H] [2 \C] [3 \D] [4 \S] [5 \S]])

;;
;; score of a hand
;;

(defn pow
  [n]
  (power 10 n))


(defn score
  [hand]
  (if-let [p (royal-straight-flush hand)]
    (* (pow 18) p)
    (if-let [p (straight-flush hand)]
      (* (pow 16) p)
      (if-let [p (four-cards hand)]
        (* (pow 14) p)
        (if-let [p (full-house hand)]
          (* (pow 12) p)
          (if-let [p (flush hand)]
            (* (pow 10) p)
            (if-let [p (straight hand)]
              (* (pow 8) p)
              (if-let [p (three-cards hand)]
                (* (pow 6) p)
                (if-let [p (two-pairs hand)]
                  (* (pow 4) p)
                  (if-let [p (one-pair hand)]
                    (* (pow 2) p)
                    (high-card hand)))))))))))


;; (score [[1 \H] [14 \C] [2 \D] [4 \S] [3 \S]])
;; (score [[7 \H] [2 \C] [3 \D] [4 \S] [5 \S]])


;; (time
;;   (count
;;     (filter true?
;;       (for [[p1 p2] pokers]
;;          (> (score p1) (score p2))))))
;; "Elapsed time: 132.758154 msecs"
;; 376
