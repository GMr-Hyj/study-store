package com.sp.mapper;

import com.sp.entity.Product;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ProductMapper {
    List<Product> findHotList();

    Product findById(@Param("id") Integer id);
}
