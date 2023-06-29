package com.ssafy.attraction.model.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.ssafy.attraction.model.AttractionDto;
import com.ssafy.attraction.model.GugunDto;
import com.ssafy.attraction.model.SidoDto;

public interface AttractionService {

	List<SidoDto> getSido() throws Exception;

	List<GugunDto> getGugun(int sidoCode) throws SQLException;

	List<AttractionDto> search(Map<String, String> map) throws SQLException;

	String getAddress(String longitude, String latitude);

}
