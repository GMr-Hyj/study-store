package com.sp.mapper;

import com.sp.entity.District;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DistrictMapper {
    /**
     * 根据父代号查询地区
     * @param parent 父代号
     * @return 某个父区域下的所有区域列表
     */
    List<District> findByParent(@Param("parent") String parent);

    String findNameByCode(@Param("code") String code);
}
