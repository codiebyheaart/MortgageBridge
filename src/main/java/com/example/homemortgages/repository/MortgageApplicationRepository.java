package com.example.homemortgages.repository;

import com.example.homemortgages.domain.MortgageApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MortgageApplicationRepository extends JpaRepository<MortgageApplication, Long> {
    List<MortgageApplication> findByStatus(String status);
}
