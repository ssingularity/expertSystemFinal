package com.expertise.demo.entity;

import com.alibaba.excel.annotation.ExcelProperty;
import com.fasterxml.jackson.annotation.JsonFormat;
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

    @ExcelProperty("项目备注")
    private String keyword;

    @ExcelProperty("评审开始时间")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm")
    private Date startTime;

    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm")
    @ExcelProperty("评审结束时间")
    private Date endTime;

    @ExcelProperty("项目单位")
    private String company;

    @ExcelProperty("项目状态")
    private Integer state = 0;

    @ExcelProperty("技术专家数量")
    private Integer numberTech;

    @ExcelProperty("管理专家数量")
    private Integer numberMng;

    @ExcelProperty("财务专家数量")
    private Integer numberAcc;

    public int getNumber() {
        return this.numberTech + numberMng + numberAcc;
    }
}
