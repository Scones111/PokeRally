package Player;

import Elements.Robot;

import java.util.Arrays;
import java.util.List;

public class Card {

    //A list of all cards available
    static public enum action { // used
        move, turnL, turnR;

        @Override
        //returns the string describe the move
        public String toString() { // used
            switch (this) {
                case move:
                    return "move";
                case turnL:
                    return "turnL"; // not used
                default:
                    return "turnR"; // not used
            }
        }
    }

    private List<action> actions;

    private String title;


    //constructor, input is used as title for the card
    public Card(String title) {
        this.title = title;
    } // used

    //returns the cards title
    public String getTitle() {
        return title;
    } // used

    //Used to add the List of actions the card makes the robot do
    public void setAction(List<action> actions) {
        this.actions = actions;
    } // used

    //when player plays a card, the robot will move accordingly to the crds instruction
    public void play(Robot robot) { // used
        for (action a : actions) {
            if( a == action.move){
                robot.moved();
            } else if( a == action.turnR){
                robot.turnR();
            } else if ( a == action.turnL) {
                robot.turnL();
            }
        }
    }

    //prints the actions the card does
    public String printActions() { // used
        String str = Arrays.toString(actions.toArray());
        System.out.println(Arrays.toString(actions.toArray()));
        return str;
    }
}

