package com.example.demoasynch.repository;

import com.example.demoasynch.domain.Result;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ResultRepository extends JpaRepository<Result, Long> {

    Optional<Result> findByTaskId(Long taskId);
}
