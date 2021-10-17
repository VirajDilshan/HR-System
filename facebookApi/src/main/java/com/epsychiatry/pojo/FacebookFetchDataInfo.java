package com.epsychiatry.pojo;



import com.epsychiatry.model.facebook.AdInsightsAgeGender;

import java.util.Date;
import java.util.List;

/**
 *  FacebookFetchDataInfo builder class
 * @author rasika
 */
public class FacebookFetchDataInfo {

    public static class Builder{
        private Integer totalDataPoint;
        private Integer newDataPoint;
        private Date startUpdateFrom;
        private Date startUpdateTo;
        private Date endUpdateFrom;
        private Date endUpdateTo;
        private ErrorPojo error;
        private List<AdInsightsAgeGender> adInsightsAgeGenderList;

        public Builder() {
        }

        public FacebookFetchDataInfo build() {
            return new FacebookFetchDataInfo(this);
        }

        public Builder totalDataPoint(Integer totalDataPoint) {
            this.totalDataPoint = totalDataPoint;
            return this;
        }

        public Builder newDataPoint(Integer newDataPoint) {
            this.newDataPoint = newDataPoint;
            return this;
        }

        public Builder startUpdateFrom(Date startUpdateFrom) {
            this.startUpdateFrom = startUpdateFrom;
            return this;
        }

        public Builder startUpdateTo(Date startUpdateTo) {
            this.startUpdateTo = startUpdateTo;
            return this;
        }

        public Builder endUpdateFrom(Date endUpdateFrom) {
            this.endUpdateFrom = endUpdateFrom;
            return this;
        }

        public Builder endUpdateTo(Date endUpdateTo) {
            this.endUpdateTo = endUpdateTo;
            return this;
        }

        public Builder error(ErrorPojo error) {
            this.error = error;
            return this;
        }

        public Builder adInsightsAgeGenderList(List<AdInsightsAgeGender> adInsightsAgeGenderList) {
            this.adInsightsAgeGenderList = adInsightsAgeGenderList;
            return this;
        }
    }

    private final Integer totalDataPoint;
    private final Integer newDataPoint;
    private final Date startUpdateFrom;
    private final Date startUpdateTo;
    private final Date endUpdateFrom;
    private final Date endUpdateTo;
    private final ErrorPojo error;
    private final List<AdInsightsAgeGender> adInsightsAgeGenderList;

    public  FacebookFetchDataInfo(Builder builder) {
        this.totalDataPoint = builder.totalDataPoint;
        this.newDataPoint = builder.newDataPoint;
        this.startUpdateFrom = builder.startUpdateFrom;
        this.startUpdateTo = builder.startUpdateTo;
        this.endUpdateFrom = builder.endUpdateFrom;
        this.endUpdateTo = builder.endUpdateTo;
        this.error = builder.error;
        this.adInsightsAgeGenderList = builder.adInsightsAgeGenderList;
    }

    public Integer getTotalDataPoint() {
        return totalDataPoint;
    }

    public Integer getNewDataPoint() {
        return newDataPoint;
    }



    public ErrorPojo getError() {
        return error;
    }


    public Date getStartUpdateFrom() {
        return startUpdateFrom;
    }

    public Date getStartUpdateTo() {
        return startUpdateTo;
    }

    public Date getEndUpdateFrom() {
        return endUpdateFrom;
    }

    public Date getEndUpdateTo() {
        return endUpdateTo;
    }

    public List<AdInsightsAgeGender> getAdInsightsAgeGenderList() {
        return adInsightsAgeGenderList;
    }

    @Override
    public String toString() {
        return "FacebookFetchDataInfo{" +
                "totalDataPoint=" + totalDataPoint +
                ", newDataPoint=" + newDataPoint +
                ", startUpdateFrom=" + startUpdateFrom +
                ", startUpdateTo=" + startUpdateTo +
                ", endUpdateFrom=" + endUpdateFrom +
                ", endUpdateTo=" + endUpdateTo +
                ", error=" + error +
                ", adInsightsAgeGenderList=" + adInsightsAgeGenderList +
                '}';
    }
}
