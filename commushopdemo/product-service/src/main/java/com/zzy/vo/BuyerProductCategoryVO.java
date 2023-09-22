package com.zzy.vo;

import lombok.Data;

import java.util.List;

@Data
public class BuyerProductCategoryVO {
    private String name;
    private Integer type;
    private List<BuyerProductInfoVO> goods;
}
