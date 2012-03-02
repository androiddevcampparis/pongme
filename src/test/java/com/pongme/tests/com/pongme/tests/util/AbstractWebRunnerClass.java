package com.pongme.tests.com.pongme.tests.util;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.mortbay.jetty.Server;
import org.mortbay.jetty.webapp.WebAppContext;

/**
 * Created by IntelliJ IDEA.
 * User: sam
 * Date: 3/2/12
 * Time: 3:46 PM
 * To change this template use File | Settings | File Templates.
 */
public abstract class AbstractWebRunnerClass {
    private static int port = 9000;
    private String url;
    private static Server server;
    private static Client client;


    protected AbstractWebRunnerClass(){
        this.url=String.format("http://localhost:%s/", port);
    }

    protected WebResource getResource(String relativeUrl) {
        String realUrl = url + relativeUrl;

        return client.resource(realUrl);
    }

    @BeforeClass
    public static void setUp() throws Exception {
        server = new Server(port);
        WebAppContext webAppContext = new WebAppContext("src/main/webapp", "/");
        webAppContext.setConfigurationClasses(new String[] {
                "org.mortbay.jetty.webapp.WebInfConfiguration",
                "org.mortbay.jetty.webapp.WebXmlConfiguration", });
        server.addHandler(webAppContext);
        server.start();

        client = Client.create();
    }


    @AfterClass
    public static void tearDown() throws Exception {
        if (server != null)
            server.stop();
    }

}
