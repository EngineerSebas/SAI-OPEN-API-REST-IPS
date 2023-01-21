package com.spring.boot.sai.open.api.rest.context;


public class UserContext {
    private String lang;
    private boolean tz;
    private int uid;

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    public boolean isTz() {
        return tz;
    }

    public void setTz(boolean tz) {
        this.tz = tz;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }
}
