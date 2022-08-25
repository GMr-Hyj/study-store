package com.sp.service;

import com.sp.entity.User;

/**
 * 用户模块业务层接口
 */
public interface UserService {
    /**
     *用户注册功能
     * @param user 用户的数据对象
     */
    void reg(User user);

    /**
     * 用户登录功能
     * @param username 用户名
     * @param password 用户密码
     * @return 当前匹配的用户数量，如果没有返回null
     */
    User login(String username,String password);

    /**
     *
     * @param uid 被修改的用户uid
     * @param username 密码修改者的用户名
     * @param oldPassword 用户的旧密码
     * @param newPassword 用户的新密码
     */
    void changePassword(Integer uid,
                        String username,
                        String oldPassword,
                        String newPassword);

    /**
     * 根据用户的id查询用户的数据
     * @param uid 用uid
     * @return 用户的数据
     */
    User getByUid(Integer uid);

    /**
     * 更新用户的数据操作
     * @param uid 用户的uid
     * @param username 用户的名称
     * @param user 用户对象的数据
     */
    void changeInfo(Integer uid,String username,User user);

    /**
     * 修改用户的头像
     * @param uid 用户uid
     * @param avatar 用户头像的路径
     * @param username 修改用户头像的执行者
     */
    void changeAvatar(Integer uid,String avatar,String username);
}
