package Elements;

public class Checkpoint extends Element{

    //constructor, assigns and integer to ID for checkpoint
    public Checkpoint(int id) {
        ID = id;
    }

    //method returns zero
    public int effect(int score) {
        return 0;
    }

    @Override
    //method check if robot has the checkcount needed to get points
    public void move(Robot robot){
        if (robot.getCheckCount() == ID) {
            robot.setScore(robot.getScore()+10);
            robot.setCheckCount(robot.getCheckCount()+1);
        }
        else{
            robot.setScore(robot.getScore()+5);
        }
    }

    //method returns String, used for saving
    public String message() {
        return "Checkpoint" + (ID);
    }
}
