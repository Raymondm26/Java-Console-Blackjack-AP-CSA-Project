public class Player {
    private Hand hand;
    private String name;
    private double money;
    private double bet;

    public Player(String name, double money) {
        this.hand = new Hand();
        this.name = name;
        this.money = money;
    }

    public Player(String name) {
        this.hand = new Hand();
        this.name = name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Hand getHand() {
        return hand;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double betValue) {
        money += betValue;
    }

    public void setBet(double bet) {
        this.bet = bet;
    }

    public double getBet() {
        return bet;
    }

    public boolean checkBet() {
        return bet > money;
    }

    public void betPayout(boolean win, boolean blackjack) {
        if(win && blackjack) {
            money += bet * 1.5;
            bet = 0;
        }

        else if(win) {
            money += bet;
            bet = 0;
        }

        else {
            money -= bet;
            bet = 0;
        }
    }

    // for testing only
    public void setHand(Card x1, Card x2) {
        hand.addToHand(x1, x2);
    }

    public int getPlayerHandValue() {
        return hand.getHandValue();
    }

    public void printHand() {
        System.out.println(name + "'s Hand: " + hand);
        System.out.println(name + " Hand Valued at: " + hand.getHandValue());
    }

    public void printFirstHand() {
        System.out.println(name + "'s Hand: " + hand.getCard(0) + " [Hidden]");
        System.out.println(name + " Card Valued at: " + hand.getCard(0).getValue());
    }

    public void printLastHand() {
        System.out.println(name + " Drew: " + hand.getCard(hand.getHandSize() -1));
    }

    public Card getLastHand() {
        return hand.getCard(hand.getHandSize() -1);
    }

    public void hit(Deck deck) {
        hand.draw(deck);
    }

    public void clearHand(Deck deck) {
        hand.moveAllToDeck(deck);
    }

    public boolean hasBlackjack() {
        if(hand.getHandValue() == 21) {
            return true;
        }

        return false;
    }
}