package com.example.demo.config;

import com.example.demo.entity.*;
import com.example.demo.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {
    
    @Autowired
    private CategoryRepository categoryRepository;
    
    @Autowired
    private StorageRepository storageRepository;
    
    @Autowired
    private StaffRepository staffRepository;
    
    @Autowired
    private GoodsRepository goodsRepository;
    
    @Override
    public void run(String... args) throws Exception {
        // Initialize categories if empty
        if (categoryRepository.count() == 0) {
            categoryRepository.save(new Category("Electronics", "Electronic devices and components"));
            categoryRepository.save(new Category("Office Supplies", "Office equipment and supplies"));
            categoryRepository.save(new Category("Furniture", "Office and warehouse furniture"));
        }
        
        // Initialize storage if empty
        if (storageRepository.count() == 0) {
            storageRepository.save(new Storage("Warehouse A", "Main warehouse building", "Primary storage facility", 1000));
            storageRepository.save(new Storage("Warehouse B", "Secondary warehouse", "Overflow storage", 500));
        }
        
        // Initialize staff if empty
        if (staffRepository.count() == 0) {
            staffRepository.save(new Staff("John Doe", "john@example.com", "Manager", "123-456-7890"));
            staffRepository.save(new Staff("Jane Smith", "jane@example.com", "Warehouse Staff", "123-456-7891"));
        }
        
        // Initialize sample goods if empty
        if (goodsRepository.count() == 0) {
            Category electronics = categoryRepository.findAll().get(0);
            Storage warehouseA = storageRepository.findAll().get(0);
            
            Goods laptop = new Goods("Laptop", "Dell Inspiron 15", 10, 799.99);
            laptop.setCategory(electronics);
            laptop.setStorage(warehouseA);
            laptop.setSku("LAP001");
            laptop.setMinStock(5);
            goodsRepository.save(laptop);
            
            Goods mouse = new Goods("Wireless Mouse", "Logitech MX Master", 25, 99.99);
            mouse.setCategory(electronics);
            mouse.setStorage(warehouseA);
            mouse.setSku("MOU001");
            mouse.setMinStock(10);
            goodsRepository.save(mouse);
        }
    }
}
