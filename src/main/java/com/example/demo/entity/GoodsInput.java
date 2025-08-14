package com.example.demo.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "goods_input")
public class GoodsInput {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne
    @JoinColumn(name = "goods_id")
    private Goods goods;
    
    private Integer quantity;
    private LocalDateTime inputDate;
    private String supplier;
    private String notes;
    
    // Constructors
    public GoodsInput() {
        this.inputDate = LocalDateTime.now();
    }
    
    public GoodsInput(Goods goods, Integer quantity, String supplier, String notes) {
        this.goods = goods;
        this.quantity = quantity;
        this.supplier = supplier;
        this.notes = notes;
        this.inputDate = LocalDateTime.now();
    }
    
    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public Goods getGoods() { return goods; }
    public void setGoods(Goods goods) { this.goods = goods; }
    
    public Integer getQuantity() { return quantity; }
    public void setQuantity(Integer quantity) { this.quantity = quantity; }
    
    public LocalDateTime getInputDate() { return inputDate; }
    public void setInputDate(LocalDateTime inputDate) { this.inputDate = inputDate; }
    
    public String getSupplier() { return supplier; }
    public void setSupplier(String supplier) { this.supplier = supplier; }
    
    public String getNotes() { return notes; }
    public void setNotes(String notes) { this.notes = notes; }
}
