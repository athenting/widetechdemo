package com.example.widetechdemo.dto.request.User;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
public class UpdateUserReq {

    @Schema(description = "userId", required = true)
    @NotNull
    @Pattern(regexp = "\\d+", message = "UserId must be a numeric value")
    @Size(min = 1, max = 16, message = "UserId length must be between 1 and 16 characters")
    private int userId;

    @Schema(description = "username", required = true)
    @Pattern(regexp = "^[\\u4e00-\\u9fa5A-Za-z0-9]{3,20}$", message = "username format incorrect length between 3-20")
    private String username;

    @Schema(description = "email", required = true, example = "aaa@qq.com")
    @Pattern(regexp = "^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+$", message = "email format incorrect")
    private String email;


}
