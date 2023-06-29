package com.ssafy.user.model.service;

import java.util.List;
import java.util.Map;

import com.ssafy.user.model.UserDto;

public interface UserService {
	int idCheck(String id) throws Exception;
	int emailCheck(String email) throws Exception;
	void joinUser(UserDto userDto) throws Exception;
	UserDto loginUser(Map<String, String> map) throws Exception;
	List<UserDto> listUser(Map<String, Object> map) throws Exception;
	String getId(Map<String, String> map) throws Exception;
	int hitCaptcha(String id) throws Exception;
	void updateUser(UserDto userDto) throws Exception;
	void deleteUser(String id) throws Exception;
	String[] getCaptcha();
	boolean checkCaptcha(String key, String value);
	public UserDto userInfo(String id) throws Exception;
	public void saveRefreshToken(String id, String refreshToken) throws Exception;
	public Object getRefreshToken(String id) throws Exception;
	public void deleRefreshToken(String id) throws Exception;
}
