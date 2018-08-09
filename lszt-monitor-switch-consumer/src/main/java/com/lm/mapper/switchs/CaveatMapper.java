package com.lm.mapper.switchs;

import com.lm.api.model.Caveat;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

/**
 * 告警信息
 * 
 * @author Administrator
 *
 */
@Mapper
public interface CaveatMapper {
	// 新增告警信息
	@Insert("INSERT INTO `lszt`.`l_caveat` (warnleave,fromguy,warncontent,dealwith,`warntime`,`switch_id`)"
			+ "VALUES (#{warnleave},#{fromguy},#{warncontent},#{dealwith},#{warntime},#{switchId})")
	int saveCall(Caveat caveat);
}
