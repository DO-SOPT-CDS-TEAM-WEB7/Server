package org.third.thirdseminar.controller.dto.response;

import java.util.List;

public record CardListDto(List<CardDto> cards) {
	public static CardListDto of(List<CardDto> cards){
		return new CardListDto(cards);
	}
}
