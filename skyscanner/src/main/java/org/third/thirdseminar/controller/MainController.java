package org.third.thirdseminar.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.third.thirdseminar.common.ApiResponse;
import org.third.thirdseminar.controller.dto.response.CardListDto;
import org.third.thirdseminar.exception.Success;
import org.third.thirdseminar.service.MainService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class MainController {

	private final MainService mainService;

	@GetMapping("/main")
	public ApiResponse<CardListDto> getCards(){
		return ApiResponse.success(Success.GET_MAIN_SUCCESS,CardListDto.of(mainService.getCards()));
	}
}
