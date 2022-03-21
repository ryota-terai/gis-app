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
@Table(name = "POLYGON")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Polygon.findAll", query = "SELECT p FROM Polygon p"),
    @NamedQuery(name = "Polygon.findByPolygonId", query = "SELECT p FROM Polygon p WHERE p.polygonId = :polygonId"),
    @NamedQuery(name = "Polygon.findByPrivate1", query = "SELECT p FROM Polygon p WHERE p.private1 = :private1"),
    @NamedQuery(name = "Polygon.findByType", query = "SELECT p FROM Polygon p WHERE p.type = :type"),
    @NamedQuery(name = "Polygon.findByUpdateTime", query = "SELECT p FROM Polygon p WHERE p.updateTime = :updateTime")})
public class Polygon implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "POLYGON_ID")
    private Integer polygonId;
    @Basic(optional = false)
    @Column(name = "PRIVATE")
    private short private1;
    @Basic(optional = false)
    @Column(name = "TYPE")
    private String type;
    @Lob
    @Column(name = "POLYGON")
    private String polygon;
    @Basic(optional = false)
    @Column(name = "UPDATE_TIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updateTime;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "polygon")
    private Collection<PolygonGeometry> polygonGeometryCollection;

    public Polygon() {
    }

    public Polygon(Integer polygonId) {
        this.polygonId = polygonId;
    }

    public Polygon(Integer polygonId, short private1, String type, Date updateTime) {
        this.polygonId = polygonId;
        this.private1 = private1;
        this.type = type;
        this.updateTime = updateTime;
    }

    public Integer getPolygonId() {
        return polygonId;
    }

    public void setPolygonId(Integer polygonId) {
        this.polygonId = polygonId;
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

    public String getPolygon() {
        return polygon;
    }

    public void setPolygon(String polygon) {
        this.polygon = polygon;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    @XmlTransient
    public Collection<PolygonGeometry> getPolygonGeometryCollection() {
        return polygonGeometryCollection;
    }

    public void setPolygonGeometryCollection(Collection<PolygonGeometry> polygonGeometryCollection) {
        this.polygonGeometryCollection = polygonGeometryCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (polygonId != null ? polygonId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Polygon)) {
            return false;
        }
        Polygon other = (Polygon) object;
        if ((this.polygonId == null && other.polygonId != null) || (this.polygonId != null && !this.polygonId.equals(other.polygonId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.r_terai.gisapp.entity.Polygon[ polygonId=" + polygonId + " ]";
    }
    
}
