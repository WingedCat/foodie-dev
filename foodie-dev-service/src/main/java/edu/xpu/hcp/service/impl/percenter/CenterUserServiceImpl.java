package edu.xpu.hcp.service.impl.percenter;

import edu.xpu.hcp.bo.percenter.CenterUserBO;
import edu.xpu.hcp.mapper.UserMapper;
import edu.xpu.hcp.pojo.User;
import edu.xpu.hcp.service.percenter.CenterUserService;
import org.n3r.idworker.Sid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
public class CenterUserServiceImpl implements CenterUserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private Sid sid;

    @Transactional(propagation = Propagation.SUPPORTS, rollbackFor = Exception.class)
    @Override
    public User queryUserInfo(String userId) {
        User user = userMapper.selectByPrimaryKey(userId);
        user.setPassword(null);
        return user;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public User updateUserInfo(String userId, CenterUserBO centerUserBO) {

        User updateUser = new User();
        BeanUtils.copyProperties(centerUserBO, updateUser);
        updateUser.setId(userId);
        updateUser.setUpdatedTime(new Date());

        userMapper.updateByPrimaryKeySelective(updateUser);

        return queryUserInfo(userId);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public User updateUserFace(String userId, String faceUrl) {
        User updateUser = new User();
        updateUser.setId(userId);
        updateUser.setFace(faceUrl);
        updateUser.setUpdatedTime(new Date());

        userMapper.updateByPrimaryKeySelective(updateUser);
        return queryUserInfo(userId);
    }
}
