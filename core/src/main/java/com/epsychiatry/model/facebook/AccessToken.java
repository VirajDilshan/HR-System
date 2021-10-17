package com.epsychiatry.model.facebook;

import com.epsychiatry.model.base.PersistentObject;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.math.BigInteger;

@Entity
@Table(name ="user_access_token")
public class AccessToken extends PersistentObject {


    @Column(name = "user_id", nullable = false, updatable = false)
    private String userID;


    @Column(name = "expire_in", nullable = false, updatable = false)
    private Long expiresIn;


    @Column(name = "signed_request", length = 1000, nullable = false, updatable = false)
    private String signedRequest;


    @Column(name = "access_token", length = 1000, nullable = false, updatable = false)
    private String token;


    @Column(name = "graph_domain", nullable = false, updatable = false)
    private String graphDomain;


    @Column(name = "access_expire_time", nullable = false, updatable = false)
    private Long accessExpTime;

    public AccessToken() {
    }

    public AccessToken(String userID,
                       Long expiresIn,
                       String signedRequest,
                       String token,
                       String graphDomain,
                       Long accessExpTime) {
        this.userID = userID;
        this.expiresIn = expiresIn;
        this.signedRequest = signedRequest;
        this.token = token;
        this.graphDomain = graphDomain;
        this.accessExpTime = accessExpTime;
    }




    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public Long getExpiresIn() {
        return expiresIn;
    }

    public void setExpiresIn(Long expiresIn) {
        this.expiresIn = expiresIn;
    }

    public String getSignedRequest() {
        return signedRequest;
    }

    public void setSignedRequest(String signedRequest) {
        this.signedRequest = signedRequest;
    }

    public String getGraphDomain() {
        return graphDomain;
    }

    public void setGraphDomain(String graphDomain) {
        this.graphDomain = graphDomain;
    }

    public Long getAccessExpTime() {
        return accessExpTime;
    }

    public void setAccessExpTime(Long accessExpTime) {
        this.accessExpTime = accessExpTime;
    }
}
