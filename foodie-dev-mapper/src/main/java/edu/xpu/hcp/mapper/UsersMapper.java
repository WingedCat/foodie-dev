package edu.xpu.hcp.mapper;

import edu.xpu.hcp.my.mapper.MyMapper;
import edu.xpu.hcp.pojo.Users;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

@Component
public interface UsersMapper extends MyMapper<Users> {
}