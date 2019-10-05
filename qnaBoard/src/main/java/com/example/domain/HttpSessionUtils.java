package com.example.domain;

import javax.servlet.http.HttpSession;

/**
 * HttpSessionUtils
 */
public class HttpSessionUtils {
    public static final String USER_SESSION_KEY = "login";

    public static boolean isLoginUser(HttpSession session){
        Object sessionedUser = session.getAttribute(USER_SESSION_KEY);
        if(sessionedUser == null){
            System.out.println("세션에 내용이 없습니다.");
            return false;
        }
        System.out.println("세션에 내용이 있습니다.");
        return true;
    }
    
    public static CustomerList getUserFromSession(HttpSession session){
        if(!isLoginUser(session)){
            return null;
        }

        return (CustomerList)session.getAttribute(USER_SESSION_KEY);
    }
}