package com.example.demo.service;

import com.example.demo.entity.Goods;
import com.example.demo.repository.GoodsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GoodsService {
    
    @Autowired
    private GoodsRepository goodsRepository;
    
    public List<Goods> getAllGoods() {
        return goodsRepository.findAll();
    }
    
    public Optional<Goods> getGoodsById(Long id) {
        return goodsRepository.findById(id);
    }
    
    public Goods saveGoods(Goods goods) {
        return goodsRepository.save(goods);
    }
    
    public void deleteGoods(Long id) {
        goodsRepository.deleteById(id);
    }
    
    public List<Goods> getLowStockGoods() {
        return goodsRepository.findAll().stream()
                .filter(goods -> goods.getQuantity() <= goods.getMinStock())
                .toList();
    }
}
