package com.dev.cinema.exceptions;

import org.apache.log4j.Logger;

public class AuthenticationException extends Exception {

    private static final Logger logger = Logger.getLogger(RuntimeException.class);

    public AuthenticationException(String message, Throwable cause) {
        super(message, cause);
        logger.error(message,cause);
    }

    public AuthenticationException(String message) {
        super(message);
        logger.error(message);
    }
}
