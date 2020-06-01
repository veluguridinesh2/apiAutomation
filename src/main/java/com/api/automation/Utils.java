package com.api.automation;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class Utils {
	
	private String url="";
	
	private void setURL(String setUrl){
		url = setUrl;
	}
	private String getURL(){
		return url;
	}
	/**
	 * Function: To return API response  
	 * Parameter: con
	 * Description: process the API response and return them 
	 * 
	 * */
	private String getResponseCode(HttpURLConnection con){
		StringBuffer response = new StringBuffer();
		try{
			BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
			String inputLine;
			
	
			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();
				 
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
		return response.toString();
	}
	
	/**
	 * Function: To send API request 
	 * Parameter: header, queryParam, apiRequestType
	 * Description: send API requests
	 * 
	 * */
	private void requestAPI(Map<String,String> header,Map<String,String> queryParam,String apiRequestType) {
		// http connection
		try {
			String responseBody ="";
			URL obj = new URL(this.getURL());
			HttpURLConnection con = (HttpURLConnection) obj.openConnection();
			con.setRequestMethod(apiRequestType);
			con.setRequestProperty("User-Agent", "Mozilla/5.0");
			
			
			for(Map.Entry<String,String> e:header.entrySet()){
				con.setRequestProperty(e.getKey(), e.getValue());
			}
			
			for(Map.Entry<String,String> e:queryParam.entrySet()){
				con.setRequestProperty(e.getKey(), e.getValue());
			}
			
			int responseCode = con.getResponseCode();
			System.out.println("Request URL::  " + this.getURL());
			System.out.println("Response Code for "+apiRequestType+" request:: " + responseCode);
			
			
			if (responseCode == HttpURLConnection.HTTP_OK) {
				responseBody = this.getResponseCode(con);
				System.out.println(responseBody);
			}
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	/**
	 * Function: To draw card from new Deck
	 * Parameter: count
	 * Description: create new Deck and draw cards from it
	 * 
	 * */
	public void drawCardFromNewDeck(String count) {
		this.setURL(Config.DECK_OF_CARDS+Uri.DRAW_CARD_NEW_DECK);
		
		//add headers 
		Map<String,String> header = new HashMap<String,String>();
		
		//add query parameters
		Map<String,String> queryParam = new HashMap<String,String>();
		queryParam.put("count", count);
		System.out.println("No of card drawn from new Deck:: "+count);
		
		this.requestAPI(header,queryParam,Config.GET_API_REQUEST);
		
	}

	/**
	 * Function: To draw card from old Deck
	 * Parameter: deckId,count
	 * Description: from an old Deck draw cards
	 * 
	 * */
	
	public void drawCardFromDeck(String deckId,String count) {
		
		this.setURL(Config.DECK_OF_CARDS+String.format(Uri.DRAW_CARD_DECK,deckId));
		
		//add headers 
		Map<String,String> header = new HashMap<String,String>();
		
		//add query parameters
		Map<String,String> queryParam = new HashMap<String,String>();
		queryParam.put("count", count);
		System.out.println("No of card drawn from new Deck:: "+count);
		
		this.requestAPI(header,queryParam,Config.GET_API_REQUEST);

	}

	/**
	 * Function: To create New Deck
	 * Parameter: isExtraJokerAdded
	 * Description: To add Extra joker if needed
	 * 
	 * */
	public void createNewDeck(boolean isExtraJokerAdded) {
		this.setURL(Config.DECK_OF_CARDS+Uri.CREATE_NEW_DECK);
		
		//add headers 
		Map<String,String> header = new HashMap<String,String>();
		
		//add query parameters
		Map<String,String> queryParam = new HashMap<String,String>();
		
		/*Tried sending "jokers_enabled" with query parameter 
		 * but this is not working
		 * so have to add this in url*/ 
		
		if(isExtraJokerAdded){
			this.setURL(Config.DECK_OF_CARDS+Uri.CREATE_NEW_DECK+"?jokers_enabled=true");
		}
		
		this.requestAPI(header,queryParam,Config.GET_API_REQUEST);
	}
	
	

}
