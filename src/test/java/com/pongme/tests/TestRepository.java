package com.pongme.tests;

import com.sk.pongme.data.IPointRepository;
import com.sk.pongme.domain.PointData;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.geo.*;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.inject.Inject;
import java.util.List;

import static org.fest.assertions.Assertions.assertThat;

/**
 * Created by IntelliJ IDEA.
 * User: sam
 * Date: 2/29/12
 * Time: 12:48 PM
 * To change this template use File | Settings | File Templates.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/app-context.xml")
public class TestRepository {

    @Inject
    IPointRepository pr;

    @Inject
    MongoTemplate mongoTemplate;

    @Test
    public void  should_connect_to_mongo(){
        assertThat(mongoTemplate.getDb()).as("DB not null").isNotNull();

    }

    @Test
    public void should_exist_a_database_named_pongme(){
        assertThat(mongoTemplate.getDb().getName()).as("DB configured as Pongme").isEqualTo("pongme");

    }

    @Test
    public void should_exist_adequate_collections_in_pongmeDB(){
        assertThat(mongoTemplate.getDb().getCollection("pointData")).isNotNull();

    }

    @Test
    public void should_repository_not_be_null(){
        assertThat(pr).as("IRepository should be instantiated and injected by Spring").isNotNull();
    }


    //Find by title
    @Test
    public void should_find_the_document_by_its_title(){
        assertThat(pr.findByTitle("Aquarium Tropical")).isNotNull();
        assertThat(pr.findByTitle("Aquarium Tropical").getCategory()).isEqualTo("musees");
        assertThat(pr.findByTitle("Aquarium Tropical").getLocation()).isEqualTo(new double[]{2.4085078, 48.8349041});

    }

    //findbycategory
    @Test
    public void should_find_all_items_incategory_arbre_is_size_32(){
        assertThat(pr.findByCategory("arbre").size()).
                as("Should find that the size of all items in the arbre category is 32").
                isEqualTo(32);
    }

    //Search By Location

    @Test
    public void should_given_point_in_neighborhood(){

        double lon = 2.4078668;
        double lat = 48.8373613;

        //Find all the Points near pointData in a close distance of 500 meters

        List<PointData> listPoints = pr.findByLocationNear(
                new org.springframework.data.mongodb.core.geo.Point(lon,lat),
                new Distance(0.5, Metrics.KILOMETERS)  );


        assertThat(listPoints).isNotNull();
        assertThat(listPoints).isNotEmpty();
        assertThat(listPoints.contains(pr.findByTitle("Micocoulier de Provence"))).isEqualTo(true);
        assertThat(listPoints.contains(pr.findByTitle("Aquarium Tropical"))).isEqualTo(true);



    }

}
