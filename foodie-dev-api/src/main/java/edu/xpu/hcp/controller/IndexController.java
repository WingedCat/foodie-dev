package edu.xpu.hcp.controller;

import edu.xpu.hcp.common.JSONResult;
import edu.xpu.hcp.enums.YesOrNo;
import edu.xpu.hcp.pojo.Carousel;
import edu.xpu.hcp.pojo.Category;
import edu.xpu.hcp.service.CarouselService;
import edu.xpu.hcp.service.CategoryService;
import edu.xpu.hcp.vo.CategoryVO;
import edu.xpu.hcp.vo.NewItemVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
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
 * @date 2020/11/15 20:52
 * @version V1.0.1
 * @Description 首页控制器
 */
@Api(value = "首页",tags = {"首页展示的相关接口"})
@Slf4j
@RestController
@RequestMapping("index")
public class IndexController {

    @Autowired
    private CarouselService carouselService;

    @Autowired
    private CategoryService categoryService;

    @ApiOperation(value = "查询轮播图",notes = "查询轮播图",httpMethod = "GET")
    @GetMapping("/carousel")
    public JSONResult carousel(){
        List<Carousel> carousels = carouselService.queryAll(YesOrNo.YES.type);
        return JSONResult.ok(carousels);
    }

    @ApiOperation(value = "获取商品分类（一级分类）",notes = "获取商品分类（一级分类）",httpMethod = "GET")
    @GetMapping("/cats")
    public JSONResult cats(){
        List<Category> categories = categoryService.queryAllRootLevelCat();
        return JSONResult.ok(categories);
    }

    @ApiOperation(value = "根据父分类获取子分类",notes = "根据父分类获取子分类",httpMethod = "GET")
    @GetMapping("/subCat/{rootCatId}")
    public JSONResult subcats(
            @ApiParam(name="rootCatId",value = "一级分类ID",required = true)
            @PathVariable("rootCatId")Integer rootCatId){
        if(rootCatId == null){
            return JSONResult.errorMsg("分类不存在");
        }

        List<CategoryVO> subCatList = categoryService.getSubCatList(rootCatId);
        return JSONResult.ok(subCatList);
    }

    @ApiOperation(value = "查询每个一级分类下的最新6条商品数据",notes = "查询每个一级分类下的最新6条商品数据",httpMethod = "GET")
    @GetMapping("sixNewItems/{rootCatId}")
    public JSONResult getSixItemLazy(@ApiParam(name="rootCatId",value = "一级分类ID",required = true)
                                         @PathVariable("rootCatId")Integer rootCatId){
        if(rootCatId == null){
            return JSONResult.errorMsg("分类不存在");
        }
        List<NewItemVO> sixNewItemsLazy = categoryService.getSixNewItemsLazy(rootCatId);
        return JSONResult.ok(sixNewItemsLazy);
    }


}
