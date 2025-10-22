package com.microstart.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Data @AllArgsConstructor
@Schema(name="ErrorResponse",
        description="Schema for Error Response details")
public class ErrorResonseDto {
    @Schema(description = "API Path where the error occurred",
            example = "/api/create")
    private String apiPath;

    @Schema(description = "HTTP Status Code of the error",
            example = "500")
    private HttpStatus errorCode;

    @Schema(description = "Detailed error message",
            example = "Internal Server Error occurred while processing the request")
    private String errorMessage;

    @Schema(description = "Timestamp when the error occurred",
            example = "2024-01-01T12:00:00")
    private LocalDateTime errorTime;
}
