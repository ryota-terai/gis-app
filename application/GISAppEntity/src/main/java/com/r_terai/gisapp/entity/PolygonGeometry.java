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
@Table(name = "POLYGON_GEOMETRY")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PolygonGeometry.findAll", query = "SELECT p FROM PolygonGeometry p"),
    @NamedQuery(name = "PolygonGeometry.findByPolygonId", query = "SELECT p FROM PolygonGeometry p WHERE p.polygonGeometryPK.polygonId = :polygonId"),
    @NamedQuery(name = "PolygonGeometry.findByPolygonIndex", query = "SELECT p FROM PolygonGeometry p WHERE p.polygonGeometryPK.polygonIndex = :polygonIndex"),
    @NamedQuery(name = "PolygonGeometry.findByPointIndex", query = "SELECT p FROM PolygonGeometry p WHERE p.polygonGeometryPK.pointIndex = :pointIndex"),
    @NamedQuery(name = "PolygonGeometry.findByUpdateTime", query = "SELECT p FROM PolygonGeometry p WHERE p.updateTime = :updateTime")})
public class PolygonGeometry implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected PolygonGeometryPK polygonGeometryPK;
    @Basic(optional = false)
    @Column(name = "UPDATE_TIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updateTime;
    @JoinColumn(name = "POINT_ID", referencedColumnName = "POINT_ID")
    @ManyToOne(optional = false)
    private Point pointId;
    @JoinColumn(name = "POLYGON_ID", referencedColumnName = "POLYGON_ID", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Polygon polygon;

    public PolygonGeometry() {
    }

    public PolygonGeometry(PolygonGeometryPK polygonGeometryPK) {
        this.polygonGeometryPK = polygonGeometryPK;
    }

    public PolygonGeometry(PolygonGeometryPK polygonGeometryPK, Date updateTime) {
        this.polygonGeometryPK = polygonGeometryPK;
        this.updateTime = updateTime;
    }

    public PolygonGeometry(int polygonId, int polygonIndex, int pointIndex) {
        this.polygonGeometryPK = new PolygonGeometryPK(polygonId, polygonIndex, pointIndex);
    }

    public PolygonGeometryPK getPolygonGeometryPK() {
        return polygonGeometryPK;
    }

    public void setPolygonGeometryPK(PolygonGeometryPK polygonGeometryPK) {
        this.polygonGeometryPK = polygonGeometryPK;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Point getPointId() {
        return pointId;
    }

    public void setPointId(Point pointId) {
        this.pointId = pointId;
    }

    public Polygon getPolygon() {
        return polygon;
    }

    public void setPolygon(Polygon polygon) {
        this.polygon = polygon;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (polygonGeometryPK != null ? polygonGeometryPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PolygonGeometry)) {
            return false;
        }
        PolygonGeometry other = (PolygonGeometry) object;
        if ((this.polygonGeometryPK == null && other.polygonGeometryPK != null) || (this.polygonGeometryPK != null && !this.polygonGeometryPK.equals(other.polygonGeometryPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.r_terai.gisapp.entity.PolygonGeometry[ polygonGeometryPK=" + polygonGeometryPK + " ]";
    }
    
}
