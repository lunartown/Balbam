package com.ssafy.notification.model.mapper;
import com.ssafy.notification.model.InquiryDto;
import com.ssafy.notification.model.NotificationDto;
import com.ssafy.notification.model.NotificationQuestionDto;
import com.ssafy.util.model.SearchCondition;
import org.apache.ibatis.annotations.Mapper;
import java.sql.SQLException;
import java.util.List;
@Mapper
public interface NotificationMapper {
    NotificationDto selectNotification(int notificationId) throws SQLException;
    List<NotificationDto> selectListNotification(SearchCondition searchCondition) throws SQLException;
    void writeNotification(NotificationDto notificationDto) throws SQLException;
    void deleteNotification(int notificationId) throws SQLException;
    void updateNotification(NotificationDto notificationDto) throws SQLException;
    int getTotalNotificationCount(SearchCondition searchCondition) throws SQLException;
    int getTotalQuestionCount(SearchCondition searchCondition) throws SQLException;
    List<NotificationQuestionDto> selectQuestion(SearchCondition searchCondition) throws SQLException;
    List<InquiryDto> selectListInquiry(int userId) throws SQLException;
    void writeInquiry(InquiryDto inquiryDto) throws SQLException;
    void updateInquiry(InquiryDto inquiryDto) throws SQLException;
    void writeAnswer(InquiryDto inquiryDto) throws SQLException;
    void updateAnswer(InquiryDto inquiryDto) throws SQLException;
}
