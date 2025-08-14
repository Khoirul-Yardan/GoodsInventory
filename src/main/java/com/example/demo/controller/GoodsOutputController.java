package com.example.demo.controller;

import com.example.demo.dto.GoodsOutputDTO;
import com.example.demo.entity.GoodsOutput;
import com.example.demo.entity.Goods;
import com.example.demo.repository.GoodsOutputRepository;
import com.example.demo.repository.GoodsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/GoodsOut")
@CrossOrigin(origins = "*")
public class GoodsOutputController {
    
    @Autowired
    private GoodsOutputRepository goodsOutputRepository;
    
    @Autowired
    private GoodsRepository goodsRepository;
    
    @GetMapping
    public List<GoodsOutput> getAllGoodsOutput() {
        return goodsOutputRepository.findAll();
    }
    
    @PostMapping
    public GoodsOutput createGoodsOutput(@RequestBody GoodsOutputDTO dto) {
        Optional<Goods> goodsOptional = goodsRepository.findById(dto.getGoodsId());
        if (goodsOptional.isPresent()) {
            Goods goods = goodsOptional.get();
            
            // Check if enough stock
            if (goods.getQuantity() < dto.getQuantity()) {
                throw new RuntimeException("Insufficient stock. Available: " + goods.getQuantity());
            }
            
            // Create GoodsOutput
            GoodsOutput goodsOutput = new GoodsOutput();
            goodsOutput.setGoods(goods);
            goodsOutput.setQuantity(dto.getQuantity());
            goodsOutput.setCustomer(dto.getCustomer());
            goodsOutput.setNotes(dto.getNotes());
            
            // Update goods quantity
            goods.setQuantity(goods.getQuantity() - dto.getQuantity());
            goodsRepository.save(goods);
            
            return goodsOutputRepository.save(goodsOutput);
        }
        throw new RuntimeException("Goods not found with id: " + dto.getGoodsId());
    }
    
    @GetMapping("/{id}")
    public Optional<GoodsOutput> getGoodsOutputById(@PathVariable Long id) {
        return goodsOutputRepository.findById(id);
    }
    
    @DeleteMapping("/{id}")
    public void deleteGoodsOutput(@PathVariable Long id) {
        goodsOutputRepository.deleteById(id);
    }
}
