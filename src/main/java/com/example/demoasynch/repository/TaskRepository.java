package com.example.demoasynch.repository;

import com.example.demoasynch.domain.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Long> {
}
