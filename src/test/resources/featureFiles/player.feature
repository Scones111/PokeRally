@tag
Feature: Player and Hand

  @tag1
  Scenario: Empty hand
    Given a hand of cards with capacity 5
    When hand is emptied
    Then hand is empty

  @tag2
  Scenario: Draw card to hand
    Given a hand of cards with capacity 5
    And a deck
    And a card with a title and an action
    And card is added to deck
    And space on hand
    And number of free spaces
    When card is drawn
    Then card is on hand
    And number of free spaces decrease by one

  @tag3
  Scenario: Draw multiple cards to hands
    Given a hand of cards with capacity 2
    And a deck
    And a card with a title and an action
    And card is added to deck
    And space on hand
    And number of free spaces
    When card is drawn
    And card is drawn
    Then card is on hand
    And number of free spaces decrease

  @tag4
  Scenario: Draw cards to empty hand until full
    Given a hand of cards with capacity 5
    And hand is emptied
    And a deck of cards
    When hand is filled
    Then hand is full

  @tag5
  Scenario: Draw cards to hand until full
    Given a hand of cards with capacity 5
    And a card with a title and an action
    And a deck of cards
    When hand is filled
    Then hand is full

  @tag6
  Scenario: player plays cards
    Given a board with one robot
    And board set up with elements
    And a player with the robot
    And a deck of cards
    And player fills hand
    And cards are not ordered
    And initial coordinates
    When cards on hand are ordered
    And player plays hand
    Then robot moved
    And players hand is empty

  @tag7
  Scenario: player order cards twice
    Given a board with one robot
    And board set up with elements
    And a player with the robot
    And a deck of cards
    And player fills hand and orders twice
    And cards are not ordered
    And initial coordinates
    When cards on hand are ordered
    And player plays hand
    Then robot moved
    And players hand is empty

  @tag7
  Scenario: player order more cards than they have
    Given a board with one robot
    And board set up with elements
    And a player with the robot
    And a deck of cards
    And player fills hand and try to order out of bounds
    And cards are not ordered
    And initial coordinates
    When cards on hand are ordered
    And player plays hand
    Then robot moved
    And players hand is empty