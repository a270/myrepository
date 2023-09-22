package com.zzy.vo;

import lombok.Data;

import java.util.List;

@Data
public class StackedLineInnerVO {
    private String name;
    private String type = "line";
    private String stack = "销量";
    private List<Integer> data;
}
