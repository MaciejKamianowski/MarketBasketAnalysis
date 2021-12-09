package com.m.kamianowski.marketbasketanalysis;

import java.util.ArrayList;

public class MarketBasketAnalysisExample {
	public static void main(String[] args) {
		ArrayList<String> shoppingList = new ArrayList<>();
		shoppingList.add("A,B,C,A,A");
		shoppingList.add("A,C,D");
		shoppingList.add("A,C,D");
		shoppingList.add("B,C,D");
		shoppingList.add("A,D,E");
		shoppingList.add("B,C,E");
		shoppingList.add("Z,B,E");
		MarketBasketAnalysis marketBasketAnalysis = new MarketBasketAnalysis(shoppingList, ",");
		marketBasketAnalysis.showData();
		marketBasketAnalysis.runApriori();
		
	}
}
