package com.sp.mapper;

import com.sp.entity.Address;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * 收获地址持久层的接口
 */
@Mapper
public interface AddressMapper {

    /**
     * 插入用户的收获地址
     * @param address 收获地址
     * @return 受影响的行数
     */
    Integer insert(Address address);

    /**
     * 根据用户的id统计收获地址数量
     * @param uid 用户的id
     * @return 当前用户收获地址总数
     */
    Integer countByUid(@Param("uid") Integer uid);

    /**
     * 根据用户的id查询用户的收货地址数据
     * @param uid 用户id
     * @return 收货地址数据
     */
    List<Address> findByUid(@Param("uid") Integer uid);

    /**
     * 根据aid查询收货地址数据
     * @param aid 收货地址id
     * @return 收货地址数据，如果没有找到则返回null
     */
    Address findByAid(@Param("aid") Integer aid);

    /**
     * 根据用户的uid值来修改用户的收货地址设置为非默认
     * @param uid 用户的id值
     * @return 受影响行数
     */
    Integer updateNonDefault(@Param("uid") Integer uid);

    /**
     * 根据用户的aid值来修改用户的收货地址设置为默认
     * @param aid 用户收货地址id
     * @return 受影响行数
     */
    Integer updateDefaultByAid(@Param("aid") Integer aid,
                               @Param("modifiedUser") String modifiedUser,
                               @Param("modifiedTime") Date modifiedTime);

    /**
     * 根据收货地址id删除收货地址
     * @param aid 收货地址id
     * @return 受影响行数
     */
    Integer deleteByAid(@Param("aid") Integer aid);

    /**
     * 根据修改时间倒序排序，获取第一条数据
     * @return 收货地址数据
     */
    Address findLastModified(@Param("uid") Integer uid);
}
