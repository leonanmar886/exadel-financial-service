package com.exadel.financial_service.service.financial_operation;

import com.exadel.financial_service.model.dto.request.CreationTransferRequestDTO;

public interface FinancialOperationsService {
    void transfer(CreationTransferRequestDTO request);
}
