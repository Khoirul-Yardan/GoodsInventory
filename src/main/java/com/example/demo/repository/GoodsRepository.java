package com.example.demo.repository;

import com.example.demo.entity.Goods;
import com.example.demo.entity.Color;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Repository
public interface GoodsRepository extends JpaRepository<Goods, Long> {
    
    List<Goods> findByIsActiveTrueOrderByName();
    
    Optional<Goods> findByIdAndIsActiveTrue(Long id);
    
    List<Goods> findByCategoryIgnoreCaseAndIsActiveTrue(String category);
    
    Optional<Goods> findBySkuAndIsActiveTrue(String sku);
    
    Long countByIsActiveTrue();
    
    // Color-related queries
    List<Goods> findByColorAndIsActiveTrue(Color color);
    
    List<Goods> findByColorInAndIsActiveTrue(List<Color> colors);
    
    Long countByColorAndIsActiveTrue(Color color);
    
    @Query("SELECT g FROM Goods g WHERE g.quantity <= g.minStockLevel AND g.isActive = true")
    List<Goods> findLowStockGoods();
    
    @Query("SELECT g FROM Goods g WHERE g.quantity >= g.maxStockLevel AND g.isActive = true")
    List<Goods> findOverstockGoods();
    
    @Query("SELECT g FROM Goods g WHERE (LOWER(g.name) LIKE LOWER(CONCAT('%', :searchTerm, '%')) OR LOWER(g.description) LIKE LOWER(CONCAT('%', :searchTerm, '%')) OR LOWER(g.sku) LIKE LOWER(CONCAT('%', :searchTerm, '%'))) AND g.isActive = true")
    List<Goods> searchGoods(@Param("searchTerm") String searchTerm);
    
    @Query("SELECT SUM(g.quantity * g.price) FROM Goods g WHERE g.isActive = true")
    BigDecimal calculateTotalInventoryValue();
    
    @Query("SELECT DISTINCT g.category FROM Goods g WHERE g.isActive = true ORDER BY g.category")
    List<String> findAllCategories();
    
    @Query("SELECT DISTINCT g.brand FROM Goods g WHERE g.brand IS NOT NULL AND g.isActive = true ORDER BY g.brand")
    List<String> findAllBrands();
    
    @Query("SELECT DISTINCT g.supplier FROM Goods g WHERE g.supplier IS NOT NULL AND g.isActive = true ORDER BY g.supplier")
    List<String> findAllSuppliers();
}
