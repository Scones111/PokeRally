package Elements;

import java.util.Random;

public class Trampoline extends Obstacle {

    @Override
    //decrements the score
    public int effect(int x) {
        return -3;
    }

    @Override
    //method used when robot hits trampoline, gives new coordinates at least 3 tiles away
    public void move(Robot robot) {
        Coordinates coordinates = new Coordinates(robot.getCoordinates().getx(), robot.getCoordinates().gety());
        coordinates.print();
        int rx;
        int ry;
        int sx = new Random().nextInt(1+1) - 1;
        if (sx == 0) {sx=1;}
        else {sx= -1;}
        int sy = new Random().nextInt(1+1) - 1;
        if (sy == 0) {sx=1;}
        else {sy= -1;}
        rx = (int) ((3 + 2 * Math.random())*sx + coordinates.getx());
        ry = (int) ((3 + 2 * Math.random())*sy + coordinates.gety());
        while (!(robot.possibleMove(rx, ry))) {
            sx = new Random().nextInt(1+1) - 1;
            if (sx == 0) {sx=1;}
            else {sx= -1;}
            sy = new Random().nextInt(1+1) - 1;
            if (sy == 0) {sx=1;}
            else {sy= -1;}
            rx = (int) ((3 + 2 * Math.random())*sx + coordinates.getx());
            ry = (int) ((3 + 2 * Math.random())*sy + coordinates.gety());
        }
        coordinates.set(rx, ry);
        robot.move(coordinates);
    }
    @Override
    //returns the name of the obstacle
    public String message() {
        return "Trampoline";
    }

    //method construct new obstacle object, used in ObstacleFactory
    public Trampoline construct() {
        return new Trampoline();
    }

}