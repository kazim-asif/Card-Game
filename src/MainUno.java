import java.util.Scanner; // Importing Scanner Class

public class MainUno {
    public static void main(String[] args) {

        GameSystem gs; // importing GameSystem
        Displayer ds; // importing Displayer
        Scanner input = new Scanner(System.in); // Making new Scanner

        System.out.println("=====");
        System.out.println("|UNO|");
        System.out.println("=====");

        System.out.print("Enter Number Of Players : ");
        int numPlayers = input.nextInt(); // Getting input from user for number of Players

        if (numPlayers > 1 && numPlayers < 5) { // If statement to ensure the number of Player is valid
            gs = new GameSystem(numPlayers); // Making new GameSystem with valid parameters
            ds = new Displayer(); // Making new Displayer
            gs.SetCurrPlayer(0); // Setting the CurrentPlayer to start with 0

            for (int i = 0; i < numPlayers; i++) { // For Loop to SetHand for each Player
                gs.SetHand(i); // Setting Hand For Each Player taking in i which is the PlayerID
            }

            while (!gs.CheckWinner()) { // Making the game keep looping as long as no one has won
                boolean playedCard = false; // Sentinel Value Of Playing Card
                ds.PrintCardPlayed(gs.GetLastCardPlayed()); // Printing the last card played
               
                System.out.println("Player " + (gs.GetCurrPlayer().GetId() + 1) + "'s" + " Turn :");
//                System.out.println("curr player is "+gs.GetCurrPlayer().GetId());
                ds.PrintDeck(gs.GetCurrPlayer().GetCards()); // Printing the current Player's hand
                
                System.out.println("\n\n=== Option ===");
                System.out.println("1. Play Card ");
                System.out.println("2. Draw Card ");
                System.out.print("Select an Option: ");
                int opt = input.nextInt(); // Getting int option for either Playing a Card or Drawing a Card
                
                while ( !playedCard ) { // Makes sure the player has to play a card to proceed
                    if( opt == 1 ) { // Goes through if the player selects Play Card
                    	
                    	input.nextLine();
                    	
                    	System.out.print("\nSelect Card To Play : ");
                        int cardPos = input.nextInt(); // Getting input on the Position of the card the Player wants to play
                       
                        if (gs.CheckForSwitchColor (gs.GetCurrPlayer().GetCards()[cardPos], cardPos)==true){
                            System.out.println("Choose a color for the card:\nR-Red\nB-Blue\nG-Green\nY-Yellow");
                            System.out.println("Press any integer and then enter alphabet for selection");
                            input.nextInt();
                             String chosenColor = input.next(); // Read user's color choice
                             gs.SwitchColor(gs.GetCurrPlayer().GetCards()[cardPos], cardPos, chosenColor);
                        }
                        
                         int playCard = gs.PlayCard(cardPos - 1,gs);
                       
                         
                        if (playCard == 1) {
                            playedCard = true; // Stops the loop using a sentinel value
                            
                            gs.SwitchPlayer(); // Switches the current Player to the next Player
                        } else if (playCard == -1) { // Goes through if the Player selects an invalid card Position
                            System.out.println("Error : No Card At That Position!");

                        } else if (playCard == -2) { // Goes through if the card played is invalid 
                            System.out.println(" Error : Invalid Card!");
                            break; // Breaks out of the loop
                        }

                        if (gs.CheckWinner()) { // Checks If There's a winner
                            System.out.println("Player " + gs.GetCurrPlayer().GetId() + 1 + " Wins!"); 
                        }
                    }
                    else if( opt == 2 ) { // Goes through if Player Selects Draw Card
                        gs.DrawCard(gs.GetCurrPlayer().GetId()); // Draw Card
                        break; // Breaks out of the loop
                    }

                    else { // Goes through if the player selects an invalid option
                        System.out.println("ERROR : Invalid Option!");
                        break; // Breaks out of the loop
                    }
                }
            }

        } else { // Goes through if number of players is invalid
            System.out.println("ERROR : INVALID NUMBER OF PLAYERS!"); // Error Message
        }

    }
}