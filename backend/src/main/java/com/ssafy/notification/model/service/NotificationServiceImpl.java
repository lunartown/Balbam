package com.ssafy.notification.model.service;

import com.ssafy.notification.model.InquiryDto;
import com.ssafy.notification.model.NotificationDto;
import com.ssafy.notification.model.NotificationQuestionDto;
import com.ssafy.notification.model.mapper.NotificationMapper;
import com.ssafy.util.model.SearchCondition;
import com.ssafy.util.model.SizeConstant;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class NotificationServiceImpl implements NotificationService {
    private final NotificationMapper notificationMapper;

    public NotificationServiceImpl(NotificationMapper notificationMapper) {
        this.notificationMapper = notificationMapper;
    }

    @Override
    public NotificationDto selectNotification(int notificationId) throws Exception {
        return notificationMapper.selectNotification(notificationId);
    }

    @Override
    public List<NotificationDto> selectListNotification(SearchCondition searchCondition ) throws Exception {
        int pgNo = searchCondition.getCurrentPage();
        int start = pgNo * SizeConstant.LIST_SIZE - SizeConstant.LIST_SIZE;
        searchCondition.setStartIndex(start);
        return notificationMapper.selectListNotification(searchCondition);
    }
	@Override
	public int getTotalNotificationCount(SearchCondition searchCondition ) throws Exception {
		return notificationMapper.getTotalNotificationCount(searchCondition);
	}


    @Override
    public void writeNotification(NotificationDto notificationDto) throws Exception {
        notificationMapper.writeNotification(notificationDto);
    }

    @Override
    public void deleteNotification(int notificationId) throws Exception {
        notificationMapper.deleteNotification(notificationId);
    }

    @Override
    public void updateNotification(NotificationDto notificationDto) throws Exception {
        notificationMapper.updateNotification(notificationDto);
    }


    @Override
    public SearchCondition makeNotificationSearchCondition(SearchCondition searchCondition) throws Exception {
        int naviSize = SizeConstant.NAVIGATION_SIZE;
        int sizePerPage = SizeConstant.LIST_SIZE;

        searchCondition.setNaviSize(naviSize);
        int totalCount = notificationMapper.getTotalNotificationCount(searchCondition);
        int totalPageCount = (totalCount - 1) / sizePerPage + 1;
        searchCondition.setTotalPageCount(totalPageCount);
        searchCondition.setTotalCount(totalCount);
        boolean startRange = searchCondition.getCurrentPage() <= naviSize;
        searchCondition.setStartRange(startRange);
        boolean endRange = (totalPageCount - 1) / naviSize * naviSize < searchCondition.getCurrentPage();
        searchCondition.setEndRange(endRange);

        return searchCondition;
    }
	@Override
	public SearchCondition makeQuestionSearchCondition(SearchCondition searchCondition) throws Exception {
        int naviSize = SizeConstant.NAVIGATION_SIZE;
        int sizePerPage = SizeConstant.LIST_SIZE;

        searchCondition.setCurrentPage(searchCondition.getCurrentPage());
        searchCondition.setNaviSize(naviSize);
        int totalCount = notificationMapper.getTotalQuestionCount(searchCondition);
        int totalPageCount = (totalCount - 1) / sizePerPage + 1;
        searchCondition.setTotalPageCount(totalPageCount);
        searchCondition.setTotalCount(totalCount);
        boolean startRange = searchCondition.getCurrentPage() <= naviSize;
        searchCondition.setStartRange(startRange);
        boolean endRange = (totalPageCount - 1) / naviSize * naviSize < searchCondition.getCurrentPage();
        searchCondition.setEndRange(endRange);

        return searchCondition;
	}

	@Override
	public List<NotificationQuestionDto> selectQuestion(SearchCondition searchCondition) throws Exception {
        int pgNo = searchCondition.getCurrentPage();
        int start = pgNo * SizeConstant.LIST_SIZE - SizeConstant.LIST_SIZE;
        searchCondition.setStartIndex(start);
        return notificationMapper.selectQuestion(searchCondition);
	}

	@Override
	public int getTotalQuestionCount(SearchCondition searchCondition) throws Exception {
		return notificationMapper.getTotalQuestionCount(searchCondition);
	}

	@Override
	public List<InquiryDto> selectListInquiry(int userId) throws SQLException {
		return notificationMapper.selectListInquiry(userId);
	}

	@Override
	public void writeInquiry(InquiryDto inquiryDto) throws SQLException {
        notificationMapper.writeInquiry(inquiryDto);
		
	}

	@Override
	public void updateInquiry(InquiryDto inquiryDto) throws SQLException {
		notificationMapper.updateInquiry(inquiryDto);
		
	}

	@Override
	public void writeAnswer(InquiryDto inquiryDto) throws SQLException {
        notificationMapper.writeAnswer(inquiryDto);
		
	}

	@Override
	public void updateAnswer(InquiryDto inquiryDto) throws SQLException {
		notificationMapper.updateAnswer(inquiryDto);		
	}




}
