package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "goods_output")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString(exclude = {"goods", "staff"}) // Prevent circular references
public class GoodsOutput {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "goods_id", nullable = false)
    private Goods goods;
    
    @Column(name = "quantity", nullable = false)
    private Integer quantity;
    
    @Column(name = "unit_price", precision = 10, scale = 2)
    private BigDecimal unitPrice;
    
    @Column(name = "total_price", precision = 10, scale = 2)
    private BigDecimal totalPrice;
    
    @Column(name = "customer")
    private String customer;
    
    @Column(name = "customer_contact")
    private String customerContact;
    
    @Column(name = "invoice_number")
    private String invoiceNumber;
    
    @Column(name = "delivery_address")
    private String deliveryAddress;
    
    @Column(name = "notes")
    private String notes;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "staff_id")
    private Staff staff;
    
    @Column(name = "output_date")
    @Builder.Default
    private LocalDateTime outputDate = LocalDateTime.now();
    
    @Column(name = "delivery_date")
    private LocalDate deliveryDate;
    
    @Column(name = "status")
    @Builder.Default
    private String status = "pending";
    
    @Column(name = "created_at")
    @Builder.Default
    private LocalDateTime createdAt = LocalDateTime.now();
    
    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
        if (this.outputDate == null) {
            this.outputDate = LocalDateTime.now();
        }
        if (this.status == null) {
            this.status = "pending";
        }
        // Auto-calculate total price
        if (this.quantity != null && this.unitPrice != null) {
            this.totalPrice = this.unitPrice.multiply(BigDecimal.valueOf(this.quantity));
        }
    }
}
