package com.ssafy.guide.controller;

import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.guide.model.GuideDto;
import com.ssafy.guide.model.service.GuideService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/guide")
@Api(tags = { "가이드관리" })
public class GuideController {

	private final Logger logger = LoggerFactory.getLogger(GuideController.class);

	private GuideService guideService;

	@Autowired
	public GuideController(GuideService guideService) {
		super();
		this.guideService = guideService;
	}

	@ApiOperation(value = "로그인", notes = "로그인합니다.")
	@ApiResponses({ @ApiResponse(code = 200, message = "로그인 OK"), @ApiResponse(code = 500, message = "서버 오류") })
	@PostMapping(value = "/login")
	public ResponseEntity<?> loginGuide(@RequestBody Map<String, String> map) {
		logger.debug("login id : {}, pwd : {}", map.get("id"), map.get("pwd"));
		try {
			GuideDto guideDto = guideService.loginGuide(map);
			return new ResponseEntity<GuideDto>(guideDto, HttpStatus.OK);
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}

	@ApiOperation(value = "아이디 찾기", notes = "아이디를 찾습니다.")
	@ApiResponses({ @ApiResponse(code = 200, message = "아이디 찾기 OK"), @ApiResponse(code = 500, message = "서버 오류") })
	@GetMapping()
	public ResponseEntity<?> getId(@RequestBody Map<String, String> map) {
		logger.debug("getId name : {}, email : {}", map.get("name"), map.get("email"));
		try {
			String id = guideService.getId(map);
			return new ResponseEntity<String>(id, HttpStatus.OK);
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}

	@ApiOperation(value = "가이드 회원가입", notes = "가이드 회원가입합니다.")
	@ApiResponses({ @ApiResponse(code = 200, message = "가이드 회원가입 OK"), @ApiResponse(code = 500, message = "서버 오류") })
	@PostMapping()
	public ResponseEntity<?> joinGuide(@RequestBody GuideDto guideDto) {
		logger.debug("join guide id : {}, name : {}", guideDto.getId(), guideDto.getName());
		try {
			guideService.joinGuide(guideDto);
			return new ResponseEntity<Void>(HttpStatus.OK);
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}

	@ApiOperation(value = "가이드 정보수정", notes = "가이드 정보수정합니다.")
	@ApiResponses({ @ApiResponse(code = 200, message = "가이드 회원수정 OK"), @ApiResponse(code = 500, message = "서버 오류") })
	@PutMapping()
	public ResponseEntity<?> updateGuide(@RequestBody GuideDto guideDto) {
		logger.debug("update guide id : {}, name : {}", guideDto.getId(), guideDto.getName());
		try {
			guideService.updateGuide(guideDto);
			return new ResponseEntity<Void>(HttpStatus.OK);
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}

	@ApiOperation(value = "가이드 정보삭제", notes = "가이드 정보삭제합니다.")
	@ApiResponses({ @ApiResponse(code = 200, message = "가이드 삭제 OK"), @ApiResponse(code = 500, message = "서버 오류") })
	@DeleteMapping(value = "{id}")
	public ResponseEntity<?> deleteGuide(@PathVariable("id") String id) {
		logger.debug("delete guide id : {}", id);
		try {
			guideService.deleteGuide(id);
			return new ResponseEntity<Void>(HttpStatus.OK);
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}

	@ApiOperation(value = "아이디 중복검사", notes = "아이디 중복검사합니다.")
	@ApiResponses({ @ApiResponse(code = 200, message = "중복 검사 OK"), @ApiResponse(code = 500, message = "서버 오류") })
	@GetMapping(value = "/idCheck/{id}")
	public ResponseEntity<?> idCheck(@PathVariable("id") String id) {
		logger.debug("id check id : {}", id);
		try {
			int check = guideService.idCheck(id);
			return new ResponseEntity<Integer>(check, HttpStatus.OK);
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}

	@ApiOperation(value = "이메일 중복검사", notes = "이메일 중복검사합니다.")
	@ApiResponses({ @ApiResponse(code = 200, message = "중복 검사 OK"), @ApiResponse(code = 500, message = "서버 오류") })
	@GetMapping(value = "/emailCheck/{email}")
	public ResponseEntity<?> emailCheck(@PathVariable("email") String email) {
		logger.debug("email check email : {}", email);
		try {
			int check = guideService.emailCheck(email);
			return new ResponseEntity<Integer>(check, HttpStatus.OK);
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}

	private ResponseEntity<String> exceptionHandling(Exception e) {
		e.printStackTrace();
		return new ResponseEntity<String>("Error : " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
