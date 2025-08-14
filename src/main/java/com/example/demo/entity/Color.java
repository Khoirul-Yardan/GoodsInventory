package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;
import java.time.LocalDateTime;

@Entity
@Table(name = "color")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Color {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "name", nullable = false, unique = true)
    private String name;
    
    @Column(name = "hex_code", nullable = false)
    private String hexCode;
    
    @Column(name = "rgb_code")
    private String rgbCode;
    
    @Column(name = "color_family")
    private String colorFamily;
    
    @Column(name = "description")
    private String description;
    
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
    
    // Custom method to convert hex to RGB
    public void setHexCode(String hexCode) {
        this.hexCode = hexCode;
        this.rgbCode = hexToRgb(hexCode);
    }
    
    private String hexToRgb(String hex) {
        if (hex == null || !hex.startsWith("#") || hex.length() != 7) {
            return null;
        }
        try {
            int r = Integer.parseInt(hex.substring(1, 3), 16);
            int g = Integer.parseInt(hex.substring(3, 5), 16);
            int b = Integer.parseInt(hex.substring(5, 7), 16);
            return String.format("rgb(%d, %d, %d)", r, g, b);
        } catch (NumberFormatException e) {
            return null;
        }
    }
}
