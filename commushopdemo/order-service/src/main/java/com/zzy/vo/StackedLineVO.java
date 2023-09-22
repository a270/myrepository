package com.zzy.vo;

import lombok.Data;

import java.util.List;

@Data
public class StackedLineVO {
    private List<String> names;
    private List<String> dates;
    private List<StackedLineInnerVO> datas;
}
