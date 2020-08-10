
/*****************************************
 * A template for a game of poker
 * 
 * @author Shivansh Srivastava (ss5945)
 * @date 4/13/2020
 
 ****************************************/

import java.util.Scanner;
import java.util.ArrayList;
import java.util.Collections;

public class Game {
	
	private Player p;
	private Deck cards;
    private Scanner scan;
	// you'll probably need some more here
	
	
	public Game(String[] testHand){
		// This constructor is to help test your code.
		// use the contents of testHand to
		// make a hand for the player
		// use the following encoding for cards
		// c = clubs
		// d = diamonds
		// h = hearts
		// s = spades
		// 1-13 correspond to ace-king
		// example: s1 = ace of spades
		// example: testhand = {s1, s13, s12, s11, s10} = royal flush
		// 
		// you need to figure out what card to instantiate and then add it to the 
		// player hand using p.addHand(Card(testHand[i]))
		p = new Player();
        cards = new Deck();
        scan = new Scanner(System.in);
        for (int i = 0; i < testHand.length; i++) { // Converts the strings to 
            // Card objects and adds them to the hand
            p.addCard(new Card(testHand[i]));
        }
		
		
	}
	
	public Game(){
		// This no-argument constructor is to actually play a normal game
		// Instantiate a player (store in p) and a deck (store in cards); that's it
		p = new Player();
        cards = new Deck();
        scan = new Scanner(System.in);
        cards.shuffle();
        for (int i = 1; i < 6; i++) { // Deals 5 cards and adds them to hand
            p.addCard(cards.deal());
        }
	}
	
