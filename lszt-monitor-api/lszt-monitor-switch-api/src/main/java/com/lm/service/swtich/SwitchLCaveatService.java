package com.lm.service.swtich;

import com.lm.api.model.Caveat;
import com.lm.base.ResponseBase;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/swCaveat")
public interface SwitchLCaveatService {
	// 新增交换机信息Cpu
	@RequestMapping("/addCaveat")
	ResponseBase addCaveat(@RequestBody Caveat caveat);
}
