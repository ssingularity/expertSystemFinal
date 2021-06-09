package com.expertise.demo.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "program")
public class Program {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;
    private String area;
    private String type;
    private String keyword;
    private Date time;
    private String company;
    private int state;
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
