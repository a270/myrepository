package com.zzy.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zzy.entity.User;
import com.zzy.exception.ShopException;
import com.zzy.form.UserForm;
import com.zzy.result.ResponseEnum;
import com.zzy.service.UserService;
import com.zzy.util.JwtUtil;
import com.zzy.util.MD5Util;
import com.zzy.util.RegexValidateUtil;
import com.zzy.util.ResultVOUtil;
import com.zzy.vo.ResultVO;
import com.zzy.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author zzy
 * @since 2023-08-14
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private RedisTemplate redisTemplate;

    @PostMapping("/register")
    public ResultVO register(@RequestBody UserForm userForm){
        boolean b = RegexValidateUtil.checkMobile(userForm.getMobile());
        if(!b){
            throw new ShopException(ResponseEnum.MOBILE_ERROR.getMsg());
        }
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("mobile", userForm.getMobile());
        User one = this.userService.getOne(queryWrapper);
        if(one != null){
            throw new ShopException(ResponseEnum.MOBILE_EXIST.getMsg());
        }
        //验证码校验
        String code = (String) this.redisTemplate.opsForValue().get(userForm.getMobile());
        if(!code.equals(userForm.getCode())){
            throw new ShopException(ResponseEnum.SMS_CODE_ERROR.getMsg());
        }

        User user = new User();
        user.setMobile(userForm.getMobile());
        user.setPassword(MD5Util.getSaltMD5(userForm.getPassword()));
        this.userService.save(user);

        return ResultVOUtil.success(null);
    }

    @GetMapping("/login")
    public ResultVO login(UserForm userForm){
        boolean b = RegexValidateUtil.checkMobile(userForm.getMobile());
        if(!b){
            throw new ShopException(ResponseEnum.MOBILE_ERROR.getMsg());
        }
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("mobile", userForm.getMobile());
        User one = this.userService.getOne(queryWrapper);
        if(one == null){
            throw new ShopException(ResponseEnum.MOBILE_IS_NULL.getMsg());
        }
        //验证密码
        if (!MD5Util.getSaltverifyMD5(userForm.getPassword(), one.getPassword())) {
            throw new ShopException(ResponseEnum.PASSWORD_ERROR.getMsg());
        }
        //生成Token
        String token = JwtUtil.createToken(one.getUserId(), one.getMobile());
        UserVO userVO = new UserVO(one.getUserId(), one.getMobile(), one.getPassword(), token);

        return ResultVOUtil.success(userVO);
    }

    @GetMapping("/checkToken/{token}")
    public ResultVO checkToken(@PathVariable("token") String token){
        boolean b = JwtUtil.checkToken(token);
        if(!b){
            throw new ShopException(ResponseEnum.TOKEN_ERROR.getMsg());
        }
        return ResultVOUtil.success(null);
    }
}

