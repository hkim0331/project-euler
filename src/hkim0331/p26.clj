(ns hkim0331.p26)


(defn- rc
  [n m ret]
  (let [q (quot n m) r (rem n m)]
    (if (= 1 r)
      (str ret q)
      (rc (* 10 r) m (str ret q)))))


(defn recurring-cycle
  [n]
  (rc 10 n ""))


;; (recurring-cycle 3)
;; ; (recurring-cycle 6)
;; (recurring-cycle 11)
;; (recurring-cycle 17)
;; (recurring-cycle 21)

(defn- max-length
  [a b]
  (if (> (count (a 1)) (count (b 1)))
    a
    b))


(defn p26
  [n]
  (reduce max-length
          (map
            (fn [x] [x (recurring-cycle x)])
            (filter
              #(and (not= 0 (rem % 2))
                    (not= 0 (rem % 5)))
              (range 2 n)))))


;; (time (p26 1000))
;; "Elapsed time: 64.078975 msecs"
;; [983 "0010172939979654120040691759918616480162767039674465920651068158697863682604272634791454730417090539165818921668362156663275686673448626653102746693794506612410986775178026449643947100712105798575788402848423194303153611393692777212614445574771108850457782299084435401831129196337741607324516785350966429298067141403865717192268565615462868769074262461851475076297049847405900305188199389623601220752797558494404883011190233977619532044760935910478128179043743641912512716174974567650050864699898270600203458799593082400813835198372329603255340793489318413021363173957273652085452695829094608341810783316378433367243133265513733468972533062054933875890132248219735503560528992878942014242115971515768056968463886063072227873855544252288911495422177009155645981688708036622583926754832146490335707019328585961342828077314343845371312309257375381485249237029501525940996948118006103763987792472024415055951169888097660223804679552390640895218718209562563580874872838250254323499491353"]

;; "Elapsed time: 30.993335 msecs"
