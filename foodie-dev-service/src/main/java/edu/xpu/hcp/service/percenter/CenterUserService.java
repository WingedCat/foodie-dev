package edu.xpu.hcp.service.percenter;

import edu.xpu.hcp.bo.percenter.CenterUserBO;
import edu.xpu.hcp.pojo.User;

public interface CenterUserService {

    /**
     * 根据用户id查询用户信息
     * @param userId
     * @return
     */
    public User queryUserInfo(String userId);

    /**
     * 修改用户信息
     * @param userId
     * @param centerUserBO
     */
    public User updateUserInfo(String userId, CenterUserBO centerUserBO);

    /**
     * 用户头像更新
     * @param userId
     * @param faceUrl
     * @return
     */
    public User updateUserFace(String userId, String faceUrl);
}
