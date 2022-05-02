package Elements;

public class Obstacle extends Element { // 100 %& used

    //overwritten
    public int effect(int score) {
        return -3;
    } // used

    //overwritten
    public String message() {
        return "obstacle";
    } // not used

    //overwritten
    public Obstacle construct() {
        return new Obstacle();
    } // not used
}

