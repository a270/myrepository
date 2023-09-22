package com.zzy.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zzy.entity.OrderMaster;
import com.zzy.form.BuyerOrderForm;
import com.zzy.service.OrderMasterService;
import com.zzy.util.ResultVOUtil;
import com.zzy.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 订单详情表 前端控制器
 * </p>
 *
 * @author zzy
 * @since 2023-08-13
 */
@RestController
@RequestMapping("/buyer/order")
public class BuyerOrderController {

    @Autowired
    private OrderMasterService orderMasterService;

    @PostMapping("/create")
    public ResultVO create(/*接收json数据*/@RequestBody BuyerOrderForm buyerOrderForm){
        String orderId = this.orderMasterService.create(buyerOrderForm);
//        ResultVO resultVO = new ResultVO();
//        resultVO.setCode(0);
//        resultVO.setMsg("成功");
//        Map<String,String> map = new HashMap<>();
//        map.put("orderId",orderId);
//        resultVO.setData(map);
//        return resultVO;
        Map<String,String> map = new HashMap<>();
        map.put("orderId",orderId);
        return ResultVOUtil.success(map);//代码极致复用
    }

    @GetMapping("/list/{buyerId}/{page}/{size}")
    public ResultVO list(
            @PathVariable("buyerId") Integer buyerId,
            @PathVariable("page") Integer page,
            @PathVariable("size") Integer size
    ){
        //mybatis-plus分页查询（需要添加一个分页配置类）
        Page<OrderMaster> orderMasterPage = new Page<>(page, size);
        QueryWrapper<OrderMaster> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("buyer_openid", buyerId);
        Page<OrderMaster> resultPage = this.orderMasterService.page(orderMasterPage, queryWrapper);
        List<OrderMaster> records = resultPage.getRecords();
        return ResultVOUtil.success(records);
    }

    @GetMapping("/detail/{buyerId}/{orderId}")
    public ResultVO detail(
            @PathVariable("buyerId") Integer buyerId,
            @PathVariable("orderId") String orderId
    ){
        return ResultVOUtil.success(this.orderMasterService.detail(buyerId, orderId));
    }

    @PutMapping("/cancel/{buyerId}/{orderId}")
    public ResultVO cancel(
            @PathVariable("buyerId") Integer buyerId,
            @PathVariable("orderId") String orderId
    ){
        this.orderMasterService.cancel(buyerId, orderId);
        return ResultVOUtil.success(null);
    }

    @PutMapping("/finish/{orderId}")
    public ResultVO cancel(@PathVariable("orderId") String orderId){
        this.orderMasterService.finish(orderId);
        return ResultVOUtil.success(null);
    }

    @PutMapping("/pay/{buyerId}/{orderId}")
    public ResultVO pay(
            @PathVariable("buyerId") Integer buyerId,
            @PathVariable("orderId") String orderId
    ){
        this.orderMasterService.pay(buyerId, orderId);
        return ResultVOUtil.success(null);
    }
}

