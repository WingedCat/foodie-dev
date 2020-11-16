package edu.xpu.hcp.service;

import edu.xpu.hcp.pojo.Category;
import edu.xpu.hcp.vo.CategoryVO;
import edu.xpu.hcp.vo.NewItemVO;

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
 * @date 2020/11/15 21:15
 * @version V1.0.1
 * @Description 分类Service
 */
public interface CategoryService {

    /**
     * 查询所有一级分类
     * @return List
     */
    List<Category> queryAllRootLevelCat();

    /**
     * 根据一级分类ID查询子分类信息
     * @param rootCatId 一级分类ID
     * @return List
     */
    List<CategoryVO> getSubCatList(Integer rootCatId);

    /**
     * 查询首页一级分类下的最新六条商品
     * @param rootCatId 一级分类ID
     * @return List
     */
    List<NewItemVO> getSixNewItemsLazy(Integer rootCatId);

}
