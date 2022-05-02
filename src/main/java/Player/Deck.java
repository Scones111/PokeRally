package Player;

import java.util.ArrayList;
import java.util.List;


public class Deck {
    private int numCards;
    private List<Card> cards;

    //constructor, instantiates Arraylist, reference to cards
    public Deck() { // used
        numCards = 0;
        cards = new ArrayList<Card>();

    }

    //adds the card to deck, increment card number, checks if the same object is added twice
    public void add(Card card) { // used
        if(!this.contains(card)) {
            cards.add(card);
            numCards ++;
        }
    }

    //check whether a cards is added to the deck, used for testing
    public boolean contains(Card c) {
        return cards.contains(c);
    } // used

    //gets then number of cards
    public int getNumCards() {
        return numCards;
    } // used

    //draws a random card from ArrayList cards
    public Card draw() { // used
        int rand = (int) (numCards * Math.random());
        return cards.get(rand);
    }

    //method that instantiate the default cards and add adds their actions
    public void defaultDeck(){
        ArrayList<Card.action> actions = new ArrayList<Card.action>();
        //move
        Card card = new Card("move1Forward");
        actions.add(Card.action.move);
        card.setAction(actions);
        add(card);
        //move2
        actions = new ArrayList<Card.action>();
        card = new Card("move2Forward");
        actions.add(Card.action.move);
        actions.add(Card.action.move);
        card.setAction(actions);
        add(card);
        //turnLeft
        actions = new ArrayList<Card.action>();
        card = new Card("turnL");
        actions.add(Card.action.turnL);
        card.setAction(actions);
        add(card);
        //turnRight
        actions = new ArrayList<Card.action>();
        card = new Card("turnR");
        actions.add(Card.action.turnR);
        card.setAction(actions);
        add(card);
        //turn180
        actions = new ArrayList<Card.action>();
        card = new Card("turn180");
        actions.add(Card.action.turnR);
        actions.add(Card.action.turnR);
        card.setAction(actions);
        add(card);
    }
}