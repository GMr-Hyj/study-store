package com.sp.service.impl;

import com.sp.entity.District;
import com.sp.mapper.DistrictMapper;
import com.sp.service.DistrictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DistrictServiceImpl implements DistrictService {

    @Autowired
    private DistrictMapper districtMapper;

    @Override
    public List<District> getByParent(String parent) {
        List<District> list = districtMapper.findByParent(parent);
        /**
         * 在进行网络数据传输时，为了尽量避免无效数据的传递，可以将无效数据设置为null
         * 可以节省流量，另一方面提升了效率
         */
        for (District d : list){
            d.setId(null);
            d.setParent(null);
        }
        return list;
    }

    @Override
    public String getNameByCode(String code) {
        return districtMapper.findNameByCode(code);
    }
}
