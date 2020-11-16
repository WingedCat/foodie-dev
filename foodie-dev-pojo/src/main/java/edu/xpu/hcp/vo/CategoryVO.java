package edu.xpu.hcp.vo;

import lombok.Data;

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
 * @date 2020/11/15 21:41
 * @version V1.0.1
 * @Description 二级分类VO
 */
@Data
public class CategoryVO {

    private Integer id;

    private String name;

    private Integer type;

    private Integer fatherId;

    /**
     * 三级分类VO列表
     */
    private List<SubCategoryVO> subCatList;
}
