package edu.xpu.hcp.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import edu.xpu.hcp.common.PagedGridResult;
import edu.xpu.hcp.enums.CommentLevel;
import edu.xpu.hcp.enums.YesOrNo;
import edu.xpu.hcp.mapper.*;
import edu.xpu.hcp.pojo.*;
import edu.xpu.hcp.service.ItemService;
import edu.xpu.hcp.utils.DesensitizationUtil;
import edu.xpu.hcp.vo.CommentLevelCountsVO;
import edu.xpu.hcp.vo.ItemCommentVO;
import edu.xpu.hcp.vo.SearchItemsVO;
import edu.xpu.hcp.vo.ShopcartVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**                                                                                ____________________
      _                _                                                           < 神兽护体，永无bug! >
    | |__  _   _  ___| |__   ___ _ __   __ _ _ __   ___ _ __   __ _                --------------------
   | '_ \| | | |/ __| '_ \ / _ \ '_ \ / _` | '_ \ / _ \ '_ \ / _` |                       \   ^__^
  | | | | |_| | (__| | | |  __/ | | | (_| | |_) |  __/ | | | (_| |                        \  (oo)\_______
 |_| |_|\__,_|\___|_| |_|\___|_| |_|\__, | .__/ \___|_| |_|\__, |                           (__)\       )\/\
                                   |___/|_|                |___/                                ||----w |
                                                                                                ||     ||
 * @author huchengpeng
 * @date 2020/11/16 10:55
 * @version V1.0.1
 */
@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    private ItemsMapper itemsMapper;

    @Autowired
    private ItemsImgMapper itemsImgMapper;

    @Autowired
    private ItemsSpecMapper itemsSpecMapper;

    @Autowired
    private ItemsParamMapper itemsParamMapper;

    @Autowired
    private ItemsCommentsMapper itemsCommentsMapper;

    @Autowired
    private ItemsMapperCustom itemsMapperCustom;

    @Transactional(propagation = Propagation.SUPPORTS,rollbackFor = Exception.class)
    @Override
    public Items queryItemById(String itemId) {
        return itemsMapper.selectByPrimaryKey(itemId);
    }

    @Transactional(propagation = Propagation.SUPPORTS,rollbackFor = Exception.class)
    @Override
    public List<ItemsImg> queryItemImgList(String itemId) {
        Example example = new Example(ItemsImg.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("itemId",itemId);
        return itemsImgMapper.selectByExample(example);
    }

    @Transactional(propagation = Propagation.SUPPORTS,rollbackFor = Exception.class)
    @Override
    public List<ItemsSpec> queryItemSpecList(String itemId) {
        Example example = new Example(ItemsSpec.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("itemId",itemId);
        return itemsSpecMapper.selectByExample(example);
    }

    @Transactional(propagation = Propagation.SUPPORTS,rollbackFor = Exception.class)
    @Override
    public ItemsParam queryItemParamList(String itemId) {
        Example example = new Example(ItemsParam.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("itemId",itemId);
        return itemsParamMapper.selectOneByExample(example);
    }

    @Transactional(propagation = Propagation.SUPPORTS,rollbackFor = Exception.class)
    @Override
    public CommentLevelCountsVO queryCommentsCounts(String itemId) {
        Integer goodCounts = getCommentCounts(itemId, CommentLevel.GOOD.type);
        Integer normalCounts = getCommentCounts(itemId, CommentLevel.NORMAL.type);
        Integer badCounts = getCommentCounts(itemId, CommentLevel.BAD.type);
        Integer totalCounts = goodCounts+normalCounts+badCounts;
        CommentLevelCountsVO countsVO = new CommentLevelCountsVO();
        countsVO.setTotalCounts(totalCounts);
        countsVO.setGoodCounts(goodCounts);
        countsVO.setNormalCounts(normalCounts);
        countsVO.setBadCounts(badCounts);
        return countsVO;
    }

    @Transactional(propagation = Propagation.SUPPORTS,rollbackFor = Exception.class)
    Integer getCommentCounts(String itemId,Integer level){
        ItemsComments condition = new ItemsComments();
        condition.setItemId(itemId);
        if(level != null){
            condition.setCommentLevel(level);
        }
        return itemsCommentsMapper.selectCount(condition);
    }

    @Transactional(propagation = Propagation.SUPPORTS,rollbackFor = Exception.class)
    @Override
    public PagedGridResult queryItemsComments(String itemId, Integer commentLevel,
                                                  Integer page,Integer pageSize) {
        Map<String,Object> map = new HashMap<>();
        map.put("itemId",itemId);
        map.put("level",commentLevel);
        //分页
        PageHelper.startPage(page,pageSize);
        List<ItemCommentVO> itemCommentVOS = itemsMapperCustom.queryItemsComments(map);
        itemCommentVOS.forEach((item)-> item.setNickname(DesensitizationUtil.commonDisplay(item.getNickname())));
        PageInfo<ItemCommentVO> pageList = new PageInfo<>(itemCommentVOS);
        PagedGridResult grid = setPagedGrid(itemCommentVOS,page);
        return grid;
    }

    private PagedGridResult setPagedGrid(List<?> list,Integer page){
        PageInfo<?> pageList = new PageInfo<>(list);
        PagedGridResult grid = new PagedGridResult();
        grid.setPage(page);
        grid.setRows(list);
        grid.setTotal(pageList.getPages());
        grid.setRecords(pageList.getTotal());
        return grid;
    }

    @Transactional(propagation = Propagation.SUPPORTS,rollbackFor = Exception.class)
    @Override
    public PagedGridResult searchItemsByKeyWords(String keywords, String sort, Integer page, Integer pageSize) {
        Map<String,Object> map = new HashMap<>();
        map.put("keywords",keywords);
        map.put("sort",sort);
        //分页
        PageHelper.startPage(page,pageSize);
        List<SearchItemsVO> searchItemsVOS = itemsMapperCustom.searchItemsByKeyWords(map);
        PagedGridResult grid = setPagedGrid(searchItemsVOS,page);
        return grid;
    }

    @Transactional(propagation = Propagation.SUPPORTS,rollbackFor = Exception.class)
    @Override
    public PagedGridResult searchItemsByCatId(String catId, String sort, Integer page, Integer pageSize) {
        Map<String,Object> map = new HashMap<>();
        map.put("catId",catId);
        map.put("sort",sort);
        //分页
        PageHelper.startPage(page,pageSize);
        List<SearchItemsVO> searchItemsVOS = itemsMapperCustom.searchItemsByCatId(map);
        PagedGridResult grid = setPagedGrid(searchItemsVOS,page);
        return grid;
    }

    @Transactional(propagation = Propagation.SUPPORTS,rollbackFor = Exception.class)
    @Override
    public List<ShopcartVO> queryItemsBySpecIds(List<String> spedIdList) {
        return itemsMapperCustom.queryItemsBySpecIds(spedIdList);
    }

    @Transactional(propagation = Propagation.SUPPORTS,rollbackFor = Exception.class)
    @Override
    public ItemsSpec queryItemSpecById(String specId) {
        return itemsSpecMapper.selectByPrimaryKey(specId);
    }

    @Transactional(propagation = Propagation.SUPPORTS,rollbackFor = Exception.class)
    @Override
    public String queryItemMainImgById(String itemId) {
        ItemsImg img = new ItemsImg();
        img.setId(itemId);
        img.setIsMain(YesOrNo.YES.type);
        ItemsImg itemsImg = itemsImgMapper.selectOne(img);
        return itemsImg != null ? itemsImg.getUrl():"";
    }

    @Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
    @Override
    public void decreaseItemSpecStock(String specId, Integer buyCounts) {
        //TODO 分布式锁减库存
        int result = itemsMapperCustom.decreaseItemSpecStock(specId, buyCounts);
        if(result != 1){
            throw new RuntimeException("库存不够了...");
        }
    }
}
