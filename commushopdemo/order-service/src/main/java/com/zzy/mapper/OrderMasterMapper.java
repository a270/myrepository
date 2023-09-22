package com.zzy.mapper;

import com.zzy.entity.OrderMaster;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import io.swagger.models.auth.In;

/**
 * <p>
 * 订单表 Mapper 接口
 * </p>
 *
 * @author zzy
 * @since 2023-08-13
 */
public interface OrderMasterMapper extends BaseMapper<OrderMaster> {
    public boolean cancel(Integer buyerId, String orderId);
    public boolean cancel2(String orderId);
    public boolean finish(String orderId);
    public boolean pay(Integer buyerId, String orderId);
}
