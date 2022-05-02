package Gameplay;

import Board.Board;
import Player.Player;
import Elements.Element;
import Elements.Robot;
import Player.*;

import java.util.ArrayList;

public class Game {
	public ArrayList<Robot> robots = new ArrayList<Robot>();
	public ArrayList<Player> players = new ArrayList<Player>();
	private boolean multiplayer;
	private Board board;
	private Player player;
	private Deck deck;
	private int round;

	private String[] difficulties = {"EASY","MEDIUM","HARD"};

	//constructor instantiates Deck
	public Game(){
		System.out.println("Game initiated");
		round = 0;
		deck = new Deck();
		deck.defaultDeck();
	}

	//returns arrayList of the elements on the board
	public ArrayList<Element> getElements() {
		return board.getElementsOnBoard();
	}

	//check whether a robot has incremented its checkcounter to match number of checkpoints
	public boolean isFinished() {
		return robots.stream().anyMatch(o -> o.winner()==true);
	}

	//adds robots to arrayList robots
	public void addRobot(Robot robot) {
		robots.add(robot);
	}

	//sets boolean that determines if its multiplayer or singlePlayer
	public void setMultiplayer(boolean b) {
		multiplayer = b;
	}

	public String[] getDifficulties() {
		return difficulties;
	}

	//Returns the board, used in save game
	public Board getBoard() {
		return board;
	}

	//method sets the reference to the board to variable board
	public void setBoard(Board b) {
		board = b;
	}


	public int getBoardDimensions() {
		return board.getDimensions();
	}


	//determines the activePlayer, changes the reference for the correct player
	public Player activePlayer(){
		if(multiplayer) {
			return players.get(round % 2);
		}
		else {
			return players.get(0);
		}
	}

	//returns the string of cards, and fills the hand if empty
	public String[] getCards() {
		player = activePlayer();
		if(player.emptyHand()){
			player.fillHand(deck);
			return player.getTitles();
		}
		else{
			return player.getTitles();
		}
	}

	//adds the object player to ArrayList player
	public void addPlayer(Player player) {
		players.add(player);
	}

	//fills hand with random cards
	public void drawCards() {
		player = activePlayer();
		player.fillHand(deck);
	}

	//method finds the card associated with the string title and gives it a position at index i
	public int setCardToPosition(String title, int position) {
		player = activePlayer();
		Card c = player.getHand().findCard(title);
		if(c != null){
			player.getHand().setPosition(c,position);
			return 0;
		}
		else return 1;
	}

	//checks if the player has ordered their hand, used for testing
	public boolean isOrdered() {
		player = activePlayer();
		return player.getHand().isOrdered();
	}

	//player begins the process of playing a card by callid the method play() from player
	public String playCard() {
		player = activePlayer();
		return player.play();
	}

	//increments the round number
	public void nextRound() {
		round++;
	}

	public String getTurn() {
		return ""+(round%2 + 1);
	}

	//returns the score of the active player
	public int getActiveScore() {
		return activePlayer().getScore();
	}

	//returns the checkcounter for the given player
	public int getActiveCheckpoints() {
		return activePlayer().getCheckpoints();
	}

	//adds robots from player to ArrayList robots
	public void addRobots() {
		for(Player player : players){
			robots.add(player.getRobot());
		}
	}


	public String getWinner() {
		for(Robot r : robots){
			if(r.winner()){
				return r.getName();
			}
		}
		return null;
	}

	public int getRound() {
		return round;
	}


	//returns the active player, used for testing
	public Player getPlayer() {
		return activePlayer();
	}

	//returns the boolean of multiplayer
	public boolean getMultiplayer(){
		return multiplayer;
	}

	//return list of robots
	public ArrayList<Robot> getRobots() {
		return robots;
	}
}
