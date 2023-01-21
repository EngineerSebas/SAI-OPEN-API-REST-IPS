package com.spring.boot.sai.open.api.rest.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.spring.boot.sai.open.api.rest.context.UserContext;

@JsonSerialize
public class TokenResponse {
    private long uid;
    private UserContext user_context;
    private long company_id;
    private String access_token;
    private long expires_in;
    private String refresh_token;
    private long refresh_expires_in;

    public TokenResponse(long uid, UserContext user_context, long company_id, String access_token, long expires_in, String refresh_token, long refresh_expires_in) {
        this.uid = uid;
        this.user_context = user_context;
        this.company_id = company_id;
        this.access_token = access_token;
        this.expires_in = expires_in;
        this.refresh_token = refresh_token;
        this.refresh_expires_in = refresh_expires_in;
    }

    public long getUid() {
        return uid;
    }

    public void setUid(long uid) {
        this.uid = uid;
    }

    public UserContext getUser_context() {
        return user_context;
    }

    public void setUser_context(UserContext user_context) {
        this.user_context = user_context;
    }

    public long getCompany_id() {
        return company_id;
    }

    public void setCompany_id(long company_id) {
        this.company_id = company_id;
    }

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public long getExpires_in() {
        return expires_in;
    }

    public void setExpires_in(long expires_in) {
        this.expires_in = expires_in;
    }

    public String getRefresh_token() {
        return refresh_token;
    }

    public void setRefresh_token(String refresh_token) {
        this.refresh_token = refresh_token;
    }

    public long getRefresh_expires_in() {
        return refresh_expires_in;
    }

    public void setRefresh_expires_in(long refresh_expires_in) {
        this.refresh_expires_in = refresh_expires_in;
    }
}