package com.zzy.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zzy.entity.ProductCategory;
import com.zzy.entity.ProductInfo;
import com.zzy.mapper.ProductCategoryMapper;
import com.zzy.mapper.ProductInfoMapper;
import com.zzy.service.ProductCategoryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zzy.vo.BuyerProductCategoryVO;
import com.zzy.vo.BuyerProductInfoVO;
import com.zzy.vo.SellerProductCategoryVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 类目表 服务实现类
 * </p>
 *
 * @author zzy
 * @since 2023-08-05
 */
@Service
public class ProductCategoryServiceImpl extends ServiceImpl<ProductCategoryMapper, ProductCategory> implements ProductCategoryService {

    @Autowired
    private ProductCategoryMapper productCategoryMapper;

    @Autowired
    private ProductInfoMapper productInfoMapper;

    @Override
    public List<BuyerProductCategoryVO> buyerList() {
        List<ProductCategory> productCategoryList = this.productCategoryMapper.selectList(null);
        List<BuyerProductCategoryVO> result = new ArrayList<>();
        for (ProductCategory productCategory : productCategoryList) {
            BuyerProductCategoryVO vo = new BuyerProductCategoryVO();
            vo.setName(productCategory.getCategoryName());
            vo.setType(productCategory.getCategoryType());
            QueryWrapper<ProductInfo> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("category_type",productCategory.getCategoryType());
            List<ProductInfo> productInfoList = this.productInfoMapper.selectList(queryWrapper);
            List<BuyerProductInfoVO> productInfoVOS = new ArrayList<>();
            for (ProductInfo productInfo : productInfoList) {
                BuyerProductInfoVO buyerProductInfoVO1 = new BuyerProductInfoVO();
                BeanUtils.copyProperties(productInfo,buyerProductInfoVO1);
                productInfoVOS.add(buyerProductInfoVO1);
            }
            vo.setGoods(productInfoVOS);
            result.add(vo);
        }
        return result;
    }

    @Override
    public List<SellerProductCategoryVO> sellerList() {
        List<ProductCategory> productCategoryList = this.productCategoryMapper.selectList(null);
        List<SellerProductCategoryVO> sellerProductCategoryVOList = new ArrayList<>();
        for (ProductCategory productCategory : productCategoryList) {
            SellerProductCategoryVO vo = new SellerProductCategoryVO();
            vo.setName(productCategory.getCategoryName());
            vo.setType(productCategory.getCategoryType());
            sellerProductCategoryVOList.add(vo);
        }
        return sellerProductCategoryVOList;
    }
}
