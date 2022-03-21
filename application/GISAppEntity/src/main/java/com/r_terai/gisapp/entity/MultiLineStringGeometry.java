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
@Table(name = "MULTI_LINE_STRING_GEOMETRY")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MultiLineStringGeometry.findAll", query = "SELECT m FROM MultiLineStringGeometry m"),
    @NamedQuery(name = "MultiLineStringGeometry.findByMultiLineStringId", query = "SELECT m FROM MultiLineStringGeometry m WHERE m.multiLineStringGeometryPK.multiLineStringId = :multiLineStringId"),
    @NamedQuery(name = "MultiLineStringGeometry.findByLineStringIndex", query = "SELECT m FROM MultiLineStringGeometry m WHERE m.multiLineStringGeometryPK.lineStringIndex = :lineStringIndex"),
    @NamedQuery(name = "MultiLineStringGeometry.findByPointIndex", query = "SELECT m FROM MultiLineStringGeometry m WHERE m.multiLineStringGeometryPK.pointIndex = :pointIndex"),
    @NamedQuery(name = "MultiLineStringGeometry.findByUpdateTime", query = "SELECT m FROM MultiLineStringGeometry m WHERE m.updateTime = :updateTime")})
public class MultiLineStringGeometry implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected MultiLineStringGeometryPK multiLineStringGeometryPK;
    @Basic(optional = false)
    @Column(name = "UPDATE_TIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updateTime;
    @JoinColumn(name = "MULTI_LINE_STRING_ID", referencedColumnName = "MULTI_LINE_STRING_ID", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private MultiLineString multiLineString;
    @JoinColumn(name = "POINT_ID", referencedColumnName = "POINT_ID")
    @ManyToOne(optional = false)
    private Point pointId;

    public MultiLineStringGeometry() {
    }

    public MultiLineStringGeometry(MultiLineStringGeometryPK multiLineStringGeometryPK) {
        this.multiLineStringGeometryPK = multiLineStringGeometryPK;
    }

    public MultiLineStringGeometry(MultiLineStringGeometryPK multiLineStringGeometryPK, Date updateTime) {
        this.multiLineStringGeometryPK = multiLineStringGeometryPK;
        this.updateTime = updateTime;
    }

    public MultiLineStringGeometry(int multiLineStringId, int lineStringIndex, int pointIndex) {
        this.multiLineStringGeometryPK = new MultiLineStringGeometryPK(multiLineStringId, lineStringIndex, pointIndex);
    }

    public MultiLineStringGeometryPK getMultiLineStringGeometryPK() {
        return multiLineStringGeometryPK;
    }

    public void setMultiLineStringGeometryPK(MultiLineStringGeometryPK multiLineStringGeometryPK) {
        this.multiLineStringGeometryPK = multiLineStringGeometryPK;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public MultiLineString getMultiLineString() {
        return multiLineString;
    }

    public void setMultiLineString(MultiLineString multiLineString) {
        this.multiLineString = multiLineString;
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
        hash += (multiLineStringGeometryPK != null ? multiLineStringGeometryPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MultiLineStringGeometry)) {
            return false;
        }
        MultiLineStringGeometry other = (MultiLineStringGeometry) object;
        if ((this.multiLineStringGeometryPK == null && other.multiLineStringGeometryPK != null) || (this.multiLineStringGeometryPK != null && !this.multiLineStringGeometryPK.equals(other.multiLineStringGeometryPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.r_terai.gisapp.entity.MultiLineStringGeometry[ multiLineStringGeometryPK=" + multiLineStringGeometryPK + " ]";
    }
    
}
