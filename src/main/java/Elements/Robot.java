package Elements;

import Board.Board;

public class Robot extends Element {
    private String name;
    private int score;
    private Coordinates start;
    private int checkCount;
    private int orientation;
    private Board board;
    private String signature;

    //constructor, assign name string, and object board to the variables
    public Robot(String name, Board board) { // used
        this.name = name;
        this.board = board;
        orientation = 0;
        score = 0;
        checkCount = 0;
    }

    //method resets orientation, score and position of robot
    public void reset() { // used
        orientation = 0;
        score = 0;
        move(start);
    }

    //sets the start position for robot
    public void setStart(int x, int y) { // used
        start = new Coordinates(x,y);
        coordinates = new Coordinates(x,y);
    }

    //returns the start position, used in testing
    public Coordinates getStart() {
        return start;
    } // used

    //method checks if robot can move to the given coordinate
    boolean possibleMove(int x, int y) { // used
        int bound = board.getDimensions();
        return (x < bound && x >= 0 && y < bound && y >= 0);
    }

    //returns the next x coordinate
    public int next_x() { // used
        int x = coordinates.getx();
        return x-((orientation-2)%2);
    }

    //returns the next y coordinate
    public int next_y() { // used
        int y = coordinates.gety();
        return y+((orientation-1)%2);
    }

    // only the robot needs this version, because the other elements don't move along the board
    //method used to move the robot along the board, set new coordinate for the robot
    public void setCoordinates(int x, int y) {
        coordinates.set(x,y);
    } // used

    //returns the checkcount
    public int getCheckCount() {
        return checkCount;
    } // used

    //sets the checkcount, used for testing
    public void setCheckCount(int c) {
        checkCount = c;
    } // used

    //method used for when robot hits obstacle
    public String hit(Element e) { // used
        score += e.effect(score);
        e.move(this);
        return e.message();
    }

    //returns the score of the robot
    public int getScore() {
        return score;
    } // used

    //set the score of the robot
    public void setScore(int i) {
        score = i;
    } // used


    //method is the start sequence for moving the robot
    public boolean moved() {
        return !move(new Coordinates(next_x(),next_y()));
    }

    //effect when one robot hits another
    public int effect(int x) {
        return -2;
    } // not used

    //method for turning the robot
    public String turnL() { // used
        orientation = (orientation+3)%4;
        System.out.println(orientation);
        return "turned left!";
    }

    //method for turning the robot
    public String turnR() { // used
        orientation = (orientation+1)%4;

        return "turned right!";
    }

    //method for checking if the robot won
    public boolean winner() { // not used
        if(checkCount == board.getCheck()) return true;
        else return false;
    }

    //gets the orientation of the robot
    public int getOrientation() {
        return orientation;
    } // used

    //returns the signature for the robot and orientation
    public String message() {
        return signature +(orientation);
    } // used

    //sets the orientation for the robot
    public void setOrientation(int d) {
        orientation = d;
    } // used

    //returns the name of the robot
    public String getName() {
        return name;
    } // used

    @Override
    //function used for when robot hits another robot
    public void move(Robot robot) {
        Coordinates coordinates = new Coordinates(getCoordinates().getx(),getCoordinates().gety());
        for(int i = 0; i < 2;i++) {
            coordinates.movey((-(robot.getOrientation()-1)%2));
            coordinates.movex(((robot.getOrientation()-2)%2));
            robot.move(coordinates);
        }
    }

    //moves the robots and check if it hits anything
    public boolean move(Coordinates coordinates) { // used in robot scenario
        if(possibleMove(coordinates.getx(),coordinates.gety())) {
            if(!board.isEmpty(coordinates)) {
                Element e = board.getElement(coordinates);
                setCoordinates(coordinates);
                hit(e);
            } else {
                setCoordinates(coordinates);
            }
        }
        return(getCoordinates() == coordinates);
    }

    //sets the signature for the robot
    public void setSignature(String s) {
        signature = s;
    }
}
