package com.database.databaseperformance.repository;

import com.database.databaseperformance.entity.Shop;
import jakarta.persistence.EntityManager;
import org.hibernate.Session;
import org.hibernate.stat.Statistics;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("읽기 전용 테스트")
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class ShopRepositoryTest {


    @Autowired
    private EntityManager entityManager;

    @Autowired
    private ShopRepository shopRepository;

    @Test
    @DisplayName("전체 조회")
    public void testFindAll() {
        Session session = entityManager.unwrap(Session.class);
        session.getSessionFactory().getStatistics().setStatisticsEnabled(true);

        long startTime = System.currentTimeMillis();
        List<Shop> shops = shopRepository.findAll();
        long endTime = System.currentTimeMillis();

        // performance check
        long executionTime = endTime - startTime;
        System.out.println("Execution time for findAll: " + executionTime + "ms");

        Statistics stats = session.getSessionFactory().getStatistics();
        double queryExecutionCount = stats.getQueryExecutionCount();
        System.out.println("Number of Queries Executed: " + queryExecutionCount);
    }

    @Test
    @DisplayName("단건 조회")
    void testFindOne() {
        String status= "폐업";
        Session session = entityManager.unwrap(Session.class);
        session.getSessionFactory().getStatistics().setStatisticsEnabled(true);

        long startTime = System.currentTimeMillis();
        List<Shop> shops = shopRepository.findByStatus(status);
        long endTime = System.currentTimeMillis();

        // performance check
        long executionTime = endTime - startTime;
        System.out.println("Execution time for findAll: " + executionTime + "ms");

        Statistics stats = session.getSessionFactory().getStatistics();
        double queryExecutionCount = stats.getQueryExecutionCount();
        System.out.println("Number of Queries Executed: " + queryExecutionCount);
    }

    @Test
    @DisplayName("like 조회")
    void testFindContainsOne() {
        String name = "술";
        Session session = entityManager.unwrap(Session.class);
        session.getSessionFactory().getStatistics().setStatisticsEnabled(true);

        long startTime = System.currentTimeMillis();
        List<Shop> shops = shopRepository.findByNameContains(name);
        long endTime = System.currentTimeMillis();

        // performance check
        long executionTime = endTime - startTime;
        System.out.println("Execution time for findAll: " + executionTime + "ms");

        Statistics stats = session.getSessionFactory().getStatistics();
        double queryExecutionCount = stats.getQueryExecutionCount();
        System.out.println("Number of Queries Executed: " + queryExecutionCount);
    }

}
