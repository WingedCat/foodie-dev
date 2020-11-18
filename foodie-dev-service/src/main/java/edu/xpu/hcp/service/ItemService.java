package edu.xpu.hcp.service;

import edu.xpu.hcp.common.PagedGridResult;
import edu.xpu.hcp.pojo.*;
import edu.xpu.hcp.vo.CommentLevelCountsVO;
import edu.xpu.hcp.vo.ShopcartVO;
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
 * @date 2020/11/16 10:51
 * @version V1.0.1
 * @Description 查询商品服务
 */
public interface ItemService {

    /**
     * 根据商品ID查询商品信息
     * @param itemId 商品ID
     * @return Items
     */
    Items queryItemById(String itemId);

    /**
     * 根据商品ID查询图片列表
     * @param itemId 商品ID
     * @return List
     */
    List<ItemsImg> queryItemImgList(String itemId);

    /**
     * 根据商品ID查询商品规格
     * @param itemId 商品ID
     * @return List
     */
    List<ItemsSpec> queryItemSpecList(String itemId);

    /**
     * 根据商品ID查询商品参数
     * @param itemId 商品ID
     * @return List
     */
    ItemsParam queryItemParamList(String itemId);

    /**
     * 根据商品ID查询商品的评价等级数量
     * @param itemId 商品ID
     * @return CommentLevelCountsVO
     */
    CommentLevelCountsVO queryCommentsCounts(String itemId);

    /**
     * 根据商品ID和评价等级查询商品评价
     * @param itemId 商品ID
     * @param commentLevel 评价等级
     * @param page 页码
     * @param pageSize 大小
     * @return List
     */
    PagedGridResult queryItemsComments(String itemId, Integer commentLevel, Integer page, Integer pageSize);

    /**
     * 搜索商品列表
     * @param keywords 关键字
     * @param sort 排序方式
     * @param page 页码
     * @param pageSize 页大小
     * @return PageGridResult
     */
    PagedGridResult searchItemsByKeyWords(String keywords,String sort,Integer page,Integer pageSize);

    /**
     * 根据商品类别ID搜索商品
     * @param catId 商品类别ID
     * @param sort 排序方式
     * @param page 页码
     * @param pageSize 页大小
     * @return PageGridResult
     */
    PagedGridResult searchItemsByCatId(String catId,String sort,Integer page,Integer pageSize);


    /**
     * 根据商品ID列表查询商品信息
     * @param spedIdList 商品VO
     * @return List
     */
    List<ShopcartVO> queryItemsBySpecIds(List<String> spedIdList);

    /**
     * 根据商品规格ID获取对象具体信息
     * @param specId 规格ID
     * @return ItemsSpec
     */
    ItemsSpec queryItemSpecById(String specId);

    /**
     * 根据商品ID获取主图图片
     * @param itemId 商品ID
     * @return String
     */
    String queryItemMainImgById(String itemId);

    /**
     * 扣除库存
     * @param specId 规格ID
     * @param buyCounts 用户购买数量
     */
    void decreaseItemSpecStock(String specId,Integer buyCounts);
}
