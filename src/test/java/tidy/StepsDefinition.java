package tidy;

import Board.Board;
import Elements.Robot;
import Elements.Coordinates;
import Elements.Element;
import Elements.Conveyer;
import Elements.Pit;
import Elements.Checkpoint;
import Elements.Gear;
import LoadSave.SaveGame;
import LoadSave.LoadGame;
import Gameplay.GameStart;
import Player.Hand;
import Elements.Trampoline;
import Player.Deck;
import Player.Card;
import Gameplay.Game;
import Elements.Obstacle;
import Player.Player;
import Board.ObstacleFactory;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.io.FileNotFoundException;
import java.util.*;

import static org.junit.Assert.*;

public class StepsDefinition {

    //StepDefinition for GameStart

    GameStart gamestart = new GameStart();

    @Given("the game difficulty is set {string}")
    public void the_game_difficulty_is_set(String string) {
        gamestart.setDifficulty(string);
    }

    @Given("amount of players is {int}")
    public void amount_of_players_is(int int1) {
        gamestart.setAmountOfPlayers(int1);
    }


    @When("initialize dimensions for board")
    public void initialize_dimensions_for_board() {
        b = Board.getBoard();
    }

    @Then("automaton returns difficulty {string}")
    public void automaton_returns_difficulty(String string) {
        assertNotNull(gamestart.getDifficulty());
    }

    @Then("automaton create one robot")
    public void automaton_create_one_robot() {
        assertEquals(gamestart.getAmountOfPlayers(),1);
    }

    @When("robot is initiated")
    public void robot_is_initiated() {
        robot = new Robot(name,b);
    }

    @Then("automaton create two robots")
    public void automaton_create_two_robots() {
        assertEquals(gamestart.getAmountOfPlayers(),2);
    }

    @Then("automaton create one robots")
    public void automaton_create_one_robots() {
        assertEquals((int) b.getNumRobots(),1);
    }

    // step definitions for robot

    String name;
    Board b;
    Robot robot;

    @Given("a board")
    public void a_board() {
        b = Board.getBoard();
    }
    @Given("a name {string}")
    public void a_name(String string) {
        name = string;
    }
    @When("the game starts")
    public void the_game_starts() {
        gamestart.start();
    }

    @Given("the names are set {string} {string}")
    public void the_names_are_set(String string, String string2) {
        if(string2 == "--") {
            gamestart.addName(string);
        } else {
            gamestart.addName(string);
            gamestart.addName(string2);
        }
    }

    @Then("board is not null")
    public void board_is_not_null() {
        assertNotNull(gamestart.getBoard());
    }
    @Then("robot names are {string} {string}")
    public void robot_names_are(String string, String string2) {
        if(string2 == "--") {
            assertEquals(string, gamestart.getRobotNames().get(0));
        } else {
            assertEquals(string, gamestart.getRobotNames().get(0));
            assertEquals(string2, gamestart.getRobotNames().get(1));
        }
    }


    int obstacles;
    int checkpoints;
    boolean valid;
    int iOrientation;
    int fOrientation;
    int diff;

    //move across board

    @Given("a robot on a board")
    public void a_robot_on_a_board() {
        robot = new Robot("Jonathan", b);
    }
    @Given("the board is set up")
    public void the_board_is_set_up() {
        b = Board.getBoard();
        b.setUp(robot, obstacles, checkpoints, null);
    }

    @When("board is set up")
    public void board_is_set_up() {
        b.setUp(robot, obstacles, checkpoints, null);
    }



    @Given("coordinates of the robot")
    public void coordinates_of_the_robot() {
        initial = new Coordinates (robot.getCoordinates().getx(),robot.getCoordinates().gety());
    }

    @Then("score is zero")
    public void score_is_zero() {
        assertEquals(robot.getScore(),0);
    }

    @When("the robot moves")
    public void the_robot_moves() {
        robot.moved();
    }

    @Given("an opponent")
    public void an_opponent() {
        e = new Robot("Jens", b);
    }

    @Then("score is decreased")
    public void score_is_decreased() {
        assertTrue(robot.getScore()-score < 0);
    }

    @Then("robot moved")
    public void robot_moved() {
        fin = new Coordinates(robot.getCoordinates().getx(),robot.getCoordinates().gety());
        assertNotEquals(initial,fin);
    }

