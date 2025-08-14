package com.example.demo.dto;

import lombok.*;
import java.time.LocalDateTime;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GoodsOutputDTO {
    private Long goodsId;
    private String goodsName;
    private Integer quantity;
    private Double unitPrice;
    private Double totalPrice;
    private String customer;
    private String customerContact;
    private String invoiceNumber;
    private String deliveryAddress;
    private String notes;
    @Builder.Default
    private String status = "pending";
    @Builder.Default
    private String priority = "normal";
    private String color;
    @Builder.Default
    private LocalDateTime outputDate = LocalDateTime.now();
    private LocalDate deliveryDate;
    private Long staffId;
    private String staffName;
    
    // Custom method to get status color
    public String getStatusColor() {
        return switch (this.status != null ? this.status.toLowerCase() : "pending") {
            case "pending" -> "#ffc107";
            case "shipped" -> "#17a2b8";
            case "delivered" -> "#28a745";
            case "cancelled" -> "#dc3545";
            default -> "#6c757d";
        };
    }
}
