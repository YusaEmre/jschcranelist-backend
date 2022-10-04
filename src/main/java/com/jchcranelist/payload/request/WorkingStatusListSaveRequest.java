package com.jchcranelist.payload.request;

import com.jchcranelist.model.WorkingStatus;

import java.util.List;

public class WorkingStatusListSaveRequest {
    List<WorkingStatus> workingStatusList;
    List<WorkingStatus> deletedWorkingStatusList;


    public WorkingStatusListSaveRequest(List<WorkingStatus> workingStatusList, List<WorkingStatus> deletedWorkingStatusList) {
        this.workingStatusList = workingStatusList;
        this.deletedWorkingStatusList = deletedWorkingStatusList;
    }

    public WorkingStatusListSaveRequest() {
    }

    public List<WorkingStatus> getWorkingStatusList() {
        return workingStatusList;
    }

    public void setWorkingStatusList(List<WorkingStatus> workingStatusList) {
        this.workingStatusList = workingStatusList;
    }

    public List<WorkingStatus> getDeletedWorkingStatusList() {
        return deletedWorkingStatusList;
    }

    public void setDeletedWorkingStatusList(List<WorkingStatus> deletedWorkingStatusList) {
        this.deletedWorkingStatusList = deletedWorkingStatusList;
    }
}
