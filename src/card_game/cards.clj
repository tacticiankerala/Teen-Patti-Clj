(ns card-game.cards)


(declare value-less-than?)
(declare card-less-than?)


;;values and suits are sorted in the increasing order of their value

(def suits [:diamonds :spades :hearts :clubs])

(def values (concat (range 2 11) [:jack :queen :king :ace]))


(defn new-card
  [suit value]
  {:suit suit :value value})

(defn suit
  [card]
  (:suit card))

(defn value
  [card]
  (:value card))

(defn sort-cards
  [cards]
  (sort (comparator card-less-than?) cards))

(defn sort-values
  [vals]
  (sort (comparator value-less-than?) vals))


(defn possible-sequences
  [num-of-cards]
  (map (partial sort value-less-than?)
       (partition num-of-cards 1 (into (list :ace) values))))


(let [same? (fn [get-fn card more]
              (every? #(= (get-fn card) (get-fn %)) more))]
  (defn same-suit?
    [card & more]
    (same? suit card more))

  (defn same-value?
    [card & more]
    (same? value card more)))

(defn card-less-than?
  [card1 card2]
  (let [get-val-index #(.indexOf values (value %))
        get-suit-index #(.indexOf suits (suit %))]
    (if (same-value? card1 card2)
      (< (get-suit-index card1) (get-suit-index card2))
      (< (get-val-index card1) (get-val-index card2)))))

(defn- value-less-than?
  [value1 value2]
  (let [get-val-index #(.indexOf values %)]
    (< (get-val-index value1) (get-val-index value2))))
