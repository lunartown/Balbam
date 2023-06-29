package com.ssafy.article.controller;

import java.io.IOException;
import java.util.*;
import com.ssafy.util.model.SearchCondition;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.ssafy.article.model.ArticleCommentDto;
import com.ssafy.article.model.ArticleDto;
import com.ssafy.article.model.service.ArticleService;

@RestController
@RequestMapping("/article")
@Api(tags = {"게시글 관리"})
public class ArticleController {
	private final Logger logger = LoggerFactory.getLogger(ArticleController.class);
	private final ArticleService articleService;
	
	@Autowired
	public ArticleController(ArticleService articleService) {
		this.articleService = articleService;
	}
	
	
	@GetMapping("")
	@ApiOperation(value = "게시글 조회", notes = "1개의 게시글을 조회합니다.")
	@ApiResponses({@ApiResponse(code = 200, message = "게시글 조회 OK"), @ApiResponse(code = 500, message = "서버 오류")})
	public ResponseEntity<?> selectArticle(@RequestParam Map<String, Integer> map) {
		logger.debug("articleId : {}, userId : {}", map.get("articleId"), map.get("userId"));
		try {
			ArticleDto articleDto = articleService.selectArticle(map);
			return new ResponseEntity<ArticleDto>(articleDto, HttpStatus.OK);
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}

	@GetMapping("/list")
	@ApiOperation(value = "게시글들 조회", notes = "현재 페이지의 게시글을 조회합니다.")
	@ApiResponses({@ApiResponse(code = 200, message = "게시글들 조회 OK"), @ApiResponse(code = 500, message = "서버 오류")})
	public ResponseEntity<?> selectAllArticle(SearchCondition searchCondition) {
		logger.debug("searchCondition : {}", searchCondition);
		try {
			searchCondition = articleService.makeArticleSearchCondition(searchCondition);
			List<ArticleDto> list = articleService.selectAllArticle(searchCondition);
			return new ResponseEntity<List<ArticleDto>>(list, HttpStatus.OK);
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}
	@GetMapping("/list/count")
	@ApiOperation(value = "게시글 개수 조회", notes = "게시글 개수를 조회합니다.")
	@ApiResponses({@ApiResponse(code = 200, message = "게시글 개수 조회 OK"), @ApiResponse(code = 500, message = "서버 오류")})
	public ResponseEntity<?> selectArticleCount(SearchCondition searchCondition) {
		logger.debug("searchCondition : {}", searchCondition);
		try {
			int count = articleService.getTotalArticleCount(searchCondition );
			return new ResponseEntity<Integer>(count, HttpStatus.OK);
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}
	
	@PostMapping( "")
	@ApiOperation(value = "게시글 작성", notes = "1개의 게시글을 작성합니다.")
	@ApiResponses({@ApiResponse(code = 200, message = "게시글 작성 OK"), @ApiResponse(code = 500, message = "서버 오류")})
	public ResponseEntity<?> writeArticle(@RequestBody ArticleDto articleDto) throws IOException {
		logger.debug("articleDto : {}", articleDto);
		try {
			articleService.writeArticle(articleDto);
			return new ResponseEntity<Void>(HttpStatus.OK);
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}
	
	@PostMapping("/update")
	@ApiOperation(value = "게시글 수정", notes = "1개의 게시글을 수정합니다.")
	@ApiResponses({@ApiResponse(code = 200, message = "게시글 수정 OK"), @ApiResponse(code = 500, message = "서버 오류")})
	public ResponseEntity<?> updateArticle(@RequestBody ArticleDto articleDto) {
		logger.debug("articleDto : {}", articleDto);
		try {
			articleService.updateArticle(articleDto);
			return new ResponseEntity<Void>(HttpStatus.OK);
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}
	
	@DeleteMapping("/{articleId}")
	@ApiOperation(value = "게시글 삭제", notes = "1개의 게시글을 삭제합니다.")
	@ApiResponses({@ApiResponse(code = 200, message = "게시글 삭제 OK"), @ApiResponse(code = 500, message = "서버 오류")})
	public ResponseEntity<?> deleteArticle(@PathVariable("articleId") int articleId) {
		logger.debug("articleId : {}", articleId);
		try {
			articleService.deleteArticle(articleId);
			return new ResponseEntity<Void>(HttpStatus.OK);
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}
	
	
	@PostMapping("/plus/view")
	@ApiOperation(value = "게시글 조회수 증가", notes = "조회수를 증가합니다.")
	@ApiResponses({@ApiResponse(code = 200, message = "게시글 조회수 증가 OK"), @ApiResponse(code = 500, message = "서버 오류")})
	public ResponseEntity<?> plusArticleView(@RequestBody Map<String, Integer> map) {
		logger.debug("articleId : {}", map.get("articleId"));
		try {
			int articleId = map.get("articleId");
			articleService.plusView(articleId);
			return new ResponseEntity<Void>(HttpStatus.OK);
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}
	
	@PostMapping("/heart")
	@ApiOperation(value = "게시글 좋아요 증가", notes = "좋아요를 증가합니다.")
	@ApiResponses({@ApiResponse(code = 200, message = "게시글 좋아요 증가 OK"), @ApiResponse(code = 500, message = "서버 오류")})
	public ResponseEntity<?> plusArticleHeart(@RequestBody Map<String, Integer> map) {
		logger.debug("articleId : {}, userId : {}", map.get("articleId"), map.get("userId"));
		try {
			articleService.plusHeart(map);
			return new ResponseEntity<Void>(HttpStatus.OK);
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}
	
	@DeleteMapping("/heart")
	@ApiOperation(value = "게시글 좋아요 감소", notes = "좋아요를 감소합니다.")
	@ApiResponses({@ApiResponse(code = 200, message = "게시글 좋아요 감소 OK"), @ApiResponse(code = 500, message = "서버 오류")})
	public ResponseEntity<?> minusArticleHeart(@RequestParam int articleId,  @RequestParam int userId) {
		logger.debug("articleId : {}, userId : {}",articleId, userId);
		try {
			Map<String, Integer> map = new HashMap<>();
			map.put("userId", userId);
			map.put("articleId", articleId);
			articleService.minusHeart(map);
			return new ResponseEntity<Void>(HttpStatus.OK);
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}
	@GetMapping("/comment")
	@ApiOperation(value = "댓글 조회", notes = "현재 페이지의 게시글에 대한 댓글을 조회합니다.")
	@ApiResponses({@ApiResponse(code = 200, message = "댓글 조회 OK"), @ApiResponse(code = 500, message = "서버 오류")})
	public ResponseEntity<?> selectComment(SearchCondition searchCondition, @RequestParam("articleId") int articleId) {
		logger.debug("SearchCondition : {}", searchCondition);
		try {
			searchCondition.setWord(String.valueOf(articleId));
			searchCondition = articleService.makeCommentSearchCondition(searchCondition);
			List<ArticleCommentDto> list = articleService.selectArticleComment(searchCondition);
			return new ResponseEntity<List<ArticleCommentDto>>(list, HttpStatus.OK);
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}
	@GetMapping("/comment/count")
	@ApiOperation(value = "댓글 개수 조회", notes = "댓글 개수를 조회합니다.")
	@ApiResponses({@ApiResponse(code = 200, message = "댓글 개수 조회 OK"), @ApiResponse(code = 500, message = "서버 오류")})
	public ResponseEntity<?> selectCommentCount(int articleId) {
		logger.debug("articleId : {}", articleId);
		try {
			int count = articleService.getTotalCommentCount(articleId );
			return new ResponseEntity<Integer>(count, HttpStatus.OK);
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}
	@PostMapping("/comment")
	@ApiOperation(value = "댓글 달기", notes = "댓글을 작성합니다.")
	@ApiResponses({@ApiResponse(code = 200, message = "댓글 작성 OK"), @ApiResponse(code = 500, message = "서버 오류")})
	public ResponseEntity<?> writeArticleComment(@RequestBody ArticleCommentDto articleCommentDto) {
		logger.debug("articleCommentDto : {}", articleCommentDto);
		try {
			articleService.writeComment(articleCommentDto);
			return new ResponseEntity<Void>(HttpStatus.OK);
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}
	@PutMapping("/comment")
	@ApiOperation(value = "댓글 수정", notes = "댓글을 수정합니다.")
	@ApiResponses({@ApiResponse(code = 200, message = "댓글 수정 OK"), @ApiResponse(code = 500, message = "서버 오류")})
	public ResponseEntity<?> updateArticleComment(@RequestBody ArticleCommentDto articleCommentDto) {
		logger.debug("articleCommentDto : {}", articleCommentDto);
		try {
			articleService.updateComment(articleCommentDto);
			return new ResponseEntity<Void>(HttpStatus.OK);
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}
	
	@DeleteMapping("/comment/{commentId}")
	@ApiOperation(value = "댓글 삭제", notes = "댓글을 삭제합니다.")
	@ApiResponses({@ApiResponse(code = 200, message = "댓글 삭제 OK"), @ApiResponse(code = 500, message = "서버 오류")})
	public ResponseEntity<?> deleteArticleComment(@PathVariable("commentId") int commentId) {
		logger.debug("commentId : {}",commentId);
		try {
			articleService.deleteComment(commentId);
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
