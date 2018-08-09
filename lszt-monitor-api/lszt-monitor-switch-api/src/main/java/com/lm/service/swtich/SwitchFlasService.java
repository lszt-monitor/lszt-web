package com.lm.service.swtich;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import com.lm.api.model.flas.Flas;
import com.lm.base.ResponseBase;

@RequestMapping("/swFlas")
public interface SwitchFlasService {

	// 新增交换机信息Flas
	@RequestMapping("/addFlas")
	ResponseBase addFlas(@RequestBody Flas flas);
	
	//查询Flas的值是否一致
	@RequestMapping("/findFlas")
	ResponseBase findFlas(@RequestBody int id_dram);
}
