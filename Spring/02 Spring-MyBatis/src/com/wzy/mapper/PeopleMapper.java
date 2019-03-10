package com.wzy.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.wzy.pojo.People;

public interface PeopleMapper {
	
	@Select("select * from people")
	List<People> selAll();
}
