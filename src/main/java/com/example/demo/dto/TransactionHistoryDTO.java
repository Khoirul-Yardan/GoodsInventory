package com.example.demo.dto;

import lombok.*;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TransactionHistoryDTO {
    private Long id;
    private String transactionType; // INPUT, OUTPUT
    private Long goodsId;
    private String goodsName;
    private Integer quantity;
    private Double unitPrice;
    private Double totalPrice;
    private String party; // supplier or customer
    private String partyContact;
    private String invoiceNumber;
    private String notes;
    private String status;
    private String color;
    private LocalDateTime transactionDate;
    private String staffName;
    private String priority;
    
    // Custom method to get transaction color
    public String getTransactionColor() {
        return switch (this.transactionType != null ? this.transactionType.toUpperCase() : "INPUT") {
            case "INPUT" -> "#28a745";  // Green for incoming
            case "OUTPUT" -> "#dc3545"; // Red for outgoing
            default -> "#6c757d";       // Gray
        };
    }
}
