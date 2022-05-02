@tag
Feature: Starting the Game

  @tag1
  Scenario Outline: Select game mode
    Given the game difficulty is set <difficulty>
    And the names are set <name1> <name2>
    And amount of players is <players>
    When the game starts
    Then automaton returns difficulty <difficulty>
    And automaton create <numRobots>
    And board is not null
    And robot names are <name1> <name2>

    Examples:
      | difficulty | players | numRobots | name1 | name2  |
      | "EASY"     | 1       |  one robot|"bo"   | "--"   |
      | "MEDIUM"   | 2       | two robots|"ib"   | "peter"|
      | "HARD"     | 1       | one robot |"kaj"  | "--"   |
