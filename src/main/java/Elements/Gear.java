package Elements;

public class Gear extends Obstacle{ // 100% coverage

    private boolean left;

    //turn gear left if true
    public void setLeft(boolean left) { // used
        this.left = left;
    }

    @Override
    //turns robot left or right
    public void move(Robot robot) { // used
        if(left) robot.turnL();
        else robot.turnR();
    }

    //return string, used in saving
    public String message() { // used
        return "Gear";
    }

    //decrement robots score
    public int effect(int score) {
        return -1;
    }

    //Instantiate the gear in when called in factory
    public Gear construct() { // not used
        return new Gear();
    }
}
