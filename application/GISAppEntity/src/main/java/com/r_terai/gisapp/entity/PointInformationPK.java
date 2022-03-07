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
public class PointInformationPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "POINT_ID")
    private String pointId;
    @Basic(optional = false)
    @Column(name = "NAME")
    private String name;

    public PointInformationPK() {
    }

    public PointInformationPK(String pointId, String name) {
        this.pointId = pointId;
        this.name = name;
    }

    public String getPointId() {
        return pointId;
    }

    public void setPointId(String pointId) {
        this.pointId = pointId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pointId != null ? pointId.hashCode() : 0);
        hash += (name != null ? name.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PointInformationPK)) {
            return false;
        }
        PointInformationPK other = (PointInformationPK) object;
        if ((this.pointId == null && other.pointId != null) || (this.pointId != null && !this.pointId.equals(other.pointId))) {
            return false;
        }
        if ((this.name == null && other.name != null) || (this.name != null && !this.name.equals(other.name))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.r_terai.gisapp.entity.PointInformationPK[ pointId=" + pointId + ", name=" + name + " ]";
    }
    
}
