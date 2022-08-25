package com.sp.mapper;

import com.sp.entity.Cart;
import com.sp.vo.CartVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

@Mapper
public interface CartMapper {
    /**
     * 插入购物车数据
     * @param cart 购物车数据
     * @return 受影响行数
     */
    Integer insert(Cart cart);

    /**
     * 更新购物车某件商品的数量
     * @param cid 购物车数据id
     * @param num 更新的数量
     * @param modifiedUser 修改者
     * @param modifiedTime 修改时间
     * @return 受影响的行数
     */
    Integer updateNumByCid(@Param("cid") Integer cid,
                           @Param("num") Integer num,
                           @Param("modifiedUser") String modifiedUser,
                           @Param("modifiedTime") Date modifiedTime);

    /**
     * 更具用户的id喝商品的id来查询购物车中的数据
     * @param uid 用户id
     * @param pid 商品id
     * @return
     */
    Cart findByUidAndPid(@Param("uid") Integer uid,
                         @Param("pid") Integer pid);

    List<CartVO> findVOByUid(@Param("uid") Integer uid);

    Cart findByCid(@Param("cid") Integer cid);

    List<CartVO> findVOByCid(@Param("cid") Integer[] cid);
}
