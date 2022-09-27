package com.jchcranelist.controller;

import com.jchcranelist.model.WorkingStatus;
import com.jchcranelist.service.WorkingStatusService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController("/api/workingstatus")
@RequiredArgsConstructor
public class WorkingStatusController {
    private final WorkingStatusService workingStatusService;




    @PostMapping("/api/workingstatus/save")
    public void saveWorkingStatus(@RequestBody WorkingStatus workingStatus){
        workingStatusService.saveWorkingStatus(workingStatus);
    }
    @GetMapping
    public List<WorkingStatus> getWorkingStatus(){
        return workingStatusService.getWorkingStatus();
    }
}

