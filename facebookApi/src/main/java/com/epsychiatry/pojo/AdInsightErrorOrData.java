package com.epsychiatry.pojo;

import com.epsychiatry.model.facebook.AdInsightsAgeGender;

import java.util.List;

public class AdInsightErrorOrData {
    private ErrorPojo error;
    private List<AdInsightsAgeGender> adInsightsAgeGenderList;
    private FBPaging paging;

    public AdInsightErrorOrData(ErrorPojo error, List<AdInsightsAgeGender> adInsightsAgeGenderList) {
        this.error = error;
        this.adInsightsAgeGenderList = adInsightsAgeGenderList;
    }

    public AdInsightErrorOrData(ErrorPojo error, List<AdInsightsAgeGender> adInsightsAgeGenderList, FBPaging paging) {
        this.error = error;
        this.adInsightsAgeGenderList = adInsightsAgeGenderList;
        this.paging = paging;
    }

    public ErrorPojo getError() {
        return error;
    }

    public void setError(ErrorPojo error) {
        this.error = error;
    }

    public List<AdInsightsAgeGender> getAdInsightsAgeGenderList() {
        return adInsightsAgeGenderList;
    }

    public void setAdInsightsAgeGenderList(List<AdInsightsAgeGender> adInsightsAgeGenderList) {
        this.adInsightsAgeGenderList = adInsightsAgeGenderList;
    }

    public FBPaging getPaging() {
        return paging;
    }

    public void setPaging(FBPaging paging) {
        this.paging = paging;
    }
}
