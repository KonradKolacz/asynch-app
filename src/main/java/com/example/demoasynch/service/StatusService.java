package com.example.demoasynch.service;

import com.example.demoasynch.domain.Status;
import com.example.demoasynch.exception.NotStartedOrCompletedTaskException;
import org.springframework.stereotype.Service;

import java.util.concurrent.ConcurrentHashMap;

@Service
public class StatusService {
    private final ConcurrentHashMap<String, Status> statusMap;

    public StatusService() {
        this.statusMap = new ConcurrentHashMap<>();
    }

    public void add(String key, Status status) {
        statusMap.put(key, status);
    }

    public void updateStatus(String key) {
        statusMap.get(key).increase();
    }

    public Integer getStatus(String key) {
        Status status = statusMap.get(key);
        if (status == null) {
            throw new NotStartedOrCompletedTaskException();
        }
        return status.getStatus();
    }

    public void removeStatus(String id) {
        statusMap.remove(id);
    }
}
