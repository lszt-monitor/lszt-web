package com.lm.serviceImpl;

import com.alibaba.fastjson.JSONObject;

import com.lm.api.model.User;
import com.lm.service.user.UserService;
import com.lm.base.BaseApiService;
import com.lm.base.ResponseBase;
import com.lm.mapper.UserMapper;
import com.lm.utils.JwtUtils;
import com.lm.utils.MD5Util;
import net.logstash.logback.encoder.org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Date;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    /**
     * 登录
     */
    @Override
    public ResponseBase userLogin(@RequestBody User user) {
        JSONObject userJson = new JSONObject();
        // 1 验证参数
        String userName = user.getUserName();
        if (StringUtils.isEmpty(userName)) {
            return BaseApiService.setResultError("用户不能为空!");
        }
        String passWord = user.getPwd();
        if (StringUtils.isEmpty(passWord)) {
            return BaseApiService.setResultError("密码不能为空!");
        }
        // 2数据库查看账号密码是否正确 加密密码
        String newPassword = MD5Util.MD5(passWord);
        // 返回user对象
        User dbUser = userMapper.login(userName, newPassword);
        if (dbUser == null) {
            return BaseApiService.setResultError("账号或密码不正确!");
        }
        if ("admin".equals(dbUser.getSign())){
            //生产jwt token
            String token = JwtUtils.genJsonWebToken(dbUser);
            userJson.put("token", token);
        }
        dbUser.setPwd(null);
        userJson.put("user", dbUser);
        return BaseApiService.setResultSuccess(userJson);
    }

    /**
     * 注册
     *
     * @param
     * @return
     */
    @Override
    public ResponseBase regUser(@RequestBody User user) {
        // 密码加密
        String password = user.getPwd();
        if (StringUtils.isEmpty(password)) {
            return BaseApiService.setResultError("密码不能为空.");
        }
        String newPassword = MD5Util.MD5(password);
        user.setPwd(newPassword);
        user.setCreateTime(new Date());
        Integer result = userMapper.insertUser(user);
        if (result <= 0) {
            return BaseApiService.setResultError("注册用户失败.");
        }
        return BaseApiService.setResultSuccess("用户注册成功.");
    }


    @Override
    public ResponseBase outLoginUser(Long id) {
        return null;
    }
}
