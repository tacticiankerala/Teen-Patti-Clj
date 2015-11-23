(ns card-game.core
  [:require
   [card-game.card-deck :as deck]
   [card-game.hand :as hand]])

(def num-of-players 10)

(def deck (deck/new-deck))

(dotimes [_ 100] (deck/shuffle-deck! deck))

(def players (into [] (for [_ (range num-of-players)]
                        (deck/deal-hand! deck))))

(def tests [
            {:name "Trio" :test hand/trio?}
            {:name "Pure Sequence" :test hand/pure-sequence?}
            {:name "Sequence" :test hand/sequence?}
            {:name "Colour" :test hand/colour?}
            {:name "Pair" :test hand/pair?}])

(println "Players")
(doall (map println players))
(println "---------------------")

(doall (map (fn [{name :name test :test}]
              (println name)
              (doall (map #(when (test  %) (println %)) players))
              (println "---------------------")
              ) tests))


(println "higher hand")
(println (apply hand/higher-hand players))

(println "************************")
(println "and...the winner is...")
(println (apply hand/higher-hand (or
                                  (seq (filter hand/trio? players))
                                  (seq (filter hand/pure-sequence? players))
                                  (seq (filter hand/sequence? players))
                                  (seq (filter hand/colour? players))
                                  (seq (filter hand/pair? players))
                                  players)))
