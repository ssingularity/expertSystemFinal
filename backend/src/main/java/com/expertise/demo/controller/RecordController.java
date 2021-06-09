package com.expertise.demo.controller;

import com.expertise.demo.entity.Expert;
import com.expertise.demo.entity.Record;
import com.expertise.demo.service.RecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = {"http://localhost:8081","null"},allowCredentials = "true")
@RestController
@RequestMapping(value="/record",method = {RequestMethod.GET, RequestMethod.PUT,RequestMethod.DELETE, RequestMethod.POST})

public class RecordController {
    @Autowired
    private RecordService recordservice;

    @PostMapping(value = "/insert")
    public Record addRecord(@RequestBody Record record){
        return recordservice.insert(record);
    }

    @PostMapping(value = "/update")
    public Record updateRecord(@RequestBody Record record){
        return recordservice.update(record);
    }

    @GetMapping(value = "/get/{id}")
    public Record findById(@PathVariable(value = "id") Integer id)
    {
        return recordservice.findById(id);
    }

    @GetMapping(value = "/getP/{pid}")
    public List<Record> findByProgram(@PathVariable(value = "pid") Integer pid)
    {
        return recordservice.findByProgram(pid);
    }

    @GetMapping(value = "/getE/{eid}")
    public List<Record> findByExpert(@PathVariable(value = "eid") String eid)
    {
        return recordservice.findByExpert(eid);
    }

    @DeleteMapping(value = "/delete/{id}")
    public String delete(@PathVariable(value = "id") Integer id){
        return recordservice.delete(id);
    }



}
