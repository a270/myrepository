package com.zzy.vo;

import com.zzy.entity.OrderMaster;
import lombok.Data;

import java.util.List;

@Data
public class SellerOrderMasterVO {
    private List<OrderMaster> content;
    private Long size;
    private Long total;
}
