package com.jchcranelist.service;

import com.jchcranelist.payload.request.WorkingStatusListSaveRequest;
import com.jchcranelist.repository.WorkingStatusRepository;
import com.jchcranelist.model.WorkingStatus;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class WorkingStatusService {
    private final WorkingStatusRepository workingStatusRepository;



    public void saveWorkingStatus(WorkingStatus workingStatus){
        workingStatusRepository.save(workingStatus);
    }

    public List<WorkingStatus> getWorkingStatus() {
       return workingStatusRepository.findAll();
    }

    public void deleteWorkingStatus(Long statusId) {
        workingStatusRepository.deleteById(statusId);
    }

    public void saveAllWorkingStatus(WorkingStatusListSaveRequest workingStatusListSaveRequest) {
        try {
            log.info("Service called: saveAllWorkingStatus");
            workingStatusRepository.deleteAll(workingStatusListSaveRequest.getDeletedWorkingStatusList());
            workingStatusRepository.saveAllAndFlush(workingStatusListSaveRequest.getWorkingStatusList());
        }catch (Exception e){
            log.error(e.getMessage());
            throw new IllegalStateException("This status already in use");
        }
    }
}
