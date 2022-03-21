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
public class MultiPolygonGeometryPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "MULTI_POLYGON_ID")
    private int multiPolygonId;
    @Basic(optional = false)
    @Column(name = "MULTI_POLYGON_INDEX")
    private int multiPolygonIndex;
    @Basic(optional = false)
    @Column(name = "POLYGON_INDEX")
    private int polygonIndex;
    @Basic(optional = false)
    @Column(name = "POINT_INDEX")
    private int pointIndex;

    public MultiPolygonGeometryPK() {
    }

    public MultiPolygonGeometryPK(int multiPolygonId, int multiPolygonIndex, int polygonIndex, int pointIndex) {
        this.multiPolygonId = multiPolygonId;
        this.multiPolygonIndex = multiPolygonIndex;
        this.polygonIndex = polygonIndex;
        this.pointIndex = pointIndex;
    }

    public int getMultiPolygonId() {
        return multiPolygonId;
    }

    public void setMultiPolygonId(int multiPolygonId) {
        this.multiPolygonId = multiPolygonId;
    }

    public int getMultiPolygonIndex() {
        return multiPolygonIndex;
    }

    public void setMultiPolygonIndex(int multiPolygonIndex) {
        this.multiPolygonIndex = multiPolygonIndex;
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
        hash += (int) multiPolygonId;
        hash += (int) multiPolygonIndex;
        hash += (int) polygonIndex;
        hash += (int) pointIndex;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MultiPolygonGeometryPK)) {
            return false;
        }
        MultiPolygonGeometryPK other = (MultiPolygonGeometryPK) object;
        if (this.multiPolygonId != other.multiPolygonId) {
            return false;
        }
        if (this.multiPolygonIndex != other.multiPolygonIndex) {
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
        return "com.r_terai.gisapp.entity.MultiPolygonGeometryPK[ multiPolygonId=" + multiPolygonId + ", multiPolygonIndex=" + multiPolygonIndex + ", polygonIndex=" + polygonIndex + ", pointIndex=" + pointIndex + " ]";
    }
    
}
