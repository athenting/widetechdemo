package com.example.widetechdemo.dto.request.User;

import com.example.widetechdemo.constants.Department;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class CreateUserReq {

    @NotBlank(message = "Username cannot be blank")
    @Schema(description = "username", required = true)
    @Pattern(regexp = "^[\\u4e00-\\u9fa5A-Za-z0-9]{3,20}$", message = "username format incorrect length between 3-20")
    private String username;

    @NotBlank(message = "Email cannot be blank")
    @Schema(description = "email", required = true, example = "aaa@qq.com")
    @Pattern(regexp = "^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+$", message = "email format incorrect")
    private String email;

}
