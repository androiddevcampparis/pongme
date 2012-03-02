package com.sk.pongme.rest;

import com.google.common.base.Objects;
import com.sk.pongme.domain.PointData;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: sam
 * Date: 2/29/12
 * Time: 5:49 PM
 * To change this template use File | Settings | File Templates.
 */
@XmlRootElement
public class PointListResource {
    private List<PointData> pointDataList;

    public PointListResource(){

    }

    public PointListResource(List<PointData> pdl){
        this.pointDataList = pdl;
    }

    public List<PointData> getPointDataList() {
        return pointDataList;
    }

    public void setPointDataList(List<PointData> pointDataList) {
        this.pointDataList = pointDataList;
    }
    
    public String toString(){
        return Objects.toStringHelper(pointDataList).toString();
        
    }
}
