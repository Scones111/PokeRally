@tag
Feature: Gameplay

  @tag1
  Scenario Outline: when a player wins the game
    Given a board with <robots>
    And robots checkpoint counter <counter>
    And add checkpoint with <checkpoints> checkID
    And board set up with elements
    And a player with the robot
    And game is initiated
    When robot hits last checkpoint
    Then game is <status>

    Examples:
      | counter | checkpoints | robots    | status |
      | 1       | 2           | one robot | won    |
      | 1       | 4           | one robot | not won|
      | 0       | 1           | one robot | won    |

  @tag2
  Scenario Outline: player gets cards twice in gameplay
    Given a factory with obstacles
    And a board with <robots>
    And <numObstacles> obstacles
    And <numCheckpoints> checkpoints
    When board set up with elements
    And game is started for <mode>
    And game is played and cards are given twice
    Then checkpoint counter is not changed

    Examples:
      | robots	 |	numObstacles	|	numCheckpoints	| mode        |
      | one robot  |  	5   		|		0			| singleplayer |
