package com.echo.bank.framework.exception;

import com.echo.bank.enums.StatusCode;

/**
 * @author echo
 * @version 1.0
 * Create by 2023/5/24 15:51
 */
public class BankException extends RuntimeException {

    private StatusCode statusCode;

    public BankException(StatusCode statusCode) {
        this.statusCode = statusCode;
    }

    public StatusCode getProcCode() {
        return statusCode;
    }

    public void setProcCode(StatusCode statusCode) {
        this.statusCode = statusCode;
    }

    private Object[] params;

    protected BankException() {
        super();
    }

    public BankException(String message) {
        super(message);
    }

    public BankException(String message, Throwable cause) {
        super(message, cause);
    }

    public BankException(Throwable cause) {
        super(cause);
    }

    public BankException(String message, Object... params) {
        super(message);
        this.params = params;
    }

    public BankException(String message, Throwable cause, Object... params) {
        super(message, cause);
        this.params = params;
    }

    public Object[] getParams() {
        return this.params;
    }

}
