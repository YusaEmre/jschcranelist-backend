package com.jchcranelist.controller;

import com.jchcranelist.model.WorkingStatus;
import com.jchcranelist.payload.request.WorkingStatusListSaveRequest;
import com.jchcranelist.service.WorkingStatusService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/workingstatus")
@RequiredArgsConstructor
public class WorkingStatusController {
    private final WorkingStatusService workingStatusService;




    @PostMapping("/save")
    public void saveWorkingStatus(@RequestBody WorkingStatus workingStatus){
        workingStatusService.saveWorkingStatus(workingStatus);
    }

    @PostMapping("/saveAll")
    public void saveAllWorkingStatus(@RequestBody WorkingStatusListSaveRequest workingStatusListSaveRequest){
        workingStatusService.saveAllWorkingStatus(workingStatusListSaveRequest);
    }
    @GetMapping
    public List<WorkingStatus> getWorkingStatus(){
        return workingStatusService.getWorkingStatus();
    }

    @DeleteMapping("/delete/id/{statusId}")
    public void deleteWorkingStatus(@PathVariable Long statusId){
        workingStatusService.deleteWorkingStatus(statusId);
    }

}

