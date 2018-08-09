package com.lm.mapper.switchs;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

import com.lm.api.model.cpu.Cpu;

/**
 * Cpu信息
 * 
 * @author Administrator
 *
 */
@Mapper
public interface CpuPortMapper {
	// 新增CpuPort机信息
	@Insert("INSERT INTO `lszt`.`l_switch_cpu` (switch_id,cpu_sec,cpu_1min,cpu_5min,`time`)"
			+ "VALUES (#{idSwitchCpu},#{cpuSec},#{cpu1Min},#{cpu5Min},#{time})")
	int saveCpu(Cpu cpu);
}
