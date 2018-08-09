package com.lm.mapper.windows;

import org.apache.ibatis.annotations.Mapper;

import com.lm.api.model.windows.WindowsPc;
@Mapper
public interface WrealtimPcMapper {

	// 新增windows机信息
	int saveWd(WindowsPc wd);

}
