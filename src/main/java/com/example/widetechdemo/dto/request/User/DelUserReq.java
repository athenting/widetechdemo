package com.example.widetechdemo.dto.request.User;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class DelUserReq {

    @Schema(description = "userId", required = true)
    @NotNull
    @Pattern(regexp = "\\d+", message = "UserId must be a numeric value")
    @Size(min = 1, max = 16, message = "UserId length must be between 1 and 16 characters")
    private int userId;

    // 构造方法、getter 和 setter 方法
}
