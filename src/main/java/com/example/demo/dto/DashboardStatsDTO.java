package com.example.demo.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DashboardStatsDTO {
    private Long totalGoods;
    private Long totalInputs;
    private Long totalOutputs;
    private Long totalStaff;
    private Long lowStockItems;
    private Long overstockItems;
    private Double totalInventoryValue;
    private Double monthlyInput;
    private Double monthlyOutput;
    private String healthStatus;
    private String healthColor;
    
    // Custom method to calculate health status
    public void calculateHealthStatus() {
        if (lowStockItems != null && totalGoods != null && totalGoods > 0) {
            double lowStockPercentage = (lowStockItems.doubleValue() / totalGoods.doubleValue()) * 100;
            if (lowStockPercentage <= 5) {
                this.healthStatus = "excellent";
                this.healthColor = "#28a745";
            } else if (lowStockPercentage <= 15) {
                this.healthStatus = "good";
                this.healthColor = "#20c997";
            } else if (lowStockPercentage <= 30) {
                this.healthStatus = "warning";
                this.healthColor = "#ffc107";
            } else {
                this.healthStatus = "critical";
                this.healthColor = "#dc3545";
            }
        } else {
            this.healthStatus = "good";
            this.healthColor = "#20c997";
        }
    }
}
