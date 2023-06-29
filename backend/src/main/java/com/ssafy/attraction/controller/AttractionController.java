package com.ssafy.attraction.controller;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.attraction.model.AttractionDto;
import com.ssafy.attraction.model.GugunDto;
import com.ssafy.attraction.model.SidoDto;
import com.ssafy.attraction.model.service.AttractionService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/attraction")
@Api(tags = { "관광지 정보" })
public class AttractionController {

	private final Logger logger = LoggerFactory.getLogger(AttractionController.class);

	private AttractionService attractionService;

	@Autowired
	public AttractionController(AttractionService attractionService) {
		super();
		this.attractionService = attractionService;
	}

	@ApiOperation(value = "시도", notes = "시도 정보를 가져옵니다.")
	@ApiResponses({ @ApiResponse(code = 200, message = "시도 가져오기 OK"), @ApiResponse(code = 500, message = "서버 오류") })
	@GetMapping("/sido")
	public ResponseEntity<?> getSido() {
		logger.debug("getSido");
		try {
			List<SidoDto> sido = attractionService.getSido();
			return new ResponseEntity<List<SidoDto>>(sido, HttpStatus.OK);
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}

	@ApiOperation(value = "구군", notes = "구군 정보를 가져옵니다.")
	@ApiResponses({ @ApiResponse(code = 200, message = "구군 가져오기 OK"), @ApiResponse(code = 500, message = "서버 오류") })
	@GetMapping("/gugun/{sido_code}")
	public ResponseEntity<?> getGugun(@PathVariable("sido_code") int sidoCode) {
		logger.debug("getGugun sido code :{}", sidoCode);
		try {
			List<GugunDto> gugun = attractionService.getGugun(sidoCode);
			return new ResponseEntity<List<GugunDto>>(gugun, HttpStatus.OK);
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}

	@ApiOperation(value = "관광지 정보", notes = "관광지 정보를 검색합니다.")
	@ApiResponses({ @ApiResponse(code = 200, message = "검색 OK"), @ApiResponse(code = 500, message = "서버 오류") })
	@PostMapping("/search")
	public ResponseEntity<?> search(@RequestBody Map<String, String> map) {
		logger.debug("search");
		try {
			List<AttractionDto> attraction = attractionService.search(map);
			return new ResponseEntity<List<AttractionDto>>(attraction, HttpStatus.OK);
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}

	@ApiOperation(value = "주소", notes = "주소 가져옵니다.")
	@ApiResponses({ @ApiResponse(code = 200, message = "주소 가져오기 OK"), @ApiResponse(code = 500, message = "서버 오류") })
	@GetMapping("/address")
	public ResponseEntity<?> getAddress(@RequestParam("longitude") String longitude, @RequestParam("latitude") String latitude) {
		logger.debug("address");
		// URL 객체 생성 (절대경로)
		try {
			return new ResponseEntity<String>(attractionService.getAddress(longitude, latitude), HttpStatus.OK);
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}

	private ResponseEntity<String> exceptionHandling(Exception e) {
		e.printStackTrace();
		return new ResponseEntity<String>("Error : " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
