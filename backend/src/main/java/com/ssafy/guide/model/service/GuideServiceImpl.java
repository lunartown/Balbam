package com.ssafy.guide.model.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.guide.model.GuideDto;
import com.ssafy.guide.model.mapper.GuideMapper;
@Service
public class GuideServiceImpl implements GuideService {

	public GuideMapper guideMapper;

	@Autowired
	public GuideServiceImpl(GuideMapper guideMapper) {
		super();
		this.guideMapper = guideMapper;
	}

	@Override
	public int idCheck(String id) throws Exception {
		return guideMapper.idCheck(id);
	}

	@Override
	public int emailCheck(String email) throws Exception {
		return guideMapper.emailCheck(email);
	}

	@Override
	public void joinGuide(GuideDto guideDto) throws Exception {
		guideMapper.joinGuide(guideDto);
	}

	@Override
	public GuideDto loginGuide(Map<String, String> map) throws Exception {
		return guideMapper.loginGuide(map);
	}

	@Override
	public List<GuideDto> listGuide(Map<String, Object> map) throws Exception {
		return guideMapper.listGuide(map);
	}

	@Override
	public String getId(Map<String, String> map) throws Exception {
		return guideMapper.getId(map);
	}

	@Override
	public int validGuide(Map<String, String> map) throws Exception {
		return guideMapper.validGuide(map);
	}

	@Override
	public void updateGuide(GuideDto guideDto) throws Exception {
		guideMapper.updateGuide(guideDto);
	}

	@Override
	public void deleteGuide(String id) throws Exception {
		guideMapper.deleteGuide(id);
	}

}
