package com.sk.pongme.rest;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Created by IntelliJ IDEA.
 * User: sam
 * Date: 3/1/12
 * Time: 11:10 AM
 * To change this template use File | Settings | File Templates.
 */
public class BadUrlException extends WebApplicationException {
    public BadUrlException(String message){
        super(Response.status(400).
                entity(message).
                type(MediaType.APPLICATION_JSON_TYPE).
                build());
    }
}
