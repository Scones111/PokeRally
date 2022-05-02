package Elements;

public class Conveyer extends Obstacle{

	//constructor
	public Conveyer(){
		super();
	}

	//overwritten method, return decrement to score
	public int effect(int score) {
		return -1;
	}

	@Override
	//method moves robot in random direction
	public void move(Robot robot) {
		Coordinates coordinates = new Coordinates(getCoordinates().getx(),getCoordinates().gety());
		int r = (int) (2*Math.random());
		if(r==0){
			for(int i = 0; i<3; i++) {
				coordinates.movex(1);
				robot.move(coordinates);
			}
		}
		else{
			for(int i = 0; i<3; i++) {
				coordinates.movey(1);
				robot.move(coordinates);
			}
		}
	}
	
	//returns a string used for saving the obstacle
	public String message() {
		return "Conveyor";
	}

	//used in ObstacleFactory to Instantiate new conveyor
	@Override
	public Conveyer construct() {
		return new Conveyer();
	}
	
}
