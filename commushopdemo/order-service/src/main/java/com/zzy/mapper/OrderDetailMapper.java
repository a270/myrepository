package com.zzy.mapper;

import com.zzy.entity.OrderDetail;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zzy.vo.BarResultVO;
import com.zzy.vo.BasicLineResultVO;

import java.util.List;

/**
 * <p>
 * 订单详情表 Mapper 接口
 * </p>
 *
 * @author zzy
 * @since 2023-08-13
 */
public interface OrderDetailMapper extends BaseMapper<OrderDetail> {
    public List<BarResultVO> barData();
    public List<BasicLineResultVO> basicLineData();
    public List<String> names();
    public List<String> dates();
    public List<Integer> stackedData(String name);
}
