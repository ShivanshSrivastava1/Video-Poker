Shivansh Srivastava (ss5945)
COMS W1004
Prof. Cannon
4/13/2020

Programming Project 4 - Video Poker

Instructions: Upon successfully compiling, the program will welcome the user to 
Video Poker. It will also print the user’s initial bankroll (represented as 
tokens) and ask the user how many they would like to bet. The number of betted 
tokens must be at least 1 and at most the same as the user’s total bankroll. 
After inputting a valid number, the player will be shown their initial hand of
5 randomly selected cards. The program will ask the player if they are okay 
with their current hand or if they would like to replace a card. The hand will
be immediately evaluated if the player enters “yes.” However, if the player 
enters the number of the card they would like to replace, that card will be 
randomly swapped for another one. This follows 2 caveats: the player won’t 
know the identity of this new card, and they won’t be able to replace a card 
after replacing it once. After choosing to replace one card, the user will 
be promoted if they are fine with their new hand or if they would like to 
replace another card. The process repeats until the player indicates that 
they are satisfied with their hand. The program will automatically sort the 
hand and present this sorted hand to the user. It will then print what the 
hand evaluated to and the updated bankroll. At this point, the program will
ask the player if they want to play again. The player will be dealt a new 
hand and the deck will be reshuffled if “yes” is entered. If the player 
enters “no,” the game ends and the program prints the final payout. The 
game will also end if they run out of tokens to bet with (their bankroll is 
0). If the player wants to instantly look up a specific hand, they can choose 
to begin the game with a preset hand of their choosing by inputting 5 cards 
separated by spaces on the command line when running the PokerTest file. For 
example, if one wanted to immediately check a royal flush, one could enter 
“java PokerTest s1 s13 s12 s11 s10,” bet their tokens, and refuse to 
replace any cards.

Card.java represents a template for a single card of a 52 card deck. It has 2 
instance variables (suit and rank), which are represented as integers. For 
suits, the integers 1-4 represent clubs, diamonds, hearts, and spades 
respectively. The ranks follow their integers closely, but the integer 1 is used
for Ace, and 11, 12, and 13 are used for Jack, Queen, and King respectively. 
The constructor is overloaded so that cards can be created via integer or string
inputs; string inputs will be appropriately indexed and ultimately converted to
the corresponding integers. A toString() method is also used for easily printing
new cards. For example, a Card object passing in the string “s1” would read 
“Ace of Spades.” Finally, public accessor methods for the integer values of the 
rank and suit are written, as they will be needed for checking the hand in the 
Game class.

Deck.java represents a template for a 52 card deck. Its instance variables are 
an array of type Card, the integer too (which represents the topmost card of the
deck), and another integer cardIdx (which is used for the indexing when 
populating the deck through the constructor). The constructor uses a nested 
for-loop between all possible suits and ranks. It creates the corresponding
Card object and adds it to the cards array using cardIdx. The shuffle() method
relies on converting the array of cards to an array list. This is because the 
Collections.shuffle() method only works on type array list. The shuffled array 
list is then converted back to an array and stored in the original variable 
cards. Last but not least, the deal method increments the value of top and 
returns cards[top-1]. To account for edge cases, the deal method will shuffle
the cards (hence why the shuffle method resets top to 0) and returns the 
topmost card of the reset deck if the top counter ever exceeds the array 
length of 52.

Game.java is a template for a game of poker. The instance variables are p of 
type Player, cards of type Deck, and scan of type Scanner. The constructor is 
again overloaded in order to play the two versions of the game. The first one 
takes an array of strings testHand as a command line input. Using a for-loop, 
the individual string elements of the testHand array will be converted to 
objects of type Card before being added to the player’s hand. The second one 
does not take in any arguments and is used to play a normal game. As before, 
a new player and deck and instantiated and stored in p and cards respectively. 
This time, the cards are shuffled, and the addCard method is used to add the 
top 5 cards of this shuffled deck to the hand. As the name implies, the play 
method plays the game. In order to prevent invalid inputs, it relies on several
Boolean variables and while loops to keep prompting the user until a valid 
input is entered. Specifically, round is used at the end of the game when
a player must choose if they want to play another game. While round is 
true, the game will play, but if the player enters “no,” round will be 
set to false and the game will end. A similar principle is applied to 
properBet, which forces the while loop to continue iterating until the 
player enters a valid input - that is, a number that is at least 1 and at 
most equal to their current bankroll. At that point, it will be set to true, 
and the loop will terminate. This is also used for the Boolean replayChoice, 
which will work in a while loop to continue asking the player if they want to
replay until they enter a proper input. The scanner is used for soliciting input
for the bet amount, hand verification, card replacing, and whether the player 
wants to play again; these are represented by the use of tokens, playerReply,
and gameReply. Speaking of replacing, Booleans are again used for firstReplaced,
secondReplaced, thirdReplaced, fourthReplaced, and fifthReplaced. The player 
can only replace a card once, so these Booleans are initially set to false. 
After the player has replaced them, they are set to true, which prevents them
from being swapped again and prints the appropriate message. If a player 
chooses to play again, the clear method will be used to reset their hand, 
and an additional 5 cards will be dealt before the play loop begins its next
iteration. All Booleans will be reset to their default values at the beginning
of a new iteration, so the results of a previous round won’t impact those of 
the next one.

