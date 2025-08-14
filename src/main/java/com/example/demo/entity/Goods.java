package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "goods")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString(exclude = {"color"}) // Prevent circular references
public class Goods {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "name", nullable = false)
    private String name;
    
    @Column(name = "description")
    private String description;
    
    @Column(name = "quantity")
    @Builder.Default
    private Integer quantity = 0;
    
    @Column(name = "price", nullable = false, precision = 10, scale = 2)
    private BigDecimal price;
    
    @Column(name = "category")
    private String category;
    
    @Column(name = "sku", unique = true)
    private String sku;
    
    @Column(name = "barcode")
    private String barcode;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "color_id")
    private Color color;
    
    @Column(name = "size")
    private String size;
    
    @Column(name = "weight")
    private Double weight;
    
    @Column(name = "dimensions")
    private String dimensions;
    
    @Column(name = "material")
    private String material;
    
    @Column(name = "brand")
    private String brand;
    
    @Column(name = "model")
    private String model;
    
    @Column(name = "supplier")
    private String supplier;
    
    @Column(name = "min_stock_level")
    @Builder.Default
    private Integer minStockLevel = 10;
    
    @Column(name = "max_stock_level")
    @Builder.Default
    private Integer maxStockLevel = 1000;
    
    @Column(name = "unit")
    @Builder.Default
    private String unit = "pcs";
    
    @Column(name = "is_active")
    @Builder.Default
    private Boolean isActive = true;
    
    @Column(name = "created_at")
    @Builder.Default
    private LocalDateTime createdAt = LocalDateTime.now();
    
    @Column(name = "updated_at")
    @Builder.Default
    private LocalDateTime updatedAt = LocalDateTime.now();
    
    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }
}
