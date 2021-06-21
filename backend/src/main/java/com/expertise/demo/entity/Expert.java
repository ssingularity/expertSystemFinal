package com.expertise.demo.entity;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;
import org.springframework.util.StringUtils;

@Data
public class Expert {
    @ExcelProperty("序号")
    private String number;

    @ExcelProperty("工作单位")
    private String company;

    @ExcelProperty("姓名")
    private String name;

    @ExcelProperty("身份证号")
    private String id;

    @ExcelProperty("性别")
    private String gender;

    @ExcelProperty("职务")
    private String jobPosition;

    @ExcelProperty("职称")
    private String jobTitle;

    @ExcelProperty("专家类型")
    private String type;

    @ExcelProperty("行业领域")
    private String area;

    @ExcelProperty("从事专业")
    private String introduction;

    @ExcelProperty("手机")
    private String phone;

    @ExcelProperty("是否涉密人员")
    private String secret;

    @ExcelProperty("涉密程度")
    private String secretLevel;

    @ExcelProperty("通讯地址")
    private String address;

    @ExcelProperty("邮编")
    private String postcode;

    @ExcelProperty("推荐单位")
    private String recommendCompany;

    @ExcelProperty("填报人")
    private String filler;

    @ExcelProperty("填报人联系方式")
    private String fillerContact;

    @ExcelProperty("是否被拉黑")
    private String isBlocked;

    public String getIsBlocked() {
        if (!StringUtils.hasText(isBlocked)) {
            return "否";
        }
        else {
            return isBlocked;
        }
    }
}
