package com.example.demo.repository;

import com.example.demo.entity.GoodsInput;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GoodsInputRepository extends JpaRepository<GoodsInput, Long> {
}
