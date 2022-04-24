package com.swe.whatscooking.entity.KrogerAPI;

public class KrogerCustomer {
    private String expires_in;
    private String access_token;
    private String token_type;
    private String refresh_token;

    public KrogerCustomer(String expires_in, String access_token, String token_type, String refresh_token) {
        this.expires_in = expires_in;
        this.access_token = access_token;
        this.token_type = token_type;
        this.refresh_token = refresh_token;
    }

    public KrogerCustomer() {
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

    public String getRefresh_token() {
        return refresh_token;
    }

    public void setRefresh_token(String refresh_token) {
        this.refresh_token = refresh_token;
    }

    @Override
    public String toString() {
        return "KrogerCustomer{" +
                "expires_in='" + expires_in + '\'' +
                ", access_token='" + access_token + '\'' +
                ", token_type='" + token_type + '\'' +
                ", refresh_token='" + refresh_token + '\'' +
                '}';
    }
}
