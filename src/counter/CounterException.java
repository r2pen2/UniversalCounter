/*
 * Copyright (c) 2023.  Gary F. Pollice
 *
 *  This files was developed for personal or educational purposes. All rights reserved.
 *
 * You may use this software for any purpose except as follows:
 * 1) You may not submit this file without modification for any educational assignment.
 * 2) You may not remove this copyright, even if you have modified this file.
 */

package counter;

/**
 * This is the exception class for all exceptions that might be
 * thrown but tje UniversalCounter. It is a subclass of `RuntimeException`
 * so that it does not have to be declared. This is for convenience and
 * not necessarily the best approach.
 *
 * ## YOU MAY NOT MODIFY OR MOVE THIS FILE ##
 */
public class CounterException extends RuntimeException {
    public static enum ExceptionReason {BAD_VALUE, DUPLICATE_LABEL, NO_LABEL}

    private ExceptionReason reason;

    /**
     * The default constructor. You must supply an appropriate message and cause.
     * @param message specifies the error
     * @param reason the type of error
     */
    public CounterException(String message, ExceptionReason reason) {
        super(message);
        this.reason = reason;
    }

    public ExceptionReason getReason() {
        return reason;
    }
}
