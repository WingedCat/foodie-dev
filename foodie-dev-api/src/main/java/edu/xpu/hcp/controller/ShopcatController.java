package edu.xpu.hcp.controller;

import edu.xpu.hcp.bo.ShopcartBO;
import edu.xpu.hcp.common.JSONResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
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
 * @date 2020/11/16 21:16
 * @version V1.0.1
 * @Description 购物车相关
 */
@Api(value="购物车相关接口",tags = {"购物车相关接口"})
@RestController
@RequestMapping("shopcart")
public class ShopcatController {

    @ApiOperation(value = "加入购物车",notes = "加入购物车",httpMethod = "POST")
    @PostMapping("/add")
    public JSONResult add(@RequestParam("userId")String userId, @RequestBody ShopcartBO shopcartBO,
                          HttpServletRequest request, HttpServletResponse response){
        if(StringUtils.isBlank(userId)){
            return JSONResult.errorMsg("");
        }
        System.out.println(shopcartBO);
        //TODO 前端用户在登录的情况下，添加商品到购物车，会同时在后端同步购物车到Redis
        return JSONResult.ok();
    }

    @ApiOperation(value = "删除购物车商品",notes = "删除购物车商品",httpMethod = "POST")
    @PostMapping("/del")
    public JSONResult del(@RequestParam("userId")String userId, @RequestParam("itemSpecId") String itemSpecId,
                          HttpServletRequest request, HttpServletResponse response){
        if(StringUtils.isBlank(userId)){
            return JSONResult.errorMsg("");
        }
        String[] ids = itemSpecId.split(",");
        List<String> specIds = Arrays.asList(ids);
        //TODO 用户在页面删除购物车中的商品数据，如果此时用户已经登录，则需要同步Redis中的数据
        return JSONResult.ok();
    }
}
