package edu.xpu.hcp.service;

import edu.xpu.hcp.bo.UserAddressBO;
import edu.xpu.hcp.pojo.UserAddress;

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
 * @date 2020/11/17 10:56
 * @version V1.0.1
 * @Description 用户地址Service
 */
public interface UserAddressService {

    /**
     * 根据用户ID查询其所有地址
     * @param userId 用户ID
     * @return List
     */
    List<UserAddress> queryAll(String userId);

    /**
     * 新增地址
     * @param userAddressBO 地址BO
     */
    void addNewUserAddress(UserAddressBO userAddressBO);

    /**
     * 更新地址
     * @param userAddressBO 地址BO
     */
    void updateUserAddress(UserAddressBO userAddressBO);

    /**
     * 根据用户ID和地址ID删除地址信息
     * @param userId 用户ID
     * @param addressId 地址ID
     */
    void deleteUserAddress(String userId,String addressId);

    /**
     * 根据用户ID和地址ID设置默认地址
     * @param userId 用户ID
     * @param addressId 地址ID
     */
    void setDefaultAddress(String userId,String addressId);

    /**
     * 根据用户ID和地址ID，查询用户具体地址对象信息
     * @param userId 用户ID
     * @param addressId 地址ID
     * @return UserAddress
     */
    UserAddress queryUserAddress(String userId,String addressId);
}
