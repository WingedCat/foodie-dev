package edu.xpu.hcp.controller;

import edu.xpu.hcp.common.JSONResult;
import edu.xpu.hcp.pojo.Items;
import edu.xpu.hcp.pojo.ItemsImg;
import edu.xpu.hcp.pojo.ItemsParam;
import edu.xpu.hcp.pojo.ItemsSpec;
import edu.xpu.hcp.service.ItemService;
import edu.xpu.hcp.vo.ItemInfoVo;
import edu.xpu.hcp.vo.NewItemVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
 * @date 2020/11/16 11:11
 * @version V1.0.1
 * @Description 商品控制器
 */
@Api(value = "商品接口",tags = {"商品信息展示的相关接口"})
@RestController
@RequestMapping("items")
public class ItemsController {

    @Autowired
    private ItemService itemService;

    @ApiOperation(value = "查询商品详情",notes = "查询商品详情",httpMethod = "GET")
    @GetMapping("/info/{itemId}")
    public JSONResult getSixItemLazy(@ApiParam(name="itemId",value = "商品ID",required = true)
                                     @PathVariable("itemId")String itemId){
        if(StringUtils.isBlank(itemId)){
            return JSONResult.errorMsg("商品不存在");
        }
        Items item = itemService.queryItemById(itemId);
        List<ItemsImg> itemImgs = itemService.queryItemImgList(itemId);
        List<ItemsSpec> itemSpecs = itemService.queryItemSpecList(itemId);
        ItemsParam itemsParam = itemService.queryItemParamList(itemId);
        ItemInfoVo itemInfoVo = new ItemInfoVo();
        itemInfoVo.setItem(item);
        itemInfoVo.setItemImgList(itemImgs);
        itemInfoVo.setItemSpecList(itemSpecs);
        itemInfoVo.setItemParams(itemsParam);
        return JSONResult.ok(itemInfoVo);
    }
}
