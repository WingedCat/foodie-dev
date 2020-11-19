package edu.xpu.hcp.mapper;

import edu.xpu.hcp.my.mapper.MyMapper;
import edu.xpu.hcp.pojo.ItemsComments;
import edu.xpu.hcp.vo.MyCommentVO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public interface ItemsCommentsMapperCustom extends MyMapper<ItemsComments> {

    public void saveComments(Map<String, Object> map);

    public List<MyCommentVO> queryMyComments(@Param("paramsMap") Map<String, Object> map);

}