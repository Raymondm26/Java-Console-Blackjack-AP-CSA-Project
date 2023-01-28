public class Card {
    private Suit suit;
    private Rank rank;

   /**
    * @param suit  The Suit
    * @param rank  The Rank (Face Value)
    */
   public Card(Suit suit, Rank rank) {
       this.suit = suit;
       this.rank = rank;
   }

   public int getValue() {
       return rank.rankValue;
   }

   public Suit getSuit() {
       return this.suit;
   }

   public Rank getRank() {
       return this.rank;
   }

   public String toString() {
       // return ("[" + rank + " of " + suit + " (" + this.getValue() + ")]");
        return ("[" + rank + " of " + suit + "]");
   }
}