package edu.xpu.hcp.mapper;

import edu.xpu.hcp.my.mapper.MyMapper;
import edu.xpu.hcp.pojo.User;
import org.springframework.stereotype.Component;

@Component
public interface UserMapper extends MyMapper<User> {
}