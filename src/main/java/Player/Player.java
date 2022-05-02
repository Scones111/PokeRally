package Player;

import Elements.Robot;

public class Player{

    private Hand hand;
    private Robot robot;

    //constructor instantiates Hand
    //and ties a robot object to the player
    public Player(Robot robot) { // used
        this.robot = robot;
		hand = new Hand(5);
    }

    //plays the card on hand
    public String play() {
        return hand.play(robot);
    }

    //return a boolean on whether hand is empty
    public boolean emptyHand() {
        return hand.isEmpty();
    } // used


    //player calls method to fill the han
    public void fillHand(Deck deck) { // used
        hand.fill(deck);
    }


    //set order to false, used in testing
    public void setOrdered(boolean b) { // used
        hand.ordered(b);

    }

    //returns the titles of the cards on hand
    public String[] getTitles() {
        return hand.getTitles();
    }

    //returns the players hand
    public Hand getHand(){
        return hand;
    }

    //returns the players/robots score
    public int getScore() {
        return robot.getScore();
    }

    //returns the amount of correct checkpoints the robot has hit
    public int getCheckpoints() {
        return robot.getCheckCount();
    }

    //returns the robot
    public Robot getRobot() {
        return robot;
    }

}
