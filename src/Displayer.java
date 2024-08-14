 public class Displayer {
    

    private String colorToString(Card card) {
        String color = null;

        if(card.GetColor() == Card.RED_CARD) {
            color = "RED"; // For Printing a Cards Color instead of the int value of the color
        }
        else if(card.GetColor() == Card.YELLOW_CARD) {
            color = "YELLOW"; // For Printing a Cards Color instead of the int value of the color
        }
        else if(card.GetColor() == Card.GREEN_CARD) {
            color = "GREEN"; // For Printing a Cards Color instead of the int value of the color
        }
        else if(card.GetColor() == Card.BLUE_CARD) {
            color = "BLUE"; // For Printing a Cards Color instead of the int value of the color
        }
        else if(card.GetColor() == Card.COLORLESS_CARD) {
            color = "WILD"; // For Printing a Cards Color instead of the int value of the color
        }

        return color;
    }

    private String valToString(Card card) {
        String value = null;

        if(card.GetValue() == Card.DEFAULT_SPECIAL_CARD_DRAW_FOUR_VAL) {
            value = "+4"; // For Printing a Special Cards Type instead of the int value of the Special Card
        }
        else if(card.GetValue() == Card.DEFAULT_SPECIAL_CARD_DRAW_TWO_VAL) {
            value = "+2"; // For Printing a Special Cards Type instead of the int value of the Special Card
        }
        else if(card.GetValue() == Card.DEFAULT_SPECIAL_CARD_REVERSE_VAL) {
            value = "Reverse"; // For Printing a Special Cards Type instead of the int value of the Special Card
        }
        else if(card.GetValue() == Card.DEFAULT_SPECIAL_CARD_SKIP_VAL) {
            value = "Skip"; // For Printing a Special Cards Type instead of the int value of the Special Card
        }

        else if(card.GetValue() == Card.DEFAULT_SPECIAL_CARD_WILD) {
            value = "Wild";
        }
        else { // Goes through if the card is not a special Card
            value = card.GetValue() + ""; // converting the Regular Card's value to a String
        }

        return value;

    }

    /* Prints the current Card Played and its content on the screen
     * @param card - The Card object 1D array. */
    public void PrintCardPlayed( Card card ) { // Method for Displaying the Last Card Played
        if(card == null) {
            System.out.println("--------------");
            System.out.println("|            |");
            System.out.println("|    Play    |");
            System.out.println("|  Starting  |");
            System.out.println("|    Card    |");
            System.out.println("|            |");
            System.out.println("--------------");
        }

        else {
            String color = colorToString(card);
            String value = valToString(card);

            System.out.println("--------------");
            System.out.println("|            |");
            System.out.printf("|   %-7s  |\n", color); // Printing the color of the card
            System.out.println("|            |");
            System.out.printf("|   %-7s  |\n", value); // Printing the value of the card
            System.out.println("|            |");
            System.out.println("--------------");
        }

    }

    /* Prints the scores of the two players
     * @param card - Current Player's Deck */
    public void PrintDeck( Card[] card ) { // Method for displaying a Player's hand
        int count = 0;
        for( int i = 0; i < card.length; i++ ) { // For Loop to go through all the players cards
            if(card[i] != null) { // To ensure it does not display null cards
                count++;
            }
        }

        for( int i = 0; i < count; i++ ) {
            System.out.printf("%2d. --------------  ", i + 1);
        }

        System.out.println("");

        for( int i = 0; i < count; i++ ) {
            System.out.print("    |            |  ");
        }

        System.out.println("");

        for( int i = 0; i < count; i++ ) {
            String color = colorToString(card[i]);
            System.out.printf("    |   %-7s  |  ", color);
        }

        System.out.println("");

        for( int i = 0; i < count; i++ ) {
            System.out.print("    |            |  ");
        }

        System.out.println("");

        for( int i = 0; i < count; i++ ) {
            String value = valToString(card[i]);
            System.out.printf("    |   %-7s  |  ", value);
        }

        System.out.println("");

        for( int i = 0; i < count; i++ ) {
            System.out.print("    |            |  ");
        }

        System.out.println("");

        for( int i = 0; i < count; i++ ) {
            System.out.print("    --------------  ");
        }
    }
}