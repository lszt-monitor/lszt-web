package com.lm.mapper;

import com.lm.api.model.User;
import org.apache.ibatis.annotations.*;


@Mapper
public interface UserMapper {


    /**
     * 登录查询User
     *
     * @param
     * @return
     */
    @Select("select  id, `user_name`,`openid`, `name`,pwd, `head_img`, `phone`, `sign`, `sex`, `city`, `create_time` from user where user_name =#{userName} and pwd=#{pwd}")
    User login(@Param("userName")String userName, @Param("pwd") String passWord);
    /**
     * 根据主键ID查找
     *
     * @param userId
     * @return
     */
    @Select("select id,`openid`, `name`,pwd, `head_img`, `phone`, `sign`, `sex`, `city`, `create_time` from user where id =#{userId}")
    User findById(int userId);


    /**
     * 根据openid找用户
     * 微信登录备用
     *
     * @param openid
     * @return
     */
    @Select("select id,`openid`, `name`,pwd, `head_img`, `phone`, `sign`, `sex`, `city`, `create_time` from user where openid =#{openid}")
    User findByopenid(String openid);

    /**
     * 保存用户新
     * @param user
     * @return
     */
    @Insert("INSERT INTO `user` ( `openid`, `name`,user_name, pwd,`head_img`, `phone`, `sex`, `city`, `create_time`)" +
            "VALUES" +
            "(#{openid},#{name},#{userName},#{pwd},#{headImg},#{phone},#{sex},#{city},#{createTime});")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    int insertUser(User user);

}
