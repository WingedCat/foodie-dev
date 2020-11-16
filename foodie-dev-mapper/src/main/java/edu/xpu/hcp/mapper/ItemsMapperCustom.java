package edu.xpu.hcp.mapper;

import edu.xpu.hcp.my.mapper.MyMapper;
import edu.xpu.hcp.pojo.Items;
import edu.xpu.hcp.vo.ItemCommentVO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**                                                                                ____________________
      _                _                                                           < 神兽护体，永无bug! >
    | |__  _   _  ___| |__   ___ _ __   __ _ _ __   ___ _ __   __ _                --------------------
   | '_ \| | | |/ __| '_ \ / _ \ '_ \ / _` | '_ \ / _ \ '_ \ / _` |                       \   ^__^
  | | | | |_| | (__| | | |  __/ | | | (_| | |_) |  __/ | | | (_| |                        \  (oo)\_______
 |_| |_|\__,_|\___|_| |_|\___|_| |_|\__, | .__/ \___|_| |_|\__, |                           (__)\       )\/\
                                   |___/|_|                |___/                                ||----w |
                                                                                                ||     ||
 * @author huchengpeng
 * @date 2020/11/16 16:01
 * @version V1.0.1
 * @Description 自定义ItemsMapper
 */
@Component
public interface ItemsMapperCustom {

    /**
     * 查询商品评论
     * @param map 查询参数
     * @return List
     */
    List<ItemCommentVO> queryItemsComments(@Param("paramsMap") Map<String,Object> map);

}