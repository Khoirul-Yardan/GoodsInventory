package com.example.demo.repository;

import com.example.demo.entity.Color;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ColorRepository extends JpaRepository<Color, Long> {

    // Find by name
    Optional<Color> findByNameIgnoreCase(String name);

    // Find by hex code
    Optional<Color> findByHexCode(String hexCode);

    // Find active colors
    List<Color> findByIsActiveTrueOrderByName();

    // Find by color family
    List<Color> findByColorFamilyIgnoreCaseAndIsActiveTrue(String colorFamily);

    // Find all color families
    @Query("SELECT DISTINCT c.colorFamily FROM Color c WHERE c.isActive = true ORDER BY c.colorFamily")
    List<String> findAllColorFamilies();

    // Search colors by name
    @Query("SELECT c FROM Color c WHERE LOWER(c.name) LIKE LOWER(CONCAT('%', :searchTerm, '%')) AND c.isActive = true")
    List<Color> searchByName(String searchTerm);
}
