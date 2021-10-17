package com.epsychiatry.pojo;

public class FacebookSaveDataInfo {
    private Integer totalNumberOfSavedInsights;

    public FacebookSaveDataInfo() {
    }

    public FacebookSaveDataInfo(Integer totalNumberOfSavedInsights) {
        this.totalNumberOfSavedInsights = totalNumberOfSavedInsights;
    }

    public Integer getTotalNumberOfSavedInsights() {
        return totalNumberOfSavedInsights;
    }

    public void setTotalNumberOfSavedInsights(Integer totalNumberOfSavedInsights) {
        this.totalNumberOfSavedInsights = totalNumberOfSavedInsights;
    }
}
