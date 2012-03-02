package com.pongme.tests;

import com.pongme.tests.com.pongme.tests.util.AbstractWebRunnerClass;
import com.sk.pongme.data.PointRepository;
import com.sk.pongme.rest.PointListResource;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.inject.Inject;
import javax.ws.rs.core.Response;

import static org.fest.assertions.Assertions.assertThat;

/**
 * Created by IntelliJ IDEA.
 * User: sam
 * Date: 3/1/12
 * Time: 10:16 AM
 * To change this template use File | Settings | File Templates.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/app-context.xml")
public class TestPointRestEndPoint extends AbstractWebRunnerClass{// extends JerseyTest{
    static Logger logger = Logger.getLogger(TestPointRestEndPoint.class);

    @Inject
    PointRepository pointRepository;


    


    @Test
    public void should_find_near_points_via_rest_call_with_jetty(){
        WebResource resource = getResource("api/poi/2.4078668+48.8373613+arbre");
        ClientResponse clientResponse = resource.get(ClientResponse.class);
        //Validate Response
        assertThat(Response.Status.OK.getStatusCode()).isEqualTo(clientResponse.getStatus());


        PointListResource receivedPointListResource = resource.get(PointListResource.class);
        assertThat(receivedPointListResource).isNotNull();
        //assertThat(receivedPointListResource.getPointDataList()).isNotNull();
        /*assertThat(receivedPointListResource.getPointDataList()
                                           .get(0)
                                           .getLocation()
                                           .equals(new double[]{2.4078668,48.8373613})
                                          ).isTrue();
*/

    }


    @Test
    public void should_fail_with_wrong_url(){
        WebResource resource = getResource("api/poi/blabla");
        ClientResponse clientResponse = resource.get(ClientResponse.class);
        //Validate Response
        assertThat(Response.Status.BAD_REQUEST.getStatusCode()).isEqualTo(clientResponse.getStatus());

    }





}
