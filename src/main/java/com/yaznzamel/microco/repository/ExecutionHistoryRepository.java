package com.yaznzamel.microco.repository;

import com.yaznzamel.microco.model.ExecutionHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ExecutionHistoryRepository extends JpaRepository<ExecutionHistory, UUID> {
    List<ExecutionHistory> findTop10ByOrderByExecutedAtDesc(); 
}
