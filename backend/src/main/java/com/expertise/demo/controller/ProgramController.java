package com.expertise.demo.controller;

import com.expertise.demo.common.Result;
import com.expertise.demo.entity.Program;
import com.expertise.demo.service.ProgramService;
import com.expertise.demo.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/program")
public class ProgramController {
    @Autowired
    private ProgramService programservice;

    @GetMapping()
    public Result<List<Program>> findAll() {
        return ResultUtil.success(programservice.findAll());
    }

    @PostMapping()
    public Result insert(@RequestBody Program program) {
        programservice.insert(program);
        return ResultUtil.success();
    }

    @GetMapping(value = "/{id}")
    public Result<Program> findById(@PathVariable(value = "id") String id) {
        return ResultUtil.success(programservice.findById(id));
    }

    @PostMapping(value = "/{id}/auto")
    public Result<String> autoChoose(@PathVariable(value = "id") String id) {
        return ResultUtil.success(programservice.autoChoose(id));
    }

    @PutMapping(value = "/{id}/state")
    public Result<String> endProgram(@PathVariable(value = "id") String id) {
        programservice.changeState(id);
        return ResultUtil.success();
    }
}
