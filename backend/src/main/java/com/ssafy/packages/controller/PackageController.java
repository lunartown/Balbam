package com.ssafy.packages.controller;

import com.ssafy.packages.model.PackageDto;
import com.ssafy.packages.model.PackageHistoryDto;
import com.ssafy.packages.model.PackageInquiryDto;
import com.ssafy.packages.model.PackageReviewDto;
import com.ssafy.packages.model.service.PackageService;
import com.ssafy.util.model.SearchCondition;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.io.IOException;
import java.util.*;

@RestController
@RequestMapping("/package")
@Api(tags = {"패키지 관리"})
public class PackageController {
    private final Logger logger = LoggerFactory.getLogger(PackageController.class);

    private final PackageService packageService;

    public PackageController(PackageService packageService) {
        this.packageService = packageService;
    }

    @GetMapping("")
    @ApiOperation(value = "패키지 조회", notes = "1개의 패키지를 조회합니다.")
    @ApiResponses({@ApiResponse(code = 200, message = "패키지 조회 OK"), @ApiResponse(code = 500, message = "서버 오류")})
    public ResponseEntity<?> selectPackage(@RequestParam Map<String, Integer> map) {
        logger.debug("packageId : {}, , userId : {}", map.get("packageId"),  map.get(("userId")));
        try {
            PackageDto packageDto = packageService.selectPackage(map);
            return new ResponseEntity<PackageDto>(packageDto, HttpStatus.OK);
        } catch (Exception e) {
            return exceptionHandling(e);
        }
    }
    @GetMapping("/list/region")
    @ApiOperation(value = "지역 기준 패키지들 조회", notes = "지역 기준 패키지을 조회합니다.")
    @ApiResponses({@ApiResponse(code = 200, message = "지역 기준 패키지들 조회 OK"), @ApiResponse(code = 500, message = "서버 오류")})
    public ResponseEntity<?> selectListRegionPackage(@RequestParam Map<String, String> map) {
        logger.debug("region : {}, userId : {}", map.get("region"), map.get("userId"));
        try {
            List<PackageDto> list = packageService.selectListRegionPackage(map);
            return new ResponseEntity<List<PackageDto>>(list, HttpStatus.OK);
        } catch (Exception e) {
            return exceptionHandling(e);
        }
    }
    @GetMapping("/list/random")
    @ApiOperation(value = "랜덤 패키지들 조회", notes = "랜덤 패키지을 조회합니다.")
    @ApiResponses({@ApiResponse(code = 200, message = "랜덤 패키지들 조회 OK"), @ApiResponse(code = 500, message = "서버 오류")})
    public ResponseEntity<?> selectListRandomPackage() {
        try {
            List<PackageDto> list = packageService.selectListRandomPackage();
            return new ResponseEntity<List<PackageDto>>(list, HttpStatus.OK);
        } catch (Exception e) {
            return exceptionHandling(e);
        }
    }
    @GetMapping("/list/region/random")
    @ApiOperation(value = "랜덤 지역 패키지들 조회", notes = "랜덤 지역 패키지을 조회합니다.")
    @ApiResponses({@ApiResponse(code = 200, message = "랜덤 지역 패키지들 조회 OK"), @ApiResponse(code = 500, message = "서버 오류")})
    public ResponseEntity<?> selectListRandomRegionPackage(@RequestParam Map<String, String> map) {
        logger.debug("region : {}, userId : {}", map.get("region"), map.get("userId"));
        try {
            List<PackageDto> list = packageService.selectListRandomRegionPackage(map);
            return new ResponseEntity<List<PackageDto>>(list, HttpStatus.OK);
        } catch (Exception e) {
            return exceptionHandling(e);
        }
    }
    @GetMapping("/getAllPackage")
    @ApiOperation(value = "패키지 리스트 조회", notes = "현재 페이지의 패키지 리스트를 조회합니다.")
    @ApiResponses({@ApiResponse(code = 200, message = "패키지 리스트 조회 OK"), @ApiResponse(code = 500, message = "서버 오류")})
    public ResponseEntity<?> selectAllPackage(SearchCondition searchCondition) {
        logger.debug("searchCondition : {}", searchCondition);
        try {
            if ("guideid".equals(searchCondition.getKey()))
                searchCondition.setKey("guide_id");
            searchCondition = packageService.makeSearchCondition(searchCondition);
            List<PackageDto> list = packageService.selectAllPackage(searchCondition);
            return new ResponseEntity<List<PackageDto>>(list, HttpStatus.OK);
        } catch (Exception e) {
            return exceptionHandling(e);
        }
    }