    //turning left

    Coordinates initial;
    Coordinates fin;

    @Given("initial orientation")
    public void initial_orientation() {
        orientation = robot.getOrientation();
    }

    @When("robot turns left")
    public void robot_turns_left() {
        robot.turnL();
    }

    @Then("robot turned by {int}")
    public void robot_turned_by(Integer int1) {
        diff = (4-(robot.getOrientation()-orientation))%4;
        assertEquals(diff, (int) int1);
    }

    @Given("the initial orientation is {int}")
    public void the_initial_orientation_is(Integer int1) {
        orientation = int1;
        robot.setOrientation(orientation);
    }

    @Then("robot turned left")
    public void robot_turned_left() {
        fOrientation = robot.getOrientation();
        diff = (4-(fOrientation-iOrientation))%4;
        assertEquals(diff,1);
    }

    @Given("initial coordinates")
    public void initial_coordinates() {
        initial = new Coordinates(robot.getCoordinates().getx(),robot.getCoordinates().gety());
    }

    //turning right
    @When("robot turns right")
    public void robot_turns_right() {
        robot.turnR();
    }
    @Then("robot turned right")
    public void robot_turned_right() {
        fOrientation = robot.getOrientation();
        diff = (4-(fOrientation-iOrientation))%4;
        assertEquals(diff,3);
    }

    @When("robot performs a valid move")
    public void robot_performs_a_valid_move() {
        robot.move(robot.getCoordinates());
    }

    @Then("robot changed position")
    public void robot_changed_position() {
        assertNotEquals(initial.diff(robot.getCoordinates()),0);
    }

    @Given("opponent has coordinates {int} and {int}")
    public void opponent_has_coordinates_and(Integer x1, Integer y1) {
        e.setCoordinates(x1, y1);
        b.add(e);
    }

    int orientation;

    //correct move

    @Then("move is correct")
    public void move_is_correct() {

        int dx = initial.diffx(robot.getCoordinates());
        int dy = initial.diffy(robot.getCoordinates());


        assertTrue((dy == ((orientation-1)%2))&&(dx == (-(orientation-2)%2)));
    }

    // hit conveyer
    Element e;
    int score;

    @Given("a conveyer")
    public void a_conveyer() {
        e = new Conveyer();
    }
    @Given("robot score")
    public void robot_score() {
        score = robot.getScore();
    }
    @When("robot hits conveyer")
    public void robot_hits_conveyer() {
        assertEquals(robot.hit(e),"Conveyer");
    }

    @When("robot hits element")
    public void robot_hits_element() {
        robot.hit(e);
        System.out.println(robot.getScore());
    }

    @Given("the robot score is {int}")
    public void the_robot_score_is(Integer int1) {
        robot.setScore(int1);
        score = robot.getScore();
    }

    @Then("the score is decreased")
    public void the_score_is_decreased() {
        assertTrue((robot.getScore() - score) < 0);
    }

    //hit pit

    @Given("a pit")
    public void a_pit() {
        e = new Pit();
    }
    @When("robot hits pit")
    public void robot_hits_pit() {
        robot.hit(e);
    }
    @Then("robot returns to start")
    public void robot_returns_to_start() {
        assertEquals(robot.getCoordinates().getx(),robot.getStart().getx());
        assertEquals(robot.getCoordinates().gety(),robot.getStart().gety());
    }

    @Then("the score is zero")
    public void the_score_is_zero() {
        assertEquals(robot.getScore(),0);
    }

    //hit gear

    @Given("a gear")
    public void a_gear() {
        e = new Gear();
    }

    @Given("a gear that turns right")
    public void a_gear_that_turns_right() {
        e = new Gear();
        e.setLeft(false);
    }

    @Given("a gear that turns left")
    public void a_gear_that_turns_left() {
        e = new Gear();
        e.setLeft(true);
    }



    @When("robot hits opponent")
    public void robot_hits_opponent() {
        robot.hit(e);
    }

    @Then("robot moved in the opposite direction")
    public void robot_moved_in_the_opposite_direction() {

        int dx = -robot.getCoordinates().diffx(initial);
        int dy = -robot.getCoordinates().diffy(initial);

        int sx = (int) (Math.signum((orientation-2)%2));
        int sy = (int) (Math.signum(-(orientation-1)%2));

        assertTrue(((int)Math.signum(dy) == sy)&&((int)Math.signum(dx) == sx));
    }

