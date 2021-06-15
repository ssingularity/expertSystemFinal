package com.expertise.demo.entity;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

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

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getJobPosition() {
        return jobPosition;
    }

    public void setJobPosition(String jobPosition) {
        this.jobPosition = jobPosition;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

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

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public String getSecretLevel() {
        return secretLevel;
    }

    public void setSecretLevel(String secretLevel) {
        this.secretLevel = secretLevel;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public String getRecommendCompany() {
        return recommendCompany;
    }

    public void setRecommendCompany(String recommendCompany) {
        this.recommendCompany = recommendCompany;
    }

    public String getFiller() {
        return filler;
    }

    public void setFiller(String filler) {
        this.filler = filler;
    }

    public String getFillerContact() {
        return fillerContact;
    }

    public void setFillerContact(String fillerContact) {
        this.fillerContact = fillerContact;
    }


    public Expert(){}

    public Expert(String id, String name, String gender, String phone, String type, String area, String introduction, String company, String secret, String secretLevel) {
        this.area=area;
        this.introduction=introduction;
        this.name=name;
        this.id=id;
        this.phone=phone;
        this.company=company;
        this.gender=gender;
        this.secret=secret;
        this.secretLevel=secretLevel;
        this.type=type;
    }

}
