package com.swe.whatscooking.entity;

public class KrogerClient {
    private String expires_in;
    private String access_token;
    private String token_type;

    public KrogerClient(String expires_in, String access_token, String token_type) {
        this.expires_in = expires_in;
        this.access_token = access_token;
        this.token_type = token_type;
    }

    public KrogerClient() {
    }

    public String getExpires_in() {
        return expires_in;
    }

    public void setExpires_in(String expires_in) {
        this.expires_in = expires_in;
    }

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public String getToken_type() {
        return token_type;
    }

    public void setToken_type(String token_type) {
        this.token_type = token_type;
    }

    @Override
    public String toString() {
        return "KrogerClient{" +
                "expires_in='" + expires_in + '\'' +
                ", access_token='" + access_token + '\'' +
                ", token_type='" + token_type + '\'' +
                '}';
    }
}
