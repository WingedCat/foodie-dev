package edu.xpu.hcp.service;

import edu.xpu.hcp.pojo.Items;
import edu.xpu.hcp.pojo.ItemsImg;
import edu.xpu.hcp.pojo.ItemsParam;
import edu.xpu.hcp.pojo.ItemsSpec;

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
}
