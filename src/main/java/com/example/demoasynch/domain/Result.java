package com.example.demoasynch.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

@Entity
@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Result {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long taskId;
    private Integer position;
    private Integer typos;

    public Result(Long taskId, Integer position, Integer typos) {
        this.taskId = taskId;
        this.position = position;
        this.typos = typos;
    }
}
