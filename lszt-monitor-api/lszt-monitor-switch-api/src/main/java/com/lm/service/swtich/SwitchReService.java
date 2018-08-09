package com.lm.service.swtich;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import com.lm.api.model.port.ReceivePort;
import com.lm.base.ResponseBase;
@RequestMapping("/swRe")
public interface SwitchReService {


	// 新增交换机信息re
	@RequestMapping("/addRe")
	ResponseBase addRe(@RequestBody ReceivePort re);
}
