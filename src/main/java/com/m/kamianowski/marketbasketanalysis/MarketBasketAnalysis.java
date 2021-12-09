package com.m.kamianowski.marketbasketanalysis;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author maciejkamianowski
 *
 */
public class MarketBasketAnalysis {
	private List<List<String>> dataToAnalyse;
	private List<String> uniqueItemList;
	private long numberOfTransactions;

	/**
	 * @param shoppingOrderList
	 * @param separator
	 */
	public MarketBasketAnalysis(List<String> shoppingOrderList, String separator) {
		validate(shoppingOrderList, separator);
		dataToAnalyse = getSeparatedData(shoppingOrderList, separator);
		numberOfTransactions = (long) dataToAnalyse.size();
		uniqueItemList = dataToAnalyse.stream().flatMap(List::stream).distinct().collect(Collectors.toList());
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
//		A, B, C, E, Z
//		
//		A -> B, A -> C, A -> E, A -> Z
//		B -> A, B -> C, B -> E, B -> Z
//		C -> A, C -> B, C -> E, C -> Z
//		E -> A, E -> B, E -> C, E -> Z
//		Z -> A, Z -> B, Z -> C, Z -> E
		for (var itemFirst : uniqueItemList) {
			for (var itemSecond : uniqueItemList) {
				if (!itemFirst.equals(itemSecond)) {
					double confidence = calculateConfidence(itemFirst, itemSecond);
					System.out.println("Confidence " + itemFirst + " -> " + itemSecond + " is equal to " + confidence);
				}
			}
		}
	}

	/**
	 * @param itemFirst
	 * @param itemSecond
	 * @return
	 */
	private double calculateConfidence(String itemFirst, String itemSecond) {
		double confidence = 0.0;
		if (itemFirst != null && itemSecond != null) {
//			calculate transactions with both items
			long howManyTimesBothItemsAppear = 0;
			long howManyTimesFirstItemAppear = 0;
			for (var itemList : dataToAnalyse) {
				if (itemList.contains(itemFirst)) {
					howManyTimesFirstItemAppear++;
					if (itemList.contains(itemSecond)) {
						howManyTimesBothItemsAppear++;
					}
				}
			}
			confidence = (double) howManyTimesBothItemsAppear / howManyTimesFirstItemAppear;
		}
		return confidence;
	}

	private double calculateSupport(String item) {
		double support = 0.0;
		if (item != null) {
			long howManyTimes = 0;
			for (var transactionItemsList : dataToAnalyse) {
				boolean listContainsItem = false;
				if (!listContainsItem && transactionItemsList.contains(item)) {
					howManyTimes++;
					listContainsItem = true;
				}
			}
			support = (double) howManyTimes / (double) numberOfTransactions;
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
