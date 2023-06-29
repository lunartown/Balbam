package com.ssafy.notification.model.service;

import com.ssafy.notification.model.InquiryDto;
import com.ssafy.notification.model.NotificationDto;
import com.ssafy.notification.model.NotificationQuestionDto;
import com.ssafy.util.model.SearchCondition;

import java.sql.SQLException;
import java.util.List;

public interface NotificationService {
    NotificationDto selectNotification(int notificationId) throws Exception;
    List<NotificationDto> selectListNotification(SearchCondition searchCondition ) throws Exception;
    void writeNotification(NotificationDto notificationDto) throws Exception;
    void deleteNotification(int notificationId) throws Exception;
    void updateNotification(NotificationDto notificationDto) throws Exception;
    SearchCondition makeNotificationSearchCondition(SearchCondition searchCondition) throws Exception;
    SearchCondition makeQuestionSearchCondition(SearchCondition searchCondition) throws Exception;
    int getTotalQuestionCount(SearchCondition searchCondition) throws Exception;
    int getTotalNotificationCount(SearchCondition searchCondition ) throws Exception;
    List<NotificationQuestionDto> selectQuestion(SearchCondition searchCondition) throws Exception;
    List<InquiryDto> selectListInquiry(int userId) throws SQLException;
    void writeInquiry(InquiryDto inquiryDto) throws SQLException;
    void updateInquiry(InquiryDto inquiryDto) throws SQLException;
    void writeAnswer(InquiryDto inquiryDto) throws SQLException;
    void updateAnswer(InquiryDto inquiryDto) throws SQLException;
}
