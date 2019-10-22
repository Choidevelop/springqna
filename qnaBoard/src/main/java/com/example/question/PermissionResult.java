package com.example.question;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * PermissionResult
 */
public class PermissionResult {
    @JsonProperty
    private boolean valid;
    @JsonProperty
    private String errorMessage;
    @JsonProperty
    private Question question = null;

    private PermissionResult(){}

    private PermissionResult(boolean valid, String errorMessage)
    {
        this.valid = valid;
        this.errorMessage = errorMessage;
    }
    private PermissionResult(boolean valid, String errorMessage, Question question)
    {
        this.valid = valid;
        this.errorMessage = errorMessage;
        this.question = question;
        // System.out.println("퍼미션으로 얻어온 question : " + question);
    }

    public boolean isVaild(){
        return valid;
    }

    public static PermissionResult ok() {
        return new PermissionResult(true, null);
    }

    public static PermissionResult deleteCountOk(Question question){
        // System.out.println("퍼미션으로 얻어온 question : " + question);
        return new PermissionResult(true, null, question);
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

    @Override
    public String toString() {
        return "PermissionResult [errorMessage=" + errorMessage + ", question 숫자=" + question.getCountOfAnswer() + ", valid=" + valid + "]";
    }
}