package com.sk.pongme.domain;


import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.GeoSpatialIndexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Arrays;

@Document
public class PointData {
    @Id
    private ObjectId id;

    private String title;
    private String addresse;
    private String description;
    
    @GeoSpatialIndexed  //[longitude, latitude]
    private double[] location;
    private String category;

    public PointData(){
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAddresse() {
        return addresse;
    }

    public void setAddresse(String addresse) {
        this.addresse = addresse;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double[] getLocation() {
        return location;
    }

    public void setLocation(double[] location) {
        this.location = location;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PointData)) return false;

        PointData pointData = (PointData) o;

        if (!category.equals(pointData.category)) return false;
        if (!id.equals(pointData.id)) return false;
        if (!Arrays.equals(location, pointData.location)) return false;
        if (!title.equals(pointData.title)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + title.hashCode();
        result = 31 * result + category.hashCode();
        return result;
    }

    @Override
	public String toString() {
		return "PointData [id=" + id + ", title=" + title + ", addresse="
				+ addresse + ", description=" + description + ", location="
				+ Arrays.toString(location) + ", category=" + category + "]";
	}
    
    
}
