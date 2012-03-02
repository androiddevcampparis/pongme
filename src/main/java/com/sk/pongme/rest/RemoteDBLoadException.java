package com.sk.pongme.rest;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

/**
 * Created by IntelliJ IDEA.
 * User: sam
 * Date: 3/2/12
 * Time: 8:25 PM
 * To change this template use File | Settings | File Templates.
 */
public class RemoteDBLoadException extends WebApplicationException {

    public RemoteDBLoadException(String message){
        super(Response.status(400).
                entity(message).
                build());
    }
}
