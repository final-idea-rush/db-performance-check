package com.database.databaseperformance.repository;

import com.database.databaseperformance.entity.TestData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TestDataRepository extends JpaRepository<TestData, Long> {
}