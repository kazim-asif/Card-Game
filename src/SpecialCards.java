public class SpecialCards extends Card {
    private String action;
    GameSystem gs ;
   
//    SpecialCards(int x){
//      gs= new GameSystem();
//    }
   
    SpecialCards( int value, int color){
        super(value, color);
        if(gs==null)
        	gs= new GameSystem();
    }
   
    public String GetAction(){
        return this.action;
    }
   
    public void SetAction(int value, GameSystem gs){
    	
    	this.gs=gs;
//    	System.out.println("value = "+value);

        if (value == DEFAULT_SPECIAL_CARD_SKIP_VAL ){//Check if the value of the card is the default skip value
            gs.SwitchPlayer();//Skip the next player by switching player
          this.action = "Skip the next player";//Set the action
        }
        else if (value == DEFAULT_SPECIAL_CARD_REVERSE_VAL){//Check if the value of the card is the defaults reverse value
            Player[] allPlayers = gs.GetAllPlayers();//Get all the players
            int currPlayerIndex = gs.GetCurrPlayer().GetId();//Get the current player's index

            int length = allPlayers.length;
            
            Player []reversedPlayers = new Player [length];//Create object for reversedPlayers array
            
            
            for (int i = 0; i<length ; i++){
//                reversedPlayers[i]= allPlayers[(currPlayerIndex - i + allPlayers.length)%allPlayers.length];
            	//Reverse the order of the players
            	reversedPlayers[length-1-i] = allPlayers[i];
            }
            
            gs.SetAllPlayers(reversedPlayers);// Update the player order
            
          this.action = "Reverse the order of the players";//Set the action

        }
        else if (value == DEFAULT_SPECIAL_CARD_DRAW_TWO_VAL){//Check if the value of the card is the default draw two value
            for (int i=0; i<2 ;i++){//Loop two times to draw card two times
                gs.DrawCard( gs.GetNextPlayerID() );//Make the next player draw cards from the deck
              this.action = "Draw two cards from the deck";//Set the action
            }

        }
        else if (value == DEFAULT_SPECIAL_CARD_DRAW_FOUR_VAL){//Check if the value of the card is the default draw four value
            for (int i=0; i<4 ;i++){//Loop four times to draw card four times
                gs.DrawCard( gs.GetNextPlayerID() );//Make the next player draw cards from the deck
              this.action = "Draw four cards from the deck";//Set the action
            }
        }
        else if (value == DEFAULT_SPECIAL_CARD_WILD){
          this.action = "Change color for the next card";//Set the action to let the current player choose what they want for the color of the next card

        }

    }
}