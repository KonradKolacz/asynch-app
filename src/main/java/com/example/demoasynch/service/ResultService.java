package com.example.demoasynch.service;

import com.example.demoasynch.domain.Result;
import com.example.demoasynch.domain.Status;
import com.example.demoasynch.domain.Task;
import com.example.demoasynch.dto.ResultDto;
import com.example.demoasynch.exception.UnfinishedTaskException;
import com.example.demoasynch.repository.ResultRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ResultService {
    private final ResultRepository resultRepository;
    private final StatusService statusService;
    private final ModelMapper modelMapper;

    @Cacheable(cacheNames = "result", key = "#taskId")
    @Transactional(readOnly = true)
    public ResultDto findByTaskId(Long taskId) {
        return modelMapper.map(resultRepository.findByTaskId(taskId).orElseThrow(UnfinishedTaskException::new), ResultDto.class);
    }

    @Async("asyncTaskExecutor")
    public void findFirstTheBestMatch(Task task) {
        String pattern = task.getPattern();
        int lengthPattern = pattern.length();
        int all = task.getInput().length() - lengthPattern + 1;
        String idStatus = task.getId().toString();
        statusService.add(idStatus, new Status(0, all));

        Integer typos = null;
        Integer position = null;

        for (int i = 0; i < all; i++) {
            takeMoreTime();
            statusService.updateStatus(idStatus);

            Integer localTypos = checkMatch(task.getInput().substring(i, i + lengthPattern), pattern);

            if (i == 0) {
                typos = localTypos;
                position = 0;
                if (typos == 0) {
                    break;
                }
                continue;
            }

            typos = typos < localTypos ? typos : localTypos;
            position = typos < localTypos ? position : i;

            if (typos == 0) {
                break;
            }

        }

        resultRepository.save(new Result(task.getId(), position, typos));
        statusService.removeStatus(idStatus);
    }

    private Integer checkMatch(String input, String pattern) {
        Integer typos = 0;
        if (input.equals(pattern)) {
            return typos;
        }
        for (int i = 0; i < input.length(); i++) {
            if (input.charAt(i) != pattern.charAt(i)) {
                typos++;
            }
        }
        return typos;
    }

    private void takeMoreTime() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }


}
