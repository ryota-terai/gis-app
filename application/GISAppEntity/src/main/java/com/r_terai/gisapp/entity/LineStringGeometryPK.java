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
public class LineStringGeometryPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "LINE_STRING_ID")
    private int lineStringId;
    @Basic(optional = false)
    @Column(name = "POINT_INDEX")
    private int pointIndex;

    public LineStringGeometryPK() {
    }

    public LineStringGeometryPK(int lineStringId, int pointIndex) {
        this.lineStringId = lineStringId;
        this.pointIndex = pointIndex;
    }

    public int getLineStringId() {
        return lineStringId;
    }

    public void setLineStringId(int lineStringId) {
        this.lineStringId = lineStringId;
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
        hash += (int) lineStringId;
        hash += (int) pointIndex;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof LineStringGeometryPK)) {
            return false;
        }
        LineStringGeometryPK other = (LineStringGeometryPK) object;
        if (this.lineStringId != other.lineStringId) {
            return false;
        }
        if (this.pointIndex != other.pointIndex) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.r_terai.gisapp.entity.LineStringGeometryPK[ lineStringId=" + lineStringId + ", pointIndex=" + pointIndex + " ]";
    }
    
}
