package edu.xpu.hcp.service;

import edu.xpu.hcp.pojo.Carousel;

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
 * @date 2020/11/15 20:41
 * @version v1.0.1
 * @Description 轮播图Service
 */
public interface CarouselService {
    /**
     * 根据是否显示查询所有符合的轮播图
     * @param isShow 是否展示
     * @return List 所有符合规范的轮播图
     */
    List<Carousel> queryAll(Integer isShow);
}
