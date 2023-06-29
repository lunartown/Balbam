package com.ssafy.attraction.model.mapper;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.attraction.model.AttractionDto;
import com.ssafy.attraction.model.GugunDto;
import com.ssafy.attraction.model.SidoDto;

@Mapper
public interface AttractionMapper {

	List<SidoDto> getSido() throws SQLException;

	List<GugunDto> getGugun(int sidoCode) throws SQLException;

	List<AttractionDto> search(Map<String, String> map) throws SQLException;

}
