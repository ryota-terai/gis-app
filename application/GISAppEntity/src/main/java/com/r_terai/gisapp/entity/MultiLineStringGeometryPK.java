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
public class MultiLineStringGeometryPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "MULTI_LINE_STRING_ID")
    private int multiLineStringId;
    @Basic(optional = false)
    @Column(name = "LINE_STRING_INDEX")
    private int lineStringIndex;
    @Basic(optional = false)
    @Column(name = "POINT_INDEX")
    private int pointIndex;

    public MultiLineStringGeometryPK() {
    }

    public MultiLineStringGeometryPK(int multiLineStringId, int lineStringIndex, int pointIndex) {
        this.multiLineStringId = multiLineStringId;
        this.lineStringIndex = lineStringIndex;
        this.pointIndex = pointIndex;
    }

    public int getMultiLineStringId() {
        return multiLineStringId;
    }

    public void setMultiLineStringId(int multiLineStringId) {
        this.multiLineStringId = multiLineStringId;
    }

    public int getLineStringIndex() {
        return lineStringIndex;
    }

    public void setLineStringIndex(int lineStringIndex) {
        this.lineStringIndex = lineStringIndex;
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
        hash += (int) multiLineStringId;
        hash += (int) lineStringIndex;
        hash += (int) pointIndex;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MultiLineStringGeometryPK)) {
            return false;
        }
        MultiLineStringGeometryPK other = (MultiLineStringGeometryPK) object;
        if (this.multiLineStringId != other.multiLineStringId) {
            return false;
        }
        if (this.lineStringIndex != other.lineStringIndex) {
            return false;
        }
        if (this.pointIndex != other.pointIndex) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.r_terai.gisapp.entity.MultiLineStringGeometryPK[ multiLineStringId=" + multiLineStringId + ", lineStringIndex=" + lineStringIndex + ", pointIndex=" + pointIndex + " ]";
    }
    
}
