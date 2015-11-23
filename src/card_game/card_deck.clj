(ns card-game.card-deck
  [:require [card-game.cards :as cards]])

(declare create-deck)

(defn new-deck
  []
  (ref (create-deck)))

(defn shuffle-deck!
  [deck]
  (dosync
   (alter deck shuffle)))

(defn deal-hand!
  [deck]
  (dosync
   (let [hand (into [] (take 3 @deck))]
     (alter deck (partial drop 3))
     hand)))

(defn- create-deck
  []
  (for [suit cards/suits
        value cards/values]
    (cards/new-card suit value)))
