package com.m.kamianowski.marketbasketanalysis;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MarketBasketAnalysis {
	private List<List<String>> dataToAnalyse;

	public MarketBasketAnalysis(List<String> shoppingOrderList, String separator) {
		validate(shoppingOrderList, separator);
		dataToAnalyse = getSeparatedData(shoppingOrderList, separator);
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
