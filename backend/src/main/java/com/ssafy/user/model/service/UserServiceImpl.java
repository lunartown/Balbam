package com.ssafy.user.model.service;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.ssafy.user.model.UserDto;
import com.ssafy.user.model.mapper.UserMapper;

@Service
public class UserServiceImpl implements UserService {

	public UserMapper userMapper;

	@Autowired
	public UserServiceImpl(UserMapper userMapper) {
		super();
		this.userMapper = userMapper;
	}

	@Override
	public int idCheck(String id) throws Exception {
		return userMapper.idCheck(id);
	}

	@Override
	public int emailCheck(String email) throws Exception {
		return userMapper.emailCheck(email);
	}

	@Override
	public void joinUser(UserDto userDto) throws Exception {
		userMapper.joinUser(userDto);
	}

	@Override
	public UserDto loginUser(Map<String, String> map) throws Exception {
		UserDto userDto = userMapper.loginUser(map);
		if(userDto != null) userMapper.initCaptcha(map.get("id"));
		return userDto;
	}

	@Override
	public List<UserDto> listUser(Map<String, Object> map) throws Exception {
		return userMapper.listUser(map);
	}

	@Override
	public String getId(Map<String, String> map) throws Exception {
		return userMapper.getId(map);
	}

	@Override
	public int hitCaptcha(String id) throws Exception {
		userMapper.hitCaptcha(id);
		return userMapper.getCaptcha(id);
	}

	@Override
	public void updateUser(UserDto userDto) throws Exception {
		userMapper.updateUser(userDto);
	}

	@Override
	public void deleteUser(String id) throws Exception {
		userMapper.deleteUser(id);
	}

	@Override
	public String[] getCaptcha() {
		String[] captcha = new String[2];
		String clientId = "v8fa47ot12";// 애플리케이션 클라이언트 아이디값";
		String clientSecret = "uIS6KyFlILy04F0OQsyXgbmN0PN3OensM1Bk4nEF";// 애플리케이션 클라이언트 시크릿값";

		try {
			String code = "0"; // 키 발급시 0, 캡차 이미지 비교시 1로 세팅
			String apiURL = "https://naveropenapi.apigw.ntruss.com/captcha/v1/nkey?code=" + code;
			URL url = new URL(apiURL);
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.setRequestMethod("GET");
			con.setRequestProperty("X-NCP-APIGW-API-KEY-ID", clientId);
			con.setRequestProperty("X-NCP-APIGW-API-KEY", clientSecret);
			int responseCode = con.getResponseCode();
			BufferedReader br;
			if (responseCode == 200) { // 정상 호출
				br = new BufferedReader(new InputStreamReader(con.getInputStream()));
			} else { // 오류 발생
				br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
			}
			String inputLine;
			StringBuffer response = new StringBuffer();
			while ((inputLine = br.readLine()) != null) {
				response.append(inputLine);
			}
			br.close();
			System.out.println(response);
			Gson gson = new Gson();
			Map map = gson.fromJson(response.toString(), Map.class);

			String key = (String) map.get("key"); // https://naveropenapi.apigw.ntruss.com/captcha/v1/nkey 호출로 받은 키값
			apiURL = "https://naveropenapi.apigw.ntruss.com/captcha-bin/v1/ncaptcha?key=" + key
					+ "&X-NCP-APIGW-API-KEY-ID" + clientId;
			url = new URL(apiURL);
			con = (HttpURLConnection) url.openConnection();
			con.setRequestMethod("GET");
			responseCode = con.getResponseCode();
			if (responseCode == 200) { // 정상 호출
				InputStream is = con.getInputStream();
				byte[] bytes = IOUtils.toByteArray(is);
				String image = Base64.getEncoder().encodeToString(bytes);
				System.out.println(image);
				is.close();
				captcha[0] = key;
				captcha[1] = image;
				
				return captcha;
			} else { // 오류 발생
				br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
				inputLine = "";
				response = new StringBuffer();
				while ((inputLine = br.readLine()) != null) {
					response.append(inputLine);
				}
				br.close();
				System.out.println(response.toString());
				return null;
			}
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
		
	}

	@Override
	public boolean checkCaptcha(String key, String value) {
		String clientId = "v8fa47ot12";//애플리케이션 클라이언트 아이디값";
        String clientSecret = "uIS6KyFlILy04F0OQsyXgbmN0PN3OensM1Bk4nEF";//애플리케이션 클라이언트 시크릿값";
        try {
            String code = "1"; // 키 발급시 0,  캡차 이미지 비교시 1로 세팅
            String apiURL = "https://naveropenapi.apigw.ntruss.com/captcha/v1/nkey?code=" + code +"&key="+ key + "&value="+ value;

            URL url = new URL(apiURL);
            HttpURLConnection con = (HttpURLConnection)url.openConnection();
            con.setRequestMethod("GET");
            con.setRequestProperty("X-NCP-APIGW-API-KEY-ID", clientId);
            con.setRequestProperty("X-NCP-APIGW-API-KEY", clientSecret);
            int responseCode = con.getResponseCode();
            BufferedReader br;
            if(responseCode==200) { // 정상 호출
                br = new BufferedReader(new InputStreamReader(con.getInputStream()));
            } else {  // 오류 발생
                br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
            }
            String inputLine;
            StringBuffer response = new StringBuffer();
            while ((inputLine = br.readLine()) != null) {
                response.append(inputLine);
            }
            br.close();

            Gson gson = new Gson();
            Map map = gson.fromJson(response.toString(), Map.class);
            
            return (boolean) map.get("result");
        } catch (Exception e) {
            System.out.println(e);
        }
        
		return false;
	}
	
	@Override
	public UserDto userInfo(String userId) throws Exception {
		UserDto userDto = userMapper.userInfo(userId);
		System.out.println(userDto);
		return userDto;
	}
	
	@Override
	public void saveRefreshToken(String userId, String refreshToken) throws Exception {
		Map<String, String> map = new HashMap<String, String>();
		map.put("userId", userId);
		map.put("token", refreshToken);
		userMapper.saveRefreshToken(map);
	}

	@Override
	public Object getRefreshToken(String userId) throws Exception {
		return userMapper.getRefreshToken(userId);
	}

	@Override
	public void deleRefreshToken(String userId) throws Exception {
		Map<String, String> map = new HashMap<String, String>();
		map.put("userId", userId);
		map.put("token", null);
		userMapper.deleteRefreshToken(map);
	}
}
