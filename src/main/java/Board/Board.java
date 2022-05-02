package Board;

import Elements.*;

import java.util.ArrayList;
import java.util.Objects;

public class Board {
	private int numObstacles;
	private int numCheckpoints;
	private int numRobots;
	private int dimensions;
	private int counter = 0;
	private static Board board = null;

	private static ArrayList<Element> elements;

	public ArrayList<Element> getElementsOnBoard() {
		return elements;
	} //used

	public static Board getBoard() {
		if(board == null) {
			board = new Board();
		} else {
			elements = new ArrayList<>();

		}
		return board;
	}

	public Board() { // used
		dimensions = 8;
		elements = new ArrayList<Element>();
	}


	public int getDimensions() {
		return dimensions;
	} // used

	//overload for multiplayer
	//sets up the board with elements
	public void setUp(Robot robot, int obs, int check, ObstacleFactory factory) { // used
		numObstacles = obs;
		numCheckpoints = check;
		numRobots = 1;
		int center = (int) (dimensions / 2);

		//robot is handled
		robot.setStart(center, center);
		add(robot);

		//checkpoints are handled
		Checkpoint c;
		for(int i = 0; i < numCheckpoints; i++) {
			c = new Checkpoint(i);
			addRandom(c);
		}
		//obstacles are handled
		Obstacle o;
		for(int i = 0; i < numObstacles; i++){ // not used
			o = factory.pick();
			addRandom(o);
		}
	}

	public void setUp(Robot robot1, Robot robot2,  int obs, int check, ObstacleFactory factory) { // used
		numObstacles = obs;
		numCheckpoints = check;
		numRobots = 2;
		int center = (int) (dimensions / 2);

		//robot is handled
		robot1.setStart(0, 0);
		robot2.setStart(dimensions-1, dimensions-1);
		add(robot1);
		add(robot2);

		//checkpoints are handled
		Checkpoint c;
		for (int i = 0; i < numCheckpoints; i++) {
			c = new Checkpoint(i);
			addRandom(c);
		}
		//obstacles are handled
		Obstacle o;
		for(int i = 0; i < obs; i++){
			o = factory.pick();
			addRandom(o);
		}
	}

	//set number of checkpoints
	public void setCheck(int c){
		numCheckpoints = c;
	}

	//return number of cehckpoint, used in winner method
	public int getCheck() {
		return numCheckpoints;
	} // used

	//check if a coordinate exist at the coordinate
	public boolean isEmpty(Coordinates coordinates) { // used
		return !(elements.stream().anyMatch(o -> o.getCoordinates().equals(coordinates)));
	}

	//add the element with a coordinate to the elements arrayList
	public void add(Element e) { // used
		if (!elements.contains(e)) {
			counter++;
			elements.add(e);
		}
	}

	//give the input element a random coordinate
	public void addRandom(Element e) { // used
		int x = (int) (dimensions*Math.random());
		int y = (int) (dimensions*Math.random());
		Coordinates c = new Coordinates(x,y);
		while(!isEmpty(c)) {
			x = (int) (dimensions*Math.random());
			y = (int) (dimensions*Math.random());
			c.set(x,y);
		}
		e.setCoordinates(c);
		add(e);
	}

	//method checks if board contains element
	public boolean contains(Element e) {
		return elements.stream().anyMatch(o -> o.equals(e));
	} // used

	//method return amount of elements on board, used in saving
	public int getElements() {
		return elements.size();
	} // used in saving

	//gets the element for the coordinate, used to move robot
	public Element getElement(Coordinates c) { // used
		return (Objects.requireNonNull(elements.stream().filter(o -> o.getCoordinates().equals(c)).findFirst().orElse(null)));
	}
	//return number of robot
	public int getNumRobots() { //used for saving
		return numRobots;
	} // used in saving

	//return number of obstacle
	public int getNumObstacles() { //used for saving
		return numObstacles;
	} // used in saving

}