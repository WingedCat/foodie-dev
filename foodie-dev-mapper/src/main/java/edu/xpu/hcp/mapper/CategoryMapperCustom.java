package edu.xpu.hcp.mapper;

import edu.xpu.hcp.my.mapper.MyMapper;
import edu.xpu.hcp.pojo.Category;
import edu.xpu.hcp.vo.CategoryVO;
import edu.xpu.hcp.vo.NewItemVO;
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
 * @date 2020/11/15 21:18
 * @version V1.0.1
 * @description 自定义Mapper
 */
@Component
public interface CategoryMapperCustom {

    /**
     * 根据一级分类ID获取子分类
     * @param rootCatId
     * @return
     */
    List<CategoryVO> getSubCatList(Integer rootCatId);

    /**
     * 查询首页一级分类下的最新六条商品
     * @param map 参数
     * @return List
     */
    List<NewItemVO> getSixNewItemsLazy(@Param("paramsMap") Map<String,Object> map);
}