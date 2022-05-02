@tag
Feature: load information into game again

  @tag1
  Scenario: load all information
    Given all paths of game
    When information is used to resume game
    Then game is started again

  @tag2
  Scenario: test for no files
    Given no files
    When information is used to resume game
    Then game is not started again

  @tag3
  Scenario Outline: test for some files
    Given some files <files>
    When information is used to resume game
    Then game is not started again
    Examples:
      | files                                                       |
      | "hello.csv,4,2,1 "                                          |
      | "DataOfRobot.csv,3,3,2"                                     |
      | "DataOfRobot.csv,DataOfObstacle.csv,1,1"                    |
      | "DataOfRobot.csv,DataOfObstacle.csv,DataOfCheckPoint.csv,1" |
