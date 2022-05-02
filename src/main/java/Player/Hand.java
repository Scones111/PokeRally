package Player;

import Elements.Robot;

import java.util.ArrayList;

public class Hand {
    private int capacity;
    private ArrayList<Card> cards;
    private Card[] order;
    private int spaces;

    //constructor Instantiates Arraylist and List reference to cards and order, assign them the size of capacity
    public Hand(int capacity) { // used
        this.capacity = capacity;
        cards = new ArrayList<Card>(capacity);
        cards.ensureCapacity(capacity);
        order = new Card[capacity];
        spaces = capacity;
    }

    //empties hand of cards/the list
    public void empty() {
        cards.clear();
    } // used

    //checks if the list is empty
    public boolean isEmpty() {
        return (cards.isEmpty());
    } // used

    //get the amount of free space in hand
    public int getSpaces() {
        return capacity - cards.size();
    } // used

    //method calls deck.draw from deck, to draw a random card, and att it to han
    public void draw(Deck deck) {
        cards.add(deck.draw());
    } // used

    //used in testing, check if card is on hand
    public boolean contains(Card card) {
        return cards.contains(card);
    } // used

    //fills the hand with random cards from deck
    public void fill(Deck deck) { // used
        if (getSpaces() > 0) {
            draw(deck);
            fill(deck);
        }
    }

    //returns the position of a card
    public int getPosition(Card card) {
        for(int i = 0; i < capacity; i++) {
            if(order[i] == card) return i;
        }
        return capacity;
    }
    //sets new position for the chosen card
    public void setPosition(Card card, int i) { // not used
        int old = getPosition(card); //2
        if(i >= capacity) return;
        if (order[i] == null) {
            order[i] = card;
            System.out.println(order[i].getTitle());
//            if(old < capacity) order[old] = null;
        }
        else {
            Card c = order[i];
            order[i] = card;
            //careful with this one, cards just get shoved down in the order if a new card is inserted at their place
            if(old < capacity) order[old] = c;
            else setPosition(c,i+1);
        }
    }

    //instantiate new list for order
    public void ordered(boolean b) {
        if(!b) order = new Card[capacity];
    } // used

    //checks if cards is on order, shows that the hand has been order, used in testing
    public boolean isOrdered() { // used
        for(int i = 0; i < order.length; i++) {
            if(order[i] != null) {
                return true;
            }
        }
        return false; // not used
    }

    //method used for playing the card
    public String play(Robot robot) { // used
        String card;
        if (order.length > 0) {
            card = order[0].getTitle();
            order[0].play(robot);
            Card[] temp = new Card[order.length-1];
            if (temp.length > 0) {
                for (int i = 1; i < order.length; i++) {
                    temp[i - 1] = order[i];
                }
                order = temp;
            } else {
                empty();
                ordered(false);
            }
            return card;
        }
        return null;
    }


    //returns card at a specified index on the cards arraylist
    public Card getCard(int i) { // not used
        if(i >= capacity) return null;
        else if(cards.isEmpty()) return null;
        return cards.get(i);
    }

    //returns all titles of the cards, used for testing
    public String[] getTitles() {
        String[] titles = new String[capacity];
        int i = 0;
        for(Card c : cards) {
            titles[i] = c.getTitle();
            i++;
        }
        return titles;
    }

    //returns Card for the given title, if it is on hand
    public Card findCard(String title){
        return (cards.stream().filter(o -> o.getTitle().equals(title)).findFirst().orElse(null));
    }
}
