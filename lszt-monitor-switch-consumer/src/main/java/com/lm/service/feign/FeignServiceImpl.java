package com.lm.service.feign;


import org.springframework.stereotype.Component;

import com.lm.service.user.HystrixService;
import com.lm.base.BaseApiService;
import com.lm.base.ResponseBase;

/**
 * hystrix 降级
 * 
 * @author Administrator
 *
 */
@Component
public class FeignServiceImpl extends BaseApiService implements HystrixService {

	public ResponseBase getfeig() {
		// 服务的降级处理
		return setResultError("开始降级!!!");
	}

}
