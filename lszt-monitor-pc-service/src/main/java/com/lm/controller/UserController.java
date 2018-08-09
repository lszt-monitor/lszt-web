package com.lm.controller;

import com.lm.api.model.User;
import com.lm.base.ResponseBase;
import com.lm.serviceImpl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 订单接口
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserServiceImpl userService;

    /**
     * 登录接口
     *
     * @param userName
     * @param userPwd
     * @return
     */
    @PostMapping("/login")
    public ResponseBase doLogin(@RequestParam(value = "userName", required = true) String userName,
                                @RequestParam(value = "userPwd", required = true) String userPwd){
        User user = new User();
        user.setUserName(userName);
        user.setPwd(userPwd);
        ResponseBase bsUser = userService.userLogin(user);
        return bsUser;
    }

    /**
     * 注册接口
     * @param user
     * @return
     */
    @PostMapping("sava")
    public ResponseBase savaUser(@RequestBody  User user) {
        return userService.regUser(user);
    }
}