    @Given("a trampoline")
    public void a_trampoline() {
        e = new Trampoline();
    }

    @Then("robot is at least {int} fields away")
    public void robot_is_at_least_fields_away(Integer int1) {
        assertTrue(robot.getCoordinates().diff(initial) >= 3);
    }

    int d;


    @Given("robot at coordinates {int} {int}")
    public void robot_at_coordinates(Integer int1, Integer int2) {
        initial = new Coordinates(int1,int2);
        robot.setCoordinates(initial);
    }

    @When("robot tries to move")
    public void robot_tries_to_move() {
        robot.moved();
    }



    Coordinates coordinates;


    @Then("robot is at new coordinates")
    public void robot_is_at_new_coordinates() {
        assertEquals(coordinates,robot.getCoordinates());
    }






    int checkCount;
    int checkID;
    int cpBoard;

    @Given("robots checkpoint counter {int}")
    public void robots_checkpoint_counter(Integer int1) {
        checkCount = int1;
        robot.setCheckCount(checkCount);
    }

    @Given("checkpoint id is equal to counter plus one")
    public void checkpoint_id_is_equal_to_counter_plus_one() {
        checkID = checkCount+1;
    }
    @Given("a checkpoint")
    public void a_checkpoint() {
        e = new Checkpoint(checkID);
    }

    @When("robot hits checkpoint")
    public void robot_hits_checkpoint() {
        robot.hit(e);
    }

    @Then("the score is incremented")
    public void the_score_is_incremented() {
        assertTrue((robot.getScore() - score) > 0);
    }

    @Given("checkpoint id is equal to counter")
    public void checkpoint_id_is_equal_to_counter() {
        checkID = checkCount;
    }

    @Given("checkpoint id is equal to counter minus one")
    public void checkpoint_id_is_equal_to_counter_minus_one() {
        checkID = checkCount -1;
    }

    @Then("the score is incremented by {int}")
    public void the_score_is_incremented_by(Integer int1) {
        assertEquals(robot.getScore() - score, (int) int1);
    }

    @Then("checkpoint counter is incremented")
    public void checkpoint_counter_is_incremented() {
        assertEquals(robot.getCheckCount() - checkCount,1);
    }

    @Then("checkpoint is visited")
    public void checkpoint_is_visited() {
        assertEquals(e.getID(), robot.getCheckCount());
    }

    @Given("number of checkpoints on the board")
    public void number_of_checkpoints_on_the_board() {
        cpBoard = b.getCheck();
    }
    @Given("number of checkpoints at least equal to checkpoint counter")
    public void number_of_checkpoints_at_least_equal_to_checkpoint_counter() {
        checkID = cpBoard;
        checkCount = cpBoard;
        robot.setCheckCount(checkCount);

    }

    //move from specific coordinates
    @Given("robot at coordinates {int} and {int}")
    public void robot_at_coordinates_and(Integer int1, Integer int2) {
        initial = new Coordinates(int1,int2);
        robot.setCoordinates(int1, int2);
    }


    //move north from first row
    int dim;
    boolean possible;
    @Given("dimensions of the board")
    public void dimensions_of_the_board() {
        dim = b.getDimensions();
    }

    @Given("robots orientation is north")
    public void robots_orientation_is_north() {
        robot.setOrientation(0);
    }
    @Given("robot is in the first row and x {int}")
    public void robot_is_in_the_first_row_and_x(Integer int1) {
        initial = new Coordinates((int1%dim), 0);
        robot.setCoordinates(initial);
    }
    @When("the robot tries to move")
    public void the_robot_tries_to_move() {
        possible = robot.moved();
    }
    @Then("move is not possible")
    public void move_is_not_possible() {
        assertFalse(possible);
    }
    @Then("robot didnt move")
    public void robot_didnt_move() {
        assertEquals(initial,robot.getCoordinates());
    }

    //move south from last row
    @Given("robots orientation is south")
    public void robots_orientation_is_south() {
        robot.setOrientation(2);
    }
    @Given("robot is in the last row and x {int}")
    public void robot_is_in_the_last_row_and_x(Integer int1) {
        initial = new Coordinates((int1%dim), dim-1);
        robot.setCoordinates(initial);
    }

