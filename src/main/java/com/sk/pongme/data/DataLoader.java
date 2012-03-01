package com.sk.pongme.data;

import com.sk.pongme.domain.PointData;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;


@Component
public class DataLoader {

    @Inject
	MongoTemplate mongoTemplate;

	public DataLoader(){
	}


	public void loadAndSaveEntriesFromFiles() throws Exception, IOException {
        if (mongoTemplate == null){
            throw new RuntimeException("MongoTemplate is not initialized, check your DB settings!!");
        }

		URL url = this.getClass().getResource("/All_sauf_voies.csv");
		File file = new File(url.getFile());

		BufferedReader buffer;
		try {
            mongoTemplate.dropCollection(PointData.class);
			buffer = new BufferedReader(new FileReader(file));
			String line;

			while ((line =buffer.readLine())!=null) {

                PointData pointData = parseLineToPoint(line);
				mongoTemplate.save(pointData);

			}
		}catch (IOException ex){

			throw ex;
		}

	}

    private PointData parseLineToPoint(String line) {
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
        return pointData;
    }


    //TODO to be implemented . Change data files directory
    private List<URL> findDataFilesUrl(){
        List<URL> urlList = new ArrayList<URL>();

        return urlList;
    }


    public static void main(String[] args) {
		//To be removed, use Spring testing instead with container instantiation
		DataLoader dl = new DataLoader();
        ApplicationContext ctx=
				new ClassPathXmlApplicationContext(
						DataLoader.class.getResource("/app-context.xml").toExternalForm());

		dl.mongoTemplate = (MongoTemplate)ctx.getBean("mongoTemplate");
        try {
            dl.loadAndSaveEntriesFromFiles();
        } catch (Exception e) {

            new RuntimeException("Can't Access Data Files");
        }


    }






}
