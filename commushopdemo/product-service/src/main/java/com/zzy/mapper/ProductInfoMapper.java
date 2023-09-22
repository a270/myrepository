package com.zzy.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zzy.entity.ProductInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zzy.vo.SellerProductInfoVO;
import org.apache.ibatis.annotations.Mapper;

import java.math.BigDecimal;

/**
 * <p>
 * 商品表 Mapper 接口
 * </p>
 *
 * @author zzy
 * @since 2023-08-05
 */

public interface ProductInfoMapper extends BaseMapper<ProductInfo> {
    public BigDecimal findPriceById(Integer id);
    public Integer findStockById(Integer id);
    public int updateStock(Integer id, Integer stock);
    public Boolean addStock(Integer id, Integer quantity);
    public Page<SellerProductInfoVO> sellerProductInfoVO(Integer page, Integer size);
}
