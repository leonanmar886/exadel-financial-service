package com.exadel.financial_service.configuration.exception;

import java.util.List;

public record ErrorResponse(
        int code,
        List<String> errors) {
}
