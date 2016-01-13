package com.clouds.liyao.fsplayground.Model;


/**
 * Created by liyao on 1/12/16.
 */

public class LoginResponse {
    private boolean error;
    private String errorMsg;
    private int errorCode;
    private int count;
    private Results results;

    public boolean getError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public Results getResults() {
        return results;
    }

    public void setResults(Results results) {
        if (results != null) {
            this.results = results;
        }
    }
}
