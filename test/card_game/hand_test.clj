(ns card-game.hand-test
  (:require [card-game.hand :refer :all]
            [card-game.cards :as cards]
            [clojure.test :refer :all]))

(deftest trio?-test
  (let [trio-hand [(cards/new-card :diamonds :king)
                   (cards/new-card :spades :king)
                   (cards/new-card :clubs :king)]
        non-trio-hand [(cards/new-card :diamonds :queen)
                       (cards/new-card :spades :king)
                       (cards/new-card :clubs :jack)]]
    (is (trio? trio-hand))
    (is (not (trio? non-trio-hand)))))

(deftest sequence?-test
  (let [sequence-hand1 [(cards/new-card :diamonds :king)
                        (cards/new-card :spades :queen)
                        (cards/new-card :clubs :ace)]
        sequence-hand2 [(cards/new-card :diamonds :ace)
                        (cards/new-card :spades 3)
                        (cards/new-card :clubs 2)]
        non-sequence-hand [(cards/new-card :diamonds 10)
                           (cards/new-card :spades :king)
                           (cards/new-card :clubs :jack)]]
    (is (sequence? sequence-hand1))
    (is (sequence? sequence-hand2))
    (is (not (sequence? non-sequence-hand)))))

(deftest pure-sequence?-test
  (let [pure-sequence-hand [(cards/new-card :diamonds :king)
                            (cards/new-card :diamonds :queen)
                            (cards/new-card :diamonds :ace)]
        non-pure-sequence-hand1 [(cards/new-card :diamonds 5)
                                 (cards/new-card :spades 6)
                                 (cards/new-card :clubs 7)]
        non-pure-sequence-hand2 [(cards/new-card :diamonds :queen)
                                 (cards/new-card :spades :king)
                                 (cards/new-card :clubs :jack)]]
    (is (pure-sequence? pure-sequence-hand))
    (is (not (pure-sequence? non-pure-sequence-hand1)))
    (is (not (pure-sequence? non-pure-sequence-hand2)))))

(deftest colour?-test
  (let [colour-hand [(cards/new-card :spades 10)
                     (cards/new-card :spades 6)
                     (cards/new-card :spades 2)]
        non-colour-hand [(cards/new-card :diamonds :queen)
                         (cards/new-card :spades :king)
                         (cards/new-card :clubs :jack)]]
    (is (colour? colour-hand))
    (is (not (colour? non-colour-hand)))))

(deftest pair?-test
  (let [pair-hand [(cards/new-card :diamonds :king)
                   (cards/new-card :spades :king)
                   (cards/new-card :clubs :queen)]
        non-pair-hand [(cards/new-card :diamonds :queen)
                       (cards/new-card :spades :king)
                       (cards/new-card :clubs :jack)]]
    (is (pair? pair-hand))
    (is (not (pair? non-pair-hand)))))

(deftest higher-hand-test
  (testing "higher rank has higher value"
    (let [hand1 [(cards/new-card :clubs 6)
                 (cards/new-card :spades 7)
                 (cards/new-card :clubs :king)]
          hand2 [(cards/new-card :diamonds 4)
                 (cards/new-card :spades 5)
                 (cards/new-card :spades :ace)]
          hand3 [(cards/new-card :diamonds 4)
                 (cards/new-card :spades 5)
                 (cards/new-card :clubs :ace)]]
      (is (= (higher-hand hand1 hand2) hand2))
      (is (= (higher-hand hand1 hand2 hand3) hand3))))
  (testing "same rank will check for suit"
    (let [hand1 [(cards/new-card :clubs 7)
                 (cards/new-card :spades 6)
                 (cards/new-card :clubs :ace)]
          hand2 [(cards/new-card :diamonds 7)
                 (cards/new-card :spades 6)
                 (cards/new-card :clubs :ace)]]
      (is (= (higher-hand hand1 hand2) hand1)))))
