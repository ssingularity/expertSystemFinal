package com.expertise.demo.entity;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

import java.util.Date;

@Data
public class Record {
    @ExcelProperty("评审ID")
    private String id;
    @ExcelProperty("专家身份证")
    private String expertID;
    @ExcelProperty("专家姓名")
    private String expertName;
    @ExcelProperty("专家类型")
    private String type;
    @ExcelProperty("专家领域")
    private String expertArea;
    @ExcelProperty("手机")
    private String phone;
    @ExcelProperty("工作单位")
    private String company;
    @ExcelProperty("是否涉密项目")
    private String secret;
    @ExcelProperty("涉密程度")
    private String secretLevel;
    @ExcelProperty("项目ID")
    private String programID;
    @ExcelProperty("评审开始时间")
    private Date startTime;
    @ExcelProperty("评审结束时间")
    private Date endTime;
    @ExcelProperty("评价")
    private String comment;
    @ExcelProperty("分数")
    private int score;

    public Record(){}

}
