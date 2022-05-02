
@tag
Feature: Robot Scenarios

  @tag1
  Scenario: Initiate Robot
    Given a board
    And a name "Robby"
    When robot is initiated
    And the board is set up
    Then robot is on board

  @tag2
  Scenario: Move across the board without orientation
    Given a board with one robot
    And board set up with elements
    And initial coordinates
    When the robot moves
    Then robot changed position

  @tag3
  Scenario Outline: turning
    Given a board with one robot
    And board set up with elements
    And initial orientation
    When robot turns <direction>
    Then robot turned by <diff>

    Examples:
      | direction  	|	diff 	|
      | right 	  	| 3			|
      | left 		| 1			|

  @tag4
  Scenario Outline: Move across the board
    Given a board with one robot
    And board set up with elements
    And initial coordinates
    And the initial orientation is <orientation>
    When the robot moves
    Then robot changed position
    And move is correct

    Examples:
      | orientation |
      | 0			|
      | 1		 	|
      |	2			|
      |	3			|

  @tag5
  Scenario Outline: hit conveyer
    Given a board with one robot
    And board set up with elements
    And initial coordinates
    And the robot score is <score>
    And a conveyer
    When robot hits element
    Then robot changed position
    And score is decreased

    Examples:
      | score |
      |1      |
      |2      |
      |3      |
      |4      |
      |5      |
      |6      |
      |7      |
      |8      |


  @tag6
  Scenario: hit pit
    Given a board with one robot
    And board set up with elements
    And the robot score is 10
    And a pit
    When the robot moves
    And robot hits element
    Then robot returns to start
    And score is zero

  @tag7
  Scenario Outline: hit gear
    Given a board with one robot
    And board set up with elements
    And the initial orientation is <orientation>
    And initial coordinates
    And a gear that turns <direction>
    When robot hits element
    Then robot turned by <diff>

    Examples:
      | orientation |	direction  	|	diff 	|
      |	0			| right 	  	|   3   	|
      |	0			| left 			|	1		|
      |	1			| right 	  	|   3		|
      |	1			| left 			|	1		|
      |	2			| right 	  	|   3		|
      |	2			| left 			|	1		|
      |	3		    | right 	  	|   3		|
      |	3	        | left 			|	1		|

  @tag8
  Scenario Outline: hit another robot
    Given a board with one robot
    And board set up with elements
    And initial coordinates
    And robot score
    And the initial orientation is <orientation>
    And an opponent
    And opponent has coordinates <x1> and <y1>
    When the robot moves
    Then robot changed position
    Then robot moved in the opposite direction
    And score is decreased

    Examples:
      | orientation | x1 | y1 |
      | 0			| 4  | 3  |
      | 1		 	| 5  | 4  |
      |	2			| 4  | 5  |
      |	3			| 3  | 4  |

  @tag9
  Scenario Outline: Trampoline
    Given a board with one robot
    And board set up with elements
    And a trampoline
    And initial coordinates
    When robot hits element
    Then robot changed position
    And robot is at least <int> fields away

    Examples:
      |int|
      |3  |
      |3  |
      |3  |
      |3  |
      |3  |
      |3  |
      |3  |
      |3  |
      |3  |
      |3  |
      |3  |
      |3  |
      |3  |
      |3  |
      |3  |
      |3  |
      |3  |


  @tag10
  Scenario Outline: stay inside borders
    Given a board with one robot
    And board set up with elements
    And the initial orientation is <orientation>
    And robot at coordinates <x> <y>
    When robot tries to move
    Then robot didnt move

    Examples:
      | orientation |	x	|	y	|
      | 0			|	3	|	0	|
      | 1		 	|	7	|	3	|
      |	2			|	3	|	7	|
      |	3			|	0	|	3	|


  @tag11
  Scenario Outline: Robot hits Checkpoint on the board
    Given a board with one robot
    And board set up with elements
    And robots checkpoint counter <checkpoint counter>
    And checkpoint id is equal to counter
    And a checkpoint
    And robot score
    When the robot moves
    And robot hits element
    Then the score is incremented by 10
    And checkpoint counter is incremented
    Examples:
      | checkpoint counter |
      |0                   |
      |1                   |
      |2                   |

  @tag13
  Scenario: Robot hits same checkpoint
    Given a board with one robot
    And board set up with elements
    And robots checkpoint counter 2
    And checkpoint id is equal to counter minus one
    And a checkpoint
    And robot score
    When the robot moves
    And robot hits element
    Then the score is incremented by 5