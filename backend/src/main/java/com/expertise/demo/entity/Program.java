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
    @ExcelProperty("项目类型")
    private String type;
    @ExcelProperty("项目简介")
    private String keyword;
    @ExcelProperty("项目时间")
    private Date time;
    @ExcelProperty("项目单位")
    private String company;
    @ExcelProperty("项目状态")
    private int state;
    @ExcelProperty("项目所需专家数量")
    private int number;

    public Program(){}

    public Program(String area,String type,String keyword,Date time,String company,int state,int number){
        this.area=area;
        this.type=type;
        this.company=company;
        this.keyword=keyword;
        this.number=number;
        this.time=time;
        this.state=state;
    }

}
