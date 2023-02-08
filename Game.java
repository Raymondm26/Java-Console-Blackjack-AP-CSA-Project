import java.util.Scanner;

public class Game {
    public static void main(String[] args) {
        String[] winCases = {
            "CASE 1: Player has natural blackjack. Player wins (3:2 Payout)",
            "CASE 2: Player and Dealer have natural blackjack. Tie",
            "CASE 3: Player and Dealer have 21. Tie",
            "CASE 4: Dealer has 21. Dealer wins",
            "CASE 5: Player has 21. Player wins",
            "CASE 6: Player busts. Dealer wins",
            "CASE 7: Dealer busts. Player wins",
            "CASE 8: Player and Dealer have the same hand value. Tie",
            "CASE 9: Player has lower hand. Dealer wins",
            "CASE 10: Player has higher hand. Player wins"
        };
        
        // game rules
        double startingMoney = 1000;
        int numDecks = 8;

        // create game
        Player player = new Player("Player", startingMoney);
        Player dealer = new Player("Dealer", 0);

        Scanner input = new Scanner(System.in);
        Deck playingDeck = new Deck();
        playingDeck.createFullDeck(numDecks);
        playingDeck.shuffle();

        double playerBet;
        boolean endRound;
        boolean playerBust;
    
        // game loop
        while(player.getMoney() > 0) {
            endRound = false;
            playerBust = false;

            System.out.println("Blackjack made by Evan \n");

            while(true) {
                System.out.println("Player Balence: " + player.getMoney());
                System.out.print("Enter Bet: ");
                playerBet = input.nextDouble();

                if(player.getMoney() < playerBet) {
                    System.out.println("\nInvalid Bet, Try Again");
                }

                else {
                    player.setBet(playerBet);
                    break;
                }
            }

            System.out.println();

            // draw cards
            player.hit(playingDeck);
            dealer.hit(playingDeck);
            player.hit(playingDeck);
            dealer.hit(playingDeck);

            // player.setHand(new Card(Suit.SPADE, Rank.TEN), new Card(Suit.SPADE, Rank.TEN));

            // print dealer hand
            dealer.printFirstHand();

            System.out.println();

            // print player hand
            player.printHand();

            // check for natual black jack (player has 21, dealer does not)
            if(player.hasBlackjack() && !dealer.hasBlackjack()) {
                System.out.println();
                System.out.println(winCases[0]);
                player.betPayout(true, true);
                endRound = true;
                System.out.println();
            }

            else if(player.hasBlackjack() && dealer.hasBlackjack()) {
                System.out.println();
                System.out.println(winCases[1]);
                player.setBet(0);
                endRound = true;
                System.out.println();
            }

            else if(dealer.hasBlackjack()) {
                System.out.println();
                dealer.printHand();
                System.out.println("Dealer has blackjack, hit to try and get blackjack");
            }

            // player loop
            if(!endRound) {
                // ask player what they want to do

                /*
                 * hit
                 * stand
                 * double
                 * split if card values are equal
                 */
                boolean canDouble = true;
                while(player.getPlayerHandValue() < 21) {
                    System.out.println();

                    System.out.print("Choose (1) hit or (2) stand or (3) double: ");
                    int playerOptions = input.nextInt();

                    // if hit, player can keep playing until he doesn't want to hit, but he also cannot double anymore
                    if(playerOptions == 1) {
                        player.hit(playingDeck);
                        player.printLastHand();
                        player.printHand();

                        if(player.getPlayerHandValue() > 21) {
                            System.out.println("Player Busts");
                            endRound = true;
                            playerBust = true;
                        }

                        canDouble = false;
                    }

                    // if stand, player ends turn
                    else if(playerOptions == 2) {
                        break;
                    }

                    // if double, double bet and end turn
                    else if(playerOptions == 3 && canDouble) {
                        if(player.getBet() * 2 > player.getMoney()) {
                            System.out.println("You don't have enough money to make this bet");
                        }

                        else {
                            player.setBet(player.getBet() * 2);
                            player.hit(playingDeck);
                            player.printLastHand();
                            player.printHand();

                            if(player.getPlayerHandValue() > 21) {
                                System.out.println("Player Busts");
                                endRound = true;
                                playerBust = true;
                            }

                            break;
                        }
                    }

                    else {
                        System.out.println("Your input was not valid, please choose a valid option");
                    }
                }

                // player ends turn and dealer turns face down card up
                if(!endRound || playerBust) {
                    Timing.loading(1500, "Blue");
                    dealer.printHand();
                }

                // dealer plays, while dealer is less than 17 and hasn't won, dealer draws cards
                while(dealer.getPlayerHandValue() < 17 && dealer.getPlayerHandValue() < player.getPlayerHandValue() && !endRound) {
                    Timing.loading(1500, "Blue");

                    dealer.hit(playingDeck);
                    dealer.printLastHand();
                    dealer.printHand();

                    if(dealer.getPlayerHandValue() > 21) {
                        System.out.println("Dealer Busts");
                    }
                }

                System.out.println();

                // check for dealer and player both tie with blackjack
                if(dealer.hasBlackjack() && player.hasBlackjack()) {
                    System.out.println(winCases[2]);
                    player.setBet(0);
                }

                // dealer has blackjack and player doesnt
                else if(dealer.hasBlackjack() && !player.hasBlackjack()) {
                    System.out.println(winCases[3]);
                    player.betPayout(false, false);
                }

                // player has blackjack and wins
                else if(!dealer.hasBlackjack() && player.hasBlackjack()) {
                    System.out.println(winCases[4]);
                    player.betPayout(true, false);
                }

                // non-blackjack cases

                else if(playerBust) {
                    System.out.println(winCases[5]);
                    player.betPayout(false, false);
                }

                else if(dealer.getPlayerHandValue() > 21) {
                    System.out.println(winCases[6]);
                    player.betPayout(true, false);
                }

                // Tie
                else if(dealer.getPlayerHandValue() == player.getPlayerHandValue()) {
                    System.out.println(winCases[7]);
                    player.setBet(0);
                }

                // Dealer Wins
                else if(dealer.getPlayerHandValue() > player.getPlayerHandValue()) {
                    System.out.println(winCases[8]);
                    player.betPayout(false, false);
                }
                
                // Player Wins
                else if(dealer.getPlayerHandValue() < player.getPlayerHandValue()) {
                    System.out.println(winCases[9]);
                    player.betPayout(true, false);
                }
            }

            // after round ends
            player.clearHand(playingDeck);
            dealer.clearHand(playingDeck);
            playingDeck.shuffle();

            System.out.println();
            System.out.println("Clearing Hands and Shuffling Deck");
            Timing.loading(1500, "Blue");
        }

        System.out.println("\nYou've run out of funds. Game over");
    }
}
