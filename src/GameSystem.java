import java.util.Iterator;

/* Manages the game and all of its attributes */

public class GameSystem {
    private Player currPlayer; // Variable for storing the currentPlayer
    private Card[] deck; // Array for storing all cards in an Uno Deck
    private Player[] allPlayers; // Array for storing all players
    private Player winner; // Variable for storing the winner
    private Card lastCardPlayed; // Variable for storing the last Card Played


    //=================== CONSTRUCTOR =====================//
    /* The constructor to initialize the Uno Game. */
    public GameSystem() {
    }

    public GameSystem(int numPlayer) {

        allPlayers = new Player[numPlayer]; // Making new Array to Store the Players

        for (int i = 0; i < allPlayers.length; i++) { // For Loop to Go through all positions of numPlayer
            allPlayers[i] = new Player(i); // Creating new Players
        }
        createCards(); // Invoking Private Method to Create all the Cards in a UNO Deck
        lastCardPlayed = null; // Setting the lastCardPlayed to be null as there is no lastCardPlayed
    }

    //================== PRIVATE METHODS ====================//

    private void createCards() {
        deck = new Card[108]; // Total number of cards in a standard Uno deck
        int cardIndex = 0;
        /*Creating regular cards*/
        for (int color = Card.RED_CARD; color <= Card.BLUE_CARD; color++) {//Loop four times to create cards of red, green, yellow,  blue
            for (int value = 0; value < 10; value++) {//Loop ten times for to create cards that have a value of 0-9
                deck[cardIndex] = new RegularCards(value, color);
                cardIndex++;
            }
        }

        for (int color = Card.RED_CARD; color <= Card.BLUE_CARD; color++) {//Loop 4 times to create cards of red, green, yellow,  blue
            for (int value = 1; value < 10; value++) {//Loop nine times for to create cards that have a value of 1-9
                deck[cardIndex] = new RegularCards(value, color);
                cardIndex++;
            }
        }
        /*Creating special cards*/
        for (int i = 0; i < 2; i++) {//Loop 2 times to create 2 same cards for each color and value
            for (int color = Card.RED_CARD; color <= Card.BLUE_CARD; color++) {
                for (int value = Card.DEFAULT_SPECIAL_CARD_SKIP_VAL; value < Card.DEFAULT_SPECIAL_CARD_DRAW_FOUR_VAL; value++) {
                    deck[cardIndex] = new SpecialCards(value, color);
                    cardIndex++;

                }
            }
        }
        for (int i = 0; i < 4; i++) {//Loop 4 times to create 4 draw four cards
            deck[cardIndex] = new SpecialCards(Card.DEFAULT_SPECIAL_CARD_DRAW_FOUR_VAL, Card.COLORLESS_CARD);
            cardIndex++;
        }
        for (int i = 0; i < 4; i++) {//Loop 4 times to create 4 wild cards
            deck[cardIndex] = new SpecialCards(Card.DEFAULT_SPECIAL_CARD_WILD, Card.COLORLESS_CARD);
            cardIndex++;

        }
    }

        private void moveCardsOrder (Card[]hand){
            int numberIndex = 0;
            for (int i = 0; i < hand.length; i++) {//Loop through all the cards in the player's hand
                if (hand[i] != null) {//Check if number at index is not null
                    if (i != numberIndex) {//Check if position i is not numberInex
                        /* Swap cards at position i with card at position numberIndex*/
                        Card temp = hand[i];
                        hand[i] = hand[numberIndex];
                        hand[numberIndex] = temp;
                    }
                    numberIndex++;// Increase numberIndex after swapping operation to prepare for the next swapping
                }
            }
        }

        private void addCardToDeck (Card card ){

            boolean success = false;//Set boolean success to false, to track if card is successfully added to the deck

            while (!success) {
                int randomNumber = (int) (Math.random() * 108);//Random number between 0 and 108 are generated
                if (deck[randomNumber] == null) {//If the randomNumber position is null in the deck
                    deck[randomNumber] = card; //Then the card in the randomNumber position in the deck is assigned to be the card from the parameter
                    success = true;//boolean succes is true as card is succesfully added to the deck
                }
            }
        }

    //================== PUBLIC METHODS ====================//

        public void SetHand ( int playerId ){ // Method to Set the Hand of a Player based on PlayerID

            for (int i = 0; i < 7; ) { // For Loop to Go Through all Number Of Default starting Cards
                int randomNumber = (int) (Math.random() * 108); // Making a random position for the cards to be assigned
                if (deck[randomNumber] != null) { // Making sure the randomNumber is not linked to a null Card
                    GetPlayer(playerId).SetCards(i, deck[randomNumber]); // Assigning Cards to the deck
                    deck[i] = null; // Setting the assigned card to null so that it can't be assigned again
                    i++;
                }
            }
        }



        public Card[] GetHand ( int playerId ){ // Method to Get A Player's Hand based on PlayerID
            return GetPlayer(playerId).GetCards(); // Returns a Player's Hand
        }

