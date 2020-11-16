package edu.xpu.hcp.controller;

import edu.xpu.hcp.common.JSONResult;
import edu.xpu.hcp.common.PagedGridResult;
import edu.xpu.hcp.pojo.Items;
import edu.xpu.hcp.pojo.ItemsImg;
import edu.xpu.hcp.pojo.ItemsParam;
import edu.xpu.hcp.pojo.ItemsSpec;
import edu.xpu.hcp.service.ItemService;
import edu.xpu.hcp.vo.CommentLevelCountsVO;
import edu.xpu.hcp.vo.ItemInfoVo;
import edu.xpu.hcp.vo.NewItemVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
public class ItemsController extends BaseController {

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

    @ApiOperation(value = "查询商品评价数量",notes = "查询商品评价数量",httpMethod = "GET")
    @GetMapping("/commentLevel")
    public JSONResult getCommentLevel(@ApiParam(name="itemId",value = "商品ID",required = true)
                                     @RequestParam("itemId")String itemId){
        if(StringUtils.isBlank(itemId)){
            return JSONResult.errorMsg("商品不存在");
        }
        CommentLevelCountsVO countsVO = itemService.queryCommentsCounts(itemId);
        return JSONResult.ok(countsVO);
    }

    @ApiOperation(value = "查询商品评价",notes = "查询商品评价",httpMethod = "GET")
    @GetMapping("/comments")
    public JSONResult getComments(@ApiParam(name="itemId",value = "商品ID",required = true)
                                      @RequestParam("itemId")String itemId,
                                  @ApiParam(name="level",value = "评论等级",required = true)
                                  @RequestParam("level")Integer level,
                                  @ApiParam(name="page",value = "页码",required = false)
                                      @RequestParam("page")Integer page,
                                  @ApiParam(name="pageSize",value = "页大小",required = false)
                                      @RequestParam("pageSize")Integer pageSize){
        if(StringUtils.isBlank(itemId)){
            return JSONResult.errorMsg("商品不存在");
        }
        if(page == null){
            page = PAGE_START;
        }
        if(pageSize == null){
            pageSize = PAGE_SIZE;
        }
        PagedGridResult pagedGridResult = itemService.queryItemsComments(itemId, level, page, pageSize);
        return JSONResult.ok(pagedGridResult);
    }

    @ApiOperation(value = "根据关键字查询商品",notes = "根据关键字查询商品",httpMethod = "GET")
    @GetMapping("/search")
    public JSONResult searchItems(@ApiParam(name="keywords",value = "关键字",required = true)
                                  @RequestParam("keywords")String keywords,
                                  @ApiParam(name="sort",value = "排序规则",required = true)
                                  @RequestParam("sort")String sort,
                                  @ApiParam(name="page",value = "页码",required = false)
                                  @RequestParam("page")Integer page,
                                  @ApiParam(name="pageSize",value = "页大小",required = false)
                                  @RequestParam("pageSize")Integer pageSize){
        if(StringUtils.isBlank(keywords)){
            return JSONResult.errorMsg(null);
        }
        if(page == null){
            page = PAGE_START;
        }
        if(pageSize == null){
            pageSize = PAGE_SIZE;
        }
        PagedGridResult result = itemService.searchItems(keywords, sort, page, pageSize);
        return JSONResult.ok(result);
    }
}