    //move west from first column
    @Given("robots orientation is west")
    public void robots_orientation_is_west() {
        robot.setOrientation(3);
    }
    @Given("robot is in the first column and y {int}")
    public void robot_is_in_the_first_column_and_y(Integer int1) {
        initial = new Coordinates(0,int1%dim);
        robot.setCoordinates(initial);
    }

    @Then("final coordinates of the robot")
    public void final_coordinates_of_the_robot() {
        fin = new Coordinates (robot.getCoordinates().getx(),robot.getCoordinates().gety());
    }

    //move east from last column
    @Given("robots orientation is east")
    public void robots_orientation_is_east() {
        robot.setOrientation(1);
    }
    @Given("robot is in the last column and y {int}")
    public void robot_is_in_the_last_column_and_y(Integer int1) {
        initial = new Coordinates(dim-1,int1%dim);
        robot.setCoordinates(initial);
    }

    boolean empty;
    boolean contains;
    @Given("coordinates {int} and {int}")
    public void coordinates_and(Integer int1, Integer int2) {
        coordinates = new Coordinates(int1,int2);
    }
    @Given("element at coordinates")
    public void element_at_coordinates() {
        e.setCoordinates(coordinates);
    }
    @Given("element on board")
    public void element_on_board() {
        b.add(e);
    }
    @When("coordinates are checked")
    public void coordinates_are_checked() {
        empty = b.isEmpty(coordinates);
    }
    @Then("spot is not empty")
    public void spot_is_not_empty() {
        assertFalse(empty);
    }


    @Given("element on random spot on board")
    public void element_on_random_spot_on_board() {
        b.addRandom(e);
    }
    @When("check if element is on board")
    public void check_if_element_is_on_board() {
        contains = b.contains(e);
    }
    @Then("element is on board")
    public void element_is_on_board() {
        assertTrue(contains);
    }

    @Given("start of the robot")
    public void start_of_the_robot() {
        coordinates = robot.getStart();
    }
    @Then("robot is on board")
    public void robot_is_on_board() {
        assertTrue(b.contains(robot));
    }


    @Given("{int} checkpoints")
    public void checkpoints(Integer int1) {
        checkpoints = int1;
    }

    @When("board is set up with checkpoints")
    public void board_is_set_up_with_checkpoints() {
        b.setUp(robot,obstacles, checkpoints, null);

    }


    @Then("board contains n checkpoints")
    public void board_contains_n_checkpoints() {
        assertEquals(checkpoints,b.getCheck());
    }
    @Then("there is one more element than checkpoints")
    public void there_is_one_more_element_than_checkpoints() {
        assertEquals(checkpoints+1,b.getNumRobots()+ b.getCheck() + b.getNumObstacles());
    }

    @Given("next coordinates of the robot")
    public void next_coordinates_of_the_robot() {
        coordinates = new Coordinates(robot.next_x(),robot.next_y());
    }

    @Then("robot is at the next coordinates")
    public void robot_is_at_the_next_coordinates() {
        assertEquals(robot.getCoordinates().getx(),coordinates.getx());
        assertEquals(robot.getCoordinates().gety(),coordinates.gety());
    }

    @Then("there is an element at the spot")
    public void there_is_an_element_at_the_spot() {
        assertNotNull(b.getElement(coordinates));
    }





//card scenarios
    Deck deck;
    int numCards;
    String title;
    Card card;
    Card card2;
    Card.action action;
    List<Card.action> actions = new ArrayList<Card.action>();

    @Given("an sequence of actions")
    public void an_sequence_of_actions() {
        actions.add(Card.action.move);
        actions.add(Card.action.turnL);
        actions.add(Card.action.turnR);
    }
    @When("add action")
    public void add_action() {
        card.setAction(actions);
    }

    @Then("action is added")
    public void action_is_added() {
        assertEquals(card.printActions(), Arrays.toString(actions.toArray()));
    }

    @Given("the action move")
    public void the_action_move() {
        action = Card.action.move;
    }

    @Given("the action turnL")
    public void the_action_turn_l() {
        action = Card.action.turnL;
    }

    @Given("the action turnR")
    public void the_action_turn_r() {
        action = Card.action.turnR;
    }


    @Given("action is added to card")
    public void action_is_added_to_card() {
        actions.add(action);
    }
    @When("card is played")
    public void card_is_played() {
        card.play(robot);
    }

