package edu.xpu.hcp.service;

import edu.xpu.hcp.bo.UserBO;
import edu.xpu.hcp.pojo.User;

/**                                                                                ____________________
      _                _                                                           < 神兽护体，永无bug! >
    | |__  _   _  ___| |__   ___ _ __   __ _ _ __   ___ _ __   __ _                --------------------
   | '_ \| | | |/ __| '_ \ / _ \ '_ \ / _` | '_ \ / _ \ '_ \ / _` |                       \   ^__^
  | | | | |_| | (__| | | |  __/ | | | (_| | |_) |  __/ | | | (_| |                        \  (oo)\_______
 |_| |_|\__,_|\___|_| |_|\___|_| |_|\__, | .__/ \___|_| |_|\__, |                           (__)\       )\/\
                                   |___/|_|                |___/                                ||----w |
                                                                                                ||     ||
 * @author: huchengpeng
 * @date: 2020/11/15 14:03
 * @description: 用户服务service
 */
public interface UserService {

    /**
     * 查询是否存在此用户名
     * @param username 待查询的用户名
     * @return boolean true-存在此用户名，false-不存在此用户名
     */
    boolean queryUsernameIsExist(String username);

    /**
     * 注册时创建用户
     * @param userBO 用户对象BO
     * @return User
     */
    User createUser(UserBO userBO);

    /**
     * 登录用户时的查询
     * @param username 用户名
     * @param password 密码
     * @return User
     */
    User queryUserForLogin(String username,String password);

}
