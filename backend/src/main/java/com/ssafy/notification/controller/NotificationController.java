package com.ssafy.notification.controller;

import com.ssafy.notification.model.InquiryDto;
import com.ssafy.notification.model.NotificationDto;
import com.ssafy.notification.model.NotificationQuestionDto;
import com.ssafy.notification.model.service.NotificationService;
import com.ssafy.util.model.SearchCondition;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.io.IOException;
import java.util.*;

@RestController
@RequestMapping("/notification")
@Api(tags = {"공지 관리"})
public class NotificationController {
    private final Logger logger = LoggerFactory.getLogger(NotificationController.class);

    private final NotificationService notificationService;

    public NotificationController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @GetMapping("")
    @ApiOperation(value = "공지 조회", notes = "1개의 공지를 조회합니다.")
    @ApiResponses({@ApiResponse(code = 200, message = "공지 조회 OK"), @ApiResponse(code = 500, message = "서버 오류")})
    public ResponseEntity<?> selectNotification(@RequestParam int notificationId) {
        logger.debug("notificationId : {}", notificationId);
        try {
            NotificationDto notificationDto = notificationService.selectNotification(notificationId);
            return new ResponseEntity<NotificationDto>(notificationDto, HttpStatus.OK);
        } catch (Exception e) {
            return exceptionHandling(e);
        }
    }
    @GetMapping("/list")
    @ApiOperation(value = "공지들 조회", notes = "현재 페이지의 공지를 조회합니다.")
    @ApiResponses({@ApiResponse(code = 200, message = "공지들 조회 OK"), @ApiResponse(code = 500, message = "서버 오류")})
    public ResponseEntity<?> selectAllNotification(SearchCondition searchCondition) {
        logger.debug("searchCondition : {}", searchCondition);
        try {
            searchCondition = notificationService.makeNotificationSearchCondition(searchCondition);
            List<NotificationDto> list = notificationService.selectListNotification(searchCondition);
            return new ResponseEntity<List<NotificationDto>>(list, HttpStatus.OK);
        } catch (Exception e) {
            return exceptionHandling(e);
        }
    }
    @GetMapping("/list/count")
    @ApiOperation(value = "공지 총 개수 조회", notes = "공지 총 개수를 조회합니다.")
    @ApiResponses({@ApiResponse(code = 200, message = "공지 개수 조회 OK"), @ApiResponse(code = 500, message = "서버 오류")})
    public ResponseEntity<?> selectNotificationCount(SearchCondition searchCondition) {
        logger.debug("searchCondition : {}", searchCondition);
        try {
            int count = notificationService.getTotalNotificationCount(searchCondition );
            return new ResponseEntity<Integer>(count, HttpStatus.OK);
        } catch (Exception e) {
            return exceptionHandling(e);
        }
    }
    @PostMapping("")
    @ApiOperation(value = "공지 작성", notes = "1개의 공지를 작성합니다.")
    @ApiResponses({@ApiResponse(code = 200, message = "공지 작성 OK"), @ApiResponse(code = 500, message = "서버 오류")})
    public ResponseEntity<?> writeNotification(@RequestBody NotificationDto notificationDto) throws IOException {
        logger.debug("notificationDto : {}", notificationDto);
        try {
        	notificationDto.setUserId(3);
            notificationService.writeNotification(notificationDto);
            return new ResponseEntity<Void>(HttpStatus.OK);
        } catch (Exception e) {
            return exceptionHandling(e);
        }
    }

