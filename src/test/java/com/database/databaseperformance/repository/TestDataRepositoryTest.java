package com.database.databaseperformance.repository;

import com.database.databaseperformance.entity.Shop;
import com.database.databaseperformance.entity.TestData;
import jakarta.persistence.EntityManager;
import org.hibernate.Session;
import org.hibernate.stat.Statistics;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("쓰기 전용")
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class TestDataRepositoryTest {

    @Autowired
    private EntityManager entityManager;

    @Autowired
    private TestDataRepository testDataRepository;

    @DisplayName("1만건 데이터")
    @Test
    void insertData10000() {
        Session session = entityManager.unwrap(Session.class);
        session.getSessionFactory().getStatistics().setStatisticsEnabled(true);

        List<TestData> testData = getData(10000);

        long startTime = System.currentTimeMillis();
        for (TestData test : testData) {
            testDataRepository.save(test);
        }
        long endTime = System.currentTimeMillis();

        // performance check
        long executionTime = endTime - startTime;
        System.out.println("Execution time for findAll: " + executionTime + "ms");

        Statistics stats = session.getSessionFactory().getStatistics();
        double queryExecutionCount = stats.getQueryExecutionCount();
        System.out.println("Number of Queries Executed: " + queryExecutionCount);
    }

    @DisplayName("10만건 데이터")
    @Test
    void insertData100000() {
        Session session = entityManager.unwrap(Session.class);
        session.getSessionFactory().getStatistics().setStatisticsEnabled(true);

        List<TestData> testData = getData(100000);

        long startTime = System.currentTimeMillis();
        for (TestData test : testData) {
            testDataRepository.save(test);
        }
        long endTime = System.currentTimeMillis();

        // performance check
        long executionTime = endTime - startTime;
        System.out.println("Execution time for findAll: " + executionTime + "ms");

        Statistics stats = session.getSessionFactory().getStatistics();
        double queryExecutionCount = stats.getQueryExecutionCount();
        System.out.println("Number of Queries Executed: " + queryExecutionCount);
    }

    @DisplayName("100만건 데이터")
    @Test
    void insertData10000000() {
        Session session = entityManager.unwrap(Session.class);
        session.getSessionFactory().getStatistics().setStatisticsEnabled(true);

        List<TestData> testData = getData(1000000);

        long startTime = System.currentTimeMillis();
        for (TestData test : testData) {
            testDataRepository.save(test);
        }
        long endTime = System.currentTimeMillis();

        // performance check
        long executionTime = endTime - startTime;
        System.out.println("Execution time for findAll: " + executionTime + "ms");

        Statistics stats = session.getSessionFactory().getStatistics();
        double queryExecutionCount = stats.getQueryExecutionCount();
        System.out.println("Number of Queries Executed: " + queryExecutionCount);
    }

    private List<TestData> getData(int size) {
        List<TestData> data = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            data.add(TestData.builder()
                    .title("제목 : "+i)
                    .name("이름 : "+i)
                    .content("내용 : "+i)
                    .build());
        }
        return data;
    }

}