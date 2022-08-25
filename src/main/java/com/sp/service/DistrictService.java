package com.sp.service;

import com.sp.entity.District;

import java.util.List;

public interface DistrictService {

    /**
     * 根据父代号来查询区域信息（省市区）
     * @param parent 父代号
     * @return 多个地区信息
     */
    List<District> getByParent(String parent);

    String getNameByCode(String code);
}
