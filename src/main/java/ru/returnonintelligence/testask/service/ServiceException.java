package ru.returnonintelligence.testask.service;

/**
 * Created by Anton on 29.03.2015.
 * Exception for entity's services
 */
public class ServiceException extends Exception {

    public ServiceException(String er) {
        super(er);
    }
    public ServiceException(String er, Exception e) {
        super(er);
    }
}
