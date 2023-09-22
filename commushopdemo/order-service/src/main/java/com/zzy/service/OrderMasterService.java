package com.zzy.service;

import com.zzy.entity.OrderMaster;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zzy.form.BuyerOrderForm;
import com.zzy.vo.BarVO;
import com.zzy.vo.BuyerOrderMasterVO;

/**
 * <p>
 * 订单表 服务类
 * </p>
 *
 * @author zzy
 * @since 2023-08-13
 */
public interface OrderMasterService extends IService<OrderMaster> {
    public String create(BuyerOrderForm buyerOrderForm);
    public BuyerOrderMasterVO detail(Integer buyerId,String orderId);
    public boolean cancel(Integer buyerId, String orderId);
    public boolean finish(String orderId);
    public boolean pay(Integer buyerId, String orderId);
}
