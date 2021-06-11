package com.expertise.demo.entity;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

import java.util.Date;

@Data
public class Expert {
    @ExcelProperty("专家身份证号")
    private String id;
    @ExcelProperty("专家姓名")
    private String name;
    @ExcelProperty("性别")
    private String gender;
    @ExcelProperty("出生年月")
    private Date birth;
    @ExcelProperty("电话")
    private String phone;
    @ExcelProperty("类型")
    private String type;
    @ExcelProperty("行业领域")
    private String area;
    @ExcelProperty("简介")
    private String introduction;
    @ExcelProperty("专家所属单位")
    private String company;
    @ExcelProperty("机密等级")
    private String secret;

    public Expert(){}

    public Expert(String name, String gender, Date birth, String phone, String type, String area, String introduction, String company, String secret) {
        this.area=area;
        this.introduction=introduction;
        this.name=name;
        this.birth=birth;
        this.phone=phone;
        this.company=company;
        this.gender=gender;
        this.secret=secret;
        this.type=type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Date getBirth() {
        return birth;
    }

    public void setBirth(Date birth) {
        this.birth = birth;
    }

    public String getPhone(){return phone;}

    public void setPhone(String phone){this.phone=phone;}

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getIntroduction(){return this.introduction;}

    public  void setIntroduction(String introduction){this.introduction=introduction;}

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String isSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }
}
