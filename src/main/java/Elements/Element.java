package Elements;

import Board.Board;

abstract public class Element {
    protected Coordinates coordinates = new Coordinates(); // used
    protected int index;
    protected Board board;
    protected int ID;
    protected boolean visited;

    //constructor
    public Element() {}; // used

    //set the coordinates for elements
    public void setCoordinates(int x, int y) {
        coordinates.set(x,y);
    } // used

    //set the coordinates for elements
    public void setCoordinates(Coordinates c) {
        coordinates = c;
    } // used

    public abstract String message();

    public Coordinates getCoordinates() {
        return coordinates;
    } // used

    //abstract used for in used in Element sub-classes
    public abstract int effect(int x);

    //used in element sub-classes
    public void move(Robot robot) {}

    //used to return the ID for checkpoints
    public int getID(){
        return ID;
    } // used for saving

    //used to set gear to turn left or right
    public void setLeft(boolean left) {}


}