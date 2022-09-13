package com.upwork.upworkbackend.service;

import com.upwork.upworkbackend.model.WorkingStatus;
import com.upwork.upworkbackend.repository.WorkingStatusRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WorkingStatusService {
    private final WorkingStatusRepository workingStatusRepository;



    public void saveWorkingStatus(WorkingStatus workingStatus){
        workingStatusRepository.save(workingStatus);
    }
}
