package com.lm.mapper.switchs;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.lm.api.model.flas.Flas;

import feign.Param;

/**
 * 端口百分比接口
 * 
 * @author Administrator
 *
 */
@Mapper
public interface FlasPortMapper {
	// 新增Flas信息
	@Insert("INSERT INTO `lszt`.`l_switch_flas`(switch_id,total_flas,util_flas,used_flas,time)"
			+ "value(#{idSwitchFlas},#{totalFlas},#{utilFlas},#{usedFlas},#{time})")
	int saveFlas(Flas flas);

	// 查询Flas的值是否一直
	@Select("SELECT used_flas,total_flas,util_flas,time FROM `l_switch_flas` WHERE switch_id=#{id_flas}  ORDER BY `time` DESC")
	Flas findByFlasTime(@Param("id_flas") int id_flas);
}
