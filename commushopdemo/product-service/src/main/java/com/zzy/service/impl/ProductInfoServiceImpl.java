package com.zzy.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.Query;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zzy.entity.ProductCategory;
import com.zzy.entity.ProductInfo;
import com.zzy.exception.ShopException;
import com.zzy.mapper.ProductCategoryMapper;
import com.zzy.mapper.ProductInfoMapper;
import com.zzy.result.ResponseEnum;
import com.zzy.service.ProductInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zzy.vo.ProductExcelVO;
import com.zzy.vo.SellerProductInfoVO;
import com.zzy.vo.SellerProductInfoVO2;
import com.zzy.vo.SellerProductInfoVO3;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.ReentrantLock;

/**
 * <p>
 * 商品表 服务实现类
 * </p>
 *
 * @author zzy
 * @since 2023-08-05
 */
@Service
public class ProductInfoServiceImpl extends ServiceImpl<ProductInfoMapper, ProductInfo> implements ProductInfoService {

    @Autowired
    private ProductInfoMapper productInfoMapper;

    //private ReentrantLock reentrantLock;

    @Autowired
    private ProductCategoryMapper productCategoryMapper;

    @Override
    public BigDecimal findPriceById(Integer id) {
        return this.productInfoMapper.findPriceById(id);
    }

    @Override
    public synchronized Boolean subStockById(Integer id, Integer quantity) {
        //判断库存是否够，能否满足需求
        Integer stock = this.productInfoMapper.findStockById(id);
        if(stock < quantity){
            //抛异常，自定义一个异常
            throw new ShopException(ResponseEnum.STOCK_ERROR.getMsg());
            //不应该自己写异常信息，应该从企业预设的异常信息中选择
        }
        //reentrantLock.lock();
        Integer result = stock - quantity;
        this.productInfoMapper.updateStock(id,result);
        //reentrantLock.unlock();

        return true;
    }

    @Override
    public Boolean addStock(Integer id, Integer quantity) {
        return this.productInfoMapper.addStock(id, quantity);
    }

    @Override
    public SellerProductInfoVO2 sellerProductInfoVO(Integer page, Integer size) {
        Page<ProductInfo> infoPage = new Page<>(page, size);
        Page<ProductInfo> productInfoPage = this.productInfoMapper.selectPage(infoPage, null);
        SellerProductInfoVO2 vo = new SellerProductInfoVO2();
        vo.setSize(productInfoPage.getSize());
        vo.setTotal(productInfoPage.getTotal());
        List<ProductInfo> records = productInfoPage.getRecords();
        List<SellerProductInfoVO> voList = new ArrayList<>();
        for (ProductInfo record : records) {
            SellerProductInfoVO vo1 = new SellerProductInfoVO();
            vo1.setId(record.getProductId());
            vo1.setPrice(record.getProductPrice());
            vo1.setStock(record.getProductStock());
            if(record.getProductStatus() == 1){
                vo1.setStatus(true);
            } else {
                vo1.setStatus(false);
            }
            vo1.setName(record.getProductName());
            vo1.setIcon(record.getProductIcon());
            vo1.setDescription(record.getProductDescription());
            QueryWrapper<ProductCategory> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("category_type", record.getCategoryType());
            ProductCategory productCategory = this.productCategoryMapper.selectOne(queryWrapper);
            vo1.setCategoryName(productCategory.getCategoryName());
            voList.add(vo1);
        }
        vo.setContent(voList);
        return vo;
    }

