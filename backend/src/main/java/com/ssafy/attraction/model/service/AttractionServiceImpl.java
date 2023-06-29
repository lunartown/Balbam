package com.ssafy.attraction.model.service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.ssafy.attraction.model.AttractionDto;
import com.ssafy.attraction.model.GugunDto;
import com.ssafy.attraction.model.SidoDto;
import com.ssafy.attraction.model.mapper.AttractionMapper;

@Service
public class AttractionServiceImpl implements AttractionService {

	private AttractionMapper attractionMapper;

	@Autowired
	public AttractionServiceImpl(AttractionMapper attractionMapper) {
		super();
		this.attractionMapper = attractionMapper;
	}

	@Override
	public List<SidoDto> getSido() throws Exception {
		return attractionMapper.getSido();
	}

	@Override
	public List<GugunDto> getGugun(int sidoCode) throws SQLException {
		return attractionMapper.getGugun(sidoCode);
	}

	@Override
	public List<AttractionDto> search(Map<String, String> map) throws SQLException {
		Map newMap = new HashMap<String, Object>();
		newMap.put("sidoCode", map.get("sidoCode"));
		newMap.put("gugunCode", map.get("gugunCode"));
		newMap.put("keyword", map.get("keyword"));
		newMap.put("east", map.get("east"));
		newMap.put("west", map.get("west"));
		newMap.put("south", map.get("south"));
		newMap.put("north", map.get("north"));
		newMap.put("limit", map.get("limit"));
		List<String> categories = new ArrayList<>();
		String category = map.get("categories");
		if (category != null) {
			for (int i = 0; i < category.length() / 2; i++) {
				categories.add(category.substring(i * 2, i * 2 + 2));
			}
		}
		newMap.put("categories", categories);
		return attractionMapper.search(newMap);
	}

	@Override
	public String getAddress(String longitude, String latitude) {
		String clientId = "v8fa47ot12";// 애플리케이션 클라이언트 아이디값";
		String clientSecret = "uIS6KyFlILy04F0OQsyXgbmN0PN3OensM1Bk4nEF";// 애플리케이션 클라이언트 시크릿값";

		try {
			String coords = longitude + "," + latitude;
		    String sourcecrs = "default";
		    String orders = "addr";
		    String output = "json";
			String apiURL = "https://naveropenapi.apigw.ntruss.com/map-reversegeocode/v2/gc?";
			apiURL += "coords=" + coords;
			apiURL += "&sorcecrs=" + sourcecrs;
			apiURL += "&orders=" + orders;
			apiURL += "&output=" + output;
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
			return response.toString();
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
		
	}

}
