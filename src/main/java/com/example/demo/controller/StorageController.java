package com.example.demo.controller;

import com.example.demo.entity.Storage;
import com.example.demo.repository.StorageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/storage")
@CrossOrigin(origins = "*")
public class StorageController {
    
    @Autowired
    private StorageRepository storageRepository;
    
    @GetMapping
    public List<Storage> getAllStorage() {
        return storageRepository.findAll();
    }
    
    @GetMapping("/{id}")
    public Optional<Storage> getStorageById(@PathVariable Long id) {
        return storageRepository.findById(id);
    }
    
    @PostMapping
    public Storage createStorage(@RequestBody Storage storage) {
        return storageRepository.save(storage);
    }
    
    @PutMapping("/{id}")
    public Storage updateStorage(@PathVariable Long id, @RequestBody Storage storageDetails) {
        Optional<Storage> optionalStorage = storageRepository.findById(id);
        if (optionalStorage.isPresent()) {
            Storage storage = optionalStorage.get();
            storage.setName(storageDetails.getName());
            storage.setLocation(storageDetails.getLocation());
            storage.setDescription(storageDetails.getDescription());
            storage.setCapacity(storageDetails.getCapacity());
            return storageRepository.save(storage);
        }
        return null;
    }
    
    @DeleteMapping("/{id}")
    public void deleteStorage(@PathVariable Long id) {
        storageRepository.deleteById(id);
    }
}
