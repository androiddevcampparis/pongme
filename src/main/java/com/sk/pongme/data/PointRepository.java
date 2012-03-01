package com.sk.pongme.data;

import com.sk.pongme.domain.PointData;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.geo.Distance;
import org.springframework.data.mongodb.core.geo.Point;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: sam
 * Date: 2/29/12
 * Time: 3:37 PM
 * To change this template use File | Settings | File Templates.
 */

public interface PointRepository extends MongoRepository<PointData, ObjectId> {

    PointData findByTitle(String title);
    List<PointData> findByCategory(String category);
    List<PointData> findByLocationNear(Point location, Distance distance);
    List<PointData>  findByLocationNearAndCategory(Point location, Distance distance, String category);

}
