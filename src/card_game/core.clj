(ns card-game.core
  [:require
   [card-game.card-deck :as deck]
   [card-game.hand :as hand]])

(def num-of-players 10)

(def deck (deck/new-deck))

(dotimes [_ 100] (deck/shuffle-deck! deck))

(def players (into [] (for [_ (range num-of-players)]
                        (deck/deal-hand! deck))))

(doall (map println players))

(println "trio")
(doall (map #(when (hand/trio? %) (println %)) players))

(println "pure sequence")
(doall (map #(when (hand/pure-sequence? %) (println %)) players))

(println "sequence")
(doall (map #(when (hand/sequence? %) (println %)) players))

(println "colour")
(doall   (map #(when (hand/colour? %) (println %)) players))

(println "pair")
(doall (map #(when (hand/pair? %) (println %)) players))

(println "higher hand")
(println (reduce hand/higher-hand players))
