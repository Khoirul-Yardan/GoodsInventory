package com.example.demo.dto;

public class GoodsInputDTO {
    private Long goodsId;
    private Integer quantity;
    private String supplier;
    private String notes;
    
    // Constructors
    public GoodsInputDTO() {}
    
    public GoodsInputDTO(Long goodsId, Integer quantity, String supplier, String notes) {
        this.goodsId = goodsId;
        this.quantity = quantity;
        this.supplier = supplier;
        this.notes = notes;
    }
    
    // Getters and Setters
    public Long getGoodsId() { return goodsId; }
    public void setGoodsId(Long goodsId) { this.goodsId = goodsId; }
    
    public Integer getQuantity() { return quantity; }
    public void setQuantity(Integer quantity) { this.quantity = quantity; }
    
    public String getSupplier() { return supplier; }
    public void setSupplier(String supplier) { this.supplier = supplier; }
    
    public String getNotes() { return notes; }
    public void setNotes(String notes) { this.notes = notes; }
}