(ns card-game.hand
  [:require [card-game.cards :as cards]])


(defn trio?
  [hand]
  (apply cards/same-value? hand))

(defn sequence?
  [hand]
  (let [card-count (count hand)
        sorted-vals (cards/sort-values (map cards/value hand))]
    (some #(= sorted-vals %) (cards/possible-sequences card-count))))

(defn pure-sequence?
  [hand]
  (and (sequence? hand)
       (apply cards/same-suit? hand)))

(defn colour?
  [hand]
  (apply cards/same-suit? hand))

(defn pair?
  [hand]
  (let [sorted-vals (cards/sort-values (map cards/value hand))
        pairs (partition 2 1 sorted-vals)]
    (some #(apply = %1) pairs)))

(defn higher-hand
  ([hand1 hand2]
   (loop [sorted-hand1 (cards/sort-cards hand1)
          sorted-hand2 (cards/sort-cards hand2)]
     (let [card1 (last sorted-hand1)
           card2 (last sorted-hand2)]
       (cond
         (= card1 card2) (recur (butlast sorted-hand1) (butlast sorted-hand2))
         (cards/card-less-than? card1 card2) hand2
         :else hand1))))
  ([hand1 hand2 & more]
   (reduce higher-hand (into [hand1 hand2] more))))
