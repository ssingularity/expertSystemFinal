package com.expertise.demo.controller;

import com.alibaba.excel.EasyExcel;
import com.expertise.demo.common.Result;
import com.expertise.demo.dao.excel.ExpertDaoExcelImpl;
import com.expertise.demo.entity.Expert;
import com.expertise.demo.entity.PageableExperts;
import com.expertise.demo.service.ExpertService;
import com.expertise.demo.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping(value = "/api/expert")
public class ExpertController {
    @Autowired
    private ExpertService expertservice;

    @PostMapping()
    public Result addExpert(@RequestBody Expert expert) {
        expertservice.insert(expert);
        return ResultUtil.success();
    }

    @GetMapping()
    public Result<PageableExperts> findAll(@RequestParam(name = "offset", defaultValue = "0") int offset,
                                           @RequestParam(name = "type", defaultValue = "") String type,
                                           @RequestParam(name = "search", defaultValue = "") String search) {
        return ResultUtil.success(expertservice.findByPageable(type, search, offset));
    }

    @GetMapping(value = "/blocked")
    public Result<List<Expert>> findAllBlocked() {
        return ResultUtil.success(expertservice.findByBlocked());
    }

    @GetMapping(value = "/{id}")
    public Result<Expert> findById(@PathVariable(value = "id") String id) {
        return ResultUtil.success(expertservice.findById(id));
    }

    @PutMapping()
    public Result updateExpert(@RequestBody Expert expert) {
        expertservice.update(expert);
        return ResultUtil.success();
    }

    @GetMapping(value = "/excel")
    public void getExcel(HttpServletResponse response) throws IOException {
        List<Expert> list = expertservice.findAll();
        download(response, Expert.class, list);
    }

    private void download(HttpServletResponse response, Class t, List list) throws IOException {
        response.setContentType("application/vnd.ms-excel");
        response.setCharacterEncoding("utf-8");
        response.setHeader("Content-disposition", "attachment;filename=expertList.xlsx");
        EasyExcel.write(response.getOutputStream(), t).sheet("专家列表").doWrite(list);
    }

    @PostMapping("/excel")
    public Result<String> excelImport(@RequestParam(value = "file") MultipartFile serviceFile) throws IOException {
        ExpertDaoExcelImpl.ExpertListener expertListener = new ExpertDaoExcelImpl.ExpertListener();
        EasyExcel.read(serviceFile.getInputStream(), Expert.class, expertListener).sheet().doRead();
        List<Expert> newExps = expertListener.getExpertList();
        for (Expert exp : newExps) {
            expertservice.insert(exp);
        }
        return ResultUtil.success();
    }
}
