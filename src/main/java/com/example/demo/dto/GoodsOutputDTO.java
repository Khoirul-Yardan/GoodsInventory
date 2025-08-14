package com.example.demo.dto;

public class GoodsOutputDTO {
    private Long goodsId;
    private Integer quantity;
    private String customer;
    private String notes;
    
    // Constructors
    public GoodsOutputDTO() {}
    
    public GoodsOutputDTO(Long goodsId, Integer quantity, String customer, String notes) {
        this.goodsId = goodsId;
        this.quantity = quantity;
        this.customer = customer;
        this.notes = notes;
    }
    
    // Getters and Setters
    public Long getGoodsId() { return goodsId; }
    public void setGoodsId(Long goodsId) { this.goodsId = goodsId; }
    
    public Integer getQuantity() { return quantity; }
    public void setQuantity(Integer quantity) { this.quantity = quantity; }
    
    public String getCustomer() { return customer; }
    public void setCustomer(String customer) { this.customer = customer; }
    
    public String getNotes() { return notes; }
    public void setNotes(String notes) { this.notes = notes; }
}
