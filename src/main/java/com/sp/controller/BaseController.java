package com.sp.controller;

import com.sp.controller.ex.*;
import com.sp.service.ex.*;
import com.sp.util.JsonResult;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpSession;

/**
 * 控制层类的基类
 */
public class BaseController {
    /** 操作成功的状态码 */
    public static final int OK =200;

    // 请求处理的方法，这个方法的返回值就是需要传递给前端的数据
    // 自动将异常对象传递此方法的列表上
    // 当前项目中产生了异常，被统一拦截到此方法中，这个方法此时就充当的是请求处理方法，方法的返回值直接给到前端

    /**
     * 一旦产生异常，则会自动跳转到被@ExceptionHandler所修饰的方法
     * 作为方法的参数进行传递过来，所以可以这个方法中来判断异常的类型，来做不同的先处理
     * @param e 异常对象
     * @return 返回的json格式数据
     */
    @ExceptionHandler({ServiceException.class,FileUploadException.class}) // 用于统一处理抛出的异常
    public JsonResult<Void> handleException(Throwable e){
        JsonResult<Void> result = new JsonResult<>(e);
        if (e instanceof UsernameDuplicatedException){
            result.setState(4000);
            result.setMessage("用户名已经被占用");
        }else  if (e instanceof UserNotFoundException){
            result.setState(4001);
            result.setMessage("用户不存在的异常");
        }else  if (e instanceof PasswordNotMatchException){
            result.setState(4002);
            result.setMessage("用户密码错误的异常");
        }else if (e instanceof AddressCountLimitException){
            result.setState(4003);
            result.setMessage("用户的收获地址超出上限的异常");
        }else if (e instanceof AddressNotFoundException){
            result.setState(4004);
            result.setMessage("用户的收货地址数据不存在的异常");
        }else if (e instanceof AccessDeniedException){
            result.setState(4005);
            result.setMessage("收货地址数据非法访问的异常");
        }else if (e instanceof ProductNotFoundException){
            result.setState(4006);
            result.setMessage("商品数据不存在的异常");
        }else if (e instanceof CartNotFoundException){
            result.setState(4007);
            result.setMessage("购物车数据不存在的异常");
        }else  if (e instanceof InsertException){
            result.setState(5000);
            result.setMessage("注册时产生未知的异常");
        }else if (e instanceof UpdateException){
            result.setState(5001);
            result.setMessage("更新数据时产生未知的异常");
        }else if (e instanceof DeleteException){
            result.setState(5002);
            result.setMessage("删除数据时产生未知的异常");
        }else if (e instanceof FileEmptyException){
            result.setState(6000);
            result.setMessage("上传文件为空的异常");
        }else if (e instanceof FileSizeException){
            result.setState(6001);
            result.setMessage("上传文件大小的异常");
        }else if (e instanceof FileTypeException){
            result.setState(6002);
            result.setMessage("上传文件类型的异常");
        }else if (e instanceof FileStateException){
            result.setState(6003);
            result.setMessage("上传文件状态的异常");
        }else if (e instanceof FileUploadIOException){
            result.setState(6004);
            result.setMessage("上传文件读写的异常");
        }
        return result;
    }

    /**
     * 获取session对象中的uid
     * @param session session对象
     * @return 当前登录的用户uid
     */
    protected final Integer getuidFromSession(HttpSession session){
        return Integer.valueOf(session.getAttribute("uid")
                .toString());
    }

    /**
     * 获取当前登录用户的username
     * @param session session对象
     * @return 当前登录用户的用户名
     *
     * 在实现类中重写父类中的toString()，不是句柄信息输出
     */
    protected final String getUsernameFromSession(HttpSession session){
        return session.getAttribute("username").toString();
    }
}
