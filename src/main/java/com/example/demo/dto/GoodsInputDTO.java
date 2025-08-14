package com.example.demo.dto;

import lombok.*;
import java.time.LocalDateTime;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GoodsInputDTO {
    private Long goodsId;
    private String goodsName;
    private Integer quantity;
    private Double unitPrice;
    private Double totalPrice;
    private String supplier;
    private String supplierInvoice;
    private String batchNumber;
    private LocalDate expiryDate;
    private String notes;
    @Builder.Default
    private String status = "pending";
    @Builder.Default
    private String urgency = "normal";
    private String color;
    @Builder.Default
    private LocalDateTime inputDate = LocalDateTime.now();
    private Long staffId;
    private String staffName;
    
    // Custom method to get status color
    public String getStatusColor() {
        return switch (this.status != null ? this.status.toLowerCase() : "pending") {
            case "pending" -> "#ffc107";
            case "received" -> "#17a2b8";
            case "verified" -> "#20c997";
            case "stored" -> "#28a745";
            default -> "#6c757d";
        };
    }
}