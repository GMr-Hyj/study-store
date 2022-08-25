package com.sp.service.impl;

import com.sp.entity.Cart;
import com.sp.entity.Product;
import com.sp.mapper.CartMapper;
import com.sp.mapper.ProductMapper;
import com.sp.service.CartService;
import com.sp.service.ex.AccessDeniedException;
import com.sp.service.ex.CartNotFoundException;
import com.sp.service.ex.InsertException;
import com.sp.service.ex.UpdateException;
import com.sp.vo.CartVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

@Service
public class CartServiceImpl implements CartService {

    /** 购物车的业务层依赖于购物车的持久层以及商品的持久层 */
    @Autowired
    private CartMapper cartMapper;
    @Autowired
    private ProductMapper productMapper;

    @Override
    public void addToCart(Integer uid, Integer pid,
                          Integer amount, String username) {
        // 查询当前要添加的这个购物车是否在表中存在
        Cart result = cartMapper.findByUidAndPid(uid, pid);
        Date date = new Date();
        if (result == null){ // 表示这个商品从来没有被添加到购物车中
            // 创建一个cart对象
            Cart cart = new Cart();
            // 补全数据：参数传递的数据
            cart.setUid(uid);
            cart.setPid(pid);
            cart.setNum(amount);
            // 价格：来自于商品中的数据
            Product product = productMapper.findById(pid);
            cart.setPrice(product.getPrice());
            // 补全4个日志
            cart.setCreatedUser(username);
            cart.setCreatedTime(date);
            cart.setModifiedUser(username);
            cart.setModifiedTime(date);

            // 执行输入的插入操作
            Integer rows = cartMapper.insert(cart);
            if (rows != 1){
                throw new InsertException("插入数据时产生未知的异常");
            }
        }else { // 表示当前的商品在购物车中已存在，则更新这条数据的num值
            Integer num = result.getNum() + amount;
            Integer rows = cartMapper.updateNumByCid(result.getCid(), num, username, date);
            if (rows != 1){
                throw new UpdateException("更新数据时产生未知的异常");
            }
        }
    }

    @Override
    public List<CartVO> getVOByUid(Integer uid) {
        return cartMapper.findVOByUid(uid);
    }

    @Override
    public Integer addNum(Integer cid, Integer uid, String username) {
        Cart result = cartMapper.findByCid(cid);
        if (result == null){
            throw new CartNotFoundException("数据不存在");
        }
        if (!result.getUid().equals(uid)){
            throw new AccessDeniedException("数据非法访问");
        }
        Integer num = result.getNum() + 1;
        Integer rows = cartMapper.updateNumByCid(cid, num, username, new Date());
        if (rows != 1){
            throw new UpdateException("更新数据失败");
        }
        return num;
    }

    @Override
    public List<CartVO> getVOByCid(Integer uid, Integer[] cids) {
        List<CartVO> result = cartMapper.findVOByCid(cids);
        Iterator<CartVO> iterator = result.iterator();
        while (iterator.hasNext()){
            CartVO cartVO = iterator.next();
            if (!cartVO.getUid().equals(uid)){ // 表示当前的数据不属于当前的用户
                result.remove(cartVO);
            }
        }
        return null;
    }
}
