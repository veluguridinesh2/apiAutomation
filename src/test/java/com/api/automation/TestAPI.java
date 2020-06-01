package com.api.automation;

public class TestAPI{
   public static void main(String[] args){
	   String noOfCardsToDraw = "2";
	   boolean isExtraJokerAdded = true;
	   
	   Utils utils = new Utils();
	   
	   System.out.println("Draw cards from new Deck");
	   utils.drawCardFromNewDeck(noOfCardsToDraw);
	   
	   System.out.println("Creating a new Deck");
	   utils.createNewDeck(isExtraJokerAdded);
	   
   }
}
