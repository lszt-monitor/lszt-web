package com.lm.service.swtich;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import com.lm.api.model.darm.Dram;
import com.lm.base.ResponseBase;

@RequestMapping("/wsDarm")
public interface SwitchDarmService {

	// 新增交换机信息Darm
	@RequestMapping("/addDarm")
	ResponseBase addDarm(@RequestBody Dram dram);

	//查询Dram的值是否一致
	@RequestMapping("/findDarm")
	ResponseBase findDarm(@RequestBody int id_dram);

}