        public void DrawCard ( int playerId ){ 
            boolean success = false;
            while (!success) {
                int randomNumber = (int) (Math.random() * 108); // Making a random position between 0 and 107 for the cards to be assigned
                if (deck[randomNumber] != null) { // Making sure the randomNumber is not linked to a null Card
                    for (int i = 0; i < GetPlayer(playerId).GetCards().length; i++) {
                        if (GetPlayer(playerId).GetCards()[i] == null) {
                            GetPlayer(playerId).SetCards(i, deck[randomNumber]); // Assigning Cards to the deck
                            deck[randomNumber] = null; // Setting the assigned card to null so that it can't be assigned again
                            success = true;
                            break;
                        }
                    }
                }
            }
        }
        
          
        public int PlayCard(int pos, GameSystem gs) {
            
        	Card card = currPlayer.GetCards()[pos];

//            for(int i=0;i<=pos;i++) {
//            	Card c = currPlayer.GetCards()[i];
//            	if(c!=null) {
//            		System.out.println("i= "+i+", "+c.GetColor()+", value ="+c.GetValue());
//            		card=c;
//            	}
//            }
//            
//            System.out.println("pos="+pos);
            
            
            if (card != null) {
              if (CheckCardValidity(card) && card instanceof SpecialCards) {
                
                  SpecialCards specialCard = (SpecialCards) card;
                  specialCard.SetAction(card.GetValue(),gs);
                  specialCard.GetAction();
                  
                this.lastCardPlayed = card;
                addCardToDeck(card);
                currPlayer.GetCards()[pos] = null;
                moveCardsOrder(currPlayer.GetCards());
                
                return 1;
                
              }
              else {
                return -2;
              }
            
            }
            return -1;
          
          }

        
        public void SetCurrPlayer ( int playerId ){
            this.currPlayer = GetPlayer(playerId); // Setting current player according to the player Id
        }

        public void SetAllPlayers (Player[]allPlayers){
            this.allPlayers = allPlayers;//Setting all players to be all the players gotten from the array in the parameter
        }

        public Player GetCurrPlayer () { // Method to Get Current Player
            return this.currPlayer; // returns this.currPlayer
        }

        public Player GetPlayer ( int playerId ){ // Method to GetPlayer based on PlayerId
            for (int i = 0; i < allPlayers.length; i++) { // Goes through the allPlayers array
                if (allPlayers[i].GetId() == playerId) { // Checking if the playerId's match
                    return allPlayers[i]; // Returning the Player that has the same Id as the param
                }
            }
            return null; // returns null if no Player matches the playerId
        }

        public Player[] GetAllPlayers () {
            return allPlayers;  //Return the arrray containing all the players
        }

        public void SwitchPlayer () {
            this.currPlayer = GetPlayer( GetNextPlayerID() );//Set the current player to the next player by getting the next player's object 
        }
        public int GetNextPlayerID() {

            int nextPlayerID;// Initialize the variable for the next player's id

            if (currPlayer.GetId() < allPlayers.length - 1) {//Check if the current player's Id is is two index less than the number of player
                nextPlayerID = currPlayer.GetId() + 1; //Set the current player to be the player in the index after the player that has played
            } else {
                nextPlayerID = 0;//If the current player has the Id that is 1 index away from the player's length, the next player will be player 0
            }

            return nextPlayerID;
        }
        
        public boolean CheckCardValidity (Card card ){
           if (this.lastCardPlayed == null || card.GetValue()  
        		   == this.lastCardPlayed.GetValue() || card.GetColor()   == this.lastCardPlayed.GetColor()
                    || card.GetColor() == Card.COLORLESS_CARD) { // Checks if either the value/ color/ if it's a colourless card
                return true; // returns true if card can be played
            } else {
                return false; // returns false if card can't be played
            }
        }
       
        public boolean CheckForSwitchColor (Card card, int cardPos){
        	
//        	System.out.println(GetCurrPlayer().GetCards()[cardPos].GetValue());
        	
          if (GetCurrPlayer().GetCards()[cardPos] instanceof SpecialCards || GetCurrPlayer().GetCards()[cardPos].GetValue()==18
        		  || GetCurrPlayer().GetCards()[cardPos].GetValue()==19)
          {  
            SpecialCards specialCard = (SpecialCards) GetCurrPlayer().GetCards()[cardPos];
            
//            System.out.println(" \nvalue = \n "+specialCard.GetValue());
            
            if (specialCard.GetValue() == Card.DEFAULT_SPECIAL_CARD_WILD ||specialCard.GetValue() == Card.DEFAULT_SPECIAL_CARD_DRAW_FOUR_VAL) {
//              System.out.println(specialCard.GetValue());
            	return true;
            }
            return false;
          }
          return false;
        }
        
        public void SwitchColor(Card card, int cardPos, String chosenColor){
          int colorInt;
          SpecialCards specialCard = (SpecialCards) GetCurrPlayer().GetCards()[cardPos];
          if (chosenColor=="R"){
            colorInt=Card.RED_CARD;
            specialCard.SetColor(colorInt);
          }
          if (chosenColor=="B"){
            colorInt=Card.BLUE_CARD;
            specialCard.SetColor(colorInt);
          }
          if (chosenColor=="G"){
            colorInt=Card.GREEN_CARD;
            specialCard.SetColor(colorInt);
          }
          if (chosenColor=="Y"){
            colorInt=Card.YELLOW_CARD;
            specialCard.SetColor(colorInt);
          }
        }
        
        public boolean CheckUno () {
            int counter = 0;//Initialize the counter value to be 0

            for (int i = 0; i < currPlayer.GetCards().length; i++) {//Loop through every card the current player has
                if (currPlayer.GetCards()[i] != null) {//Check if there is any cards that are not null
                    counter++;//Increase the value of counter if there is a card that is not null present
                }
            }

            if (counter == 1) {
                return true; //return true if the player has only one card that is not null
            } else {
                return false;//return false if the player has more than one card that is not null
            }
        }

        
        public boolean CheckWinner () {
        
          for (int i = 0; i < GetHand(GetCurrPlayer().GetId()).length; i++) {
            if (GetHand(GetCurrPlayer().GetId())[i] != null) {
              return false;
            }
          }
          this.winner= this.currPlayer;
          return true;
        }
        
        public Card GetLastCardPlayed () {
            return this.lastCardPlayed; // returning the last card played
        }
}