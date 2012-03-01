package com.sk.pongme.rest;

import com.sk.pongme.data.DataLoader;
import com.sk.pongme.data.PointRepository;
import com.sk.pongme.domain.PointData;
import org.springframework.context.annotation.Scope;
import org.springframework.data.mongodb.core.geo.Distance;
import org.springframework.data.mongodb.core.geo.Metrics;
import org.springframework.data.mongodb.core.geo.Point;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import java.util.ArrayList;
import java.util.List;

@Component
@Path("/api/")
@Scope("request")
public class PointEndpoint {

    @Inject
    private PointRepository pointRepository;

    @Inject
    private DataLoader dl;

    public PointEndpoint(){
    }

    @GET
    @Path("/poi/{requesturl}")
    @Produces("application/json")
    public PointListResource findNearestPoints(@PathParam("requesturl") String reqUrl){
        PointListResource listResource = new PointListResource();
        List<PointData> pointList = new ArrayList<PointData>();

        String[] reqs = reqUrl.split("\\+");
        switch(reqs.length){
            case 2 : pointList = pointRepository.findByLocationNear(
                     new Point(Double.valueOf(reqs[0]),Double.valueOf(reqs[1])),
                     new Distance(0.5, Metrics.KILOMETERS)
            );  break;
            case 3 : pointList = pointRepository.findByLocationNearAndCategory(
                     new Point(Double.valueOf(reqs[0]),Double.valueOf(reqs[1])),
                     new Distance(0.5, Metrics.KILOMETERS),
                     reqs[2]
            ); break;
            default: throw new BadUrlException("Bad URl in the request. It should be longitude+latitude+category ");

        }

        return new PointListResource(pointList);
    }





}
