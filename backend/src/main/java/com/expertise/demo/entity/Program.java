package com.expertise.demo.entity;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

import java.util.Date;

@Data
public class Program {

    @ExcelProperty("项目ID")
    private String id;
    @ExcelProperty("项目领域")
    private String area;
    @ExcelProperty("是否涉密项目")
    private String secret;
    @ExcelProperty("涉密程度")
    private String secretLevel;
    @ExcelProperty("项目简介")
    private String keyword;
    @ExcelProperty("评审时间")
    private Date time;
    @ExcelProperty("项目单位")
    private String company;
    @ExcelProperty("项目状态")
    private int state;
    @ExcelProperty("技术专家数量")
    private int numberTech;
    @ExcelProperty("管理专家数量")
    private int numberMng;
    @ExcelProperty("财务专家数量")
    private int numberAcc;

    public Program(){}


}