    @Given("a deck")
    public void a_deck() {
        deck = new Deck();
    }
    @Given("number of cards in deck")
    public void number_of_cards_in_deck() {
        numCards = deck.getNumCards();
    }
    @Given("a card with a title {string}")
    public void a_card_with_a_title(String string) {
        title = string;
        card = new Card(title);
    }
    @When("card is added to deck")
    public void card_is_added_to_deck() {
        deck.add(card);
    }
    @Then("deck size incremented")
    public void deck_size_incremented() {
        assertEquals(numCards+1,deck.getNumCards());
    }
    @Then("card is in deck")
    public void card_is_in_deck() {
        assertTrue(deck.contains(card));
    }

    //card drawn from deck
    @Given("three cards {string} {string} {string} in deck")
    public void three_cards_in_deck(String string, String string2, String string3) {
        deck.add(new Card(string));
        deck.add(new Card(string2));
        deck.add(new Card(string3));
    }

    @When("card is drawn from deck")
    public void card_is_drawn_from_deck() {
        card = deck.draw();
    }

    //add card that is already there
    @Then("deck size is not incremented")
    public void deck_size_is_not_incremented() {
        assertEquals(numCards,deck.getNumCards());
    }


    @Then("card is not in deck")
    public void card_is_not_in_deck() {
        assertFalse(deck.contains(card));
    }

    @Then("deck size is decreased")
    public void deck_size_is_decreased() {
        assertEquals(numCards-1,deck.getNumCards());
    }


    //ordering cards
    int position;
    @Given("card is at position two")
    public void card_is_at_position_two() {
        hand.setPosition(card,1);
    }
    @Given("initial position of the card")
    public void initial_position_of_the_card() {
        position = hand.getPosition(card);
    }
    @Given("position one is empty")
    public void position_one_is_empty() {
        hand.setPosition(null, 0);
    }
    @When("card is placed in position one")
    public void card_is_placed_in_position_one() {
        hand.setPosition(card,0);
    }
    @Then("position changed")
    public void position_changed() {
        assertNotEquals(hand.getPosition(card),position);
    }
    @Then("card is at position one")
    public void card_is_at_position_one() {
        assertEquals(hand.getPosition(card),0);
    }

    //order cards as player
    @Given("player fills hand")
    public void player_fills_hand() {
        player.fillHand(deck);
    }

    @Given("player fills hand and orders twice")
    public void player_fills_hand_and_orders_twice() {
        player.fillHand(deck);
        for(int i = 0; i < 5; i++) {
            player.getHand().setPosition(player.getHand().getCard(i), i);
        }
        for(int i = 0; i < 5; i++) {
            player.getHand().setPosition(player.getHand().getCard(i), i);
        }
    }

    @Given("player fills hand and try to order out of bounds")
    public void player_fills_hand_and_try_to_order_out_of_bounds() {
        player.fillHand(deck);
        for(int i = 0; i < 5; i++) {
            player.getHand().setPosition(player.getHand().getCard(i), i);
        }
        for(int i = 0; i < 6; i++) {
            player.getHand().setPosition(player.getHand().getCard(i), i);
        }
    }


    @Given("cards are not ordered")
    public void cards_are_not_ordered() {
        player.setOrdered(false);
    }

    @When("cards on hand are ordered")
    public void cards_on_hand_are_ordered() {
        for(int i = 0; i < 5; i++) {
            player.getHand().setPosition(player.getHand().getCard(i), i);
        }
    }

    @Given("a player with the robot")
    public void a_player_with_the_robot() {
        player = new Player(robot);
    }

    @When("player plays hand")
    public void player_plays_hand() {
        for(int i = 0; i < 5; i++) {
            player.play();
        }
    }
    @Then("players hand is empty")
    public void players_hand_is_empty() {
        assertTrue(player.emptyHand());
    }


// player
    Hand hand;
    int capacity;
    int spaces;
    Player player;


    @Given("a hand of cards with capacity {int}")
    public void a_hand_of_cards_with_capacity(Integer int1) {
        capacity = int1;
        player = new Player(robot);
        hand = new Hand(capacity);
    }

    @When("hand is emptied")
    public void hand_is_emptied() {
        hand.empty();
    }
    @Then("hand is empty")
    public void hand_is_empty() {
        assertTrue(hand.isEmpty());
    }


