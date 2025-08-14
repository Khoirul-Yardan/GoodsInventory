package com.example.demo.config;

import com.example.demo.entity.*;
import com.example.demo.repository.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
@RequiredArgsConstructor
@Slf4j
public class DataInitializer implements CommandLineRunner {
    
    private final CategoryRepository categoryRepository;
    private final StorageRepository storageRepository;
    private final StaffRepository staffRepository;
    private final GoodsRepository goodsRepository;
    private final ColorRepository colorRepository;
    
    @Override
    public void run(String... args) throws Exception {
        log.info("Starting data initialization...");
        
        initializeColors();
        initializeCategories();
        initializeStorage();
        initializeStaff();
        initializeGoods();
        
        log.info("Data initialization completed!");
    }
    
    private void initializeColors() {
        if (colorRepository.count() == 0) {
            log.info("Initializing colors...");
            
            Color[] colors = {
                Color.builder().name("Red").hexCode("#FF0000").colorFamily("Primary").description("Bright red color").build(),
                Color.builder().name("Blue").hexCode("#0000FF").colorFamily("Primary").description("Bright blue color").build(),
                Color.builder().name("Green").hexCode("#00FF00").colorFamily("Primary").description("Bright green color").build(),
                Color.builder().name("Black").hexCode("#000000").colorFamily("Neutral").description("Pure black color").build(),
                Color.builder().name("White").hexCode("#FFFFFF").colorFamily("Neutral").description("Pure white color").build(),
                Color.builder().name("Gray").hexCode("#808080").colorFamily("Neutral").description("Medium gray color").build(),
                Color.builder().name("Silver").hexCode("#C0C0C0").colorFamily("Neutral").description("Metallic silver").build()
            };
            
            for (Color color : colors) {
                colorRepository.save(color);
            }
            log.info("Colors initialized: {}", colors.length);
        }
    }
    
    private void initializeCategories() {
        if (categoryRepository.count() == 0) {
            log.info("Initializing categories...");
            
            Category[] categories = {
                Category.builder().name("Electronics").description("Electronic devices and components").build(),
                Category.builder().name("Furniture").description("Office and home furniture").build(),
                Category.builder().name("Stationery").description("Office supplies and stationery").build(),
                Category.builder().name("Tools").description("Hardware tools and equipment").build(),
                Category.builder().name("Clothing").description("Apparel and accessories").build()
            };
            
            for (Category category : categories) {
                categoryRepository.save(category);
            }
            log.info("Categories initialized: {}", categories.length);
        }
    }
    
    private void initializeStorage() {
        if (storageRepository.count() == 0) {
            log.info("Initializing storage locations...");
            
            Storage[] storages = {
                Storage.builder().name("Main Warehouse").location("Building A, Floor 1").capacity(10000).currentStock(0).build(),
                Storage.builder().name("Electronics Storage").location("Building B, Floor 2").capacity(5000).currentStock(0).build(),
                Storage.builder().name("Office Storage").location("Building A, Floor 3").capacity(2000).currentStock(0).build()
            };
            
            for (Storage storage : storages) {
                storageRepository.save(storage);
            }
            log.info("Storage locations initialized: {}", storages.length);
        }
    }
    
    private void initializeStaff() {
        if (staffRepository.count() == 0) {
            log.info("Initializing staff...");
            
            Staff[] staffMembers = {
                Staff.builder().name("John Doe").email("john.doe@company.com").position("Warehouse Manager").phone("+1-555-0101").salary(new BigDecimal("65000.00")).build(),
                Staff.builder().name("Jane Smith").email("jane.smith@company.com").position("Inventory Clerk").phone("+1-555-0102").salary(new BigDecimal("45000.00")).build(),
                Staff.builder().name("Mike Johnson").email("mike.johnson@company.com").position("Stock Supervisor").phone("+1-555-0103").salary(new BigDecimal("55000.00")).build()
            };
            
            for (Staff staff : staffMembers) {
                staffRepository.save(staff);
            }
            log.info("Staff initialized: {}", staffMembers.length);
        }
    }
    
    private void initializeGoods() {
        if (goodsRepository.count() == 0) {
            log.info("Initializing sample goods...");
            
            // Get references
            Color blackColor = colorRepository.findByNameIgnoreCase("Black").orElse(null);
            Color silverColor = colorRepository.findByNameIgnoreCase("Silver").orElse(null);
            
            Goods[] goodsItems = {
                Goods.builder()
                    .name("Dell Laptop 15\"")
                    .description("Dell Inspiron 15 3000 Series, Intel i5, 8GB RAM, 256GB SSD")
                    .quantity(25)
                    .price(new BigDecimal("799.99"))
                    .category("Electronics")
                    .sku("DELL-LAP-001")
                    .color(silverColor)
                    .brand("Dell")
                    .minStockLevel(5)
                    .maxStockLevel(100)
                    .supplier("Dell Technologies")
                    .build(),
                    
                Goods.builder()
                    .name("Office Chair")
                    .description("Ergonomic office chair with lumbar support, black leather")
                    .quantity(40)
                    .price(new BigDecimal("249.99"))
                    .category("Furniture")
                    .sku("FURN-CHR-001")
                    .color(blackColor)
                    .brand("OfficeMax")
                    .material("Leather")
                    .minStockLevel(10)
                    .maxStockLevel(200)
                    .supplier("Office Depot")
                    .build(),
                    
                Goods.builder()
                    .name("Wireless Mouse")
                    .description("Logitech wireless optical mouse, 2.4GHz, black")
                    .quantity(80)
                    .price(new BigDecimal("19.99"))
                    .category("Electronics")
                    .sku("LOGI-MOU-001")
                    .color(blackColor)
                    .brand("Logitech")
                    .minStockLevel(20)
                    .maxStockLevel(400)
                    .supplier("Logitech")
                    .build(),
                    
                Goods.builder()
                    .name("A4 Paper Ream")
                    .description("White copy paper, 500 sheets per ream, 80gsm")
                    .quantity(200)
                    .price(new BigDecimal("8.99"))
                    .category("Stationery")
                    .sku("STAT-PAP-001")
                    .unit("ream")
                    .minStockLevel(50)
                    .maxStockLevel(1000)
                    .supplier("Staples")
                    .build()
            };
            
            for (Goods goods : goodsItems) {
                goodsRepository.save(goods);
            }
            log.info("Sample goods initialized: {}", goodsItems.length);
        }
    }
}
