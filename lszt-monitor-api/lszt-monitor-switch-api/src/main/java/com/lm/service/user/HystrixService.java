package com.lm.service.user;



import org.springframework.web.bind.annotation.RequestMapping;

import com.lm.base.ResponseBase;

public interface HystrixService {
	//熔断器
	@RequestMapping("/getfeig")
	ResponseBase getfeig();
	
	
}
