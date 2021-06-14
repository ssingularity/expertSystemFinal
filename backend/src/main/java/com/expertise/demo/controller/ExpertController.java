package com.expertise.demo.controller;

import com.expertise.demo.entity.Expert;
import com.expertise.demo.service.ExpertService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = {"http://localhost:8081","null"},allowCredentials = "true")
@RestController
@RequestMapping(value="/expert",method = {RequestMethod.GET, RequestMethod.PUT,RequestMethod.DELETE, RequestMethod.POST})
public class ExpertController {
    @Autowired
    private ExpertService expertservice;

    @PostMapping(value = "/insert")
    public Expert addExpert(@RequestBody Expert expert){
        return expertservice.insert(expert);
    }

    @GetMapping(value = "/get_all")
    public List<Expert> findAll()
    {
        return expertservice.findAll();
    }

    @GetMapping(value = "/get_blocked")
    public List<Expert> findAllBlocked()
    {
        return expertservice.findByBlocked(Boolean.TRUE);
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

    @GetMapping(value = "/block/{id}")
    public void blockById(@PathVariable(value = "id") String id)
    {
        expertservice.blockById(id);
    }

    @GetMapping(value = "/unblock/{id}")
    public void unblockById(@PathVariable(value = "id") String id)
    {
        expertservice.unblockById(id);
    }

    @DeleteMapping(value = "/delete/{id}")
    public void delete(@PathVariable(value = "id") String id)
    {
        expertservice.deleteById(id);
    }
}
