package Gameplay;

import Board.Board;
import Elements.Robot;
import Board.*;
import Player.*;

import java.util.ArrayList;

public class GameStart {
	private int obstacles;
	private int checkpoints;
	private ArrayList<String> names;
	private Board board;
	private Game game;
	private ObstacleFactory factory;
	private int amountOfPlayers;
	private String hardness;

	//constructor GameStart, instantiates Game and Obstacle factory
	public GameStart() {
		board = Board.getBoard();
		game = new Game();
		factory = new ObstacleFactory();
		factory.defaultFill();
		names = new ArrayList<>();
	}

	//set the difficulty for the board, sets the amount of obstacles, checkpoint, and instantiate the obstacles
	public void setDifficulty(String difficulty) {
		switch(difficulty) {
			case ("EASY"):
				obstacles = (int) (6 + 6 * Math.random());
				checkpoints = (int) (3 + 2 * Math.random());
				hardness = "EASY";
				break;
			case ("MEDIUM"):
				obstacles = (int) (12 + 6 * Math.random());
				checkpoints = (int) (1 + 2 * Math.random());
				hardness = "MEDIUM";
				break;
			case ("HARD"):
				obstacles = (int) (18 + 6 * Math.random());
				checkpoints = 1;
				hardness = "HARD";
				break;
		}
	}

	public void setAmountOfPlayers(int amountOfPlayers) {
		this.amountOfPlayers = amountOfPlayers;
	} // used

	public String getDifficulty() { return hardness;}

	//starts the game, instantiates  robots and players
	//sets multiplayer true or false and then adds the player and robots
	//to the game class
	public Game start() {
		System.out.println("GameStart.start()...");
		if(amountOfPlayers == 1) {
			System.out.println("one player");
			Robot robot = new Robot(names.get(0),board);
			Player player = new Player(robot);
			robot.setSignature("Robot1_");
			board.setUp(robot, obstacles, checkpoints, factory);
			game.setMultiplayer(false);
			game.addPlayer(player);
			game.addRobots();

		}
		else if (amountOfPlayers == 2) {
			System.out.println("two players");
			Robot robot1 = new Robot(names.get(0),board);
			robot1.setSignature("Robot1_");
			Player player1 = new Player(robot1);
			Robot robot2 = new Robot(names.get(1),board);
			robot2.setSignature("Robot2_");
			Player player2 = new Player(robot2);
			board.setUp(robot1, robot2, obstacles, checkpoints, factory);
			game.setMultiplayer(true);
			game.addPlayer(player1);
			game.addPlayer(player2);
			//try to find a different way of accessing the robots (is it necessary to have an array of robots and
			// an array of players
			game.addRobots();

		}
		game.setBoard(board);
		return game;
	}

	//gets the board, used for testing
	public Board getBoard() {
		return board;
	} // not used

	//get amount of players
	public int getAmountOfPlayers() {
		return amountOfPlayers;
	} // used

	//used for adding names to robot
	public void addName(String name) {
		names.add(name);
	}

	//returns the list of String of the robots names
	public ArrayList<String> getRobotNames() {
		return names;
	}


	//
	public int getAmountofPlayers() {
		return amountOfPlayers;
	}
}