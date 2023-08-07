package com.database.databaseperformance.repository;

import com.database.databaseperformance.entity.Shop;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ShopRepository extends JpaRepository<Shop, Long> {

    List<Shop> findByStatus(String status);

    List<Shop> findByNameContains(String name);

}