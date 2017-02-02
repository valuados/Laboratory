package com.netcracker.backend.exceptions;

/**
 * Create custom exception on service layer.
 * @author Hromenkov Ilya
 * @version 1.0
 */
public class ServException extends Exception {

    private ServErrorCode code;
    private Object[] params;
    private String message;

    public ServException(Throwable t, ServErrorCode code, Object... params) {
        super(t);
        this.code = code;
        this.params = params;
        this.message = String.format(code.toString(), params);
    }

    public ServErrorCode getCode() {
        return code;
    }

    public void setCode(ServErrorCode code) {
        this.code = code;
    }

    public Object[] getParams() {
        return params;
    }

    public void setParams(Object[] params) {
        this.params = params;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
