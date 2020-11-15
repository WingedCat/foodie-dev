package edu.xpu.hcp.enums;
/**                                                                                ____________________
      _                _                                                           < 神兽护体，永无bug! >
    | |__  _   _  ___| |__   ___ _ __   __ _ _ __   ___ _ __   __ _                --------------------
   | '_ \| | | |/ __| '_ \ / _ \ '_ \ / _` | '_ \ / _ \ '_ \ / _` |                       \   ^__^
  | | | | |_| | (__| | | |  __/ | | | (_| | |_) |  __/ | | | (_| |                        \  (oo)\_______
 |_| |_|\__,_|\___|_| |_|\___|_| |_|\__, | .__/ \___|_| |_|\__, |                           (__)\       )\/\
                                   |___/|_|                |___/                                ||----w |
                                                                                                ||     ||
 * @author: huchengpeng
 * @date: 2020/11/15 15:17
 * @description: 性别枚举
 */
public enum Sex {
    /**
     * 女性
     */
    woman (0,"女"),
    /**
     * 男性
     */
    man(1,"男"),
    /**
     * 保密
     */
    secret(2,"保密");

    public final Integer type;

    public final String value;

    Sex(Integer type,String value){
        this.type = type;
        this.value = value;
    }
}
