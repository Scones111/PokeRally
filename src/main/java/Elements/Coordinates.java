package Elements;

public class Coordinates { // 100 % coverage
    private int[] coordinates;

    //constructor, instantiate new coordinates list
    public Coordinates() {
        coordinates = new int[2];
    } // used

    //constructor that takes input, and instantiate new coordinates with given input
    public Coordinates(int x, int y) {
        coordinates = new int[] {x,y};
    } // used

    //used to set new coordinates
    public void set(int x, int y){ // used
        coordinates[0] = x;
        coordinates[1] = y;
    }

    //returns the x coordinate
    public int getx() {
        return coordinates[0];
    } // used

    //returns the y coordinate
    public int gety() {
        return coordinates[1];
    } // used

    //method used in testing, returns the difference in x coordinates
    public int diffx(Coordinates c) {
        return (c.getx()-this.getx());
    } // used

    //method used in testing, returns the difference in y coordinates
    public int diffy(Coordinates c) {
        return (c.gety()-this.gety());
    } // used

    //returns the difference tiles? moved
    public int diff(Coordinates c) { // used
        int dx = Math.abs(this.getx()-c.getx());
        int dy = Math.abs(this.gety()-c.gety());
        return (int) Math.sqrt(dx*dx+dy*dy);
    }

    //used to check if the spot on board is empty
    public boolean equals(Coordinates c) {
        return (this.getx()==c.getx()&&this.gety()==c.gety());
    } // used

    //prints the coordinates used in testing
    public void print() {
        System.out.println("("+getx()+","+gety()+")");
    } // used

    //dont know, think this should be delete
    public void setRandom(int dimensions) { // used
        int x = (int) (dimensions*Math.random());
        int y = (int) (dimensions*Math.random());
        this.set(x, y);
    }

    //set the coordinate to move in the x direction
    public void movex(int move) {
        this.set(getx()+move, gety());
    }

    //set the coordinate to move in the y direction
    public void movey(int move) {
        this.set(getx(), gety()+move);
    }

}
