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
}
