package com.ssafy.user.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ssafy.user.model.UserDto;
import com.ssafy.user.model.service.JwtServiceImpl;
import com.ssafy.user.model.service.UserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/user")
@Api(tags = { "회원관리" })
public class UserController {

	private final Logger logger = LoggerFactory.getLogger(UserController.class);

	@Autowired
	private JwtServiceImpl jwtService;

	private UserService userService;

	@Autowired
	public UserController(UserService userService) {
		super();
		this.userService = userService;
	}

	@ApiOperation(value = "로그인", notes = "로그인합니다.")
	@ApiResponses({ @ApiResponse(code = 200, message = "로그인 OK"), @ApiResponse(code = 500, message = "서버 오류") })
	@PostMapping(value = "/login")
	public ResponseEntity<?> loginUser(@RequestBody Map<String, String> map, HttpSession session) {
		logger.debug("login id : {}, pwd : {}", map.get("id"), map.get("pwd"));
		try {
//			captcha가 있을 경우
			if (map.get("value") != null || "".equals(map.get("value"))) {
//				captcha 실패시
				if (!userService.checkCaptcha(map.get("key"), map.get("value"))) {
					return new ResponseEntity<String>("captcha", HttpStatus.OK);
				}
//				captcha 성공시
				if (userService.idCheck(map.get("id")) == 0) {
					return new ResponseEntity<String>("id", HttpStatus.OK);
				} else {
					UserDto userDto = userService.loginUser(map);
					System.out.println(userDto);
					if (userDto == null) {
						return new ResponseEntity<Integer>(userService.hitCaptcha(map.get("id")), HttpStatus.OK);
					} else {
						Map<String, Object> resultMap = new HashMap<>();
						String accessToken = jwtService.createAccessToken("userId", userDto.getUserId());// key, data
						String refreshToken = jwtService.createRefreshToken("userId", userDto.getUserId());// key, data
						userService.saveRefreshToken(String.valueOf(userDto.getUserId()), refreshToken);
						logger.debug("로그인 accessToken 정보 : {}", accessToken);
						logger.debug("로그인 refreshToken 정보 : {}", refreshToken);
						resultMap.put("access-token", accessToken);
						resultMap.put("refresh-token", refreshToken);
						resultMap.put("message", "success");
						return new ResponseEntity<Map<String, Object>>(resultMap, HttpStatus.ACCEPTED);
					}
				}
//				captcha 없을 경우
			} else {
				if (userService.idCheck(map.get("id")) == 0) {
					return new ResponseEntity<String>("id", HttpStatus.OK);
				} else {
					UserDto userDto = userService.loginUser(map);
					System.out.println(userDto);
					if (userDto == null) {
						return new ResponseEntity<Integer>(userService.hitCaptcha(map.get("id")), HttpStatus.OK);
					} else if (userDto.getCaptcha() >= 5) {
						return new ResponseEntity<String>("captcha", HttpStatus.OK);
					} else {
						Map<String, Object> resultMap = new HashMap<>();
						String accessToken = jwtService.createAccessToken("userId", userDto.getUserId());// key, data
						String refreshToken = jwtService.createRefreshToken("userId", userDto.getUserId());// key, data
						userService.saveRefreshToken(String.valueOf(userDto.getUserId()), refreshToken);
						logger.debug("로그인 accessToken 정보 : {}", accessToken);
						logger.debug("로그인 refreshToken 정보 : {}", refreshToken);
						resultMap.put("access-token", accessToken);
						resultMap.put("refresh-token", refreshToken);
						resultMap.put("message", "success");
						return new ResponseEntity<Map<String, Object>>(resultMap, HttpStatus.ACCEPTED);
					}
				}
			}
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}

	@ApiOperation(value = "회원인증", notes = "회원 정보를 담은 Token을 반환한다.", response = Map.class)
	@GetMapping("/info/{userId}")
	public ResponseEntity<Map<String, Object>> getInfo(
			@PathVariable("userId") @ApiParam(value = "인증할 회원의 아이디.", required = true) String userId,
			HttpServletRequest request) {
		logger.debug("userId : {} ", userId);
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = HttpStatus.UNAUTHORIZED;
		if (jwtService.checkToken(request.getHeader("access-token"))) {
			logger.info("사용 가능한 토큰!!!");
			try {
//				로그인 사용자 정보.
				UserDto userDto = userService.userInfo(userId);
				resultMap.put("userInfo", userDto);
				resultMap.put("message", "success");
				status = HttpStatus.ACCEPTED;
			} catch (Exception e) {
				logger.error("정보조회 실패 : {}", e);
				resultMap.put("message", e.getMessage());
				status = HttpStatus.INTERNAL_SERVER_ERROR;
			}
		} else {
			logger.error("사용 불가능 토큰!!!");
			resultMap.put("message", "fail");
			status = HttpStatus.UNAUTHORIZED;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}

	@ApiOperation(value = "로그아웃", notes = "회원 정보를 담은 Token을 제거한다.", response = Map.class)
	@GetMapping("/logout/{userId}")
	public ResponseEntity<?> removeToken(@PathVariable("userId") String userId) {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = HttpStatus.ACCEPTED;
		try {
			userService.deleRefreshToken(userId);
//			System.out.println(userId);
			resultMap.put("message", "success");
			status = HttpStatus.ACCEPTED;
		} catch (Exception e) {
			logger.error("로그아웃 실패 : {}", e);
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);

	}

	@ApiOperation(value = "Access Token 재발급", notes = "만료된 access token을 재발급받는다.", response = Map.class)
	@PostMapping("/refresh")
	public ResponseEntity<?> refreshToken(@RequestBody UserDto userDto, HttpServletRequest request)
			throws Exception {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = HttpStatus.ACCEPTED;
		String token = request.getHeader("refresh-token");
		logger.debug("token : {}, userDto : {}", token, userDto);
		if (jwtService.checkToken(token)) {
			if (token.equals(userService.getRefreshToken(String.valueOf(userDto.getUserId())))) {
				String accessToken = jwtService.createAccessToken("userId", userDto.getUserId());
				logger.debug("token : {}", accessToken);
				logger.debug("정상적으로 액세스토큰 재발급!!!");
				resultMap.put("access-token", accessToken);
				resultMap.put("message", "success");
				status = HttpStatus.ACCEPTED;
			}
			else {
				logger.debug("리프레쉬토큰도 사용불!!!!!!!");
				status = HttpStatus.UNAUTHORIZED;
			}
		} else {
			logger.debug("리프레쉬토큰도 사용불!!!!!!!");
			status = HttpStatus.UNAUTHORIZED;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}

	
	@ApiOperation(value = "로그인 확인", notes = "세션 확인합니다")
	@ApiResponses({ @ApiResponse(code = 200, message = "로그인 찾기 OK"), @ApiResponse(code = 500, message = "서버 오류") })
	@GetMapping(value = "/getSession")
	public ResponseEntity<?> getSession(HttpSession session) {
		logger.debug("get Session");
		try {
			String userDto = (String) session.getAttribute("userinfo");
			System.out.println(userDto);
			return new ResponseEntity<String>(userDto, HttpStatus.OK);
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}

	@ApiOperation(value = "아이디 찾기", notes = "아이디를 찾습니다.")
	@ApiResponses({ @ApiResponse(code = 200, message = "아이디 찾기 OK"), @ApiResponse(code = 500, message = "서버 오류") })
	@GetMapping()
	public ResponseEntity<?> getId(@RequestParam Map<String, String> map) {
		logger.debug("getId name : {}, email : {}", map.get("name"), map.get("email"));
		try {
			String id = userService.getId(map);
			return new ResponseEntity<String>(id, HttpStatus.OK);
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}

	@ApiOperation(value = "회원가입", notes = "회원가입합니다.")
	@ApiResponses({ @ApiResponse(code = 200, message = "회원가입 OK"), @ApiResponse(code = 500, message = "서버 오류") })
	@PostMapping()
	public ResponseEntity<?> joinUser(@RequestBody UserDto userDto) {
		logger.debug("join user id : {}, name : {}", userDto.getId(), userDto.getName());
		try {
			userService.joinUser(userDto);
			return new ResponseEntity<Void>(HttpStatus.OK);
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}

	@ApiOperation(value = "회원정보수정", notes = "회원정보수정합니다.")
	@ApiResponses({ @ApiResponse(code = 200, message = "회원수정 OK"), @ApiResponse(code = 500, message = "서버 오류") })
	@PutMapping()
	public ResponseEntity<?> updateUser(@RequestBody UserDto userDto) {
		logger.debug("update user id : {}, name : {}", userDto.getId(), userDto.getName());
		try {
			userService.updateUser(userDto);
			return new ResponseEntity<Void>(HttpStatus.OK);
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}

	@ApiOperation(value = "회원정보삭제", notes = "회원정보삭제합니다.")
	@ApiResponses({ @ApiResponse(code = 200, message = "회원삭제 OK"), @ApiResponse(code = 500, message = "서버 오류") })
	@DeleteMapping(value = "{id}")
	public ResponseEntity<?> deleteUser(@PathVariable("id") String id) {
		logger.debug("delete user id : {}", id);
		try {
			userService.deleteUser(id);
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
			int check = userService.idCheck(id);
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
			int check = userService.emailCheck(email);
			return new ResponseEntity<Integer>(check, HttpStatus.OK);
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}

	@ApiOperation(value = "비밀번호 오류", notes = "비밀번호 1회 오류.")
	@ApiResponses({ @ApiResponse(code = 200, message = "오류 확인 OK"), @ApiResponse(code = 500, message = "서버 오류") })
	@GetMapping(value = "/hitCaptcha/{id}")
	public ResponseEntity<?> hitCaptcha(@PathVariable("id") String id) {
		logger.debug("hit captcha id : {}", id);
		try {
			return new ResponseEntity<Integer>(userService.hitCaptcha(id), HttpStatus.OK);
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}

	@ApiOperation(value = "캡차", notes = "캡차 이미지 받아오기.")
	@ApiResponses({ @ApiResponse(code = 200, message = "캡차 받아오기 OK"), @ApiResponse(code = 500, message = "서버 오류") })
	@GetMapping(value = "/getCaptcha")
	public ResponseEntity<?> getCaptcha() {
		logger.debug("get captcha");
		try {
			return new ResponseEntity<String[]>(userService.getCaptcha(), HttpStatus.OK);
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}

	private ResponseEntity<String> exceptionHandling(Exception e) {
		e.printStackTrace();
		return new ResponseEntity<String>("Error : " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
