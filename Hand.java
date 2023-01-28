import java.util.ArrayList;

/**
 * A hand of cards to play with
 */
public class Hand {

    private ArrayList<Card> hand;

    public Hand() {
        hand = new ArrayList<Card>();
    }

    public Card getCard(int i) {
        return hand.get(i);
    }

    public int getHandSize() {
        return hand.size();
    }

    public void draw(Deck comingFrom) {
        hand.add(comingFrom.getCard(0));
        comingFrom.removeCard(0);
    }

    public void addToHand(Card x1, Card x2) {
        hand.add(x1);
        hand.add(x2);
    }

    public void moveAllToDeck(Deck moveTo) {
        int handSize = this.hand.size();

        for(int i = 0; i < handSize; i++) {
            moveTo.addCard(this.getCard(i));
        }

        hand.clear();
    }

    /**
     * @return gets then sum value of all the cards in hand
     */
    public int getHandValue() {
        int value = 0;
        int aceCount = 0;

        for(Card card: hand) {
            value += card.getValue();

            if (card.getValue() == 11) {
                aceCount ++;
            }
        }

        // if hand value < 21 and ace count > 0
        // set each ace to 1 until the value is less than 21
        if (value > 21 && aceCount > 0) {
            while(aceCount > 0 && value > 21) {
                aceCount --;
                value -= 10;
            }
        }

        return value;
    }
    
    public String toString() {
        String output = "";

        for(Card card : hand) {
            output += card + " ";
        }

        return output;
    }
}