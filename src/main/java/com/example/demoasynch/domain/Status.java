package com.example.demoasynch.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Status {
    private Integer now;
    private Integer all;

    public void increase() {
        this.now++;
    }

    public Integer getStatus() {
        return now * 100 / all;
    }

}
