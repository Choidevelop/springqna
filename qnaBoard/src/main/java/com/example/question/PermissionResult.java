package com.example.question;

/**
 * PermissionResult
 */
public class PermissionResult {

    private boolean valid;

    private String errorMessage;

    private PermissionResult(boolean valid, String errorMessage)
    {
        this.valid = valid;
        this.errorMessage = errorMessage;
    }

    public boolean isVaild(){
        return valid;
    }

    public static PermissionResult ok() {
        return new PermissionResult(true, null);
    }

    public static PermissionResult fail(String errorMessage){
        return new PermissionResult(false, errorMessage);
    }

    /**
     * @return the errorMessage
     */
    public String getErrorMessage() {
        return errorMessage;
    }
}