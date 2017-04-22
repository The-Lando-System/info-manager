package com.mattvoget.infomanager.utils;


import org.apache.commons.lang3.StringUtils;

public class UserHelper {

    public static void checkUsernames(String givenUsername, String actualUsername, String errorMessage){
        if (!StringUtils.equals(actualUsername,givenUsername)){
            throw new IllegalAccessError(errorMessage);
        }
    }
}
