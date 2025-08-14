package com.example.demo.service;

import com.example.demo.entity.Goods;
import com.example.demo.entity.Color;
import com.example.demo.repository.GoodsRepository;
import com.example.demo.repository.ColorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class GoodsService {
    
    private final GoodsRepository goodsRepository;
    private final ColorRepository colorRepository;
    
    public List<Goods> getAllGoods() {
        return goodsRepository.findByIsActiveTrueOrderByName();
    }
    
    public Optional<Goods> getGoodsById(Long id) {
        return goodsRepository.findByIdAndIsActiveTrue(id);
    }
    
    public Goods saveGoods(Goods goods) {
        // Auto-generate SKU if not provided
        if (goods.getSku() == null || goods.getSku().isEmpty()) {
            goods.setSku(generateSku(goods.getName()));
        }
        
        // Handle color assignment if color name is provided but color entity is null
        if (goods.getColor() == null && goods.getName() != null) {
            assignDefaultColor(goods);
        }
        
        return goodsRepository.save(goods);
    }
    
    public Goods saveGoodsWithColor(Goods goods, String colorName) {
        // Find and assign color by name
        if (colorName != null && !colorName.isEmpty()) {
            Optional<Color> color = colorRepository.findByNameIgnoreCase(colorName);
            if (color.isPresent()) {
                goods.setColor(color.get());
            } else {
                // Create new color if it doesn't exist
                Color newColor = Color.builder()
                    .name(colorName)
                    .hexCode(getDefaultHexForColorName(colorName))
                    .colorFamily("Custom")
                    .description("Auto-created color for " + colorName)
                    .build();
                Color savedColor = colorRepository.save(newColor);
                goods.setColor(savedColor);
            }
        }
        
        return saveGoods(goods);
    }
    
    public void deleteGoods(Long id) {
        Optional<Goods> goods = goodsRepository.findById(id);
        if (goods.isPresent()) {
            Goods goodsEntity = goods.get();
            goodsEntity.setIsActive(false); // Soft delete
            goodsRepository.save(goodsEntity);
        }
    }
    
    public List<Goods> getLowStockGoods() {
        return goodsRepository.findLowStockGoods();
    }
    
    public List<Goods> getOverstockGoods() {
        return goodsRepository.findOverstockGoods();
    }
    
    public List<Goods> getGoodsByCategory(String category) {
        return goodsRepository.findByCategoryIgnoreCaseAndIsActiveTrue(category);
    }
    
    public List<Goods> getGoodsByColor(String colorName) {
        Optional<Color> color = colorRepository.findByNameIgnoreCase(colorName);
        if (color.isPresent()) {
            return goodsRepository.findByColorAndIsActiveTrue(color.get());
        }
        return List.of(); // Return empty list if color not found
    }
    
    public List<Goods> getGoodsByColorFamily(String colorFamily) {
        List<Color> colors = colorRepository.findByColorFamilyIgnoreCaseAndIsActiveTrue(colorFamily);
        return goodsRepository.findByColorInAndIsActiveTrue(colors);
    }
    
    public List<Goods> searchGoods(String searchTerm) {
        return goodsRepository.searchGoods(searchTerm);
    }
    
    public Goods updateStock(Long goodsId, Integer quantity, String operation) {
        Optional<Goods> optionalGoods = goodsRepository.findById(goodsId);
        if (optionalGoods.isPresent()) {
            Goods goods = optionalGoods.get();
            switch (operation.toLowerCase()) {
                case "add":
                    goods.setQuantity(goods.getQuantity() + quantity);
                    break;
                case "subtract":
                    goods.setQuantity(Math.max(0, goods.getQuantity() - quantity));
                    break;
                case "set":
                    goods.setQuantity(quantity);
                    break;
                default:
                    throw new IllegalArgumentException("Invalid operation: " + operation);
            }
            return goodsRepository.save(goods);
        }
        throw new RuntimeException("Goods not found with id: " + goodsId);
    }
    
    public Goods updateGoodsColor(Long goodsId, String colorName) {
        Optional<Goods> optionalGoods = goodsRepository.findById(goodsId);
        if (optionalGoods.isPresent()) {
            Goods goods = optionalGoods.get();
            
            if (colorName == null || colorName.isEmpty()) {
                goods.setColor(null);
            } else {
                Optional<Color> color = colorRepository.findByNameIgnoreCase(colorName);
                if (color.isPresent()) {
                    goods.setColor(color.get());
                } else {
                    throw new RuntimeException("Color not found: " + colorName);
                }
            }
            
            return goodsRepository.save(goods);
        }
        throw new RuntimeException("Goods not found with id: " + goodsId);
    }
    
    // Helper method to assign default color based on category
    private void assignDefaultColor(Goods goods) {
        String defaultColorName = getDefaultColorForCategory(goods.getCategory());
        if (defaultColorName != null) {
            Optional<Color> defaultColor = colorRepository.findByNameIgnoreCase(defaultColorName);
            defaultColor.ifPresent(goods::setColor);
        }
    }
    
    // Helper method to get default color based on category
    private String getDefaultColorForCategory(String category) {
        if (category == null) return null;
        
        return switch (category.toLowerCase()) {
            case "electronics" -> "Black";
            case "furniture" -> "Brown";
            case "stationery" -> "White";
            case "tools" -> "Gray";
            case "clothing" -> "Blue";
            default -> "Gray";
        };
    }
    
    // Helper method to get default hex code for color names
    private String getDefaultHexForColorName(String colorName) {
        if (colorName == null) return "#808080";
        
        return switch (colorName.toLowerCase()) {
            case "red" -> "#FF0000";
            case "blue" -> "#0000FF";
            case "green" -> "#00FF00";
            case "yellow" -> "#FFFF00";
            case "orange" -> "#FFA500";
            case "purple" -> "#800080";
            case "pink" -> "#FFC0CB";
            case "brown" -> "#A52A2A";
            case "black" -> "#000000";
            case "white" -> "#FFFFFF";
            case "gray", "grey" -> "#808080";
            case "silver" -> "#C0C0C0";
            default -> "#808080"; // Default gray
        };
    }
    
    private String generateSku(String name) {
        if (name == null || name.isEmpty()) {
            name = "ITEM";
        }
        String prefix = name.replaceAll("[^A-Za-z]", "")
                           .substring(0, Math.min(3, name.replaceAll("[^A-Za-z]", "").length()))
                           .toUpperCase();
        if (prefix.isEmpty()) {
            prefix = "ITM";
        }
        long count = goodsRepository.count();
        return String.format("%s-%03d", prefix, count + 1);
    }
    
    public BigDecimal getTotalInventoryValue() {
        BigDecimal total = goodsRepository.calculateTotalInventoryValue();
        return total != null ? total : BigDecimal.ZERO;
    }
    
    public Long getGoodsCount() {
        return goodsRepository.countByIsActiveTrue();
    }
    
    // Color-related statistics
    public List<Color> getAllActiveColors() {
        return colorRepository.findByIsActiveTrueOrderByName();
    }
    
    public Long getGoodsCountByColor(String colorName) {
        Optional<Color> color = colorRepository.findByNameIgnoreCase(colorName);
        return color.map(c -> goodsRepository.countByColorAndIsActiveTrue(c)).orElse(0L);
    }
    
    public List<String> getAllColorFamilies() {
        return colorRepository.findAllColorFamilies();
    }
}