	public void play(){
		// this method should play the game
		// 0. Shuffle cards (cards.shuffle())
		// 
		// 1. Computer deals 5 cards
		// 5 times:
            // Deal a single card - How? cards.deal() gives a card
            // Add it to the player hand - p.addCard(put the card here)
            // 
        // 2. Player chooses which cards to replace
        // Solicit input from player for which cards to keep (IMPORTANT!)
        // They need to tell you which are replaced before seeing the new cards
        // 
        // 3. Computer replaces specified cards - Use remove and add methods on 
        // p to do this
        // 
        // 4. Computer declares the hand type
        // This is the meat - call checkHand():
            // Evaluate the hand with checkHand()
            // 
        // 5. Payout

        System.out.println("Welcome to Video Poker! You have " + p.getBankroll()
                           + " tokens.");
        boolean round = true;

        while (p.getBankroll() > 0 && round) { // While bankroll isn't 0 and
            // user wants to play
            cards.shuffle(); // Shuffle cards
            boolean firstReplaced = false; // Reset all booleans to false so
            // earlier games have no effect on current one
            boolean secondReplaced = false;
            boolean thirdReplaced = false;
            boolean fourthReplaced = false;
            boolean fifthReplaced = false;
            boolean replayChoice = false;
            String gameReply;
            boolean properBet = false;
            while (!properBet) { // While proper bet is false; used to reprompt
                // for input
                System.out.println("How much will you bet?"); 
                System.out.println("Please enter a valid amount!");
                double tokens = scan.nextDouble();
                if (tokens <= p.getBankroll() && tokens >= 1){ // Valid bets are
                    // at most equal to the bankroll and at least 1
                    p.bets(tokens);
                    properBet = true; // Stops loop
                }
            }

            System.out.println("Here's your current hand:"); // Unsorted hand
            for (int i = 0; i < p.getHand().size(); i++) {
                System.out.println(p.getHand().get(i).toString());
            }
            
            System.out.println("Is this hand fine? Enter 'yes' or");
            System.out.println("the number of the card to be replaced.");
            String playerReply = scan.next();
            
            while (!(playerReply.equals("yes"))) { // While player doesn't input
                // 'yes'
                if (playerReply.equals("1")) { // If input is 1 (same logic 
                    // applies for other cards below)
                    if (!(firstReplaced)) {
                        p.removeCard(p.getHand().get(0)); // Used to get index 
                        // of the card to be removed
                        p.getHand().set(p.getRemovedIdx(), cards.deal());
                        // set() used to swap the removed card with a new dealt 
                        // card while maintaining the original indices
                        firstReplaced = true; // Prevents user from trying to 
                        // replace it again
                    } else {
                        System.out.println("You can't replace that card!");
                        // Prints warning
                    }
                    
                } else if (playerReply.equals("2")) { // If input is 2
                    if (!(secondReplaced)) {
                        p.removeCard(p.getHand().get(1));
                        p.getHand().set(p.getRemovedIdx(), cards.deal());
                        secondReplaced = true;
                    } else {
                        System.out.println("You can't replace that card!");
                    }

                } else if (playerReply.equals("3")) { // If input is 3
                    if (!(thirdReplaced)) {
                        p.removeCard(p.getHand().get(2));
                        p.getHand().set(p.getRemovedIdx(), cards.deal());
                        thirdReplaced = true;
                    } else {
                        System.out.println("You can't replace that card!");
                    }
                    
                } else if (playerReply.equals("4")) { // If input is 4
                    if (!(fourthReplaced)) {
                        p.removeCard(p.getHand().get(3));
                        p.getHand().set(p.getRemovedIdx(), cards.deal());
                        fourthReplaced = true;
                    } else {
                        System.out.println("You can't replace that card!");
                    }

                } else if (playerReply.equals("5")) { // If input is 5
                    if (!(fifthReplaced)) {
                        p.removeCard(p.getHand().get(4));
                        p.getHand().set(p.getRemovedIdx(), cards.deal());
                        fifthReplaced = true;
                    } else {
                        System.out.println("You can't replace that card!");
                    }

                } else {
                    System.out.println("Please provide a valid number!");
                }
                
                System.out.println("Are you done replacing? Enter 'yes' or");
                System.out.println("the number of the next card.");
                playerReply = scan.next(); // 'yes' ends loop
            }
            
            Collections.sort(p.getHand()); // Sorts the hand
            System.out.println("Here's the hand that will be evaluated:");
            // Prints sorted hand
            for (int i = 0; i < p.getHand().size(); i++) {
                System.out.println(p.getHand().get(i).toString());
            }
            
            System.out.println(checkHand(p.getHand())); // Prints the message 
            // obtained from the check method
            
            if (p.getBankroll() == 0) { // Ends the game if you're out of tokens
                System.out.println("You're out of tokens! Game over!");
            } else {
                while (!replayChoice) { // Used for input validation
                    System.out.println("Play again? Enter 'yes' or 'no'");
                    gameReply = scan.next();
                    if (gameReply.equals("yes")) {
                        replayChoice = true;
                        p.getHand().clear(); // Clears hand for a new game
                        for (int i = 1; i < 6; i++) {
                            p.addCard(cards.deal()); // Deals 5 new cards
                        }
                    } else if (gameReply.equals("no")) {
                        round = false; // Ends the play loop
                        replayChoice = true;
                        System.out.println("Thanks for playing!"); 
                        System.out.println("Won: " + p.getBankroll() + 
                                           " tokens");
                    } else { // Keep asking if neither
                        continue;
                    }
                }

            }
            
        }
        
        
        
	}
	
