package com.expertise.demo.service;

import com.expertise.demo.entity.Expert;

import java.util.List;

public interface ExpertService {
     Expert insert(Expert expert);
     List<Expert> findAll();
     List<Expert> findByName(String name);
     Expert findById(String id);
}
