package com.exadel.financial_service.repository;

import com.exadel.financial_service.model.entity.FinancialOperation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface FinancialOperationRepository extends JpaRepository<FinancialOperation, UUID> {

}
