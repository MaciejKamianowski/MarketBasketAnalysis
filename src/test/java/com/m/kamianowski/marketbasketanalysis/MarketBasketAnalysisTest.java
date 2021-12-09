package com.m.kamianowski.marketbasketanalysis;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class MarketBasketAnalysisTest {

	@DataProvider
	Object[][] constructorArgumentsDataProvider() {
		return new Object[][] { { null, "," },
				{ new ArrayList<String>(Arrays.asList("item1", "item2", "item3")), null } };
	}

	@Test(dataProvider = "constructorArgumentsDataProvider")
	void should_Throw_Exception_When_Argument_Is_Null(List<String> shoppingOrderList, String separator) {
		// when
		Throwable thrown = catchThrowable(() -> {
			new MarketBasketAnalysis(shoppingOrderList, separator);
		});
		// then
		assertThat(thrown).isInstanceOf(NullPointerException.class);
	}

	@Test
	void should_Throw_IllegalArgumentException_When_List_Is_Empty() {
		// given
		ArrayList<String> arrayList = new ArrayList<>();
		String separator = ",";
		// when
		Throwable thrown = catchThrowable(() -> {
			new MarketBasketAnalysis(arrayList, separator);
		});
		// then
		assertThat(thrown).isInstanceOf(IllegalArgumentException.class);
	}
	
}
