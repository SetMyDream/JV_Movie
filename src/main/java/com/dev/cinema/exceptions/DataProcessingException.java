package com.dev.cinema.exceptions;

import org.apache.log4j.Logger;

public class DataProcessingException extends RuntimeException {

    private static final Logger logger = Logger.getLogger(RuntimeException.class);

    public DataProcessingException(String message, Throwable cause) {
        super(message, cause);

        logger.error(message,cause);
    }
}
