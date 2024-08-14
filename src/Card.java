/* To Create and Keep Track Of Cards */

public class Card {

    public final static int DEFAULT_SPECIAL_CARD_SKIP_VAL = 15; // Final int Value for skip cards.
    public final static int DEFAULT_SPECIAL_CARD_REVERSE_VAL = 16; // Final int Value for reverse cards.
    public final static int DEFAULT_SPECIAL_CARD_DRAW_TWO_VAL = 17; // Final int Value for draw two.
    public final static int DEFAULT_SPECIAL_CARD_DRAW_FOUR_VAL = 18; // Final int Value for draw four.
    public final static int DEFAULT_SPECIAL_CARD_WILD = 19; // Final int Value for wild card.
    public final static int COLORLESS_CARD = 10; // Final int Value for colorless card.
    public final static int RED_CARD = 11; // Final int Value for red card.
    public final static int YELLOW_CARD = 12; // Final int Value for yellow card.
    public final static int GREEN_CARD = 13; // Final int Value for green card.
    public final static int BLUE_CARD = 14; // Final int Value for blue card.


    private int color; // private int for color.
    private int value; // private int for value.

    public Card() {

    }

    public Card( int value, int color ) {
        this.value = value; // assigning value to this.value
        this.color = color; // assigning value to this.color
    }
          
    public void SetColor( int color ) { // Setter Method for Color
        this.color = color; // Assigning param to color
    }

    public void SetValue( int value ) { // Setter Method for Value
        this.value = value; // Assigning param to value
    }
    public int GetColor() { // Getter Method for Color
        return this.color; // Returning this.color
    }
    public int GetValue() { // Getter Method for Value
        return this.value; // Returning this.value
    }

}