package com.ssafy.user.model.mapper;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Mapper;
import com.ssafy.user.model.UserDto;

@Mapper
public interface UserMapper {
	int idCheck(String id) throws SQLException;
	int emailCheck(String email) throws SQLException;
	void joinUser(UserDto userDto) throws SQLException;
	UserDto loginUser(Map<String, String> map) throws SQLException;
	List<UserDto> listUser(Map<String, Object> map) throws SQLException;
	String getId(Map<String, String> map) throws SQLException;
	void hitCaptcha(String id) throws SQLException;
	int getCaptcha(String id) throws SQLException;
	void initCaptcha(String id) throws SQLException;
	void updateUser(UserDto userDto) throws SQLException;
	void deleteUser(String id) throws SQLException;
	public UserDto userInfo(String userId) throws SQLException;
	public void saveRefreshToken(Map<String, String> map) throws SQLException;
	public Object getRefreshToken(String userid) throws SQLException;
	public void deleteRefreshToken(Map<String, String> map) throws SQLException;
}
