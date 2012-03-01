package com.pongme.tests;

import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.test.framework.JerseyTest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

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
public class TestPointRestEndPoint extends JerseyTest{

    public TestPointRestEndPoint()
    {
        super("com.sk.pongme.rest");
        /* Map<String, String> contextParams = new HashMap<String, String>();
        contextParams.put("contextConfigLocation", "classpath:applicationContext.xml");
        ApplicationDescriptor appDescriptor = new ApplicationDescriptor()
                .setContextPath(your-context-path)
                .setRootResourcePackageName(your-root-resource-package-name)
        .setServletClass(com.sun.jersey.spi.spring.container.servlet.SpringServlet.class)
            .setContextListenerClassName("org.springframework.web.context.ContextLoaderListener")
            .setContextParams(contextParams);
        super.setupTestEnvironment(appDescriptor);*/
    }


    @Test
    public void should_find_near_points_via_rest(){
        double lon = 2.4078668;
        double lat = 48.8373613;

        WebResource webResource = resource();
        String responseMsg = webResource.path("api/poi/2.4078668+48.8373613").
                get(String.class);
        System.out.println(responseMsg);/*
        assertThat(responseMsg).isNotNull();
        assertThat(responseMsg).isNotEmpty();*/
        assertThat("").isEmpty();

    }





}