	public String checkHand(ArrayList<Card> hand){
		// this method should take an ArrayList of cards
		// as input and then determine what evaluates to and
		// return that as a String
		// 
		// First thing to do is check the hand - How?
		// Call each of the submethods (giving them access to the hand)
		// 
		// sort the hand
            // how do we do this?
            // get the hand, then sort it
        // Collections.sort(p.getHand());
        // 
        // evaluate the hand
            // call helper methods for each hand in descending order
        // return the string
        // Suggested: Write boolean methods to check each hand
        // Royal flush, straight flush, 4 of a kind, full house, flush, 
        // straight, 3 of a kind, two pair, 1 pair tie (get tokens 
        // back), nothing (high card)
        // 
        // public boolean royalFlush() {
            // return straightflush() && p.getHand().get(0).getRank() == 1
        // }
        // public boolean straightFlush() { Last number is  greater than the 
        // first and everything is sorted (1 pair is false)
            // return 
        // }
        // Or can be one line: if straightFlush() is true and 1st element is 
        // A (rank 1), return true
        // straight: 1s, 10s, 11s, 12s, 13s (s is spade)
        
        String result; // This will contain the evluation and the bankroll
        
        if (isRoyalFlush()) {
            p.winnings(250); // x250 pay for Royal Flush
            result = "You got a Royal Flush! Current bankroll: " + 
                               p.getBankroll();
        } else if (isStraightFlush()) {
            p.winnings(50); // x50 pay for Straight Flush
            result = "You got a Straight Flush! Current bankroll: " + 
                               p.getBankroll();
        } else if (isFourOfAKind()) {
            p.winnings(25); // x25 pay for 4 of a Kind
            result = "You got Four of a Kind! Current bankroll: " + 
                               p.getBankroll();
        } else if (isFullHouse()) {
            p.winnings(6); // x6 pay for Full House
            result = "You got a Full House! Current bankroll: " + 
                               p.getBankroll();
        } else if (isFlush()) {
            p.winnings(5); // x5 pay for Flush
            result = "You got a Flush! Current bankroll: " + 
                               p.getBankroll();
        } else if (isStraight()) {
            p.winnings(4); // x4 pay for Straight
            result = "You got a Straight! Current bankroll: " + 
                               p.getBankroll();
        } else if (isThreeOfAKind()) {
            p.winnings(3); //  x3 pay for 3 of a Kind
            result = "You got Three of a Kind! Current bankroll: " + 
                               p.getBankroll();
        } else if (isTwoPair()) {
            p.winnings(2); // x2 pay for 2 Pair
            result = "You got a Two Pair! Current bankroll: " + 
                               p.getBankroll();
        } else if (isPair()) {
            p.winnings(1); //  x1 pay for Pair
            result = "You got a Pair! Current bankroll: " + 
                               p.getBankroll();
        } else { // x0 pay for High Card
            result = "You only got a High Card... Current bankroll: " + 
                              p.getBankroll();
        }
        
		return result;
	}
	
    private boolean isRoyalFlush() { // Royal Flush requires Straight Flush 
        // criteria and an Ace to be first in the sorted hand
        return (isStraightFlush() && p.getHand().get(0).getRank() == 1);
    }
    
    private boolean isStraightFlush() { // Straight Flush requires Straight and
        // Flush criteria
        return (isStraight() && isFlush());
    }
    
