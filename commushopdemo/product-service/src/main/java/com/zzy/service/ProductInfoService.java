package com.zzy.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zzy.entity.ProductInfo;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zzy.vo.ProductExcelVO;
import com.zzy.vo.SellerProductInfoVO;
import com.zzy.vo.SellerProductInfoVO2;
import com.zzy.vo.SellerProductInfoVO3;

import java.math.BigDecimal;
import java.util.List;

/**
 * <p>
 * 商品表 服务类
 * </p>
 *
 * @author zzy
 * @since 2023-08-05
 */
public interface ProductInfoService extends IService<ProductInfo> {
    public BigDecimal findPriceById(Integer id);
    public Boolean subStockById(Integer id, Integer quantity);
    public Boolean addStock(Integer id, Integer quantity);
    public SellerProductInfoVO2 sellerProductInfoVO(Integer page, Integer size);
    public SellerProductInfoVO2 sellerProductInfoVOLike(String keyWord, Integer page, Integer size);
    public SellerProductInfoVO2 sellerProductInfoVOLikeByCategoryType(Integer categoryType, Integer page, Integer size);
    public SellerProductInfoVO3 sellerProductInfoVOById(Integer id);
    public List<ProductExcelVO> excelVOList();
}
