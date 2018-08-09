package com.lm.mapper.switchs;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

import com.lm.api.model.port.UtilPort;

/**
 * 端口百分比接口
 * @author Administrator
 *
 */
@Mapper
public interface UtilPortMapper {
	// 新增UtilPort机信息
		@Insert("INSERT INTO `lszt`.`l_util_port` (`switch_id`,"
				+ "`port1`,`port2`,`port3`,`port4`,"
				+ "`port5`,`port6`,`port7`, `port8`, "
				+ "`port9`, `port10`,`port11`,"
				+ "`port12`,`port13`,`port14`,"
				+ "`port15`,`port16`,`port17`,"
				+ "`port18`,`port19`,`port20`,"
				+ "`port21`,`port22`,`port23`"
				+ ",`port24`,`port25`,"
				+ "`port26`, `port27`,`port28`,`port29`,`port30`,`time`)"
				+ "value(#{idPort},#{port1},#{port2},"
				+ "#{port3},#{port4},"
				+ "#{port5},#{port6},#{port7},#{port8},#{port9},"
				+ "#{port10},#{port11},#{port12},"
				+ "#{port13},#{port14},#{port15},#{port16},#{port17},"
				+ "#{port18},#{port19},#{port20},#{port21},"
				+ "#{port22},#{port23},#{port24},#{port25},"
				+ "#{port26},#{port27},#{port28},"
				+ "#{port29},#{port30},#{time})")
	int saveUtil(UtilPort up);
}
