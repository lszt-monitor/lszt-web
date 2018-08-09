package com.lm.service.swtich;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import com.lm.api.model.port.UtilPort;
import com.lm.base.ResponseBase;

@RequestMapping("/swUtil")
public interface SwitchUtilService {
	// 新增交换机信息util
	@RequestMapping("/addUtil")
	ResponseBase addUtil(@RequestBody UtilPort util);

}
