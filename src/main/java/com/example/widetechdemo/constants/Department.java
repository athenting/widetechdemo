package com.example.widetechdemo.constants;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Department {

    UNDEFINED("A00","undefined"),

    MARKETING("A01","marketing"),

    TECHNOLOGY("A02","technology"),

    ADMIN("A03","Administration and human resource");


    /**
     * code
     */
    private final String code;

    /**
     * msg
     */
    private final String message;

}
