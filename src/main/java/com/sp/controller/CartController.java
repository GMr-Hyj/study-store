package com.sp.controller;

import com.sp.service.CartService;
import com.sp.util.JsonResult;
import com.sp.vo.CartVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequestMapping("carts")
public class CartController extends BaseController {

    @Autowired
    private CartService cartService;

    @RequestMapping("add_to_cart")
    public JsonResult<Void> addToCart(Integer pid,
                                      Integer amount,
                                      HttpSession session){
        cartService.addToCart(
                getuidFromSession(session),
                pid,
                amount,
                getUsernameFromSession(session));
        return new JsonResult<Void>(OK);
    }

    @GetMapping({"","/"})
    public JsonResult<List<CartVO>> getVOByUid(HttpSession session){
        List<CartVO> data = cartService.getVOByUid(getuidFromSession(session));
        return new JsonResult<List<CartVO>>(OK,data);
    }

    @GetMapping("{cid}/num/add")
    public JsonResult<Integer> addNum(@PathVariable("cid") Integer cid, HttpSession session){
        Integer num = cartService.addNum(
                            cid,
                            getuidFromSession(session),
                            getUsernameFromSession(session));
        return new JsonResult<>(OK,num);
    }

    @RequestMapping("list")
    public JsonResult<List<CartVO>> getVOByCid(Integer[] cids,HttpSession session){
        List<CartVO> data = cartService.getVOByCid(getuidFromSession(session),cids);
        return new JsonResult<>(OK,data);
    }
}
