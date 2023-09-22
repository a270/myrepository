package com.zzy.controller;


import com.zzy.entity.ProductInfo;
import com.zzy.service.ProductCategoryService;
import com.zzy.service.ProductInfoService;
import com.zzy.util.ResultVOUtil;
import com.zzy.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

/**
 * <p>
 * 类目表 前端控制器
 * </p>
 *
 * @author zzy
 * @since 2023-08-05
 */
@RestController
@RequestMapping("/buyer/product")
public class BuyerProductController {

    @Autowired
    private ProductCategoryService productCategoryService;

    @Autowired
    private ProductInfoService productInfoService;

    @GetMapping("/list")
    public ResultVO list(){
//        ResultVO resultVO = new ResultVO();
//        resultVO.setCode(0);
//        resultVO.setMsg("成功");
//        resultVO.setData(this.productCategoryService.buyerList());
//        return resultVO;
        //代码复用，统一进行了封装
        return ResultVOUtil.success(this.productCategoryService.buyerList());
    }

    @GetMapping("/findPriceById/{id}")
    public BigDecimal findPriceById(@PathVariable("id") Integer id){
        return this.productInfoService.findPriceById(id);
    }

    @GetMapping("/findById/{id}")
    public ProductInfo findById(@PathVariable("id") Integer id){
        return this.productInfoService.getById(id);
    }

    @PutMapping("/subStockById/{id}/{quantity}")
    public Boolean subStockById(
            @PathVariable("id") Integer id,
            @PathVariable("quantity") Integer quantity
    ){
        return this.productInfoService.subStockById(id, quantity);
    }

    @PutMapping("/addStockById/{id}/{quantity}")
    public Boolean addStockById(
            @PathVariable("id") Integer id,
            @PathVariable("quantity") Integer quantity
    ){
        this.productInfoService.addStock(id, quantity);
        return true;
    }
}


