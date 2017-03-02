package com.app.manics.models;



public class AuthInfo {

    private String token;
    private String socialType;
    private String session;

    public AuthInfo(String token, String socialType) {
        this.token = token;
        this.socialType = socialType;
    }

    public String getToken() {
        return token;
    }

    public String getSocialType() {
        return socialType;
    }

    public String getSession() {
        return session;
    }

    public void setSession(String session) {
        this.session = session;
    }
}
