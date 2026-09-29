package com.ispc.florappmovil;

public class LoginResponse {

    private String access;
    private String refresh;

    private User user;

    public String getAccess() {
        return access;
    }

    public String getRefresh() {
        return refresh;
    }

    public User getUser() { return user; }
}
