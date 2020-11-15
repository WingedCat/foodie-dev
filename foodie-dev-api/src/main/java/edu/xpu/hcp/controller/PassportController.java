package edu.xpu.hcp.controller;

import edu.xpu.hcp.bo.UserBO;
import edu.xpu.hcp.common.JSONResult;
import edu.xpu.hcp.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**                                                                                ____________________
      _                _                                                           < 神兽护体，永无bug! >
    | |__  _   _  ___| |__   ___ _ __   __ _ _ __   ___ _ __   __ _                --------------------
   | '_ \| | | |/ __| '_ \ / _ \ '_ \ / _` | '_ \ / _ \ '_ \ / _` |                       \   ^__^
  | | | | |_| | (__| | | |  __/ | | | (_| | |_) |  __/ | | | (_| |                        \  (oo)\_______
 |_| |_|\__,_|\___|_| |_|\___|_| |_|\__, | .__/ \___|_| |_|\__, |                           (__)\       )\/\
                                   |___/|_|                |___/                                ||----w |
                                                                                                ||     ||
 * @author: huchengpeng
 * @date: 2020/11/15 14:11
 * @description: 门户控制器
 */
@Api(value="注册/登录",tags = {"用于注册登录的相关接口"})
@RestController
@RequestMapping("passport")
public class PassportController {

    @Autowired
    private UserService userService;

    @ApiOperation(value = "用户名是否存在",notes = "用户名是否存在",httpMethod = "GET")
    @GetMapping("/usernameIsExist")
    public JSONResult usernameIsExist(@RequestParam("username") String username){
        //1、判断用户名不能为空
        if(StringUtils.isBlank(username)){
            return JSONResult.errorMsg("用户名不能为空");
        }
        //2、查找注册的用户名是否存在
        boolean isExist = userService.queryUsernameIsExist(username);
        if( isExist ){
            return JSONResult.errorMsg("用户名已存在");
        }
        //3、请求成功，用户名没有重复
        return JSONResult.ok();
    }

    @ApiOperation(value = "用户注册",notes = "用户注册",httpMethod = "POST")
    @PostMapping("/regist")
    public JSONResult regist(@RequestBody UserBO userBO){
        String username = userBO.getUsername();
        String password = userBO.getPassword();
        String confirmPwd = userBO.getConfirmPassword();

        //1、判断用户名和密码不为空
        if (StringUtils.isBlank(username) ||
            StringUtils.isBlank(password) ||
            StringUtils.isBlank(confirmPwd)){
            return JSONResult.errorMsg("用户名或密码不能为空");
        }
        //2、查询用户名是否存在
        boolean isExist = userService.queryUsernameIsExist(username);
        if (isExist){
            return JSONResult.errorMsg("用户名已存在");
        }
        //3、密码长度不能少于6位
        if (password.length() < 6){
            return JSONResult.errorMsg("密码长度不能小于6");
        }
        //4、判断两次密码是否一致
        if(!password.equals(confirmPwd)){
            return JSONResult.errorMsg("两次密码输入不一致");
        }
        //5、实现注册
        userService.createUser(userBO);
        return JSONResult.ok();
    }






}