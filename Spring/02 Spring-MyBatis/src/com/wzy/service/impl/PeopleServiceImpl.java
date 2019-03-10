package com.wzy.service.impl;

import java.util.List;

import com.wzy.mapper.PeopleMapper;
import com.wzy.pojo.People;
import com.wzy.service.PeopleService;

public class PeopleServiceImpl implements PeopleService {

	private PeopleMapper peopleMapper;
	
	public PeopleMapper getPeopleMapper() {
		return peopleMapper;
	}

	public void setPeopleMapper(PeopleMapper peopleMapper) {
		this.peopleMapper = peopleMapper;
	}

	@Override
	public List<People> show() {
		// TODO Auto-generated method stub
		return peopleMapper.selAll();
	}

}
