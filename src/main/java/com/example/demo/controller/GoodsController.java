package com.example.demo.controller;

import com.example.demo.entity.Goods;
import com.example.demo.repository.GoodsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/Goods")
@CrossOrigin(origins = "*")
public class GoodsController {
    
    @Autowired
    private GoodsRepository goodsRepository;
    
    @GetMapping
    public List<Goods> getAllGoods() {
        return goodsRepository.findAll();
    }
    
    @GetMapping("/{id}")
    public Optional<Goods> getGoodsById(@PathVariable Long id) {
        return goodsRepository.findById(id);
    }
    
    @PostMapping
    public Goods createGoods(@RequestBody Goods goods) {
        return goodsRepository.save(goods);
    }
    
    @PutMapping("/{id}")
    public Goods updateGoods(@PathVariable Long id, @RequestBody Goods goodsDetails) {
        Optional<Goods> optionalGoods = goodsRepository.findById(id);
        if (optionalGoods.isPresent()) {
            Goods goods = optionalGoods.get();
            goods.setName(goodsDetails.getName());
            goods.setDescription(goodsDetails.getDescription());
            goods.setQuantity(goodsDetails.getQuantity());
            goods.setPrice(goodsDetails.getPrice());
            goods.setCategory(goodsDetails.getCategory());
            return goodsRepository.save(goods);
        }
        return null;
    }
    
    @DeleteMapping("/{id}")
    public void deleteGoods(@PathVariable Long id) {
        goodsRepository.deleteById(id);
    }
}
