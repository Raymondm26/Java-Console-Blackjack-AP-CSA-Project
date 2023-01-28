import java.util.ArrayList;

public class Deck {
    // cards is the deck arraylist
    private ArrayList<Card> cards;

    public Deck() {
        this.cards = new ArrayList<Card>();
    }

    /**
     * @param numDecks takes the number of decks you want to play with
     * Casino's play with 8 decks
     * Generates a full 52 card deck * 8
     * 4 Suits
     * 13 Cards
     * adds them all into the cards arraylist
    */
    public void createFullDeck(int numDecks) {
        for(int i = 0; i < numDecks; i++) {
            for(Suit cardSuit : Suit.values()) {
                for(Rank cardValue : Rank.values()) {
                    this.cards.add(new Card(cardSuit, cardValue));
                }
            }
        }
    }

    /*
     * 1) Randomly sorts the cards arraylist
     * 2) pull all cards out of current deck
     * 3) put them in temp deck at random index
     * 4) then put them back in playingDeck
     */
    public void shuffle() {
        ArrayList<Card> tempDeck = new ArrayList<Card>();
        int ogSize = this.cards.size(); // keeps the orginal deck size of 52

        for(int i = 0; i < ogSize; i++) {
            int rIndex = (int) (Math.random() * this.cards.size());
            tempDeck.add(this.cards.get(rIndex));
            this.cards.remove(rIndex);
        }

        this.cards = tempDeck;
    }

    // removes card from deck at index
    public void removeCard(int i) {
        this.cards.remove(i);
    }

    // adds a card to the deck
    public void addCard(Card addCard) {
        this.cards.add(addCard);
    }

    // gets card at index
    public Card getCard(int i) {
        return this.cards.get(i);
    }

    // returns deck size
    public int getDeckSize() {
        return cards.size();
    }

    // toString
    public String toString() {
        String deckString = "";
        int i = 0;
        for(Card x : this.cards) {
            deckString += i + " " +  x + " \n";
            i++;
        }

        return deckString;
    }
}