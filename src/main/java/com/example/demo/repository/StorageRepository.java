package com.example.demo.repository;

import com.example.demo.entity.Storage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StorageRepository extends JpaRepository<Storage, Long> {
    List<Storage> findByNameContainingIgnoreCase(String name);

    List<Storage> findByLocationContainingIgnoreCase(String location);

    List<Storage> findByCapacityGreaterThanEqual(Integer capacity);

    List<Storage> findByCurrentStockLessThan(Integer stock);

    @Query("SELECT s FROM Storage s WHERE s.currentStock < s.capacity * 0.9")
    List<Storage> findStorageWithAvailableSpace();

    @Query("SELECT s FROM Storage s ORDER BY (s.capacity - s.currentStock) DESC")
    List<Storage> findStorageOrderByAvailableSpace();
}
