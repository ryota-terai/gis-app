/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.r_terai.gisapp.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author r-terai
 */
@Entity
@Table(name = "MULTI_POLYGON_GEOMETRY")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MultiPolygonGeometry.findAll", query = "SELECT m FROM MultiPolygonGeometry m"),
    @NamedQuery(name = "MultiPolygonGeometry.findByMultiPolygonId", query = "SELECT m FROM MultiPolygonGeometry m WHERE m.multiPolygonGeometryPK.multiPolygonId = :multiPolygonId"),
    @NamedQuery(name = "MultiPolygonGeometry.findByMultiPolygonIndex", query = "SELECT m FROM MultiPolygonGeometry m WHERE m.multiPolygonGeometryPK.multiPolygonIndex = :multiPolygonIndex"),
    @NamedQuery(name = "MultiPolygonGeometry.findByPolygonIndex", query = "SELECT m FROM MultiPolygonGeometry m WHERE m.multiPolygonGeometryPK.polygonIndex = :polygonIndex"),
    @NamedQuery(name = "MultiPolygonGeometry.findByPointIndex", query = "SELECT m FROM MultiPolygonGeometry m WHERE m.multiPolygonGeometryPK.pointIndex = :pointIndex"),
    @NamedQuery(name = "MultiPolygonGeometry.findByUpdateTime", query = "SELECT m FROM MultiPolygonGeometry m WHERE m.updateTime = :updateTime")})
public class MultiPolygonGeometry implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected MultiPolygonGeometryPK multiPolygonGeometryPK;
    @Basic(optional = false)
    @Column(name = "UPDATE_TIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updateTime;
    @JoinColumn(name = "MULTI_POLYGON_ID", referencedColumnName = "MULTI_POLYGON_ID", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private MultiPolygon multiPolygon;
    @JoinColumn(name = "POINT_ID", referencedColumnName = "POINT_ID")
    @ManyToOne(optional = false)
    private Point pointId;

    public MultiPolygonGeometry() {
    }

    public MultiPolygonGeometry(MultiPolygonGeometryPK multiPolygonGeometryPK) {
        this.multiPolygonGeometryPK = multiPolygonGeometryPK;
    }

    public MultiPolygonGeometry(MultiPolygonGeometryPK multiPolygonGeometryPK, Date updateTime) {
        this.multiPolygonGeometryPK = multiPolygonGeometryPK;
        this.updateTime = updateTime;
    }

    public MultiPolygonGeometry(int multiPolygonId, int multiPolygonIndex, int polygonIndex, int pointIndex) {
        this.multiPolygonGeometryPK = new MultiPolygonGeometryPK(multiPolygonId, multiPolygonIndex, polygonIndex, pointIndex);
    }

    public MultiPolygonGeometryPK getMultiPolygonGeometryPK() {
        return multiPolygonGeometryPK;
    }

    public void setMultiPolygonGeometryPK(MultiPolygonGeometryPK multiPolygonGeometryPK) {
        this.multiPolygonGeometryPK = multiPolygonGeometryPK;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public MultiPolygon getMultiPolygon() {
        return multiPolygon;
    }

    public void setMultiPolygon(MultiPolygon multiPolygon) {
        this.multiPolygon = multiPolygon;
    }

    public Point getPointId() {
        return pointId;
    }

    public void setPointId(Point pointId) {
        this.pointId = pointId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (multiPolygonGeometryPK != null ? multiPolygonGeometryPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MultiPolygonGeometry)) {
            return false;
        }
        MultiPolygonGeometry other = (MultiPolygonGeometry) object;
        if ((this.multiPolygonGeometryPK == null && other.multiPolygonGeometryPK != null) || (this.multiPolygonGeometryPK != null && !this.multiPolygonGeometryPK.equals(other.multiPolygonGeometryPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.r_terai.gisapp.entity.MultiPolygonGeometry[ multiPolygonGeometryPK=" + multiPolygonGeometryPK + " ]";
    }
    
}
