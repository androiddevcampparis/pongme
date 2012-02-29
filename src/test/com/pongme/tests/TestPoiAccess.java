package com.pongme.tests;

import org.junit.Before;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.mongodb.core.MongoTemplate;


public class TestPoiAccess {
	private MongoTemplate mongoTemplate;

	@Before
	public void setUp(){
		ApplicationContext ctx=
				new ClassPathXmlApplicationContext(
						this.getClass().getResource("/app-context.xml").toExternalForm());
		mongoTemplate = (MongoTemplate)ctx.getBean("mongoTemplate");



	}

	public void test_find_poi_near_me(){
		
		
	}

}
