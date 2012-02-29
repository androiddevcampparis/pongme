package com.pongme.tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;

public class TestStorage {
	
	@Autowired
	public MongoTemplate mongoTemplate;
	
	@Before
	public void setUp(){
		
	}
	
	
	@Test
	public void test_store_and_retrieve_data(){
		
	}

	@Test
	public void test() {
		fail("Not yet implemented");
	}

}
