package com.expertise.demo.controller;

import com.expertise.demo.common.Result;
import com.expertise.demo.entity.Record;
import com.expertise.demo.service.RecordService;
import com.expertise.demo.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping
public class RecordController {
    @Autowired
    private RecordService recordservice;

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
}
