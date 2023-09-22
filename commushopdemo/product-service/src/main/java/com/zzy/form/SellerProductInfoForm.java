package com.zzy.form;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class SellerProductInfoForm {//单独创建一个form，和直接使用实体类相比，好处就是数据项上更匹配一些
    private Integer categoryType;
    private String productDescription;
    private String productIcon;
    private String productName;
    private BigDecimal productPrice;
    private Integer productStatus;
    private Integer productStock;
}
