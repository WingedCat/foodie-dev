package edu.xpu.hcp.bo;

import lombok.Data;
import lombok.ToString;

/**                                                                                ____________________
      _                _                                                           < 神兽护体，永无bug! >
    | |__  _   _  ___| |__   ___ _ __   __ _ _ __   ___ _ __   __ _                --------------------
   | '_ \| | | |/ __| '_ \ / _ \ '_ \ / _` | '_ \ / _ \ '_ \ / _` |                       \   ^__^
  | | | | |_| | (__| | | |  __/ | | | (_| | |_) |  __/ | | | (_| |                        \  (oo)\_______
 |_| |_|\__,_|\___|_| |_|\___|_| |_|\__, | .__/ \___|_| |_|\__, |                           (__)\       )\/\
                                   |___/|_|                |___/                                ||----w |
                                                                                                ||     ||
 * @author huchengpeng
 * @date 2020/11/16 21:19
 * @version V1.0.1
 * @Description 购物车BO
 */
@Data
@ToString
public class ShopcatBO {
    private String itemImgUrl;

    private String itemId;

    private String itemName;

    private String specId;

    private String specName;

    private Integer buyCounts;

    private Integer priceDiscount;

    private Integer priceNormal;
}