    @Override
    public SellerProductInfoVO2 sellerProductInfoVOLike(String keyWord, Integer page, Integer size) {
        Page<ProductInfo> infoPage = new Page<>(page, size);
        QueryWrapper<ProductInfo> queryWrapper1 = new QueryWrapper<>();
        queryWrapper1.like("product_name", keyWord);
        Page<ProductInfo> productInfoPage = this.productInfoMapper.selectPage(infoPage, queryWrapper1);
        SellerProductInfoVO2 vo = new SellerProductInfoVO2();
        vo.setSize(productInfoPage.getSize());
        vo.setTotal(productInfoPage.getTotal());
        List<ProductInfo> records = productInfoPage.getRecords();
        List<SellerProductInfoVO> voList = new ArrayList<>();
        for (ProductInfo record : records) {
            SellerProductInfoVO vo1 = new SellerProductInfoVO();
            vo1.setId(record.getProductId());
            vo1.setPrice(record.getProductPrice());
            vo1.setStock(record.getProductStock());
            if(record.getProductStatus() == 1){
                vo1.setStatus(true);
            } else {
                vo1.setStatus(false);
            }
            vo1.setName(record.getProductName());
            vo1.setIcon(record.getProductIcon());
            vo1.setDescription(record.getProductDescription());
            QueryWrapper<ProductCategory> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("category_type", record.getCategoryType());
            ProductCategory productCategory = this.productCategoryMapper.selectOne(queryWrapper);
            vo1.setCategoryName(productCategory.getCategoryName());
            voList.add(vo1);
        }
        vo.setContent(voList);
        return vo;
    }

    @Override
    public SellerProductInfoVO2 sellerProductInfoVOLikeByCategoryType(Integer categoryType, Integer page, Integer size) {
        Page<ProductInfo> infoPage = new Page<>(page, size);
        QueryWrapper<ProductInfo> queryWrapper1 = new QueryWrapper<>();
        queryWrapper1.eq("category_type", categoryType);
        Page<ProductInfo> productInfoPage = this.productInfoMapper.selectPage(infoPage, queryWrapper1);
        SellerProductInfoVO2 vo = new SellerProductInfoVO2();
        vo.setSize(productInfoPage.getSize());
        vo.setTotal(productInfoPage.getTotal());
        List<ProductInfo> records = productInfoPage.getRecords();
        List<SellerProductInfoVO> voList = new ArrayList<>();
        for (ProductInfo record : records) {
            SellerProductInfoVO vo1 = new SellerProductInfoVO();
            vo1.setId(record.getProductId());
            vo1.setPrice(record.getProductPrice());
            vo1.setStock(record.getProductStock());
            if(record.getProductStatus() == 1){
                vo1.setStatus(true);
            } else {
                vo1.setStatus(false);
            }
            vo1.setName(record.getProductName());
            vo1.setIcon(record.getProductIcon());
            vo1.setDescription(record.getProductDescription());
            QueryWrapper<ProductCategory> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("category_type", record.getCategoryType());
            ProductCategory productCategory = this.productCategoryMapper.selectOne(queryWrapper);
            vo1.setCategoryName(productCategory.getCategoryName());
            voList.add(vo1);
        }
        vo.setContent(voList);
        return vo;
    }

    @Override
    public SellerProductInfoVO3 sellerProductInfoVOById(Integer id) {
        ProductInfo productInfo = this.productInfoMapper.selectById(id);
        SellerProductInfoVO3 vo1 = new SellerProductInfoVO3();
        vo1.setId(productInfo.getProductId());
        vo1.setPrice(productInfo.getProductPrice());
        vo1.setStock(productInfo.getProductStock());
        if(productInfo.getProductStatus() == 1){
            vo1.setStatus(true);
        } else {
            vo1.setStatus(false);
        }
        vo1.setName(productInfo.getProductName());
        vo1.setIcon(productInfo.getProductIcon());
        vo1.setDescription(productInfo.getProductDescription());
        Map<String,Integer> map = new HashMap<>();
        map.put("category_type", productInfo.getCategoryType());
        vo1.setCategory(map);
        return vo1;
    }

    @Override
    public List<ProductExcelVO> excelVOList() {
        List<ProductInfo> productInfoList = this.productInfoMapper.selectList(null);
        List<ProductExcelVO> productExcelVOList = new ArrayList<>();
        for (ProductInfo productInfo : productInfoList) {
            ProductExcelVO vo = new ProductExcelVO();
            BeanUtils.copyProperties(productInfo, vo);
            if(productInfo.getProductStatus() == 1){
                vo.setProductStatus("上架");
            }else{
                vo.setProductStatus("下架");
            }
            vo.setCategoryName(this.productCategoryMapper.findNameByType(productInfo.getCategoryType()));
            productExcelVOList.add(vo);
        }
        return productExcelVOList;
    }
}
