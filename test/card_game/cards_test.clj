(ns card-game.cards-test
  (:require  [clojure.test :refer :all]
             [card-game.cards :refer :all]))

(deftest card-test
  (let [ace-of-spade (new-card :spades :ace)]
    (is (= (value ace-of-spade) :ace))
    (is (= (suit ace-of-spade) :spades))))

(deftest same-suit-test
  (let [ace-of-spade (new-card :spades :ace)
        king-of-spade (new-card :spades :king)
        five-of-spade (new-card :spades 5)
        ace-of-diamond (new-card :diamonds :ace)]
    (is (same-suit? ace-of-spade king-of-spade))
    (is (same-suit? ace-of-spade king-of-spade five-of-spade))
    (is (not (same-suit? ace-of-spade ace-of-diamond)))))

(deftest same-value-test
  (let [ace-of-spade (new-card :spades :ace)
        king-of-spade (new-card :spades :king)
        ace-of-club (new-card :clubs :ace)
        ace-of-diamond (new-card :diamonds :ace)]
    (is (same-value? ace-of-spade ace-of-diamond))
    (is (same-value? ace-of-spade ace-of-diamond ace-of-club))
    (is (not (same-value? ace-of-spade ace-of-diamond king-of-spade)))))


(deftest sort-cards-test
  (let [ace-of-spade (new-card :spades :ace)
        five-of-diamond (new-card :diamonds 5)
        five-of-club (new-card :clubs 5)
        king-of-club (new-card :clubs :king)]
    (is (= (sort-cards [ace-of-spade five-of-diamond king-of-club])
           [five-of-diamond king-of-club ace-of-spade]))
    (is (= (sort-cards [five-of-club ace-of-spade five-of-diamond])
           [five-of-diamond five-of-club ace-of-spade]))))

(deftest sort-values-test
  (is (= (sort-values [5 7 :king 2 :ace 10 :jack])
         [2 5 7 10 :jack :king :ace])))

(deftest card-less-than?-test
  (let [ace-of-spade (new-card :spades :ace)
        five-of-diamond (new-card :diamonds 5)
        ace-of-diamond (new-card :diamonds :ace)]
    (is (card-less-than? five-of-diamond ace-of-spade))
    (is (not (card-less-than? ace-of-spade five-of-diamond)))
    (is (card-less-than? ace-of-diamond ace-of-spade))))

(deftest possible-sequences-test
  (let [sequences (possible-sequences 3)]
    (is (= (first sequences) (list :queen :king :ace)))
    (is (= (first (next sequences)) (list :jack :queen :king)))
    (testing "special case where :ace, 2, 3 is a sequence"
      (is (= (last sequences) (list 2 3 :ace))))))
