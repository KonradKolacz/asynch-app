package com.example.demoasynch.controller;

import com.example.demoasynch.command.TaskCommand;
import com.example.demoasynch.dto.ResultDto;
import com.example.demoasynch.dto.TaskDto;
import com.example.demoasynch.service.ResultService;
import com.example.demoasynch.service.StatusService;
import com.example.demoasynch.service.TaskService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasks")
@RequiredArgsConstructor
public class TaskController {
    private final TaskService taskService;
    private final StatusService statusService;
    private final ResultService resultService;

    @PostMapping
    public ResponseEntity<TaskDto> addTask(@RequestBody @Valid TaskCommand taskCommand) {
        return new ResponseEntity<>(taskService.addTask(taskCommand), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<TaskDto>> findAll() {
        return new ResponseEntity<>(taskService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}/status")
    public ResponseEntity<Integer> getStatus(@PathVariable("id") Long id) {
        return new ResponseEntity<>(statusService.getStatus(id.toString()), HttpStatus.OK);
    }

    @GetMapping("/{id}/result")
    public ResponseEntity<ResultDto> getResult(@PathVariable("id") Long id) {
        return new ResponseEntity<>(resultService.findByTaskId(id), HttpStatus.OK);
    }
}
