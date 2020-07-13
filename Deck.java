
/*****************************************
 * A template for a 52 card deck
 * 
 * @author Cannon
 * @editor Shivansh Srivastava (ss5945)
 * @date 4/13/2020
 
 ****************************************/ 

import java.util.Arrays;
import java.util.Collections;
import java.util.List;


public class Deck {
	
	private Card[] cards;
	private int top = 0; // the index of the top of the deck
    private int cardIdx = 0; // Used for the index of the array of cards being 
    // initialized

	//add more instance variables if needed
	
	public Deck(){
		//make a 52 card deck here
		cards = new Card[52];
        for (int suit = 1; suit < 5; suit++) { // Nested for loop for each 
            // suit/rank
            for (int rank = 1; rank < 14; rank++) {
                cards[cardIdx] = new Card(suit, rank);
                cardIdx++;
            }
        }
        
	}
	
	public void shuffle(){
		//shuffle the deck here
		top = 0; // Resets counter
		List<Card> cardsList = Arrays.asList(cards); // Convert array of cards to an 
        // arraylist to shuffle, then convert back and store in the original array
        Collections.shuffle(cardsList);
        cardsList.toArray(cards);
	}
	
	public Card deal(){
        if (top > 52) { // Shuffle if out of cards
            this.shuffle();
            return cards[top];
        }
        top++;
        return cards[top-1]; // Subtract by 1 to get the previous one after 
        // incrementing top
		//deal the top card in the deck
    }
	

}
