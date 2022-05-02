package Elements;

public class Pit extends Obstacle{ // 100 % used
    //if score is below 0 returns 0, if  positive reset score
    public int effect(int score) {
        if(score<0) return 0;// used
        return -score;
    }

    //returns string, used in SaveGame
    public String message() { // used
        return "Pit";
    } // used

    @Override
    //method resets robot
    public void move(Robot robot) { // used
        robot.reset();
    }

    @Override
    //Instantiate the gear in when called in factory
    public Pit construct() { // used
        return new Pit();
    } //

}
