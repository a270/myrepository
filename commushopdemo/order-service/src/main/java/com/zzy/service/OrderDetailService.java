package com.zzy.service;

import com.zzy.entity.OrderDetail;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zzy.vo.BarVO;
import com.zzy.vo.BasicLineVO;
import com.zzy.vo.StackedLineVO;

/**
 * <p>
 * 订单详情表 服务类
 * </p>
 *
 * @author zzy
 * @since 2023-08-13
 */
public interface OrderDetailService extends IService<OrderDetail> {
    public BarVO barData();
    public BasicLineVO basicLineData();
    public StackedLineVO stackedLineData();
}
