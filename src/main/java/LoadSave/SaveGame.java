package LoadSave;

import Board.Board;
import Elements.Checkpoint;
import Elements.Element;
import Elements.Obstacle;
import Elements.Robot;
import Gameplay.Game;
import Player.Hand;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
public class SaveGame {
    private Game game;

    public SaveGame(Robot robot, Board board, Hand hand, Element element, String difficulty) {
    }

    public SaveGame(Game game){
        this.game = game;
    }

    public int save(){
        try {
            savingElements(game.getBoard().getElementsOnBoard());
            savingBoard(game.getBoard());
        } catch (FileNotFoundException e) {
            return 1;
        }
        return 0;
    }

    public void savingElements(ArrayList<Element> elementsOnBoard) throws FileNotFoundException {

        File robotcsvfile = new File("DataOfRobot.csv");
        PrintWriter robotOut = new PrintWriter(robotcsvfile);

        File obstaclecsvfile = new File("DataOfObstacle.csv");
        PrintWriter ObstacleOut = new PrintWriter(obstaclecsvfile);

        File checkpointcsvfile = new File("DataOfCheckPoint.csv");
        PrintWriter checkpointout = new PrintWriter(checkpointcsvfile);

        ArrayList<Robot> Robots = new ArrayList<>();
        ArrayList<Obstacle> Obstacles = new ArrayList<>();
        ArrayList<Checkpoint> Checkpoints = new ArrayList<>();

        for(Element element : elementsOnBoard) {

            if(element instanceof Robot) {
                Robots.add((Robot) element);
            }

            else if(element instanceof Obstacle) {
                Obstacles.add((Obstacle) element);
            }

            else if(element instanceof Checkpoint) {
                Checkpoints.add((Checkpoint) element);
            }
        }

        for(Robot robots : Robots) {
            robotOut.printf("%s,%d,%d,%d,%d,%d\n",robots.getName(),robots.getOrientation(),robots.getScore(),robots.getCheckCount(),robots.getCoordinates().getx(),robots.getCoordinates().gety());
        }
        robotOut.close();

        for(Obstacle obstacles : Obstacles) {
            ObstacleOut.printf("%s,%d,%d\n",obstacles.message(),obstacles.getCoordinates().getx(),obstacles.getCoordinates().gety());
        }
        ObstacleOut.close();

        for(Checkpoint checkpoints : Checkpoints) {
            checkpointout.printf("%s,%d,%d,%d\n",checkpoints.message(),checkpoints.getID(),checkpoints.getCoordinates().getx(),
                    checkpoints.getCoordinates().gety());
        }
        checkpointout.close();
    }

    public void savingBoard(Board board) throws FileNotFoundException {
        File boardcsvfile = new File("DataOfBoard.csv");
        PrintWriter boardout = new PrintWriter(boardcsvfile);

        boardout.printf("%d,%d,%d,%d,%d\n",board.getNumRobots(),board.getDimensions(),board.getNumObstacles(),board.getCheck(),board.getElements());
        boardout.close();
    }
}
