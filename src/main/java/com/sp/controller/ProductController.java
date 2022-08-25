package com.sp.controller;

import com.sp.entity.Product;
import com.sp.service.ProductService;
import com.sp.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("products")
public class ProductController extends BaseController {

    @Autowired
    private ProductService productService;

    @RequestMapping("hot_list")
    public JsonResult<List<Product>> getHotList(){
        List<Product> data = productService.findHotList();
        return new JsonResult<List<Product>>(OK,data);
    }

    @GetMapping("{id}/details")
    public JsonResult<Product> getProductById(@PathVariable("id") Integer id){
        Product data = productService.findById(id);
        return new JsonResult<Product>(OK,data);
    }
}
