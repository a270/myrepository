package com.zzy.controller;


import com.alibaba.excel.EasyExcel;
import com.zzy.entity.ProductInfo;
import com.zzy.form.SellerProductInfoForm;
import com.zzy.form.SellerProductInfoUpdateForm;
import com.zzy.handler.CustomCellWriteHandler;
import com.zzy.service.ProductCategoryService;
import com.zzy.service.ProductInfoService;
import com.zzy.util.ResultVOUtil;
import com.zzy.vo.ProductExcelVO;
import com.zzy.vo.ResultVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 商品表 前端控制器
 * </p>
 *
 * @author zzy
 * @since 2023-08-05
 */
@RestController
@RequestMapping("/seller/product")
public class SellerProductController {

    @Autowired
    private ProductCategoryService productCategoryService;
    @Autowired
    private ProductInfoService productInfoService;

    @GetMapping("/findAllProductCategory")
    public ResultVO findAllProductCategory(){
        Map<String, List> map = new HashMap<>();
        map.put("content", this.productCategoryService.sellerList());
        return ResultVOUtil.success(map);
    }

    @PostMapping("/add")
    public ResultVO add(@RequestBody SellerProductInfoForm sellerProductInfoForm){
        ProductInfo productInfo = new ProductInfo();
        BeanUtils.copyProperties(sellerProductInfoForm, productInfo);
        this.productInfoService.save(productInfo);
        return ResultVOUtil.success(null);
    }

    @GetMapping("/list/{page}/{size}")
    public ResultVO list(
            @PathVariable("page") Integer page,
            @PathVariable("size") Integer size
    ){
        return ResultVOUtil.success(this.productInfoService.sellerProductInfoVO(page, size));
    }

    @GetMapping("/like/{keyWord}/{page}/{size}")
    public ResultVO like(
            @PathVariable("keyWord") String keyWord,
            @PathVariable("page") Integer page,
            @PathVariable("size") Integer size
    ){
        return ResultVOUtil.success(this.productInfoService.sellerProductInfoVOLike(keyWord, page, size));
    }

    @GetMapping("/findByCategory/{categoryType}/{page}/{size}")
    public ResultVO findByCategory(
            @PathVariable("categoryType") Integer categoryType,
            @PathVariable("page") Integer page,
            @PathVariable("size") Integer size
    ){
        return ResultVOUtil.success(this.productInfoService.sellerProductInfoVOLikeByCategoryType(categoryType, page, size));
    }

    @GetMapping("/findById/{id}")
    public ResultVO findById(@PathVariable("id") Integer id){
        return ResultVOUtil.success(this.productInfoService.sellerProductInfoVOById(id));
    }

    @DeleteMapping("/delete/{id}")
    public ResultVO delete(@PathVariable("id") Integer id){
        this.productInfoService.removeById(id);
        return ResultVOUtil.success(null);
    }

    @PutMapping("/updateStatus/{id}/{status}")
    public ResultVO updateStatus(
            @PathVariable("id") Integer id,
            @PathVariable("status") Boolean status
    ){
//        ProductInfo productInfo = this.productInfoService.getById(id);
//        if (status) {
//            productInfo.setProductStatus(1);
//        } else {
//            productInfo.setProductStatus(0);
//        }
        //先查所有字段，改后再全部赋值回去，效率低
        ProductInfo productInfo = new ProductInfo();
        productInfo.setProductId(id);
        if (status) {
            productInfo.setProductStatus(1);
        } else {
            productInfo.setProductStatus(0);
        }
        this.productInfoService.updateById(productInfo);
        return ResultVOUtil.success(null);
        //现在这样的好处，就是set的值变少了，现在除了id和status之外，值都为null。可以从控制台的sql日志中查看
    }

    @PutMapping("/update")
    public ResultVO update(@RequestBody SellerProductInfoUpdateForm form){
        ProductInfo productInfo = new ProductInfo();
        BeanUtils.copyProperties(form, productInfo);
        if(form.getStatus()){
            productInfo.setProductStatus(1);
        }else{
            productInfo.setProductStatus(0);
        }
        productInfo.setCategoryType(form.getCategory().getCategoryType());
        this.productInfoService.updateById(productInfo);
        return ResultVOUtil.success(null);
    }

    @GetMapping("/export")
    public void exportData(HttpServletResponse response){
        try {
            response.setContentType("application/vnd.ms-excel");
            response.setCharacterEncoding("UTF-8");
            String fileName = URLEncoder.encode("商品信息", "UTF-8");
            response.setHeader("Content-disposition", "attachment;filename=" + fileName + ".xlsx");
            //获取ProductExcelVO类型的List
            List<ProductExcelVO> productExcelVOList = this.productInfoService.excelVOList();
            EasyExcel.write(response.getOutputStream(), ProductExcelVO.class)
                    .registerWriteHandler(new CustomCellWriteHandler())
                    .sheet("商品信息")
                    .doWrite(productExcelVOList);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

//    @PostMapping("/import")
//    public ResultVO importData(@RequestParam("file") MultipartFile file){
//        List<ProductInfo> productInfoList = null;
//        try {
//            productInfoList = this.productInfoService.excleToProductInfoList(file.getInputStream());
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        if(productInfoList == null){
//            return ResultVOUtil.fail("导入Excel失败！");
//        }
//        boolean result = this.productInfoService.saveBatch(productInfoList);
//        if(result) return ResultVOUtil.success(null);
//        return ResultVOUtil.fail("导入Excel失败！");
//    }

}

