package com.sk.pongme.rest;

import com.sk.pongme.data.DataLoader;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;


@Component
@Path("/api/admin")
@Scope("request")
public class Admin {

    @Qualifier("dataLoader")
    @Inject
    private DataLoader dataLoader;


    @GET
    @Path("/load")
    @Produces("application/text")
    public Response loadDatabase(){
        try {
            dataLoader.loadAndSaveEntriesFromFiles();
        } catch (Exception e) {
            new RemoteDBLoadException("Cannot load Data to Mongo ....");
        }

        return Response.ok().build();
    }

    @GET
    @Path("/reload")
    @Produces("application/text")
    public Response flushReloadDb(){
        
        
        
        return Response.ok().build();
    }

}
