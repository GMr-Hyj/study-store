package com.sp.service.impl;

import com.sp.entity.Address;
import com.sp.mapper.AddressMapper;
import com.sp.service.AddressService;
import com.sp.service.DistrictService;
import com.sp.service.ex.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/** 收货地址的实现类 */
@Service
public class AddressServiceImpl implements AddressService {

    @Autowired
    private AddressMapper addressMapper;
    // 在添加用户的收货地址的业务层依赖于DistrictService的业务层接口
    @Autowired
    private DistrictService districtService;

    @Value("${user.address.max-count}")
    private Integer maxCount;

    @Override
    public void addNewAddress(Integer uid, String username, Address address) {
        // 调用收获地址统计的方法
        Integer count = addressMapper.countByUid(uid);
        if (count >= maxCount){
            throw new AddressCountLimitException("用户收货地址超出上限");
        }

        // 对address对象中的数据进行补全：省市区
        String provinceName = districtService.getNameByCode(address.getProvinceCode());
        String cityName = districtService.getNameByCode(address.getCityCode());
        String areaName = districtService.getNameByCode(address.getAreaCode());

        address.setProvinceName(provinceName);
        address.setCityName(cityName);
        address.setAreaName(areaName);

        // uid、isDefault
        address.setUid(uid);
        Integer isDefault = count == 0 ? 1:0;
        address.setIsDefault(isDefault);
        // 补全4项日志
        address.setCreatedUser(username);
        address.setModifiedUser(username);
        address.setCreatedTime(new Date());
        address.setModifiedTime(new Date());
        // 插入收获地址的方法
        Integer rows = addressMapper.insert(address);
        if (rows != 1){
            throw new InsertException("新增用户收获地址时产生未知的异常");
        }
    }

    @Override
    public List<Address> getByUid(Integer uid) {
        List<Address> list = addressMapper.findByUid(uid);
        for (Address address : list) {
            // address.setAid(null);
            // address.setUid(null);
            address.setProvinceCode(null);
            address.setCityCode(null);
            address.setAreaCode(null);
            address.setTel(null);
            address.setIsDefault(null);
            address.setCreatedTime(null);
            address.setModifiedTime(null);
            address.setCreatedUser(null);
            address.setModifiedUser(null);
        }
        return list;
    }

    @Override
    public void setDefault(Integer aid, Integer uid, String username) {
        Address result = addressMapper.findByAid(aid);
        if (result == null){
            throw new AddressNotFoundException("收货地址不存在");
        }
        // 检测当前获取到的收货地址数据的归属
        if (!result.getUid().equals(uid)){
            throw new AccessDeniedException("非法数据访问");
        }
        // 先将所有的收货地址设置非默认
        Integer rows = addressMapper.updateNonDefault(uid);
        if (rows<1){
            throw new UpdateException("更新数据产生未知的异常");
        }
        // 将用户选中的某条地址设置为默认收货地址
        rows = addressMapper.updateDefaultByAid(aid, username, new Date());
        if (rows != 1){
            throw new UpdateException("更新数据产生未知的异常");
        }
    }

    @Override
    public void delete(Integer aid, Integer uid, String username) {
        Integer rows;
        Address result = addressMapper.findByAid(aid);
        if (result == null){
            throw new AddressNotFoundException("收获数据不存在");
        }
        if (! result.getUid().equals(uid)){
            throw new AccessDeniedException("非法数据访问");
        }
        if (result.getIsDefault() == 1){
            rows = addressMapper.deleteByAid(aid);
            if (rows != 1){
                throw new DeleteException("删除数据时产生未知的异常");
            }
            Integer count = addressMapper.countByUid(uid);
            if (count == 0){
                //直接终止程序
                return;
            }
            Address address = addressMapper.findLastModified(uid);
            rows = addressMapper.updateNonDefault(uid);
            if (rows < 1){
                throw new UpdateException("更新数据时产生未知的异常");
            }
            rows = addressMapper.updateDefaultByAid(address.getAid(), username, new Date());
            if (rows != 1){
                throw new UpdateException("更新数据时产生未知的异常");
            }
        }else {
            rows = addressMapper.deleteByAid(aid);
            if (rows != 1){
                throw new DeleteException("删除数据时产生未知的异常");
            }
        }
    }
}
