package com.example.lukeboyde.projectofficial;

/**
 * Created by lukeboyde on 01/05/2018.
 */

public class Interest {

    private String userInterests;

    public Interest() {
    }

    public Interest(String userInterests){
        this.userInterests = userInterests;
    }

    public String getUserInterests() {
        return userInterests;
    }

    public void setUserInterests(String userInterests) {
        this.userInterests = userInterests;
    }

    @Override
    public String toString() {
                return "Interest:" + userInterests;
//        return "Interest{" +
//                "userInterests='" + userInterests + '\'' +
//                '}';
    }
}
