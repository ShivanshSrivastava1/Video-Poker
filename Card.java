
/*****************************************
 * A template for a card in a deck of cards
 * 
 * @author Cannon
 * @editor Shivansh Srivastava (ss5945)
 * @date 4/13/2020
 
 ****************************************/ 

public class Card implements Comparable<Card>{
	
	private int suit; //use integers 1-4 to encode the suit
	private int rank; //use integers 1-13 to encode the rank
	
	public Card(int s, int r){
        suit = s;
        rank = r;
		//make a card with suit s and value v
	}
    
    public Card(String cardString) { // Constructor for passing strings 
        // (command line inputs)
        String suitString = cardString.substring(0,1);
        rank = Integer.parseInt(cardString.substring(1,cardString.length()));
        if (suitString.equals("c")) {
            suit = 1;
        } else if (suitString.equals("d")) {
            suit = 2;
        } else if (suitString.equals("h")) {
            suit = 3;
        } else if (suitString.equals("s")) {
            suit = 4;
        }
    }
	
	public int compareTo(Card c){ // Compares cards by rank. If they tie, 
        // compare by suit
		//use this method to compare cards so they 
		//may be easily sorted
		int difference = 0;
        if (this.getRank() > c.getRank()) {
            difference = 1;
        } else if (this.getRank() < c.getRank()) {
            difference = - 1;
        } else if (this.getRank() == c.getRank()) {
            if (this.getSuit() > c.getSuit()) {
                difference = 1;
            } else if (this.getSuit() < c.getSuit()) {
                difference = - 1;
            } else if (this.getSuit() == c.getSuit()) {
                difference = 0;
            }
        }
        return difference;

	}
	
	public String toString(){ // Used for printing the Card object
        String card;
        String suitString = "";
        String rankString = "";
        
        if (suit == 1) {
            suitString = "Clubs";
        } else if (suit == 2) {
            suitString = "Diamonds";
        } else if (suit == 3) {
            suitString = "Hearts";
        } else if (suit == 4) {
            suitString = "Spades";
        }
        
        if (rank == 1) {
            rankString = "Ace";
        } else if (rank == 11) {
            rankString = "Jack";
        } else if (rank == 12) {
            rankString = "Queen";
        } else if (rank == 13) {
            rankString = "King";
        } else {
            rankString = Integer.toString(rank);
        }
        
        card = rankString + " of " + suitString;
        return card;
		//use this method to easily print a Card object
	}
	
    public int getSuit() { // Public accessor method for suit
        return suit;
    }
    
    public int getRank() { // Public accessor method for rank
        return rank;
    }

}
