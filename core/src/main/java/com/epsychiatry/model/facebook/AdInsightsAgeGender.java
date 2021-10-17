package com.epsychiatry.model.facebook;


import com.epsychiatry.model.base.PersistentObject;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "ad_insights_age_gender")
public class AdInsightsAgeGender extends PersistentObject {

    @Column(name = "campaign_id", nullable = false, updatable = false)
    private String campaign_id;
    @Column(name = "campaign_name", nullable = false, updatable = false)
    private String campaign_name;
    @Column(name = "adset_id", nullable = false, updatable = false)
    private String adset_id;
    @Column(name = "adset_name", nullable = false, updatable = false)
    private String adset_name;
    @Column(name = "ad_id", nullable = false, updatable = false)
    private String ad_id;
    @Column(name = "ad_name", nullable = false, updatable = false)
    private String ad_name;
    @Column(name = "impressions", nullable = false, updatable = false)
    private Integer impressions;
    @Column(name = "reach", nullable = false, updatable = false)
    private Integer reach;
    @Column(name = "clicks", nullable = false, updatable = false)
    private Integer clicks;
    @Column(name = "spend", nullable = false, updatable = false)
    private Double spend;
    @Column(name = "cpm", nullable = false, updatable = false)
    private Double cpm;
    @Column(name = "cpp", nullable = false, updatable = false)
    private Double cpp;
    @Column(name = "ctr", nullable = false, updatable = false)
    private Double ctr;
    @Column(name = "date_start", nullable = false, updatable = false)
    private Date date_start;
    @Column(name = "date_stop", nullable = false, updatable = false)
    private Date date_stop;
    @Column(name = "age", nullable = false, updatable = false)
    private String age;
    @Column(name = "gender", nullable = false, updatable = false)
    private String gender;

    public AdInsightsAgeGender() {
    }


    public String getAdset_name() {
        return adset_name;
    }

    public void setAdset_name(String adset_name) {
        this.adset_name = adset_name;
    }

    public String getCampaign_id() {
        return campaign_id;
    }

    public void setCampaign_id(String campaign_id) {
        this.campaign_id = campaign_id;
    }

    public String getCampaign_name() {
        return campaign_name;
    }

    public void setCampaign_name(String campaign_name) {
        this.campaign_name = campaign_name;
    }

    public String getAdset_id() {
        return adset_id;
    }

    public void setAdset_id(String adset_id) {
        this.adset_id = adset_id;
    }

    public String getAd_name() {
        return ad_name;
    }

    public void setAd_name(String ad_name) {
        this.ad_name = ad_name;
    }

    public Date getDate_start() {
        return date_start;
    }

    public void setDate_start(Date date_start) {
        this.date_start = date_start;
    }

    public Date getDate_stop() {
        return date_stop;
    }

    public void setDate_stop(Date date_stop) {
        this.date_stop = date_stop;
    }

    public String getAd_id() {
        return ad_id;
    }

    public void setAd_id(String ad_id) {
        this.ad_id = ad_id;
    }

    public Integer getImpressions() {
        return impressions;
    }

    public void setImpressions(Integer impressions) {
        this.impressions = impressions;
    }

    public Integer getReach() {
        return reach;
    }

    public void setReach(Integer reach) {
        this.reach = reach;
    }

    public Integer getClicks() {
        return clicks;
    }

    public void setClicks(Integer clicks) {
        this.clicks = clicks;
    }

    public Double getSpend() {
        return spend;
    }

    public void setSpend(Double spend) {
        this.spend = spend;
    }

    public Double getCpm() {
        return cpm;
    }

    public void setCpm(Double cpm) {
        this.cpm = cpm;
    }

    public Double getCpp() {
        return cpp;
    }

    public void setCpp(Double cpp) {
        this.cpp = cpp;
    }

    public Double getCtr() {
        return ctr;
    }

    public void setCtr(Double ctr) {
        this.ctr = ctr;
    }


    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}
