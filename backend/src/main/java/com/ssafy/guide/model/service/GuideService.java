package com.ssafy.guide.model.service;

import java.util.List;
import java.util.Map;

import com.ssafy.guide.model.GuideDto;
import com.ssafy.user.model.UserDto;

public interface GuideService {
	int idCheck(String id) throws Exception;
	int emailCheck(String email) throws Exception;
	void joinGuide(GuideDto guideDto) throws Exception;
	GuideDto loginGuide(Map<String, String> map) throws Exception;
	List<GuideDto> listGuide(Map<String, Object> map) throws Exception;
	String getId(Map<String, String> map) throws Exception;
	int validGuide(Map<String, String> map) throws Exception;
	void updateGuide(GuideDto guideDto) throws Exception;
	void deleteGuide(String id) throws Exception;
	
}
