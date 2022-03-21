/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.r_terai.gisapp.entity;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author r-terai
 */
@Entity
@Table(name = "LINE_STRING")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "LineString.findAll", query = "SELECT l FROM LineString l"),
    @NamedQuery(name = "LineString.findByLineStringId", query = "SELECT l FROM LineString l WHERE l.lineStringId = :lineStringId"),
    @NamedQuery(name = "LineString.findByPrivate1", query = "SELECT l FROM LineString l WHERE l.private1 = :private1"),
    @NamedQuery(name = "LineString.findByType", query = "SELECT l FROM LineString l WHERE l.type = :type"),
    @NamedQuery(name = "LineString.findByUpdateTime", query = "SELECT l FROM LineString l WHERE l.updateTime = :updateTime")})
public class LineString implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "LINE_STRING_ID")
    private Integer lineStringId;
    @Basic(optional = false)
    @Column(name = "PRIVATE")
    private short private1;
    @Basic(optional = false)
    @Column(name = "TYPE")
    private String type;
    @Lob
    @Column(name = "LINE_STRING")
    private String lineString;
    @Basic(optional = false)
    @Column(name = "UPDATE_TIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updateTime;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "lineString")
    private Collection<LineStringGeometry> lineStringGeometryCollection;

    public LineString() {
    }

    public LineString(Integer lineStringId) {
        this.lineStringId = lineStringId;
    }

    public LineString(Integer lineStringId, short private1, String type, Date updateTime) {
        this.lineStringId = lineStringId;
        this.private1 = private1;
        this.type = type;
        this.updateTime = updateTime;
    }

    public Integer getLineStringId() {
        return lineStringId;
    }

    public void setLineStringId(Integer lineStringId) {
        this.lineStringId = lineStringId;
    }

    public short getPrivate1() {
        return private1;
    }

    public void setPrivate1(short private1) {
        this.private1 = private1;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getLineString() {
        return lineString;
    }

    public void setLineString(String lineString) {
        this.lineString = lineString;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    @XmlTransient
    public Collection<LineStringGeometry> getLineStringGeometryCollection() {
        return lineStringGeometryCollection;
    }

    public void setLineStringGeometryCollection(Collection<LineStringGeometry> lineStringGeometryCollection) {
        this.lineStringGeometryCollection = lineStringGeometryCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (lineStringId != null ? lineStringId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof LineString)) {
            return false;
        }
        LineString other = (LineString) object;
        if ((this.lineStringId == null && other.lineStringId != null) || (this.lineStringId != null && !this.lineStringId.equals(other.lineStringId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.r_terai.gisapp.entity.LineString[ lineStringId=" + lineStringId + " ]";
    }
    
}