    private boolean isFourOfAKind() { // Searches for 4 consecutive neighbors
        for (int i = 0; i < p.getHand().size()-3; i++) { // i.e. 1-2-3-4 or 
            // 2-3-4-5; that's why size()-3 is upper bound and we compare i to 
            // i+1, i+2, and i+3
            if (p.getHand().get(i).getRank() == 
                p.getHand().get(i+1).getRank()) {
                if (p.getHand().get(i).getRank() == 
                    p.getHand().get(i+2).getRank()) {
                    if (p.getHand().get(i).getRank() == 
                        p.getHand().get(i+3).getRank()) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
    
    private boolean isFullHouse() { // Searches for 3 of a Kind and a different 
        // pair
        if (p.getHand().get(0).getRank() == p.getHand().get(1).getRank()) {
            // If 1st and 2nd are the same
            if (p.getHand().get(3).getRank() == p.getHand().get(4).getRank()) {
                // If 4th and 5th are the same
                if (p.getHand().get(2).getRank() == p.getHand().get(0).getRank()
                    || p.getHand().get(2).getRank() == 
                    p.getHand().get(4).getRank()) {
                    // 3rd must be the same as one of the pairs
                    return true;
                }
            }
        }
        return false;
    }
    
    private boolean isFlush() { // All cards must be of the same suit
        if (p.getHand().get(0).getSuit() == p.getHand().get(1).getSuit() &&
            p.getHand().get(0).getSuit() == p.getHand().get(2).getSuit() &&
            p.getHand().get(0).getSuit() == p.getHand().get(3).getSuit() &&
            p.getHand().get(0).getSuit() == p.getHand().get(4).getSuit()) {
            return true;
        }
        return false;
    }
    
    private boolean isStraight() { // All cards in a sequence
        if (p.getHand().get(0).getRank() == 1) { // Accounts for the ace being
            // a low or high
            if (p.getHand().get(1).getRank() != 2 && 
                 p.getHand().get(4).getRank() != 13) {
                return false;
            } else if (p.getHand().get(1).getRank() == 10 &&
                       p.getHand().get(2).getRank() == 11 && 
                       p.getHand().get(3).getRank() == 12 && 
                       p.getHand().get(4).getRank() == 13) {
                // Royal flush conditions
                return true;
            }
        }
        for (int i = 0; i < p.getHand().size() - 1; i++) { // In all other 
            // cases, subsequent cards should have a rank that is 1 higher 
            // than the last
            if (p.getHand().get(i).getRank() + 1 != 
                p.getHand().get((i+1)).getRank()) {
                return false;
            }
        }
        return true;
    }
    
    private boolean isThreeOfAKind() { // Similar to 4 of a Kind, but 2 
        // consecutive neighbors
        for (int i = 0; i < p.getHand().size()-2 ; i++) { // i.e. 1-2-3, 2-3-4, 
            // or 3-4-5. That's why size()-2 is upper bound and we compare i to
            // i+1 and i+2
            if (p.getHand().get(i).getRank() == 
                p.getHand().get(i+1).getRank()) {
                if (p.getHand().get(i).getRank() == 
                    p.getHand().get(i+2).getRank()) {
                    return true;
                }
            }
        }
        return false;
    }
        
    
    private boolean isTwoPair() { // Finds 2 different pairs
        int firstPairRank = 0; // Will be used in 2nd set of nested for loops
        for (int i = 0; i < p.getHand().size(); i++) { // Nested for loop to 
            // compare each element to the others
            for (int j = 0; j < p.getHand().size(); j++) {
                if (i == j) { // Ignore if i and j are at same index
                    continue;
                } else if (p.getHand().get(i).getRank() == // If the ranks are 
                           // the same, store rank in firstPairRank
                           p.getHand().get(j).getRank()) {
                    firstPairRank = p.getHand().get(i).getRank();
                }
            }
        }
        for (int i = 0; i < p.getHand().size(); i++) { // Repeat nested for 
            // loops
            for (int j = 0;  j < p.getHand().size(); j++) {
                if (i == j || p.getHand().get(i).getRank() == firstPairRank || 
                    p.getHand().get(j).getRank() == firstPairRank) { // Ignore 
                    // if i and j point to same index or if the ranks of i or j 
                    // are the same as the rank that was found before
                    continue;
                } else if (p.getHand().get(i).getRank() == //If the indexed 
                           // ranks are the same and aren't the same as the 
                           // first one
                           p.getHand().get(j).getRank()) {
                    return true;
                }
            }
        }
        return false;
    }
    
    private boolean isPair() { // Finds 1 pair
        for (int i = 0; i < p.getHand().size(); i++) { // Nested for loops to 
            // compare each element to the others
            for (int j = 0; j < p.getHand().size(); j++) {
                if (i == j) { // Ignore if i and j are at same index
                    continue;
                } else if (p.getHand().get(i).getRank() == // If the ranks are 
                           // the same, it's a pair
                           p.getHand().get(j).getRank()) {
                    return true;
                }
            }
        }
        return false;
    }
    
}
