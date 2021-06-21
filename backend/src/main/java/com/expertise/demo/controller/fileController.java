package com.expertise.demo.controller;

import com.alibaba.excel.EasyExcel;
import com.expertise.demo.entity.Expert;
import com.expertise.demo.entity.Record;
import com.expertise.demo.service.ExpertService;
import com.expertise.demo.service.RecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value="/file")
public class fileController {
    @Autowired
    private ExpertService expertservice;
    @Autowired
    private RecordService recordService;

    @RequestMapping("/downloadexcelbyprogram/{id}")
    public void getProgramExcel(HttpServletResponse response, @PathVariable(value = "id") String id) throws IOException {
        List<Record> records = recordService.findByProgram(id);
        List<Expert> list = new ArrayList<>();
        records.forEach(record -> {
            String expertId = record.getExpertID();
            list.add(expertservice.findById(expertId));
        });
        download(response,Expert.class,list);
    }

    public void download(HttpServletResponse response, Class t, List list) throws IOException {
        response.setContentType("application/vnd.ms-excel");
        response.setCharacterEncoding("utf-8");
        response.setHeader("Content-disposition", "attachment;filename=expertList.xlsx");
        EasyExcel.write(response.getOutputStream(), t).sheet("专家列表").doWrite(list);
    }
}