    @Given("a card with a title and an action")
    public void a_card_with_a_title_and_an_action() {
        card = new Card("title");
        action = Card.action.move;
        actions.add(action);
        card.setAction(actions);
    }

    @Given("space on hand")
    public void space_on_hand() {
        hand.getSpaces();
    }

    @Given("number of free spaces")
    public void number_of_free_spaces() {
        spaces = hand.getSpaces();
    }
    @When("card is drawn")
    public void card_is_drawn() {
        hand.draw(deck);
    }

    @Then("card is on hand")
    public void card_is_on_hand() {
        assertTrue(hand.contains(card));
    }

    @Then("number of free spaces decrease by one")
    public void number_of_free_spaces_decrease_by_one() {
        assertEquals(spaces,hand.getSpaces()+1);
    }

    @Then("number of free spaces decrease")
    public void number_of_free_spaces_decrease() {
        assertTrue(hand.getSpaces()-spaces<0);
    }

    @Given("a deck of cards")
    public void a_deck_of_cards() {
        deck = new Deck();
        deck.defaultDeck();
    }
    @When("hand is filled")
    public void hand_is_filled() {
        hand.fill(deck);
    }

    @Then("hand is full")
    public void hand_is_full() {
        assertEquals(hand.getSpaces(),0);
    }
/*
    @Given("card is added to hand")
    public void card_is_added_to_hand() {
        hand.add(card);
    }

 */


//saving state of game
    SaveGame savingFile;
    int currentOrientation;
    boolean multiplayer;

    @And("orientation of the robot")
    public void orientationOfTheRobot() {
        currentOrientation = robot.getOrientation();
    }

    @When("game is saved")
    public void game_is_saved() {
        savingFile  = new SaveGame(robot, b, hand, e, difficulty);
    }


    @Then("status of robot is written in csv")
    public void statusOfRobotIsWrittenInCsv() throws FileNotFoundException {
        savingFile.savingElements(b.getElementsOnBoard());
    }

    @Then("status of obstacle is written in csv")
    public void status_of_obstacle_is_written_in_csv() throws FileNotFoundException {
        savingFile.savingElements(b.getElementsOnBoard());
    }

    @Then("status of board is written in csv")
    public void statusOfBoardIsWrittenInCsv() throws FileNotFoundException {
        savingFile.savingBoard(b);
    }

    @And("status of checkpoint is written in csv")
    public void statusOfCheckpointIsWrittenInCsv() throws FileNotFoundException {
        savingFile.savingElements(b.getElementsOnBoard());
    }

    Game game;

    @When("game is started for singleplayer")
    public void game_is_started_for_singleplayer() {
        game = new Game();
        game.setBoard(b);
        game.setMultiplayer(false);
        player = new Player(robot);
        game.addRobot(player.getRobot());
        game.addPlayer(player);
    }


    @When("game is started for multiplayer")
    public void game_is_started_for_multiplayer() {
        game = new Game();
        game.setBoard(b);
        game.setMultiplayer(true);
        player = new Player(robot1);
        game.addRobot(player.getRobot());
        game.addPlayer(player);
        player = new Player(robot2);
        game.addRobot(player.getRobot());
        game.addPlayer(player);
    }
    @When("game is played")
    public void game_is_played() {

        for(int i = 0; i < 4; i++) {

            game.getCards();
            game.drawCards();
            // ordering

            while(true){
                if(game.isOrdered()) {
                    for (int j = 0; j < 5; j++) {
                        game.playCard();
                    }
                    break;
                } else {
                    for (int k = 0; k < 5; k++) {
                        game.setCardToPosition(game.getPlayer().getHand().getCard(k).getTitle(), k);
                    }
                }
            }
            game.nextRound();
        }
    }

    @When("game is played and cards are given twice")
    public void game_is_played_and_cards_are_given_twice() {
        for(int i = 0; i < 4; i++) {

            game.getCards();
            game.getCards();
            game.drawCards();
            // ordering

            while(true){
                if(game.isOrdered()) {
                    for (int j = 0; j < 5; j++) {
                        game.playCard();
                    }
                    break;
                } else {
                    for (int k = 0; k < 5; k++) {
                        game.setCardToPosition(game.getPlayer().getHand().getCard(k).getTitle(), k);
                    }
                }
            }
            game.nextRound();
        }
    }

