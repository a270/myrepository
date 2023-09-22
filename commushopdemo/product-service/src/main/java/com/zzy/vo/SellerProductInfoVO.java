package com.zzy.vo;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class SellerProductInfoVO {
    private Integer id;
    private Boolean status;
    private BigDecimal price;
    private String name;
    private Integer stock;
    private String description;
    private String icon;
    private String categoryName;
}
