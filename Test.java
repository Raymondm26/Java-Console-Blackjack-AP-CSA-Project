public class Test {
    public static void main(String[] args) {
        makeDeck();
        // makeHand();
        // makePlayer();
    }

    // print deck (pass)
    public static void makeDeck() {
        Deck x = new Deck();
        x.createFullDeck(1);

        System.out.println(x);
    }

    // create hand
    public static void makeHand() {
        Deck x = new Deck();
        x.createFullDeck(1);

        Hand playerHand = new Hand();
        playerHand.draw(x);
        playerHand.draw(x);

        System.out.println(playerHand);
    }

    public static void makePlayer() {
        Deck x = new Deck();
        x.createFullDeck(1);

        Player evan = new Player("Evan");

        evan.hit(x);

        evan.printHand();
    }
}