package com.example.widetechdemo.dto.request.Employee;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class QueryEmployeeReq {

    @Schema(description = "userId", required = true)
    @NotNull
    @Pattern(regexp = "\\d+", message = "id must be a numeric value")
    @Size(min = 1, max = 16, message = "id length must be between 1 and 16 characters")
    private int id;
}
