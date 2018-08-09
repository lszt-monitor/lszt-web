package com.lm.mapper.switchs;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.lm.api.model.darm.Dram;

import feign.Param;

/**
 * 端口百分比接口
 * 
 * @author Administrator
 *
 */
@Mapper
public interface DramPortMapper {
	// 新增UtilPort机信息
	@Insert("INSERT INTO `lszt`.`l_switch_dram`(switch_id,util_dram,total_dram,used_dram,time)"
			+ "value(#{idSwitchDram},#{utilDram},#{totalDram},#{usedDram},#{time})")
	int saveDram(Dram dram);
	@Select("SELECT util_dram,total_dram,used_dram FROM `l_switch_dram` WHERE switch_id=#{id_dram} ORDER BY `time` DESC")
	// 查询Dram的值是否一直
	Dram findByDramTime(@Param("id_dram") int id_dram);
}
