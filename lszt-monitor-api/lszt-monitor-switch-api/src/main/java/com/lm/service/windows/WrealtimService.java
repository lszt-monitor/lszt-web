package com.lm.service.windows;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import com.lm.api.model.windows.WindowsPc;
import com.lm.base.ResponseBase;
@RequestMapping("/windows")
public interface WrealtimService{

	// 新增windows信息
	@RequestMapping("/save")
	ResponseBase save(@RequestBody WindowsPc windows);
}
