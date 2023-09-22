package com.zzy.mapper;

import com.zzy.entity.ProductCategory;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 类目表 Mapper 接口
 * </p>
 *
 * @author zzy
 * @since 2023-08-05
 */

public interface ProductCategoryMapper extends BaseMapper<ProductCategory> {
    public String findNameByType(Integer type);
}
