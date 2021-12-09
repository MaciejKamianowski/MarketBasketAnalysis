package com.m.kamianowski.marketbasketanalysis;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class MarketBasketAnalysis {
	private List<List<String>> dataToAnalyse;
	private List<String> uniqueItemList;
	private long numberOfTransactions;
	
	public MarketBasketAnalysis(List<String> shoppingOrderList, String separator) {
		validate(shoppingOrderList, separator);
		dataToAnalyse = getSeparatedData(shoppingOrderList, separator);
		numberOfTransactions = (long) dataToAnalyse.size();
		uniqueItemList = dataToAnalyse.stream().flatMap(List::stream).distinct()
				.collect(Collectors.toList());
	}

	public void runApriori() {
		System.out.println("Transactions: " + numberOfTransactions);
		System.out.println("All items: " + uniqueItemList);
//		calculate support for every item
		for (var item : uniqueItemList) {
			double support = calculateSupport(item);
			System.out.println("Support for item: \'" + item + "\'");
			System.out.println("Is equal to: " + support);
			System.out.println();
		}
//		calculate confidence item1 -> item2
		
	}

	private double calculateSupport(String item) {
		double support = 0.0;
		if (item != null) {
			long howManyTimes = 0;
			for (var transactionItemsList : dataToAnalyse) {
				boolean listContainsItem = false;
				if (!listContainsItem && transactionItemsList.contains(item) ) {
					howManyTimes++;
					listContainsItem = true;
				}
			}
			support = (double)howManyTimes / (double)numberOfTransactions;
		}
		return support;
	}
	
	
	public void showData() {
		for (var nestedList : dataToAnalyse) {
			for (var item : nestedList) {
				System.out.print(item + " ");
			}
			System.out.println();
		}
	}

	private void validate(List<String> shoppingOrderList, String separator) {
		if (shoppingOrderList == null) {
			throw new NullPointerException("ShoppingOrderList cannot be null");
		}
		if (separator == null) {
			throw new NullPointerException("Separator cannot be null");
		}
		if (shoppingOrderList.isEmpty()) {
			throw new IllegalArgumentException("ShoppingOrderList cannot be empty");
		}
	}

	private List<List<String>> getSeparatedData(List<String> shoppingOrderList, String separator) {
		List<List<String>> listOfTrimmedData = new ArrayList<>();
		for (var itemsWithSeparator : shoppingOrderList) {
			listOfTrimmedData.add(Arrays.asList(itemsWithSeparator.split(separator)));
		}
		return listOfTrimmedData;
	}
}
