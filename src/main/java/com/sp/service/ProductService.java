package com.sp.service;

import com.sp.entity.Product;

import java.util.List;

public interface ProductService {
    List<Product> findHotList();

    Product findById(Integer id);
}
