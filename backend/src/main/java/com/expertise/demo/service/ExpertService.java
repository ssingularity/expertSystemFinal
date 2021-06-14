package com.expertise.demo.service;

import com.expertise.demo.entity.Expert;

import java.util.List;

public interface ExpertService {
     Expert insert(Expert expert);
     List<Expert> findAll();
     List<Expert> findByName(String name);
     List<Expert> findByBlocked(Boolean blocked);
     Expert findById(String id);
     void blockById(String id);
     void unblockById(String id);
     void deleteById(String id);
}
