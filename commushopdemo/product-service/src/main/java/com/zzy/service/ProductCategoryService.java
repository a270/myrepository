package com.zzy.service;

import com.zzy.entity.ProductCategory;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zzy.vo.BuyerProductCategoryVO;
import com.zzy.vo.SellerProductCategoryVO;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 类目表 服务类
 * </p>
 *
 * @author zzy
 * @since 2023-08-05
 */
public interface ProductCategoryService extends IService<ProductCategory> {
    public List<BuyerProductCategoryVO> buyerList();
    public List<SellerProductCategoryVO> sellerList();
}
