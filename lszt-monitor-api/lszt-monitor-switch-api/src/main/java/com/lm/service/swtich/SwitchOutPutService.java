package com.lm.service.swtich;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import com.lm.api.model.port.OutPutPort;
import com.lm.base.ResponseBase;

@RequestMapping("/swOutPut")
public interface SwitchOutPutService {

	// 新增交换机信息OutPut
	@RequestMapping("/addOutPut")
	ResponseBase addOutPut(@RequestBody OutPutPort outPut);

}
