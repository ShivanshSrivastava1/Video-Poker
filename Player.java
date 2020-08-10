
/*****************************************
 * A template for a poker player
 * 
 * @author Shivansh Srivastava (ss5945)
 * @date 4/13/2020
 
 ****************************************/

import java.util.ArrayList;

public class Player {
	
		
	private ArrayList<Card> hand; //the player's cards
	private double bankroll;
    private double bet;
    private int removedIdx;

	//you may choose to use more instance variables
		
	public Player(){		
	    //create a player here
	    hand = new ArrayList<Card>();
        bankroll = 5.0;
        bet = 0.0;
	}

	public void addCard(Card c){
	    //add the card c to the player's hand
	    hand.add(c);
	}

	public void removeCard(Card c){
	    //remove the card c from the player's hand
	    removedIdx = hand.indexOf(c); // Actually more of a swap method, so need 
        // index
    }
		
    public void bets(double amt){
        //player makes a bet
        bankroll -= amt;
        bet = amt;
    }

    public void winnings(double odds){
        //adjust bankroll if player wins
        bankroll += bet * odds;
    }

    public double getBankroll(){
        //return current balance of bankroll
        return bankroll;
    }
    
    public int getRemovedIdx() {
        // Public accessor method for the removed card's index
        return removedIdx;
    }

    public ArrayList<Card> getHand() {
        // Public accessor method for the hand
        return hand;
    }

}


