package com.example.widetechdemo.dto.request.Employee;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class UpdateEmployeeReq {

    @Schema(description = "userId", required = true)
    @NotNull
    @Pattern(regexp = "\\d+", message = "id must be a numeric value")
    @Size(min = 1, max = 16, message = "id length must be between 1 and 16 characters")
    private int id;

    @Schema(description = "name", required = true)
    @Pattern(regexp = "^[\\u4e00-\\u9fa5A-Za-z0-9]{3,20}$", message = "name format incorrect length between 3-20")
    private String name;

    @Size(min = 1, max = 32, message = "position length must be between 1 and 32 characters")
    private String position;

    @Pattern(regexp = "A0[0-3]", message = "Invalid department code. It should be A00, A01, A02, or A03.")
    private String dept;
}
