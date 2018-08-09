package com.lm.service.swtich;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import com.lm.api.model.cpu.Cpu;
import com.lm.base.ResponseBase;

@RequestMapping("/swCpu")
public interface SwitchCpuService {
	// 新增交换机信息Cpu
	@RequestMapping("/addCpu")
	ResponseBase addCpu(@RequestBody Cpu cpu);
}
