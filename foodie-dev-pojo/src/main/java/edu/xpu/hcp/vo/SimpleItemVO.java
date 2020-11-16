package edu.xpu.hcp.vo;

import lombok.Data;

import java.util.Date;

/**                                                                                ____________________
      _                _                                                           < 神兽护体，永无bug! >
    | |__  _   _  ___| |__   ___ _ __   __ _ _ __   ___ _ __   __ _                --------------------
   | '_ \| | | |/ __| '_ \ / _ \ '_ \ / _` | '_ \ / _ \ '_ \ / _` |                       \   ^__^
  | | | | |_| | (__| | | |  __/ | | | (_| | |_) |  __/ | | | (_| |                        \  (oo)\_______
 |_| |_|\__,_|\___|_| |_|\___|_| |_|\__, | .__/ \___|_| |_|\__, |                           (__)\       )\/\
                                   |___/|_|                |___/                                ||----w |
                                                                                                ||     ||
 * @author huchengpeng
 * @date 2020/11/16 10:10
 * @version V1.0.1
 * @Description 6个商品的简单数据类型
 */
@Data
public class SimpleItemVO {

    private String itemId;

    private String itemName;

    private String itemUrl;

    private Date createdTime;

}