While the checkHand method will take in an array list of type Card and return a 
string, it will require many private helper methods. On its own checkHand checks
each hand in descending order. When the highest hand has been verified (the 
Boolean of the hand’s check returns true), it updates the player’s winnings 
and returns the string which contains the final result. Before passing the 
hand to this method, the array list will be sorted in ascending order using 
Collections.sort. This will allow the hand to be evaluated easily. The first
helper method checks for a royal flush. This is defined as a hand with an 
Ace, 10, Jack, Queen, and King of the same suit. Essentially, this is the 
same as verifying that the hand is a Straight Flush and the rank of the 
first card in hand is equal to 1 (the Ace’s), so this method can be written 
in one line. Similarly, the method that checks for a Straight Flush can also 
be written in one line, as it requires the hand to fulfill the criteria for 
both a Straight (5 cards in a sequence but not of the same suit) and a Flush 
(5 cards of the same suit but not in a sequence). The method for 4 of a Kind 
requires some more work. For the first 2 cards in the array, it will check to
see if the 4 cards adjacent to it are of the same rank (i.e. 1-2-3-4 or 
2-3-4-5). Cards behind the second one will be excluded, as they will have 
already been checked by the first two, and the indices would be out of bounds. 
This method only works because the hand will be sorted in order by rank )and by
suit if there is a tie). A Full House requires 3 of a Kind with a Pair is a 
different rank. To easily check this, the method verified that the first and 
second cards have the same rank. If this is true, it then verifies that the 
fourth and fifth cards have the same rank. If this is also true, it checks if 
the third card’s rank matches either the first pair or the second pair. Again, 
this is possible due to the hand being sorted. As previously stated, Flush 
requires all cards to be of the same suit, so the method just has to ensure 
that all of the cards in the hand have the same suit. The Straight requires 
more thought to implement due to the fact that an Ace can act as a either a 
low card or a high card depending on how it’s most beneficial to the player’s 
hand. If the first card’s rank is 1 (an Ace), it will check if the next card’s 
rank is not 2 and if the card’s rank is not 13. If this conjunction is true, 
it will return false. However, to account for this method’s role in the Royal 
Flush check, it will also return true if the band’s next ranks are 10, 11, 12, 
and 13. For all other cases, the method will verify that the ranks of all the 
cards increment by 1; if this is the case it will return true. Otherwise, it 
will return false. The method for checking 3 of a Kind is almost identical to 
that of 4 of a Kind, but the difference is that the first 3 cards must be 
checked against the 2 cards adjacent to it (i.e. 1-2-3, 2-3-4, or 3-4-5). 
The method for a 2 Pair involves first declaring an integer firstPairRank. 
Using a nested for-loop with integers i and j, every element of the hand will 
be compared to the remaining neighbors. If the i and j point to the same index, 
the method skips over them using continue. If they point to different indices 
and their indexed elements’ ranks are the same, the rank will be stored in 
firstPairRank. The process will then be repeated, but now the method will skip 
the iteration using continue if the new i and j point to the same index or if 
the indexed rank of the new i or j is the same as firstPairRank. If these 
conditions aren’t met and the new i and j’s indexed elements’ ranks are equal
to each other, the method will return true. The method for a single Pair will 
repeat the first half of the method for a 2 Pair. If none of these methods 
return true, the game will default to a High Card. Depending on the outcomes 
of these tests, the program will print out the user’s sorted final hand along 
with the updated bankroll. It will then ask the player if they want to play 
again. If the player enters “no,” the game ends and the final payout is printed.
If they enter “yes,” the hand is cleared, the deck is shuffled, and a new game 
begins.

Player.java contains the template for the human poker player. The instance 
variables include hand (an array list of type Card), the double bankroll, 
the double bet, and the integer removedIdx). The bankroll is initially set
to 5.0 tokens. The bet is initially 0.0, as it will be updated by the scanner
input. The method addCard takes in a Card c as a parameter and adds it to the 
hand. The method removeCard also takes in a Card c, but it does not remove the
card from the list. Using hand.remove for this method could cause complications.
Because the player only sees their initial and final hands, and because the 
combination of the basic remove and add methods could potentially change the
positions of the untouched cards (thereby not producing a simple swap), 
removeCard only assigns the value of the Card c to removedIdx. The bets 
and winnings methods update the bankroll accordingly after the player 
makes a bet or plays a successful hand. getBankroll(), getRemovedIdx() 
and getHand() are all public accessor methods which allow the bankroll, 
removed index, and hand to be used in the Game class.

Test Cases:
Royal Flush - s1 s13 s12 s11 s10
Straight Flush - s8 s7 s6 s5 s4
Four of a Kind - h11 d11 s11 c11 d7
Full House - h10 d10 s10 c9 d9
Flush - s4 s11 s8 s2 s9
Straight - c9 d8 s7 d6 h5
Three of a Kind - c7 d7 s7 c13 d3
Two Pair - c4 s4 c3 d3 c12
Pair - h1 d1 c8 s4 h7
High Card - d3 c11 s8 h4 s2

Sources:
set method for replacing elements in an ArrayList: 
https://www.google.com/amp/s/howtodoinjava.com/java/collections/arraylist/add-replace-element-at-index/amp/