    @PostMapping("/update")
    @ApiOperation(value = "공지 수정", notes = "1개의 공지를 수정합니다.")
    @ApiResponses({@ApiResponse(code = 200, message = "공지 수정 OK"), @ApiResponse(code = 500, message = "서버 오류")})
    public ResponseEntity<?> updateNotification(@RequestBody NotificationDto notificationDto) {
        logger.debug("notificationDto : {}", notificationDto);
        try {
            notificationService.updateNotification(notificationDto);
            return new ResponseEntity<Void>(HttpStatus.OK);
        } catch (Exception e) {
            return exceptionHandling(e);
        }
    }
    @DeleteMapping("/{notificationId}")
    @ApiOperation(value = "공지 삭제", notes = "1개의 공지를 삭제합니다.")
    @ApiResponses({@ApiResponse(code = 200, message = "공지 삭제 OK"), @ApiResponse(code = 500, message = "서버 오류")})
    public ResponseEntity<?> deleteNotification(@PathVariable("notificationId") int notificationId) {
        logger.debug("notificationId : {}", notificationId);
        try {
            notificationService.deleteNotification(notificationId);
            return new ResponseEntity<Void>(HttpStatus.OK);
        } catch (Exception e) {
            return exceptionHandling(e);
        }
    }
    private ResponseEntity<String> exceptionHandling(Exception e) {
        e.printStackTrace();
        return new ResponseEntity<String>("Error : " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @GetMapping("/question")
    @ApiOperation(value = "자주 찾는 질문들 조회", notes = "현재 페이지의 질문을 조회합니다.")
    @ApiResponses({@ApiResponse(code = 200, message = "질문들 조회 OK"), @ApiResponse(code = 500, message = "서버 오류")})
    public ResponseEntity<?> selectQuestions(SearchCondition searchCondition) {
        logger.debug("searchCondition : {}", searchCondition);
        try {
            searchCondition = notificationService.makeQuestionSearchCondition(searchCondition);
            List<NotificationQuestionDto> list = notificationService.selectQuestion(searchCondition);
            return new ResponseEntity<List<NotificationQuestionDto>>(list, HttpStatus.OK);
        } catch (Exception e) {
            return exceptionHandling(e);
        }
    }
    @GetMapping("/question/count")
    @ApiOperation(value = "자주 찾는 질문들 총 개수 조회", notes = "자주 찾는 질문 총 개수를 조회합니다.")
    @ApiResponses({@ApiResponse(code = 200, message = "질문 개수 조회 OK"), @ApiResponse(code = 500, message = "서버 오류")})
    public ResponseEntity<?> selectQuestionCount(SearchCondition searchCondition) {
        logger.debug("searchCondition : {}", searchCondition);
        try {
            int count = notificationService.getTotalQuestionCount(searchCondition);
            return new ResponseEntity<Integer>(count, HttpStatus.OK);
        } catch (Exception e) {
            return exceptionHandling(e);
        }
    }
    @GetMapping("/inquiry")
    @ApiOperation(value = "문의내역 조회", notes = "문의 내역을 조회합니다.")
    @ApiResponses({@ApiResponse(code = 200, message = "문의 내역 조회 OK"), @ApiResponse(code = 500, message = "서버 오류")})
    public ResponseEntity<?> selectAllInquiry(int userId) {
        logger.debug("userId : {}", userId);
        try {
            List<InquiryDto> list = notificationService.selectListInquiry(userId);
            return new ResponseEntity<List<InquiryDto>>(list, HttpStatus.OK);
        } catch (Exception e) {
            return exceptionHandling(e);
        }
    }
    @PostMapping("/inquiry")
    @ApiOperation(value = "문의 작성", notes = "1개의 문의를 작성합니다.")
    @ApiResponses({@ApiResponse(code = 200, message = "문의 작성 OK"), @ApiResponse(code = 500, message = "서버 오류")})
    public ResponseEntity<?> writeInquiry(@RequestBody InquiryDto inquiryDto) throws IOException {
        logger.debug("inquiryDto : {}", inquiryDto);
        try {
            notificationService.writeInquiry(inquiryDto);
            return new ResponseEntity<Void>(HttpStatus.OK);
        } catch (Exception e) {
            return exceptionHandling(e);
        }
    }
    @PostMapping("/inquiry/update")
    @ApiOperation(value = "문의 수정", notes = "1개의 문의를 수정합니다.")
    @ApiResponses({@ApiResponse(code = 200, message = "문의 수정 OK"), @ApiResponse(code = 500, message = "서버 오류")})
    public ResponseEntity<?> updateInquiry(@RequestBody InquiryDto inquiryDto) {
        logger.debug("inquiryDto : {}", inquiryDto);
        try {
            notificationService.updateInquiry(inquiryDto);
            return new ResponseEntity<Void>(HttpStatus.OK);
        } catch (Exception e) {
            return exceptionHandling(e);
        }
    }
    @PostMapping("/inquiry/answer")
    @ApiOperation(value = "문의 답변 작성", notes = "문의에 대한 답변 작성합니다.")
    @ApiResponses({@ApiResponse(code = 200, message = "답변 작성 OK"), @ApiResponse(code = 500, message = "서버 오류")})
    public ResponseEntity<?> writeInquiryAnswer(@RequestBody InquiryDto inquiryDto) throws IOException {
        logger.debug("inquiryDto : {}", inquiryDto);
        try {
            notificationService.writeAnswer(inquiryDto);
            return new ResponseEntity<Void>(HttpStatus.OK);
        } catch (Exception e) {
            return exceptionHandling(e);
        }
    }
    @PostMapping("/inquiry/answer/update")
    @ApiOperation(value = "문의 답변 수정", notes = "1개의 문의 답변를 수정합니다.")
    @ApiResponses({@ApiResponse(code = 200, message = "문의 답변 수정 OK"), @ApiResponse(code = 500, message = "서버 오류")})
    public ResponseEntity<?> updateInquiryAnswer(@RequestBody InquiryDto inquiryDto) {
        logger.debug("inquiryDto : {}", inquiryDto);
        try {
            notificationService.updateAnswer(inquiryDto);
            return new ResponseEntity<Void>(HttpStatus.OK);
        } catch (Exception e) {
            return exceptionHandling(e);
        }
    }
    
}
