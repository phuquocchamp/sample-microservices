package com.phuquocchamp.loansservice.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Schema(
        name = "ErrorResponse",
        description = "Schema to hold error response information"
)
@Data
@AllArgsConstructor
public class ErrorResponseDto {
    @Schema(
            description = "API path that caused the error"
    )
    private String apiPath;

    @Schema(
            description = "HTTP status code of the error"
    )
    private HttpStatus errorCode;

    @Schema(
            description = "Error message"
    )
    private String errorMessage;

    @Schema(
            description = "Time of the error"
    )
    private LocalDateTime errorTime;
}
