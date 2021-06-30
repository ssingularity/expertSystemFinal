package com.expertise.demo.controller;

import com.alibaba.excel.EasyExcel;
import com.expertise.demo.common.Result;
import com.expertise.demo.entity.Expert;
import com.expertise.demo.entity.Record;
import com.expertise.demo.service.ExpertService;
import com.expertise.demo.service.RecordService;
import com.expertise.demo.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/api")
public class RecordController {
    @Autowired
    private RecordService recordservice;

    @Autowired
    private ExpertService expertservice;

    @PostMapping(value = "/record")
    public Result<String> addRecord(@RequestBody Record record) {
        recordservice.insert(record);
        return ResultUtil.success();
    }

    @PutMapping(value = "/record")
    public Result<String> updateRecord(@RequestBody Record record) {
        recordservice.update(record);
        return ResultUtil.success();
    }

    @GetMapping(value = "/record/{id}")
    public Result<Record> findById(@PathVariable(value = "id") Integer id) {
        return ResultUtil.success(recordservice.findById(id));
    }

    @GetMapping(value = "/program/{pid}/record")
    public Result<List<Record>> findByProgram(@PathVariable(value = "pid") String pid) {
        return ResultUtil.success(recordservice.findByProgram(pid));
    }

    @GetMapping(value = "/expert/{eid}/record")
    public Result<List<Record>> findByExpert(@PathVariable(value = "eid") String eid) {
        return ResultUtil.success(recordservice.findByExpert(eid));
    }

    @DeleteMapping(value = "/record/{id}")
    public Result<String> delete(@PathVariable(value = "id") String id) {
        recordservice.delete(id);
        return ResultUtil.success();
    }

    @GetMapping(value = "/program/{id}/record/excel")
    public void getProgramExcel(HttpServletResponse response, @PathVariable(value = "id") String id) throws IOException {
        List<Record> records = recordservice.findByProgram(id);
        List<Expert> list = new ArrayList<>();
        records.forEach(record -> {
            String expertId = record.getExpertID();
            list.add(expertservice.findById(expertId));
        });
        download(response, Expert.class, list);
    }

    public void download(HttpServletResponse response, Class t, List list) throws IOException {
        response.setContentType("application/vnd.ms-excel");
        response.setCharacterEncoding("utf-8");
        response.setHeader("Content-disposition", "attachment;filename=expertList.xlsx");
        EasyExcel.write(response.getOutputStream(), t).sheet("专家列表").doWrite(list);
    }

}
