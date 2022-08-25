package com.sp.mapper;

import com.sp.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;

/**
 * 用户模块的持久层接口
 */
@Mapper
public interface UserMapper {
    /**
     * 插入用户的数据
     * @param user 用户的数据
     * @return 受影响的行数
     */
    Integer insert(User user);

    /**
     * 根据用户名来查询用户的数据
     * @param username 用户名
     * @return 如果找到对应的用户则返回这个用户数据，如果没有则返回null值
     */
    User findByUsername(@Param("username") String username);

    /**
     * 根据用户的uid来修改用户密码
     * @param uid 用户的id
     * @param password 用户输入的新密码
     * @param modifiedUser 修改数据的执行者
     * @param modifiedTime 修改数据的时间
     * @return 返回值为受影响的行数
     */
    Integer updatePasswordByUid(@Param("uid") Integer uid,
                                @Param("password") String password,
                                @Param("modifiedUser") String modifiedUser,
                                @Param("modifiedTime") Date modifiedTime);

    /**
     * 根据用户的id查询用户的数据
     * @param uid 用户的id
     * @return 如果存在则返回对象，否则返回null
     */
    User findByUid(@Param("uid") Integer uid);

    /**
     * 更新用户信息
     * @param user 用户的数据
     * @return 返回受影响的行数
     */
    Integer updateInfoByUid(User user);

    /**
     * 根据用户uid值来修改用户的头像
     * @param uid 用户的uid
     * @param avatar 用户的头像
     * @param modifiedUser 修改用户头像的执行者
     * @param modifiedTime 修改用户头像的时间
     * @return 返回受影响的行数
     */
    Integer updateAvatarByUid(@Param("uid") Integer uid,
                              @Param("avatar") String avatar,
                              @Param("modifiedUser") String modifiedUser,
                              @Param("modifiedTime") Date modifiedTime);
}
