package org.third.thirdseminar.dto.response;

public record CardDto(String cityName, String country, String startDate, String endDate, String companyAir, String minPriceString) {
	public static CardDto of(String cityName, String country,String startDate,String endDate, String companyAir, String minPriceString){
		return new CardDto(cityName, country, startDate, endDate, companyAir, minPriceString);
	}
}
