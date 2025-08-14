package com.example.demo.controller;

import com.example.demo.entity.Storage;
import com.example.demo.repository.StorageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/Storage")
@CrossOrigin(origins = "*")
public class StorageController {
    
    @Autowired
    private StorageRepository storageRepository;
    
    @GetMapping
    public List<Storage> getAllStorage() {
        return storageRepository.findAll();
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Storage> getStorageById(@PathVariable Long id) {
        Optional<Storage> storage = storageRepository.findById(id);
        return storage.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }
    
    @PostMapping
    public Storage createStorage(@RequestBody Storage storage) {
        return storageRepository.save(storage);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Storage> updateStorage(@PathVariable Long id, @RequestBody Storage storageDetails) {
        Optional<Storage> optionalStorage = storageRepository.findById(id);
        if (optionalStorage.isPresent()) {
            Storage storage = optionalStorage.get();
            storage.setName(storageDetails.getName());
            storage.setLocation(storageDetails.getLocation());
            storage.setCapacity(storageDetails.getCapacity());
            storage.setCurrentStock(storageDetails.getCurrentStock());
            return ResponseEntity.ok(storageRepository.save(storage));
        }
        return ResponseEntity.notFound().build();
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStorage(@PathVariable Long id) {
        if (storageRepository.existsById(id)) {
            storageRepository.deleteById(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
    
    // Additional endpoints
    @GetMapping("/capacity")
    public List<Storage> getStorageByCapacity(@RequestParam Integer minCapacity) {
        return storageRepository.findByCapacityGreaterThanEqual(minCapacity);
    }
    
    @GetMapping("/search")
    public List<Storage> searchStorage(@RequestParam String name) {
        return storageRepository.findByNameContainingIgnoreCase(name);
    }
}
