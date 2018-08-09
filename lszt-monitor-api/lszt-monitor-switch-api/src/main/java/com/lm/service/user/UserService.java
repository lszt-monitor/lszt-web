package com.lm.service.user;


import com.lm.api.model.User;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import com.lm.base.ResponseBase;

@RequestMapping("/user")
public interface UserService {

	// 登录接口
	@RequestMapping("/login")
	ResponseBase userLogin(@RequestBody User user);

	// 注销接口
	@RequestMapping("/outLoginUser")
	ResponseBase outLoginUser(@RequestBody Long id);


	// 新增用户信息接口
	@RequestMapping("/regUser")
	ResponseBase regUser(@RequestBody User user);




}
