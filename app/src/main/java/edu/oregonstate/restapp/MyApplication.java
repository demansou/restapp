package edu.oregonstate.restapp;

import android.app.Application;

public class MyApplication extends Application {

    /* Global variable UserAuthToken */
    private String UserAuthToken;

    /**
     * Returns global variable UserAuthToken
     * @return string
     */
    public String getUserAuthToken() {
        if (this.UserAuthToken == null) {
            return "";
        }
        return this.UserAuthToken;
    }

    /**
     * Sets global variable UsersAuthToken
     * @param UserAuthToken String
     */
    public void setUserAuthToken(String UserAuthToken) {
        this.UserAuthToken = UserAuthToken;
    }
}
