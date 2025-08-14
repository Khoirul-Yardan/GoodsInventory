package com.example.demo.controller;

import com.example.demo.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/dashboard")
@CrossOrigin(origins = "*")
public class DashboardController {
    
    @Autowired
    private GoodsRepository goodsRepository;
    
    @Autowired
    private GoodsInputRepository goodsInputRepository;
    
    @Autowired
    private GoodsOutputRepository goodsOutputRepository;
    
    @Autowired
    private StaffRepository staffRepository;
    
    @GetMapping("/stats")
    public Map<String, Object> getDashboardStats() {
        Map<String, Object> stats = new HashMap<>();
        
        stats.put("totalGoods", goodsRepository.count());
        stats.put("totalInputs", goodsInputRepository.count());
        stats.put("totalOutputs", goodsOutputRepository.count());
        stats.put("totalStaff", staffRepository.count());
        
        return stats;
    }
}
