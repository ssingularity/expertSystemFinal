package com.expertise.demo.controller;

import com.expertise.demo.entity.Expert;
import com.expertise.demo.service.ExpertService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@CrossOrigin(origins = {"http://localhost:8081","null"},allowCredentials = "true")
@RestController
@RequestMapping(value="/expert",method = {RequestMethod.GET, RequestMethod.PUT,RequestMethod.DELETE, RequestMethod.POST})
public class ExpertController {
    @Autowired
    private ExpertService expertservice;

//    @PostMapping(value = "/insert")
//    public Expert insertExpert(@RequestParam(value = "name") String name,
//                                @RequestParam(value = "gender") String gender,
//                                @RequestParam(value = "birth") String birthstr,
//                               @RequestParam(value = "type") String type,
//                               @RequestParam(value = "area") String area,
//                               @RequestParam(value = "company") String company,
//                                @RequestParam(value = "secret") boolean secret) throws ParseException {
//        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");//参数为你要格式化时间日期的模式
////        Date date = df.parse(birthstr);//将字符串按照定义的模式转换为Date对象
//        Date birth = df.parse(birthstr);
//        return expertservice.insert(name,gender,birth,type,area,company,secret);
//    }
    @PostMapping(value = "/insert")
    public Expert addExpert(@RequestBody Expert expert){
        return expertservice.insert(expert);
    }

    @GetMapping(value = "/get_all")
    public List<Expert> findAll()
    {
        return expertservice.findAll();
    }

    @GetMapping(value = "/find/{name}")
    public List<Expert> findByName(@PathVariable(value = "name") String name)
    {
        return expertservice.findByName(name);
    }

    @GetMapping(value = "/get/{id}")
    public Expert findById(@PathVariable(value = "id") String id)
    {
        return expertservice.findById(id);
    }
}
