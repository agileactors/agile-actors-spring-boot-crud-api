package com.agileactors.crud.core.response;

import java.time.LocalDateTime;

public record ApiError(
    LocalDateTime timestamp,
    String message
) {
}