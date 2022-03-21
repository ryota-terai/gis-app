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
@Table(name = "MULTI_LINE_STRING")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MultiLineString.findAll", query = "SELECT m FROM MultiLineString m"),
    @NamedQuery(name = "MultiLineString.findByMultiLineStringId", query = "SELECT m FROM MultiLineString m WHERE m.multiLineStringId = :multiLineStringId"),
    @NamedQuery(name = "MultiLineString.findByPrivate1", query = "SELECT m FROM MultiLineString m WHERE m.private1 = :private1"),
    @NamedQuery(name = "MultiLineString.findByType", query = "SELECT m FROM MultiLineString m WHERE m.type = :type"),
    @NamedQuery(name = "MultiLineString.findByUpdateTime", query = "SELECT m FROM MultiLineString m WHERE m.updateTime = :updateTime")})
public class MultiLineString implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "MULTI_LINE_STRING_ID")
    private Integer multiLineStringId;
    @Basic(optional = false)
    @Column(name = "PRIVATE")
    private short private1;
    @Basic(optional = false)
    @Column(name = "TYPE")
    private String type;
    @Lob
    @Column(name = "MULTI_LINE_STRING")
    private String multiLineString;
    @Basic(optional = false)
    @Column(name = "UPDATE_TIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updateTime;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "multiLineString")
    private Collection<MultiLineStringGeometry> multiLineStringGeometryCollection;

    public MultiLineString() {
    }

    public MultiLineString(Integer multiLineStringId) {
        this.multiLineStringId = multiLineStringId;
    }

    public MultiLineString(Integer multiLineStringId, short private1, String type, Date updateTime) {
        this.multiLineStringId = multiLineStringId;
        this.private1 = private1;
        this.type = type;
        this.updateTime = updateTime;
    }

    public Integer getMultiLineStringId() {
        return multiLineStringId;
    }

    public void setMultiLineStringId(Integer multiLineStringId) {
        this.multiLineStringId = multiLineStringId;
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

    public String getMultiLineString() {
        return multiLineString;
    }

    public void setMultiLineString(String multiLineString) {
        this.multiLineString = multiLineString;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    @XmlTransient
    public Collection<MultiLineStringGeometry> getMultiLineStringGeometryCollection() {
        return multiLineStringGeometryCollection;
    }

    public void setMultiLineStringGeometryCollection(Collection<MultiLineStringGeometry> multiLineStringGeometryCollection) {
        this.multiLineStringGeometryCollection = multiLineStringGeometryCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (multiLineStringId != null ? multiLineStringId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MultiLineString)) {
            return false;
        }
        MultiLineString other = (MultiLineString) object;
        if ((this.multiLineStringId == null && other.multiLineStringId != null) || (this.multiLineStringId != null && !this.multiLineStringId.equals(other.multiLineStringId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.r_terai.gisapp.entity.MultiLineString[ multiLineStringId=" + multiLineStringId + " ]";
    }
    
}
