package com.example.demo.controller;

import com.example.demo.entity.Color;
import com.example.demo.repository.ColorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/Color")
@CrossOrigin(origins = "*")
public class ColorController {
    
    @Autowired
    private ColorRepository colorRepository;
    
    // Get all colors
    @GetMapping
    public List<Color> getAllColors() {
        return colorRepository.findByIsActiveTrueOrderByName();
    }
    
    // Get color by ID
    @GetMapping("/{id}")
    public ResponseEntity<Color> getColorById(@PathVariable Long id) {
        Optional<Color> color = colorRepository.findById(id);
        return color.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }
    
    // Create new color
    @PostMapping
    public Color createColor(@RequestBody Color color) {
        return colorRepository.save(color);
    }
    
    // Update color
    @PutMapping("/{id}")
    public ResponseEntity<Color> updateColor(@PathVariable Long id, @RequestBody Color colorDetails) {
        Optional<Color> optionalColor = colorRepository.findById(id);
        if (optionalColor.isPresent()) {
            Color color = optionalColor.get();
            color.setName(colorDetails.getName());
            color.setHexCode(colorDetails.getHexCode());
            color.setColorFamily(colorDetails.getColorFamily());
            color.setDescription(colorDetails.getDescription());
            color.setIsActive(colorDetails.getIsActive());
            return ResponseEntity.ok(colorRepository.save(color));
        }
        return ResponseEntity.notFound().build();
    }
    
    // Delete color
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteColor(@PathVariable Long id) {
        if (colorRepository.existsById(id)) {
            colorRepository.deleteById(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
    
    // Get color families
    @GetMapping("/families")
    public List<String> getColorFamilies() {
        return colorRepository.findAllColorFamilies();
    }
    
    // Search colors
    @GetMapping("/search")
    public List<Color> searchColors(@RequestParam String q) {
        return colorRepository.searchByName(q);
    }
}
