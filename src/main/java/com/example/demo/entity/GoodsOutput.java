package com.example.demo.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "goods_output")
public class GoodsOutput {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne
    @JoinColumn(name = "goods_id")
    private Goods goods;
    
    private Integer quantity;
    private LocalDateTime outputDate;
    private String customer;
    private String notes;
    
    // Constructors
    public GoodsOutput() {
        this.outputDate = LocalDateTime.now();
    }
    
    public GoodsOutput(Goods goods, Integer quantity, String customer, String notes) {
        this.goods = goods;
        this.quantity = quantity;
        this.customer = customer;
        this.notes = notes;
        this.outputDate = LocalDateTime.now();
    }
    
    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public Goods getGoods() { return goods; }
    public void setGoods(Goods goods) { this.goods = goods; }
    
    public Integer getQuantity() { return quantity; }
    public void setQuantity(Integer quantity) { this.quantity = quantity; }
    
    public LocalDateTime getOutputDate() { return outputDate; }
    public void setOutputDate(LocalDateTime outputDate) { this.outputDate = outputDate; }
    
    public String getCustomer() { return customer; }
    public void setCustomer(String customer) { this.customer = customer; }
    
    public String getNotes() { return notes; }
    public void setNotes(String notes) { this.notes = notes; }
}
