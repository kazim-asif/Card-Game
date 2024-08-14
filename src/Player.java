/* To keep track of a player's information */
public class Player {
    private int playerId; // The player's ID
    private boolean isUno; // Is the Player on One Card
    private Card[] hand; // The player's hand (cards)
    private static final int MAX_HAND_SIZE = 21; // Final static variable for max hand size

    public Player(int id) {
        this.playerId = id; // Assigns playerId
        this.isUno = false; // Assigns default boolean which is not on One Card
        this.hand = new Card[MAX_HAND_SIZE]; // Making a new Card array for Player
    }

    public int GetId() {
        return this.playerId; // returns PlayerID
    }

    public void SetIsUno(boolean isUno) {
        this.isUno = isUno; // Set the player's uno status to be true or false
    }

    public boolean GetIsUno() {
        return this.isUno; // Get if the player is Uno or not ( true or false )
    }

    public Card[] GetCards() { // Getter method for Player's hand
        return this.hand; // returns Player's hand
    }
    public void SetCards(int pos, Card card) { // Setter Method for Player's hand
        hand[pos] = card; // Setting the Player's Hand depending on Pos and Card
    }
}
