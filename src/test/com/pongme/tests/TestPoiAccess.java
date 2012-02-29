package com.pongme.tests;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.geo.GeoResult;
import org.springframework.data.mongodb.core.geo.GeoResults;
import org.springframework.data.mongodb.core.query.NearQuery;
import org.springframework.data.mongodb.repository.Near;

import com.sk.pongme.domain.PoiData;

import static org.junit.Assert.*;


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
