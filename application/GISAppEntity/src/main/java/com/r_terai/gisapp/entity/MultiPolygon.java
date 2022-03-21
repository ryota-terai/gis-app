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
@Table(name = "MULTI_POLYGON")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MultiPolygon.findAll", query = "SELECT m FROM MultiPolygon m"),
    @NamedQuery(name = "MultiPolygon.findByMultiPolygonId", query = "SELECT m FROM MultiPolygon m WHERE m.multiPolygonId = :multiPolygonId"),
    @NamedQuery(name = "MultiPolygon.findByPrivate1", query = "SELECT m FROM MultiPolygon m WHERE m.private1 = :private1"),
    @NamedQuery(name = "MultiPolygon.findByType", query = "SELECT m FROM MultiPolygon m WHERE m.type = :type"),
    @NamedQuery(name = "MultiPolygon.findByUpdateTime", query = "SELECT m FROM MultiPolygon m WHERE m.updateTime = :updateTime")})
public class MultiPolygon implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "MULTI_POLYGON_ID")
    private Integer multiPolygonId;
    @Basic(optional = false)
    @Column(name = "PRIVATE")
    private short private1;
    @Basic(optional = false)
    @Column(name = "TYPE")
    private String type;
    @Lob
    @Column(name = "MULTI_POLYGON")
    private String multiPolygon;
    @Basic(optional = false)
    @Column(name = "UPDATE_TIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updateTime;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "multiPolygon")
    private Collection<MultiPolygonGeometry> multiPolygonGeometryCollection;

    public MultiPolygon() {
    }

    public MultiPolygon(Integer multiPolygonId) {
        this.multiPolygonId = multiPolygonId;
    }

    public MultiPolygon(Integer multiPolygonId, short private1, String type, Date updateTime) {
        this.multiPolygonId = multiPolygonId;
        this.private1 = private1;
        this.type = type;
        this.updateTime = updateTime;
    }

    public Integer getMultiPolygonId() {
        return multiPolygonId;
    }

    public void setMultiPolygonId(Integer multiPolygonId) {
        this.multiPolygonId = multiPolygonId;
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

    public String getMultiPolygon() {
        return multiPolygon;
    }

    public void setMultiPolygon(String multiPolygon) {
        this.multiPolygon = multiPolygon;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    @XmlTransient
    public Collection<MultiPolygonGeometry> getMultiPolygonGeometryCollection() {
        return multiPolygonGeometryCollection;
    }

    public void setMultiPolygonGeometryCollection(Collection<MultiPolygonGeometry> multiPolygonGeometryCollection) {
        this.multiPolygonGeometryCollection = multiPolygonGeometryCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (multiPolygonId != null ? multiPolygonId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MultiPolygon)) {
            return false;
        }
        MultiPolygon other = (MultiPolygon) object;
        if ((this.multiPolygonId == null && other.multiPolygonId != null) || (this.multiPolygonId != null && !this.multiPolygonId.equals(other.multiPolygonId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.r_terai.gisapp.entity.MultiPolygon[ multiPolygonId=" + multiPolygonId + " ]";
    }
    
}
