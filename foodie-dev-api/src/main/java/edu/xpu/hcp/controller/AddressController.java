package edu.xpu.hcp.controller;

import edu.xpu.hcp.bo.UserAddressBO;
import edu.xpu.hcp.common.JSONResult;
import edu.xpu.hcp.pojo.UserAddress;
import edu.xpu.hcp.service.UserAddressService;
import edu.xpu.hcp.utils.MobileEmailUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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
 * @date 2020/11/17 10:41
 * @version V1.0.1
 * @Description 地址
 */
@Api(value = "地址相关接口",tags = {"地址相关接口"})
@RestController
@RequestMapping("address")
public class AddressController {


    @Autowired
    private UserAddressService userAddressService;

    @ApiOperation(value = "根据用户ID查询收货地址",notes = "根据用户ID查询收货地址",httpMethod = "POST")
    @PostMapping("/list")
    public JSONResult getAddressByuserId(@RequestParam String userId){
        if(StringUtils.isBlank(userId)){
            return JSONResult.errorMsg("");
        }
        List<UserAddress> userAddresses = userAddressService.queryAll(userId);
        return JSONResult.ok(userAddresses);
    }

    @ApiOperation(value = "根据用户ID添加收货地址",notes = "根据用户ID添加收货地址",httpMethod = "POST")
    @PostMapping("/add")
    public JSONResult addAddressByuserId(@RequestBody UserAddressBO userAddressBO){
        JSONResult checkResult = checkAddress(userAddressBO);
        if(checkResult.getStatus() != 200){
            return checkResult;
        }
        userAddressService.addNewUserAddress(userAddressBO);
        return JSONResult.ok();
    }

    @ApiOperation(value = "更新收货地址",notes = "更新收货地址",httpMethod = "POST")
    @PostMapping("/update")
    public JSONResult updateAddressByuserId(@RequestBody UserAddressBO userAddressBO){
        JSONResult checkResult = checkAddress(userAddressBO);
        if(checkResult.getStatus() != 200){
            return checkResult;
        }
        userAddressService.updateUserAddress(userAddressBO);
        return JSONResult.ok();
    }

    @ApiOperation(value = "删除收货地址",notes = "删除收货地址",httpMethod = "POST")
    @PostMapping("/delete")
    public JSONResult updateAddressByuserId(@RequestParam String userId,@RequestParam String addressId){
        if(StringUtils.isBlank(userId) || StringUtils.isBlank(addressId)){
            return JSONResult.errorMsg("");
        }
        userAddressService.deleteUserAddress(userId,addressId);
        return JSONResult.ok();
    }

    @ApiOperation(value = "设置默认地址",notes = "设置默认地址",httpMethod = "POST")
    @PostMapping("/setDefault")
    public JSONResult setDefaultAddr(@RequestParam String userId,@RequestParam String addressId){
        if(StringUtils.isBlank(userId) || StringUtils.isBlank(addressId)){
            return JSONResult.errorMsg("");
        }
        userAddressService.setDefaultAddress(userId,addressId);
        return JSONResult.ok();
    }

    private JSONResult checkAddress(UserAddressBO userAddressBO){
        String receiver = userAddressBO.getReceiver();
        if(StringUtils.isBlank(receiver)){
            return JSONResult.errorMsg("收货人不能为空");
        }
        if(receiver.length() > 12){
            return JSONResult.errorMsg("收货人姓名太长了");
        }
        String mobile = userAddressBO.getMobile();
        if(StringUtils.isBlank(mobile)){
            return JSONResult.errorMsg("手机号不能为空");
        }
        if(mobile.length() != 11){
            return JSONResult.errorMsg("手机号长度不正确");
        }
        boolean isMobileOK = MobileEmailUtils.checkMobileIsOk(mobile);
        if(!isMobileOK){
            return JSONResult.errorMsg("收货人手机号格式不正确");
        }
        String province = userAddressBO.getProvince();
        String city = userAddressBO.getCity();
        String district = userAddressBO.getDistrict();
        String detail = userAddressBO.getDetail();
        if(StringUtils.isBlank(province) ||
            StringUtils.isBlank(city) ||
            StringUtils.isBlank(district) ||
            StringUtils.isBlank(detail)){
            return JSONResult.errorMsg("收货地址不能为空");
        }
        return JSONResult.ok();
    }


}
