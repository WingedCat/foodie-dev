package edu.xpu.hcp.service.impl;

import edu.xpu.hcp.enums.Sex;
import edu.xpu.hcp.bo.UserBO;
import edu.xpu.hcp.service.UserService;
import edu.xpu.hcp.utils.DateUtil;
import edu.xpu.hcp.utils.MD5Utils;
import edu.xpu.hcp.mapper.UserMapper;
import edu.xpu.hcp.pojo.User;
import org.n3r.idworker.Sid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;

/**                                                                                ____________________
      _                _                                                           < 神兽护体，永无bug! >
    | |__  _   _  ___| |__   ___ _ __   __ _ _ __   ___ _ __   __ _                --------------------
   | '_ \| | | |/ __| '_ \ / _ \ '_ \ / _` | '_ \ / _ \ '_ \ / _` |                       \   ^__^
  | | | | |_| | (__| | | |  __/ | | | (_| | |_) |  __/ | | | (_| |                        \  (oo)\_______
 |_| |_|\__,_|\___|_| |_|\___|_| |_|\__, | .__/ \___|_| |_|\__, |                           (__)\       )\/\
                                   |___/|_|                |___/                                ||----w |
                                                                                                ||     ||
 * @author: huchengpeng
 * @date: 2020/11/15 14:06
 * @description: 用户服务实现类
 */
@Service
public class UserServiceImpl implements UserService {

    //默认头像地址
    private static final String USER_FACE = "http://www.chengpengper.cn/wp-content/uploads/2018/11/fac.jpg";


    @Autowired
    private UserMapper userMapper;

    @Autowired
    private Sid sid;

    @Transactional(propagation = Propagation.SUPPORTS,rollbackFor = Exception.class)
    @Override
    public boolean queryUsernameIsExist(String username) {
        Example userExample = new Example(User.class);
        Example.Criteria userCriteria = userExample.createCriteria();
        userCriteria.andEqualTo("username",username);
        User user = userMapper.selectOneByExample(userExample);
        if (user == null) {
            return false;
        }
        return true;
    }

    @Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
    @Override
    public User createUser(UserBO userBO) {
        String userId = sid.nextShort();
        User user = new User();
        user.setId(userId);
        user.setUsername(userBO.getUsername());
        user.setPassword(MD5Utils.getMD5Str(userBO.getPassword()));
        //默认昵称同用户名
        user.setNickname(userBO.getUsername());
        //默认头像
        user.setFace(USER_FACE);
        user.setBirthday(DateUtil.stringToDate("1970-01-01"));
        //默认性别为保密
        user.setSex(Sex.secret.type);
        user.setCreatedTime(new Date());
        user.setUpdatedTime(new Date());

        //保存用户
        userMapper.insert(user);
        return user;
    }
}
