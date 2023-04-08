package com.example.demoasynch.exception;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class NotStartedOrCompletedTaskException extends RuntimeException {

    public String getMessage() {
        return "The task is not started or it is completed.";
    }
}
