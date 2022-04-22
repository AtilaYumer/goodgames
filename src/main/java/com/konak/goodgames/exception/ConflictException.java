package com.konak.goodgames.exception;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ConflictException extends RuntimeException {
    public ConflictException(String message) {
        super(message);
    }
}