    @PostMapping("")
    @ApiOperation(value = "패키지 등록", notes = "1개의 패키지를 등록합니다.")
    @ApiResponses({@ApiResponse(code = 200, message = "패지키 등록 OK"), @ApiResponse(code = 500, message = "서버 오류")})
    public ResponseEntity<?> writePackage(@RequestBody PackageDto packageDto) throws IOException {
        logger.debug("packageDto : {}", packageDto);
        try {
            packageService.writePackage(packageDto);
			int id = packageService.getPackageId(packageDto);
            return new ResponseEntity<Integer>(id, HttpStatus.OK);
        } catch (Exception e) {
            return exceptionHandling(e);
        }
    }
	@PostMapping("/img")
	@ApiOperation(value = "메인 이미지  업로드", notes = "1개의 메인 이미지 업로드를 진행합니다.")
	@ApiResponses({@ApiResponse(code = 200, message = "메인 이미지 업로드 OK"), @ApiResponse(code = 500, message = "서버 오류")})
	public ResponseEntity<?> writeImg(@RequestBody Map<String, String> map) throws IOException {
		logger.debug("url : {} packageId : {}", map.get("url"), map.get("packageId"));
		try {
			packageService.writeImg(map);
			return new ResponseEntity<Void>(HttpStatus.OK);
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}
	@PostMapping("/desc/img")
	@ApiOperation(value = "기본 정보 이미지  업로드", notes = "1개의 기본 정보 이미지 업로드를 진행합니다.")
	@ApiResponses({@ApiResponse(code = 200, message = "기본 정보 이미지 업로드 OK"), @ApiResponse(code = 500, message = "서버 오류")})
	public ResponseEntity<?> writeDescImg(@RequestBody Map<String, String> map) throws IOException {
		logger.debug("url : {} packageId : {}", map.get("url"), map.get("packageId"));
		try {
			packageService.writeDescImg(map);
			return new ResponseEntity<Void>(HttpStatus.OK);
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}

    @PutMapping("")
    @ApiOperation(value = "패키지 수정", notes = "1개의 패키지를 수정합니다.")
    @ApiResponses({@ApiResponse(code = 200, message = "패키지 수정 OK"), @ApiResponse(code = 500, message = "서버 오류")})
    public ResponseEntity<?> updatePackage(@RequestBody PackageDto packageDto) {
        logger.debug("packageDto : {}", packageDto);
        try {
            packageService.updatePackage(packageDto);
            return new ResponseEntity<Void>(HttpStatus.OK);
        } catch (Exception e) {
            return exceptionHandling(e);
        }
    }
    @DeleteMapping("/{packageId}")
    @ApiOperation(value = "패키지 삭제", notes = "1개의 패키지를 삭제합니다.")
    @ApiResponses({@ApiResponse(code = 200, message = "패키지 삭제 OK"), @ApiResponse(code = 500, message = "서버 오류")})
    public ResponseEntity<?> deletePackage(@PathVariable("packageId") int packageId) {
        logger.debug("packageId : {}", packageId);
        try {
            packageService.deletePackage(packageId);
            return new ResponseEntity<Void>(HttpStatus.OK);
        } catch (Exception e) {
            return exceptionHandling(e);
        }
    }
    
    @PostMapping("/heart")
    @ApiOperation(value = "패키지 좋아요 증가", notes = "좋아요를 증가합니다.")
    @ApiResponses({@ApiResponse(code = 200, message = "패키지 좋아요 증가 OK"), @ApiResponse(code = 500, message = "서버 오류")})
    public ResponseEntity<?> plusPackageHeart(@RequestBody Map<String, Integer> map) {
        logger.debug("packageId : {}, userId : {}", map.get("packageId"), map.get("userId"));
        try {
            packageService.plusHeart(map);
            return new ResponseEntity<Void>(HttpStatus.OK);
        } catch (Exception e) {
            return exceptionHandling(e);
        }
    }

    @DeleteMapping("/heart")
    @ApiOperation(value = "패키지 좋아요 감소", notes = "좋아요를 감소합니다.")
    @ApiResponses({@ApiResponse(code = 200, message = "패키지 좋아요 감소 OK"), @ApiResponse(code = 500, message = "서버 오류")})
    public ResponseEntity<?> minusPackageHeart(@RequestParam Map<String, Integer> map) {
        logger.debug("packageId : {}, userId : {}", map.get("packageId"), map.get("userId"));
        try {
            packageService.minusHeart(map);
            return new ResponseEntity<Void>(HttpStatus.OK);
        } catch (Exception e) {
            return exceptionHandling(e);
        }
    }
    @GetMapping("/inquiry")
    @ApiOperation(value = "패키지 문의 리스트 조회", notes = "현재 페이지의 패키지 문의 리스트를 조회합니다.")
    @ApiResponses({@ApiResponse(code = 200, message = "패키지 문의 리스트 조회 OK"), @ApiResponse(code = 500, message = "서버 오류")})
    public ResponseEntity<?> selectAllPackageInquiry(SearchCondition searchCondition) {
        logger.debug("searchCondition : {}", searchCondition);
        try {
            searchCondition = packageService.makeSearchCondition(searchCondition);
            List<PackageInquiryDto> list = packageService.selectPackageInquiry(searchCondition);
            return new ResponseEntity<List<PackageInquiryDto>>(list, HttpStatus.OK);
        } catch (Exception e) {
            return exceptionHandling(e);
        }
    }
    @GetMapping("/inquiry/count")
    @ApiOperation(value = "패키지 문의 개수 조회", notes = "현재 페이지의 패키지 문의 개수를 조회합니다.")
    @ApiResponses({@ApiResponse(code = 200, message = "패키지 문의 개수 조회 OK"), @ApiResponse(code = 500, message = "서버 오류")})
    public ResponseEntity<?> selectPackageInquiryCount( int packageId) {
        logger.debug("packageId : {}", packageId);
        try {
            int count = packageService.getTotalPackageInquiryCount(packageId);
            return new ResponseEntity<Integer>(count, HttpStatus.OK);
        } catch (Exception e) {
            return exceptionHandling(e);
        }
    }
    @PostMapping("/inquiry")
    @ApiOperation(value = "문의 작성", notes = "문의를 작성합니다.")
    @ApiResponses({@ApiResponse(code = 200, message = "문의 작성 OK"), @ApiResponse(code = 500, message = "서버 오류")})
    public ResponseEntity<?> writeArticleInquiry(@RequestBody PackageInquiryDto packageInquiryDto) {
        logger.debug("packageInquiryDto : {}", packageInquiryDto);
        try {
            packageService.writePackageInquiry(packageInquiryDto);
            return new ResponseEntity<Void>(HttpStatus.OK);
        } catch (Exception e) {
            return exceptionHandling(e);
        }
    }
    @PutMapping("/inquiry")
    @ApiOperation(value = "문의 수정", notes = "문의을 수정합니다.")
    @ApiResponses({@ApiResponse(code = 200, message = "문의 수정 OK"), @ApiResponse(code = 500, message = "서버 오류")})
    public ResponseEntity<?> updateArticleInquiry(@RequestBody PackageInquiryDto packageInquiryDto) {
        logger.debug("packageInquiryDto : {}", packageInquiryDto);
        try {
            packageService.updatePackageInquiry(packageInquiryDto);
            return new ResponseEntity<Void>(HttpStatus.OK);
        } catch (Exception e) {
            return exceptionHandling(e);
        }
    }
    @DeleteMapping("/inquiry/{inquiryId}")
    @ApiOperation(value = "문의 삭제", notes = "문의을 삭제합니다.")
    @ApiResponses({@ApiResponse(code = 200, message = "문의 삭제 OK"), @ApiResponse(code = 500, message = "서버 오류")})
    public ResponseEntity<?> deleteArticleInquiry(@PathVariable("inquiryId") int inquiryId) {
        logger.debug("inquiryId : {}",inquiryId);
        try {
            packageService.deletePackageInquiry(inquiryId);
            return new ResponseEntity<Void>(HttpStatus.OK);
        } catch (Exception e) {
            return exceptionHandling(e);
        }
    }
    @PostMapping("/answer")
    @ApiOperation(value = "응답 작성", notes = "응답를 작성합니다.")
    @ApiResponses({@ApiResponse(code = 200, message = "응답 작성 OK"), @ApiResponse(code = 500, message = "서버 오류")})
    public ResponseEntity<?> writeAnswer(@RequestBody PackageInquiryDto packageInquiryDto) {
        logger.debug("packageInquiryDto : {}", packageInquiryDto);
        try {
            packageService.writePackageAnswer(packageInquiryDto);
            return new ResponseEntity<Void>(HttpStatus.OK);
        } catch (Exception e) {
            return exceptionHandling(e);
        }
    }
    @PutMapping("/answer")
    @ApiOperation(value = "응답 수정", notes = "응답을 수정합니다.")
    @ApiResponses({@ApiResponse(code = 200, message = "응답 수정 OK"), @ApiResponse(code = 500, message = "서버 오류")})
    public ResponseEntity<?> updateAnswer(@RequestBody PackageInquiryDto packageInquiryDto) {
        logger.debug("packageInquiryDto : {}", packageInquiryDto);
        try {
            packageService.updatePackageAnswer(packageInquiryDto);
            return new ResponseEntity<Void>(HttpStatus.OK);
        } catch (Exception e) {
            return exceptionHandling(e);
        }
    }
    @DeleteMapping("/answer/{answerId}")
    @ApiOperation(value = "응답 삭제", notes = "응답을 삭제합니다.")
    @ApiResponses({@ApiResponse(code = 200, message = "응답 삭제 OK"), @ApiResponse(code = 500, message = "서버 오류")})
    public ResponseEntity<?> deleteAnswer(@PathVariable("answerId") int answerId) {
        logger.debug("answerId : {}",answerId);
        try {
            packageService.deletePackageAnswer(answerId);
            return new ResponseEntity<Void>(HttpStatus.OK);
        } catch (Exception e) {
            return exceptionHandling(e);
        }
    }
    @GetMapping("/review")
    @ApiOperation(value = "패키지 리뷰 리스트 조회", notes = "현재 페이지의 패키지 리뷰 리스트를 조회합니다.")
    @ApiResponses({@ApiResponse(code = 200, message = "패키지 리뷰 리스트 조회 OK"), @ApiResponse(code = 500, message = "서버 오류")})
    public ResponseEntity<?> selectAllPackageReview(SearchCondition searchCondition) {
        logger.debug("searchCondition : {}", searchCondition);
        try {
            searchCondition = packageService.makeSearchCondition(searchCondition);
            List<PackageReviewDto> list = packageService.selectPackageReview(searchCondition);
            return new ResponseEntity<List<PackageReviewDto>>(list, HttpStatus.OK);
        } catch (Exception e) {
            return exceptionHandling(e);
        }
    }
    @GetMapping("/review/count")
    @ApiOperation(value = "리뷰 개수 조회", notes = "현재 페이지의 패키지 리뷰 개수를 조회합니다.")
    @ApiResponses({@ApiResponse(code = 200, message = "패키지 리뷰 개수 조회 OK"), @ApiResponse(code = 500, message = "서버 오류")})
    public ResponseEntity<?> selectPackageReviewCount( int packageId) {
        logger.debug("packageId : {}", packageId);
        try {
            int count = packageService.getTotalPackageReviewCount(packageId);
            return new ResponseEntity<Integer>(count, HttpStatus.OK);
        } catch (Exception e) {
            return exceptionHandling(e);
        }
    }
    @PostMapping("/review")
    @ApiOperation(value = "리뷰 작성", notes = "리뷰를 작성합니다.")
    @ApiResponses({@ApiResponse(code = 200, message = "리뷰 작성 OK"), @ApiResponse(code = 500, message = "서버 오류")})
    public ResponseEntity<?> writeArticleReview(@RequestBody PackageReviewDto packageReviewDto) {
        logger.debug("packageReviewDto : {}", packageReviewDto);
        try {
            packageService.writePackageReview(packageReviewDto);
            return new ResponseEntity<Void>(HttpStatus.OK);
        } catch (Exception e) {
            return exceptionHandling(e);
        }
    }
    @PutMapping("/review")
    @ApiOperation(value = "리뷰 수정", notes = "리뷰을 수정합니다.")
    @ApiResponses({@ApiResponse(code = 200, message = "리뷰 수정 OK"), @ApiResponse(code = 500, message = "서버 오류")})
    public ResponseEntity<?> updateArticleReview(@RequestBody PackageReviewDto packageReviewDto) {
        logger.debug("packageReviewDto : {}", packageReviewDto);
        try {
            packageService.updatePackageReview(packageReviewDto);
            return new ResponseEntity<Void>(HttpStatus.OK);
        } catch (Exception e) {
            return exceptionHandling(e);
        }
    }
    @DeleteMapping("/review/{reviewId}")
    @ApiOperation(value = "리뷰 삭제", notes = "리뷰을 삭제합니다.")
    @ApiResponses({@ApiResponse(code = 200, message = "리뷰 삭제 OK"), @ApiResponse(code = 500, message = "서버 오류")})
    public ResponseEntity<?> deleteArticleReview(@PathVariable("reviewId") int inquiryId) {
        logger.debug("inquiryId : {}",inquiryId);
        try {
            packageService.deletePackageReview(inquiryId);
            return new ResponseEntity<Void>(HttpStatus.OK);
        } catch (Exception e) {
            return exceptionHandling(e);
        }
    }
//    @GetMapping("/getAllHistory")
//    @ApiOperation(value = "여행기록 리스트 조회", notes = "현재 페이지의 여행기록 리스트를 조회합니다.")
//    @ApiResponses({@ApiResponse(code = 200, message = "여행기록 리스트 조회 OK"), @ApiResponse(code = 500, message = "서버 오류")})
//    public ResponseEntity<?> selectAllHistory(SearchCondition searchCondition) {
//        logger.debug("searchCondition : {}", searchCondition);
//        try {
//            searchCondition = packageService.makePackageSearchCondition(searchCondition);
//            List<PackageDto> list = packageService.selectAllPackage(searchCondition);
//            return new ResponseEntity<List<PackageDto>>(list, HttpStatus.OK);
//        } catch (Exception e) {
//            return exceptionHandling(e);
//        }
//    }
    
    
    @GetMapping("/countHistory")
    @ApiOperation(value = "패키지 기록 조회", notes = "현재 페이지의 패키지 기록을 조회합니다.")
    @ApiResponses({@ApiResponse(code = 200, message = "패키지 기록 조회 OK"), @ApiResponse(code = 500, message = "서버 오류")})
    public ResponseEntity<?> countHistory(SearchCondition searchCondition) {
        logger.debug("searchCondition : {}", searchCondition);
        try {
            if ("historyid".equals(searchCondition.getKey()))
                searchCondition.setKey("history_id");
            searchCondition = packageService.makeSearchCondition(searchCondition);
            int count = packageService.getTotalHistoryCount(searchCondition);
            return new ResponseEntity<Integer>(count, HttpStatus.OK);
        } catch (Exception e) {
            return exceptionHandling(e);
        }
    }
    
    @GetMapping("/getHistory")
    @ApiOperation(value = "패키지 기록 조회", notes = "현재 페이지의 패키지 기록을 조회합니다.")
    @ApiResponses({@ApiResponse(code = 200, message = "패키지 기록 조회 OK"), @ApiResponse(code = 500, message = "서버 오류")})
    public ResponseEntity<?> searchHistory(SearchCondition searchCondition) {
        logger.debug("searchCondition : {}", searchCondition);
        try {
            if ("historyid".equals(searchCondition.getKey()))
                searchCondition.setKey("history_id");
            searchCondition = packageService.makeSearchCondition(searchCondition);
            List<PackageHistoryDto> list = packageService.searchPackageHistory(searchCondition);
            return new ResponseEntity<List<PackageHistoryDto>>(list, HttpStatus.OK);
        } catch (Exception e) {
            return exceptionHandling(e);
        }
    }
    
    @PostMapping("/history")
    @ApiOperation(value = "여행기록 작성", notes = "여행기록를 저장합니다.")
    @ApiResponses({@ApiResponse(code = 200, message = "여행기록 저장 OK"), @ApiResponse(code = 500, message = "서버 오류")})
    public ResponseEntity<?> writeHistory(@RequestBody Map<String, Integer> map) {
        logger.debug("packageId : {}, userId : {}, headcount : {}", map.get("packageId"), map.get("userId"), map.get("headcount"));
        try {
            packageService.writePackageHistory(map);
            return new ResponseEntity<Void>(HttpStatus.OK);
        } catch (Exception e) {
            return exceptionHandling(e);
        }
    }
    
    @PutMapping("/history/{historyId}")
    @ApiOperation(value = "여행 취소", notes = "여행 예약을 취소합니다.")
    @ApiResponses({@ApiResponse(code = 200, message = "여행기록 취소 OK"), @ApiResponse(code = 500, message = "서버 오류")})
    public ResponseEntity<?> cancelHistory(@PathVariable("historyId") int historyId) {
        logger.debug("historyId : {}", historyId);
        try {
            packageService.cancelPackageHistory(historyId);
            return new ResponseEntity<Void>(HttpStatus.OK);
        } catch (Exception e) {
            return exceptionHandling(e);
        }
    }
    
    @DeleteMapping("/history/{historyId}")
    @ApiOperation(value = "여행기록 삭제", notes = "여행기록을 삭제합니다.")
    @ApiResponses({@ApiResponse(code = 200, message = "여행기록 삭제 OK"), @ApiResponse(code = 500, message = "서버 오류")})
    public ResponseEntity<?> deleteHistory(@PathVariable("historyId") int historyId) {
        logger.debug("historyId : {}",historyId);
        try {
            packageService.deletePackageHistory(historyId);
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
