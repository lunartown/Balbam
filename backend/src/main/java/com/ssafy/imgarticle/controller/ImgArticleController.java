package com.ssafy.imgarticle.controller;

import java.io.IOException;
import java.util.*;
import com.ssafy.util.model.SearchCondition;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.ssafy.imgarticle.model.ImgArticleDto;
import com.ssafy.imgarticle.model.service.ImgArticleService;

@RestController
@RequestMapping("/img/article")
@Api(tags = {"이미지게시글 관리"})
public class ImgArticleController {
	private final Logger logger = LoggerFactory.getLogger(ImgArticleController.class);
	private final ImgArticleService imgArticleService;
	
	@Autowired
	public ImgArticleController(ImgArticleService imgArticleService) {
		this.imgArticleService = imgArticleService;
	}
	
	
	@GetMapping("")
	@ApiOperation(value = "이미지 게시글 조회", notes = "1개의 이미지 게시글을 조회합니다.")
	@ApiResponses({@ApiResponse(code = 200, message = "이미지 게시글 조회 OK"), @ApiResponse(code = 500, message = "서버 오류")})
	public ResponseEntity<?> selectImgArticle(@RequestParam Map<String, Integer> map) {
		logger.debug("articleId : {}, userId : {}", map.get("articleId"), map.get("userId"));
		try {
			ImgArticleDto imgArticleDto = imgArticleService.selectImgArticle(map);
			return new ResponseEntity<ImgArticleDto>(imgArticleDto, HttpStatus.OK);
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}
	@GetMapping("/list/region")
	@ApiOperation(value = "지역 기준 이미지 게시글들 조회", notes = "지역 기준 현재 페이지의 이미지 게시글을 조회합니다.")
	@ApiResponses({@ApiResponse(code = 200, message = "지역 기준 이미지 게시글들 조회 OK"), @ApiResponse(code = 500, message = "서버 오류")})
	public ResponseEntity<?> selectListRegionImgArticle(String region) {
		logger.debug("region : {}", region);
		try {
			List<ImgArticleDto> list = imgArticleService.selectListRegionImgArticle(region);
			return new ResponseEntity<List<ImgArticleDto>>(list, HttpStatus.OK);
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}
	@GetMapping("/list/random")
	@ApiOperation(value = "좋아요 기준 이미지 게시글들 조회", notes = "좋아요 기준 현재 페이지의 이미지 게시글을 조회합니다.")
	@ApiResponses({@ApiResponse(code = 200, message = "좋아요 기준 이미지 게시글들 조회 OK"), @ApiResponse(code = 500, message = "서버 오류")})
	public ResponseEntity<?> selectListRandomImgArticle() {
		try {
			List<ImgArticleDto> list = imgArticleService.selectListRandomImgArticle();
			return new ResponseEntity<List<ImgArticleDto>>(list, HttpStatus.OK);
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}
	
	@GetMapping("/list")
	@ApiOperation(value = "이미지 게시글들 조회", notes = "현재 페이지의 이미지 게시글을 조회합니다.")
	@ApiResponses({@ApiResponse(code = 200, message = "이미지 게시글들 조회 OK"), @ApiResponse(code = 500, message = "서버 오류")})
	public ResponseEntity<?> selectListImgArticle(SearchCondition searchCondition) {
		logger.debug("searchCondition : {}", searchCondition);
		try {
			searchCondition = imgArticleService.makeImgArticleSearchCondition(searchCondition);
			List<ImgArticleDto> list = imgArticleService.selectListImgArticle(searchCondition);
			return new ResponseEntity<List<ImgArticleDto>>(list, HttpStatus.OK);
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}
	@GetMapping("/list/count")
	@ApiOperation(value = "이미지 게시글 개수 조회", notes = "이미지 게시글 개수를 조회합니다.")
	@ApiResponses({@ApiResponse(code = 200, message = "이미지 게시글 개수 조회 OK"), @ApiResponse(code = 500, message = "서버 오류")})
	public ResponseEntity<?> selectImgArticleCount(SearchCondition searchCondition) {
		logger.debug("searchCondition : {}", searchCondition);
		try {
			int count = imgArticleService.getTotalImgArticleCount(searchCondition );
			return new ResponseEntity<Integer>(count, HttpStatus.OK);
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}
	
	@PostMapping("")
	@ApiOperation(value = "이미지 게시글 작성", notes = "1개의 이미지 게시글을 작성합니다.")
	@ApiResponses({@ApiResponse(code = 200, message = "이미지 게시글 작성 OK"), @ApiResponse(code = 500, message = "서버 오류")})
	public ResponseEntity<?> writeImgArticle(@RequestBody ImgArticleDto imgArticleDto) throws IOException {
		logger.debug("imgArticleDto : {}", imgArticleDto);
		try {
			imgArticleService.writeImgArticle(imgArticleDto);
			int id = imgArticleService.getArticleId(imgArticleDto);
			return new ResponseEntity<Integer>(id, HttpStatus.OK);
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}
	@PostMapping("/img")
	@ApiOperation(value = "이미지  업로드", notes = "1개의 이미지 업로드를 진행합니다.")
	@ApiResponses({@ApiResponse(code = 200, message = "이미지 업로드 OK"), @ApiResponse(code = 500, message = "서버 오류")})
	public ResponseEntity<?> writeImg(@RequestBody Map<String, String> map) throws IOException {
		logger.debug("url : {} articleId : {}", map.get("url"), map.get("articleId"));
		try {
			imgArticleService.writeImg(map);
			return new ResponseEntity<Void>(HttpStatus.OK);
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}
	
	
	@PostMapping("/update")
	@ApiOperation(value = "이미지 게시글 수정", notes = "1개의 이미지 게시글을 수정합니다.")
	@ApiResponses({@ApiResponse(code = 200, message = "이미지 게시글 수정 OK"), @ApiResponse(code = 500, message = "서버 오류")})
	public ResponseEntity<?> updateImgArticle(@RequestBody ImgArticleDto imgArticleDto) {
		logger.debug("imgArticleDto : {}", imgArticleDto);
		try {
			imgArticleService.updateImgArticle(imgArticleDto);
			return new ResponseEntity<Void>(HttpStatus.OK);
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}
	
	@DeleteMapping("/{articleId}")
	@ApiOperation(value = "이미지 게시글 삭제", notes = "1개의 이미지 게시글을 삭제합니다.")
	@ApiResponses({@ApiResponse(code = 200, message = "이미지 게시글 삭제 OK"), @ApiResponse(code = 500, message = "서버 오류")})
	public ResponseEntity<?> deleteImgArticle(@PathVariable("articleId") int articleId) {
		logger.debug("articleId : {}", articleId);
		try {
			imgArticleService.deleteImgArticle(articleId);
			return new ResponseEntity<Void>(HttpStatus.OK);
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}
	
	
	@PostMapping("/plus/view")
	@ApiOperation(value = "이미지 게시글 조회수 증가", notes = "이미지 조회수를 증가합니다.")
	@ApiResponses({@ApiResponse(code = 200, message = "이미지 게시글 조회수 증가 OK"), @ApiResponse(code = 500, message = "서버 오류")})
	public ResponseEntity<?> plusImgArticleView(@RequestBody Map<String, Integer> map) {
		logger.debug("articleId : {}", map.get("articleId"));
		try {
			int articleId = map.get("articleId");
			imgArticleService.plusView(articleId);
			return new ResponseEntity<Void>(HttpStatus.OK);
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}
	
	@PostMapping("/heart")
	@ApiOperation(value = "이미지 게시글 좋아요 증가", notes = "이미지 좋아요를 증가합니다.")
	@ApiResponses({@ApiResponse(code = 200, message = "이미지 게시글 좋아요 증가 OK"), @ApiResponse(code = 500, message = "서버 오류")})
	public ResponseEntity<?> plusImgArticleHeart(@RequestBody Map<String, Integer> map) {
		logger.debug("articleId : {}, userId : {}", map.get("articleId"), map.get("userId"));
		try {
			imgArticleService.plusHeart(map);
			return new ResponseEntity<Void>(HttpStatus.OK);
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}
	
	@DeleteMapping("/heart")
	@ApiOperation(value = "이미지 게시글 좋아요 감소", notes = "이미지 좋아요를 감소합니다.")
	@ApiResponses({@ApiResponse(code = 200, message = "이미지 게시글 좋아요 감소 OK"), @ApiResponse(code = 500, message = "서버 오류")})
	public ResponseEntity<?> minusImgArticleHeart(@RequestParam int articleId,  @RequestParam int userId) {
		logger.debug("articleId : {}, userId : {}",articleId, userId);
		try {
			Map<String, Integer> map = new HashMap<>();
			map.put("userId", userId);
			map.put("articleId", articleId);
			imgArticleService.minusHeart(map);
			return new ResponseEntity<Void>(HttpStatus.OK);
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}

	private ResponseEntity<String> exceptionHandling(Exception e) {
		e.printStackTrace();
		return new ResponseEntity<String>("Error : " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	}
}