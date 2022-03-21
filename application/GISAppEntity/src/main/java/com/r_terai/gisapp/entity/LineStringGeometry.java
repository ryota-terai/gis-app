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
@Table(name = "LINE_STRING_GEOMETRY")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "LineStringGeometry.findAll", query = "SELECT l FROM LineStringGeometry l"),
    @NamedQuery(name = "LineStringGeometry.findByLineStringId", query = "SELECT l FROM LineStringGeometry l WHERE l.lineStringGeometryPK.lineStringId = :lineStringId"),
    @NamedQuery(name = "LineStringGeometry.findByPointIndex", query = "SELECT l FROM LineStringGeometry l WHERE l.lineStringGeometryPK.pointIndex = :pointIndex"),
    @NamedQuery(name = "LineStringGeometry.findByUpdateTime", query = "SELECT l FROM LineStringGeometry l WHERE l.updateTime = :updateTime")})
public class LineStringGeometry implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected LineStringGeometryPK lineStringGeometryPK;
    @Basic(optional = false)
    @Column(name = "UPDATE_TIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updateTime;
    @JoinColumn(name = "LINE_STRING_ID", referencedColumnName = "LINE_STRING_ID", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private LineString lineString;
    @JoinColumn(name = "POINT_ID", referencedColumnName = "POINT_ID")
    @ManyToOne(optional = false)
    private Point pointId;

    public LineStringGeometry() {
    }

    public LineStringGeometry(LineStringGeometryPK lineStringGeometryPK) {
        this.lineStringGeometryPK = lineStringGeometryPK;
    }

    public LineStringGeometry(LineStringGeometryPK lineStringGeometryPK, Date updateTime) {
        this.lineStringGeometryPK = lineStringGeometryPK;
        this.updateTime = updateTime;
    }

    public LineStringGeometry(int lineStringId, int pointIndex) {
        this.lineStringGeometryPK = new LineStringGeometryPK(lineStringId, pointIndex);
    }

    public LineStringGeometryPK getLineStringGeometryPK() {
        return lineStringGeometryPK;
    }

    public void setLineStringGeometryPK(LineStringGeometryPK lineStringGeometryPK) {
        this.lineStringGeometryPK = lineStringGeometryPK;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public LineString getLineString() {
        return lineString;
    }

    public void setLineString(LineString lineString) {
        this.lineString = lineString;
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
        hash += (lineStringGeometryPK != null ? lineStringGeometryPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof LineStringGeometry)) {
            return false;
        }
        LineStringGeometry other = (LineStringGeometry) object;
        if ((this.lineStringGeometryPK == null && other.lineStringGeometryPK != null) || (this.lineStringGeometryPK != null && !this.lineStringGeometryPK.equals(other.lineStringGeometryPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.r_terai.gisapp.entity.LineStringGeometry[ lineStringGeometryPK=" + lineStringGeometryPK + " ]";
    }
    
}
