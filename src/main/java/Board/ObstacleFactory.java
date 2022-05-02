package Board;

import Elements.*;

import java.util.ArrayList;

public class ObstacleFactory {
	private ArrayList<Obstacle> obstacles;
	//constructor instantiate new Arraylist
	public ObstacleFactory() { // used
		obstacles = new ArrayList<>();
	}

	//method return random obstacle arraylist
	public Obstacle pick() {
		if(!(obstacles.isEmpty())) { // used
			int rand = (int) (obstacles.size() *  Math.random());
			System.out.println(rand);
			return obstacles.get(rand).construct();
		}
		else return null; // not used
	}

	//method used to add obstacle to arralist
	public void add(Obstacle e) {
		obstacles.add(e);
	} // used


	//method return true if it contains an element, used in testing
	public boolean contains(Obstacle e) {
		return obstacles.contains(e);
	} // used

	//method instantiates and add objects to ArrayList
	public void defaultFill() { // used
		obstacles.add(new Pit());
		obstacles.add(new Gear());
		obstacles.add(new Conveyer());
		obstacles.add(new Trampoline());
	}

}