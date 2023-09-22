package com.zzy.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zzy.entity.Admin;
import com.zzy.exception.ShopException;
import com.zzy.form.AdminForm;
import com.zzy.result.ResponseEnum;
import com.zzy.service.AdminService;
import com.zzy.util.JwtUtil;
import com.zzy.util.ResultVOUtil;
import com.zzy.vo.AdminVO;
import com.zzy.vo.ResultVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author zzy
 * @since 2023-08-14
 */
@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @GetMapping("/login")
    public ResultVO login(AdminForm adminForm){
        QueryWrapper<Admin> queryWrapper = new QueryWrapper<Admin>();
        queryWrapper.eq("username", adminForm.getUsername());
        Admin one = this.adminService.getOne(queryWrapper);
        if(one == null){
            throw new ShopException(ResponseEnum.USERNAME_ERROR.getMsg());
        }
        if(!one.getPassword().equals(adminForm.getPassword())){
            throw new ShopException(ResponseEnum.PASSWORD_ERROR.getMsg());
        }
        String token = JwtUtil.createToken(one.getAdminId(), one.getUsername());
        AdminVO adminVO = new AdminVO();
        BeanUtils.copyProperties(one, adminVO);
        adminVO.setToken(token);
        return ResultVOUtil.success(adminVO);
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

