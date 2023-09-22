package com.zzy.vo;

import lombok.Data;

import java.util.Map;

@Data
public class BarValueVO {
    private Integer value;
    private Map<String,String> itemStyle;
}
