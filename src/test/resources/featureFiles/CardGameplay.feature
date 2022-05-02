@tag
Feature: Card and Deck in the game

  @tag1
  Scenario: Add card to deck
    Given a deck
    And number of cards in deck
    And a card with a title "title"
    When card is added to deck
    Then deck size incremented
    And card is in deck

  @tag2
  Scenario: Draw card from deck
    Given a deck
    And three cards "title1" "title2" "title3" in deck
    When card is drawn from deck
    Then card is in deck


  @tag3
  Scenario: Add card to deck that already exists
    Given a deck
    And a card with a title "title"
    And card is added to deck
    And number of cards in deck
    And card is in deck
    When card is added to deck
    Then card is in deck
    And deck size is not incremented

  @tag4
  Scenario: Add action to card
    Given a card with a title "title"
    And an sequence of actions
    When add action
    Then action is added

  @tag5
  Scenario: card moves robot by one
    Given a board with one robot
    And board set up with elements
    And a card with a title "title"
    And the action move
    And action is added to card
    And add action
    And robot at coordinates 3 and 4
    When card is played
    Then robot moved
    And move is correct

  @tag6
  Scenario Outline: card turns robot
    Given a board with one robot
    And board set up with elements
    And orientation of the robot
    And a card with a title "title"
    And the action <action>
    And action is added to card
    And add action
    And robot at coordinates 3 and 4
    When card is played
    Then robot turned <orientation>

    Examples:
      | action| orientation |
      |turnL  | left        |
      |turnR  | right       |