package com.ssafy.guide.model.mapper;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Mapper;

import com.ssafy.guide.model.GuideDto;
import com.ssafy.user.model.UserDto;

@Mapper
public interface GuideMapper {
	int idCheck(String id) throws SQLException;
	int emailCheck(String email) throws SQLException;
	void joinGuide(GuideDto guideDto) throws SQLException;
	GuideDto loginGuide(Map<String, String> map) throws SQLException;
	List<GuideDto> listGuide(Map<String, Object> map) throws SQLException;
	String getId(Map<String, String> map) throws SQLException;
	int validGuide(Map<String, String> map) throws SQLException;
	void updateGuide(GuideDto guideDto) throws SQLException;
	void deleteGuide(String id) throws SQLException;
}
