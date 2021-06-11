package com.expertise.demo.controller;

import com.alibaba.excel.EasyExcel;
import com.expertise.demo.entity.Expert;
import com.expertise.demo.entity.Record;
import com.expertise.demo.service.ExpertService;
import com.expertise.demo.service.RecordService;
import com.expertise.demo.util.ExpertListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = {"http://localhost:8080","null"},allowCredentials = "true")
@RestController
@RequestMapping(value="/file",method = {RequestMethod.GET, RequestMethod.PUT,RequestMethod.DELETE, RequestMethod.POST})
public class fileController {
    @Autowired
    private ExpertService expertservice;
    @Autowired
    private RecordService recordService;

    @RequestMapping("/downloadexcel")
    public void getExcel(HttpServletResponse response) throws IllegalAccessException, IOException,
            InstantiationException {
        List<Expert> list = expertservice.findAll();
        this.download(response,Expert.class,list);
    }

    @RequestMapping("/downloadexcelbyprogram/{id}")
    public void getProgramExcel(HttpServletResponse response, @PathVariable(value = "id") String id) throws IllegalAccessException, IOException,
            InstantiationException {
        List<Record> records = recordService.findByProgram(id);
        List<Expert> list = new ArrayList<>();
        records.forEach(record -> {
            String expertId = record.getExpertID();
            list.add(expertservice.findById(expertId));
        });
        this.download(response,Expert.class,list);
    }

    public static void download(HttpServletResponse response, Class t, List list) throws IOException, IllegalAccessException,InstantiationException {
        response.setContentType("application/vnd.ms-excel");
        response.setCharacterEncoding("utf-8");
        response.setHeader("Content-disposition", "attachment;filename=expertList.xlsx");
        EasyExcel.write(response.getOutputStream(), t).sheet("专家列表").doWrite(list);
    }

    @RequestMapping("/importexcel")
    public void excelImport(@RequestParam(value = "file") MultipartFile serviceFile) throws IOException {
        ExpertListener expertListener = new ExpertListener();
        EasyExcel.read(serviceFile.getInputStream(), Expert.class, expertListener).sheet().doRead();
        List<Expert> newExps = expertListener.getExpertList();
        for (Expert exp : newExps) {
            expertservice.insert(exp);
        }
    }

}
