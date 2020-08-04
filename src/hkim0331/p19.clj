(ns hkim0331.p19)

; How many Sundays fell on the first of the month during the
; twentieth century (1 Jan 1901 to 31 Dec 2000)?

(def plain [31 28 31 30 31 30 31 31 30 31 30 31])
(def leap  [31 29 31 30 31 30 31 31 30 31 30 31])

(defn leap? [y]
  (or (zero? (mod y 400))
      (and (zero? (mod y 4)) ((complement zero?) (mod y 100)))))

(def days (atom []))
(loop [year 1901]
  (when (< year 2001)
    (reset! days
            (conj @days (if (leap? year)
                            leap
                            plain)))
    (recur (+ 1 year))))

;; この acc, acc-aux は iterate で置き換えられるはず。
(defn- acc-aux [f ini a ret]
  (if (empty? a)
      ret
      (let [v (f ini (first a))]
        (acc-aux f v (rest a) (cons v ret)))))

(defn acc [f a]
  (acc-aux f 0 a []))

; (acc + [])
; (acc + [1])
; (acc + [1 2 3 4 5])

(defn p19 [ds]
  (count
    (filter #(= 5 (mod % 7))
            (acc + (flatten ds)))))

; (time (p19 @days))
; "Elapsed time: 7.840267 msecs"
; 171