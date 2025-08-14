package com.example.demo.controller;

import com.example.demo.entity.Goods;
import com.example.demo.entity.Color;
import com.example.demo.service.GoodsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/Goods")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class GoodsController {
    
    private final GoodsService goodsService;
    
    @GetMapping
    public List<Goods> getAllGoods() {
        return goodsService.getAllGoods();
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Goods> getGoodsById(@PathVariable Long id) {
        Optional<Goods> goods = goodsService.getGoodsById(id);
        return goods.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }
    
    @PostMapping
    public Goods createGoods(@RequestBody Goods goods) {
        return goodsService.saveGoods(goods);
    }
    
    @PostMapping("/with-color")
    public Goods createGoodsWithColor(@RequestBody Goods goods, @RequestParam String colorName) {
        return goodsService.saveGoodsWithColor(goods, colorName);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Goods> updateGoods(@PathVariable Long id, @RequestBody Goods goodsDetails) {
        Optional<Goods> optionalGoods = goodsService.getGoodsById(id);
        if (optionalGoods.isPresent()) {
            Goods goods = optionalGoods.get();
            goods.setName(goodsDetails.getName());
            goods.setDescription(goodsDetails.getDescription());
            goods.setPrice(goodsDetails.getPrice());
            goods.setCategory(goodsDetails.getCategory());
            goods.setBrand(goodsDetails.getBrand());
            goods.setColor(goodsDetails.getColor());
            goods.setSize(goodsDetails.getSize());
            goods.setMaterial(goodsDetails.getMaterial());
            goods.setSupplier(goodsDetails.getSupplier());
            goods.setMinStockLevel(goodsDetails.getMinStockLevel());
            goods.setMaxStockLevel(goodsDetails.getMaxStockLevel());
            return ResponseEntity.ok(goodsService.saveGoods(goods));
        }
        return ResponseEntity.notFound().build();
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGoods(@PathVariable Long id) {
        goodsService.deleteGoods(id);
        return ResponseEntity.ok().build();
    }
    
    // Color-related endpoints
    @GetMapping("/color/{colorName}")
    public List<Goods> getGoodsByColor(@PathVariable String colorName) {
        return goodsService.getGoodsByColor(colorName);
    }
    
    @GetMapping("/color-family/{colorFamily}")
    public List<Goods> getGoodsByColorFamily(@PathVariable String colorFamily) {
        return goodsService.getGoodsByColorFamily(colorFamily);
    }
    
    @PutMapping("/{id}/color")
    public ResponseEntity<Goods> updateGoodsColor(@PathVariable Long id, @RequestParam String colorName) {
        try {
            Goods updatedGoods = goodsService.updateGoodsColor(id, colorName);
            return ResponseEntity.ok(updatedGoods);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
    
    @GetMapping("/colors")
    public List<Color> getAllActiveColors() {
        return goodsService.getAllActiveColors();
    }
    
    @GetMapping("/color-stats/{colorName}")
    public ResponseEntity<Long> getGoodsCountByColor(@PathVariable String colorName) {
        Long count = goodsService.getGoodsCountByColor(colorName);
        return ResponseEntity.ok(count);
    }
    
    // Other existing endpoints...
    @GetMapping("/low-stock")
    public List<Goods> getLowStockGoods() {
        return goodsService.getLowStockGoods();
    }
    
    @GetMapping("/overstock")
    public List<Goods> getOverstockGoods() {
        return goodsService.getOverstockGoods();
    }
    
    @GetMapping("/category/{category}")
    public List<Goods> getGoodsByCategory(@PathVariable String category) {
        return goodsService.getGoodsByCategory(category);
    }
    
    @GetMapping("/search")
    public List<Goods> searchGoods(@RequestParam String q) {
        return goodsService.searchGoods(q);
    }
    
    @PutMapping("/{id}/stock")
    public ResponseEntity<Goods> updateStock(@PathVariable Long id, 
                                           @RequestParam Integer quantity, 
                                           @RequestParam String operation) {
        try {
            Goods updatedGoods = goodsService.updateStock(id, quantity, operation);
            return ResponseEntity.ok(updatedGoods);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
