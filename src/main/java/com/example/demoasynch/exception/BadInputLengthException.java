package com.example.demoasynch.exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class BadInputLengthException extends RuntimeException {

    public String getMessage() {
        return "Input can't be shorter than pattern!";
    }

}
