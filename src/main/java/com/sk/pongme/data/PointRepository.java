package com.sk.pongme.data;

import java.util.ArrayList;
import java.util.List;

import com.sk.pongme.domain.PointData;
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


@Repository
public class PointRepository {

	@Autowired
	private MongoTemplate mongoTemplate;
	
	

	public void add(PointData point){
		mongoTemplate.save(point);
	}

	public List<PointData> findAll(){
		return mongoTemplate.findAll(PointData.class);
	}

	public List<PointData> findPoiDataByTitle(String title){
		Query query = new Query(new Criteria().where("title").is(title));

		return mongoTemplate.find(query, PointData.class);
	}
	
	
	public List<PointData> findPoiNearbyWithCoordinates(double lon, double lat){
	    NearQuery nq = NearQuery.near(lon, lat).maxDistance(new Distance(0.5, Metrics.KILOMETERS));
		GeoResults<PointData> georesults = mongoTemplate.geoNear(nq, PointData.class);
		List<PointData> lp = new ArrayList<PointData>();
		for (GeoResult<PointData> geoResult : georesults.getContent()){
			lp.add( geoResult.getContent());
		}
			
		return lp;
		
	}
	
	public List<PointData> findPoiNearBy(PointData p){
		double x = p.getLocation()[0];
		double y = p.getLocation()[1];
		
		return findPoiNearbyWithCoordinates(x, y);
		
	}





}
