package com.sk.pongme.data;

import com.sk.pongme.domain.PointData;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;


public class DataLoader {
	private MongoTemplate mongoTemplate;

	public DataLoader(){
	}

	public void init(){
		//To be removed, use Spring testing instead with container instantiation
		ApplicationContext ctx=
				new ClassPathXmlApplicationContext(
						this.getClass().getResource("/app-context.xml").toExternalForm());

		mongoTemplate = (MongoTemplate)ctx.getBean("mongoTemplate");
	}


	public void loadAndSaveEntriesFromFiles() throws Exception, IOException {
        if (mongoTemplate == null){
            throw new RuntimeException("MongoTemplate is not initialzed, check your spring conf");
        }

		URL url = this.getClass().getResource("/All_sauf_voies.csv");
		File file = new File(url.getFile());

		BufferedReader buffer;
		try {
            mongoTemplate.dropCollection(PointData.class);
			buffer = new BufferedReader(new FileReader(file));
			String line;

			while ((line =buffer.readLine())!=null) {

				String[] splitted = line.split("\\t");
				PointData pointData = new PointData();

				pointData.setTitle(splitted[0]);
				pointData.setDescription(splitted[1]);
				pointData.setAddresse(splitted[2]);
				pointData.setLocation(new double[]{
						Double.valueOf(splitted[4]),
						Double.valueOf(splitted[3])
				});
				pointData.setCategory(splitted[5]);
				mongoTemplate.save(pointData);

			}
		}catch (IOException ex){

			throw ex;
		}

	}

	public static void main(String[] args) {
		DataLoader dl = new DataLoader();
		dl.init();



    }






}
