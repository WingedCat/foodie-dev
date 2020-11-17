package edu.xpu.hcp.service.impl;

import edu.xpu.hcp.bo.UserAddressBO;
import edu.xpu.hcp.enums.YesOrNo;
import edu.xpu.hcp.mapper.UserAddressMapper;
import edu.xpu.hcp.pojo.UserAddress;
import edu.xpu.hcp.service.UserAddressService;
import org.n3r.idworker.Sid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**                                                                                ____________________
      _                _                                                           < 神兽护体，永无bug! >
    | |__  _   _  ___| |__   ___ _ __   __ _ _ __   ___ _ __   __ _                --------------------
   | '_ \| | | |/ __| '_ \ / _ \ '_ \ / _` | '_ \ / _ \ '_ \ / _` |                       \   ^__^
  | | | | |_| | (__| | | |  __/ | | | (_| | |_) |  __/ | | | (_| |                        \  (oo)\_______
 |_| |_|\__,_|\___|_| |_|\___|_| |_|\__, | .__/ \___|_| |_|\__, |                           (__)\       )\/\
                                   |___/|_|                |___/                                ||----w |
                                                                                                ||     ||
 * @author huchengpeng
 * @date 2020/11/17 10:58
 * @version V1.0.1
 */
@Service
public class UserAddressServiceImpl implements UserAddressService {

    @Autowired
    private UserAddressMapper userAddressMapper;

    @Autowired
    private Sid sid;

    @Transactional(propagation = Propagation.SUPPORTS,rollbackFor = Exception.class)
    @Override
    public List<UserAddress> queryAll(String userId) {
        UserAddress ua = new UserAddress();
        ua.setUserId(userId);
        return userAddressMapper.select(ua);
    }

    @Transactional(propagation = Propagation.SUPPORTS,rollbackFor = Exception.class)
    @Override
    public void addNewUserAddress(UserAddressBO userAddressBO) {
        Integer isDefault = 0;
        //1、判断当前用户是否存在地址，如果没有，则新增为“默认地址”
        List<UserAddress> uaList = this.queryAll(userAddressBO.getUserId());
        if(uaList == null || uaList.isEmpty() || uaList.size() == 0){
            isDefault = 1;
        }
        //2、保存地址到数据库
        String addressId = sid.nextShort();
        UserAddress newAddress = new UserAddress();
        BeanUtils.copyProperties(userAddressBO,newAddress);
        newAddress.setId(addressId);
        newAddress.setIsDefault(isDefault);
        newAddress.setCreatedTime(new Date());
        newAddress.setUpdatedTime(new Date());
        userAddressMapper.insert(newAddress);
    }

    @Transactional(propagation = Propagation.SUPPORTS,rollbackFor = Exception.class)
    @Override
    public void updateUserAddress(UserAddressBO userAddressBO) {
        String addressId = userAddressBO.getAddressId();
        UserAddress userAddress = new UserAddress();
        BeanUtils.copyProperties(userAddressBO,userAddress);
        userAddress.setId(addressId);
        userAddress.setUpdatedTime(new Date());
        userAddressMapper.updateByPrimaryKeySelective(userAddress);
    }

    @Transactional(propagation = Propagation.SUPPORTS,rollbackFor = Exception.class)
    @Override
    public void deleteUserAddress(String userId, String addressId) {
        UserAddress userAddress = new UserAddress();
        userAddress.setId(addressId);
        userAddress.setUserId(userId);
        userAddressMapper.delete(userAddress);
    }

    @Transactional(propagation = Propagation.SUPPORTS,rollbackFor = Exception.class)
    @Override
    public void setDefaultAddress(String userId, String addressId) {
        //1、查找默认地址，设置为不默认
        UserAddress userAddress = new UserAddress();
        userAddress.setUserId(userId);
        userAddress.setIsDefault(YesOrNo.YES.type);
        List<UserAddress> defaultAddr = userAddressMapper.select(userAddress);
        defaultAddr.forEach((item)->{
            item.setIsDefault(YesOrNo.NO.type);
            userAddressMapper.updateByPrimaryKeySelective(item);
        });
        //2、根据地址ID修改为默认地址
        UserAddress newDefaultAddr = new UserAddress();
        newDefaultAddr.setId(addressId);
        newDefaultAddr.setUserId(userId);
        newDefaultAddr.setIsDefault(YesOrNo.YES.type);
        userAddressMapper.updateByPrimaryKeySelective(newDefaultAddr);
    }

    @Transactional(propagation = Propagation.SUPPORTS,rollbackFor = Exception.class)
    @Override
    public UserAddress queryUserAddress(String userId, String addressId) {
        UserAddress userAddress = new UserAddress();
        userAddress.setUserId(userId);
        userAddress.setId(addressId);
        return userAddressMapper.selectOne(userAddress);
    }
}