    @When("game saved in the middle")
    public void game_saved_in_the_middle() {
        savingFile = new SaveGame(game);
        savingFile.save();
    }
    @Then("status of game is saved")
    public void status_of_game_is_saved() {
        assertEquals(0, savingFile.save());
    }


    String difficulty;

    // loading game

    LoadGame loadgame;

    @Given("all paths of game")
    public void all_paths_of_game() {
        loadgame = new LoadGame();
    }

    @When("information is used to resume game")
    public void information_is_used_to_resume_game() {
        loadgame.tryLoad();
    }

    //Game game;

    @Then("game is started again")
    public void game_is_started_again() {
        assertEquals(loadgame.tryLoad(),0);
        loadgame.load();
    }

    @Then("game is not started again")
    public void game_is_not_started_again() {
        assertEquals(loadgame.tryLoad(),1);
    }

    @Given("no files")
    public void no_files() {
        loadgame = new LoadGame("hello.csv","hello.csv", "hello.csv", "hello.csv");
    }

    @Given("some files {string}")
    public void some_files(String string) {
        String[] files;
        files = string.split(",");
        loadgame = new LoadGame(files[0], files[1], files[2], files[3]);
    }

    //obstaclefactory

    ObstacleFactory factory;
    int dimensions;
    int numRobots;
    int numObstacles;
    Robot robot1;
    Robot robot2;
    @Given("a factory")
    public void a_factory() {
        factory = new ObstacleFactory();
    }
    @When("obstacle is added to factory")
    public void obstacle_is_added_to_factory() {
        factory.add((Obstacle) e);
    }
    @Then("obstacle is in factory")
    public void obstacle_is_in_factory() {
        assertTrue(factory.contains((Obstacle) e));
    }


    @Given("a factory with obstacles")
    public void a_factory_with_obstacles() {
        factory = new ObstacleFactory();
        factory.add(new Pit());
        factory.add(new Gear());
        factory.add(new Conveyer());
        factory.add(new Trampoline());
    }
    @Given("{int} obstacles")
    public void obstacles(Integer int1) {
        numObstacles = int1;
    }
    @Given("{int} of the board")
    public void of_the_board(Integer int1) {
        dimensions = int1;
    }

    @Given("a board with one robot")
    public void a_board_with_one_robot() {
        b = Board.getBoard();
        robot = new Robot("Robby", b);
        numRobots = 1;
    }

    @Given("a board with two robots")
    public void a_board_with_two_robots() {
        b = Board.getBoard();
        robot1 = new Robot("robert", b);
        robot2 = new Robot("roberta", b);
        numRobots = 2;
    }

    @When("board set up with elements")
    public void board_set_up_with_elements() {
        if(numRobots == 1) b.setUp(robot, numObstacles, checkpoints, factory);
        else if (numRobots == 2) b.setUp(robot1, robot2, numObstacles, checkpoints, factory);
    }
    @Then("there are {int} obstacles on board")
    public void there_are_obstacles_on_board(Integer int1) {
        assertEquals((int) int1,numObstacles);
    }


    @Given("add checkpoint with {int} checkID")
    public void add_checkpoint_with_check_id(Integer ID) {
        checkpoints = ID;
        e = new Checkpoint(ID);
    }

    @Given("game is initiated")
    public void game_is_initiated() {
        game = new Game();
        game.setBoard(b);
        game.setMultiplayer(false);
        player = new Player(robot);
        game.addPlayer(player);
        game.addRobot(robot);
    }

    @When("robot hits last checkpoint")
    public void robot_hits_last_checkpoint() {
        robot.hit(e);
    }

    @Then("game is won")
    public void game_is_won() {
        checkpoints = checkpoints + 1;
        robot.setCheckCount(robot.getCheckCount()+1);
        assertTrue(game.isFinished());
    }

    @Then("game is not won")
    public void game_is_not_won() {
        assertFalse(game.isFinished());
    }

    @Then("robot score is changed from {int}")
    public void robot_score_is_changed_from(Integer int1) {
        assertNotEquals(game.getActiveScore(), 0);
    }
    @Then("checkpoint counter is not changed")
    public void checkpoint_counter_is_not_changed() {
        assertEquals(game.getActiveCheckpoints(),0);
    }


}