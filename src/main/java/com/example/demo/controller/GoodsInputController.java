package com.example.demo.controller;

import com.example.demo.dto.GoodsInputDTO;
import com.example.demo.entity.GoodsInput;
import com.example.demo.entity.Goods;
import com.example.demo.repository.GoodsInputRepository;
import com.example.demo.repository.GoodsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/GoodsIn")
@CrossOrigin(origins = "*")
public class GoodsInputController {
    
    @Autowired
    private GoodsInputRepository goodsInputRepository;
    
    @Autowired
    private GoodsRepository goodsRepository;
    
    @GetMapping
    public List<GoodsInput> getAllGoodsInput() {
        return goodsInputRepository.findAll();
    }
    
    @PostMapping
    public GoodsInput createGoodsInput(@RequestBody GoodsInputDTO dto) {
        Optional<Goods> goodsOptional = goodsRepository.findById(dto.getGoodsId());
        if (goodsOptional.isPresent()) {
            Goods goods = goodsOptional.get();
            
            // Create GoodsInput
            GoodsInput goodsInput = new GoodsInput();
            goodsInput.setGoods(goods);
            goodsInput.setQuantity(dto.getQuantity());
            goodsInput.setSupplier(dto.getSupplier());
            goodsInput.setNotes(dto.getNotes());
            
            // Update goods quantity
            goods.setQuantity(goods.getQuantity() + dto.getQuantity());
            goodsRepository.save(goods);
            
            return goodsInputRepository.save(goodsInput);
        }
        throw new RuntimeException("Goods not found with id: " + dto.getGoodsId());
    }
    
    @GetMapping("/{id}")
    public Optional<GoodsInput> getGoodsInputById(@PathVariable Long id) {
        return goodsInputRepository.findById(id);
    }
    
    @DeleteMapping("/{id}")
    public void deleteGoodsInput(@PathVariable Long id) {
        goodsInputRepository.deleteById(id);
    }
}
