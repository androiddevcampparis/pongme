package com.sk.pongme.data;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.geo.Distance;
import org.springframework.data.mongodb.core.geo.GeoResult;
import org.springframework.data.mongodb.core.geo.GeoResults;
import org.springframework.data.mongodb.core.geo.Metrics;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.NearQuery;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.sk.pongme.domain.PoiData;


@Repository
public class POIRepository {

	@Autowired
	private MongoTemplate mongoTemplate;
	
	

	public void add(PoiData poi){
		mongoTemplate.save(poi);
	}

	public List<PoiData> findAll(){
		return mongoTemplate.findAll(PoiData.class);
	}

	public List<PoiData> findPoiDataByTitle(String title){
		Query query = new Query(new Criteria().where("title").is(title));

		return mongoTemplate.find(query, PoiData.class);
	}
	
	
	public List<PoiData> findPoiNearbyWithCoordinates(double lon, double lat){
	    NearQuery nq = NearQuery.near(lon, lat).maxDistance(new Distance(0.5, Metrics.KILOMETERS));
		GeoResults<PoiData> georesults = mongoTemplate.geoNear(nq, PoiData.class);
		List<PoiData> lp = new ArrayList<PoiData>();
		for (GeoResult<PoiData> geoResult : georesults.getContent()){
			lp.add( geoResult.getContent());
		}
			
		return lp;
		
	}
	
	public List<PoiData> findPoiNearBy(PoiData p){
		double x = p.getLocation()[0];
		double y = p.getLocation()[1];
		
		return findPoiNearbyWithCoordinates(x, y);
		
	}





}
