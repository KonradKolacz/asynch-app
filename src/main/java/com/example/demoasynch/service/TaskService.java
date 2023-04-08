package com.example.demoasynch.service;

import com.example.demoasynch.command.TaskCommand;
import com.example.demoasynch.domain.Task;
import com.example.demoasynch.dto.TaskDto;
import com.example.demoasynch.exception.BadInputLengthException;
import com.example.demoasynch.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskService {
    private final TaskRepository taskRepository;
    private final ModelMapper modelMapper;
    private final ResultService resultService;

    @Transactional
    public TaskDto addTask(TaskCommand taskCommand) {
        validTask(taskCommand);
        Task task = taskRepository.save(modelMapper.map(taskCommand, Task.class));
        resultService.findFirstTheBestMatch(task);
        return modelMapper.map(task, TaskDto.class);
    }

    @Transactional(readOnly = true)
    public List<TaskDto> findAll() {
        return taskRepository.findAll().stream().map(task -> modelMapper.map(task, TaskDto.class)).toList();
    }

    private void validTask(TaskCommand taskCommand) {
        if (taskCommand.getPattern().length() > taskCommand.getInput().length()) {
            throw new BadInputLengthException();
        }
    }
}
