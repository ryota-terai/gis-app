/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.r_terai.gisapp.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author r-terai
 */
@Embeddable
public class PolygonGeometryPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "POLYGON_ID")
    private int polygonId;
    @Basic(optional = false)
    @Column(name = "POLYGON_INDEX")
    private int polygonIndex;
    @Basic(optional = false)
    @Column(name = "POINT_INDEX")
    private int pointIndex;

    public PolygonGeometryPK() {
    }

    public PolygonGeometryPK(int polygonId, int polygonIndex, int pointIndex) {
        this.polygonId = polygonId;
        this.polygonIndex = polygonIndex;
        this.pointIndex = pointIndex;
    }

    public int getPolygonId() {
        return polygonId;
    }

    public void setPolygonId(int polygonId) {
        this.polygonId = polygonId;
    }

    public int getPolygonIndex() {
        return polygonIndex;
    }

    public void setPolygonIndex(int polygonIndex) {
        this.polygonIndex = polygonIndex;
    }

    public int getPointIndex() {
        return pointIndex;
    }

    public void setPointIndex(int pointIndex) {
        this.pointIndex = pointIndex;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) polygonId;
        hash += (int) polygonIndex;
        hash += (int) pointIndex;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PolygonGeometryPK)) {
            return false;
        }
        PolygonGeometryPK other = (PolygonGeometryPK) object;
        if (this.polygonId != other.polygonId) {
            return false;
        }
        if (this.polygonIndex != other.polygonIndex) {
            return false;
        }
        if (this.pointIndex != other.pointIndex) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.r_terai.gisapp.entity.PolygonGeometryPK[ polygonId=" + polygonId + ", polygonIndex=" + polygonIndex + ", pointIndex=" + pointIndex + " ]";
    }
    
}
