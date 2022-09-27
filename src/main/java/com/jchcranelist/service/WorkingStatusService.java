package com.jchcranelist.service;

import com.jchcranelist.repository.WorkingStatusRepository;
import com.jchcranelist.model.WorkingStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class WorkingStatusService {
    private final WorkingStatusRepository workingStatusRepository;



    public void saveWorkingStatus(WorkingStatus workingStatus){
        workingStatusRepository.save(workingStatus);
    }

    public List<WorkingStatus> getWorkingStatus() {
       return workingStatusRepository.findAll();
    }
}
