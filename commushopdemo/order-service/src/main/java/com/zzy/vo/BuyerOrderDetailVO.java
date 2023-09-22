package com.zzy.vo;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class BuyerOrderDetailVO {
    private String detailId;
    private String orderId;
    private Integer productId;
    private String productName;
    private BigDecimal productPrice;
    private Integer productQuantity;
    private String productIcon;
}
