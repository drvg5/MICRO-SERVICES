package com.microstart.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data @AllArgsConstructor
@Schema(name="Response",
        description="Schema for General Response details")
public class ResponseDto {
    @Schema(description = "Status Code of the response",
            example = "200 OK")
    private String statusCode;

    @Schema(description = "Status Message of the response",
            example = "Request processed successfully")
    private String statusMessage;
}
