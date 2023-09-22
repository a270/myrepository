package com.zzy.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zzy.entity.OrderMaster;
import com.zzy.service.OrderDetailService;
import com.zzy.service.OrderMasterService;
import com.zzy.util.ResultVOUtil;
import com.zzy.vo.ResultVO;
import com.zzy.vo.SellerOrderMasterVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 订单表 前端控制器
 * </p>
 *
 * @author zzy
 * @since 2023-08-13
 */
@RestController
@RequestMapping("/seller/order")
public class SellerOrderController {
    @Autowired
    private OrderMasterService orderMasterService;
    @Autowired
    private OrderDetailService orderDetailService;

    @GetMapping("/list/{page}/{size}")
    public ResultVO list(
            @PathVariable("page") Integer page,
            @PathVariable("size") Integer size
    ){
        Page<OrderMaster> orderMasterPage = new Page<>(page, size);
        Page<OrderMaster> resultPage = this.orderMasterService.page(orderMasterPage);
        SellerOrderMasterVO vo = new SellerOrderMasterVO();
        vo.setTotal(resultPage.getTotal());
        vo.setSize(resultPage.getSize());
        vo.setContent(resultPage.getRecords());
        return ResultVOUtil.success(vo);
    }

    @PutMapping("/cancel/{orderId}")
    public ResultVO cancel(@PathVariable("orderId") String orderId){
        this.orderMasterService.cancel(null, orderId);
        return ResultVOUtil.success(null);
    }

    @PutMapping("/finish/{orderId}")
    public ResultVO finish(
            @PathVariable("orderId") String orderId
    ){
        this.orderMasterService.finish(orderId);
        return ResultVOUtil.success(null);
    }

    @GetMapping("/barSale")
    public ResultVO barSale(){
        return ResultVOUtil.success(this.orderDetailService.barData());
    }

    @GetMapping("/basicLineSale")
    public ResultVO basicLineSale(){
        return ResultVOUtil.success(this.orderDetailService.basicLineData());
    }

    @GetMapping("/stackedLineSale")
    public ResultVO stackedLineSale(){
        return ResultVOUtil.success(this.orderDetailService.stackedLineData());
    }
}

