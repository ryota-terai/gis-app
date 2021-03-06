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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
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
@Table(name = "POINT")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Point.findAll", query = "SELECT p FROM Point p"),
    @NamedQuery(name = "Point.findByPointId", query = "SELECT p FROM Point p WHERE p.pointId = :pointId"),
    @NamedQuery(name = "Point.findByPrivate1", query = "SELECT p FROM Point p WHERE p.private1 = :private1"),
    @NamedQuery(name = "Point.findByType", query = "SELECT p FROM Point p WHERE p.type = :type"),
    @NamedQuery(name = "Point.findByX", query = "SELECT p FROM Point p WHERE p.x = :x"),
    @NamedQuery(name = "Point.findByY", query = "SELECT p FROM Point p WHERE p.y = :y"),
    @NamedQuery(name = "Point.findByZ", query = "SELECT p FROM Point p WHERE p.z = :z"),
    @NamedQuery(name = "Point.findByUpdateTime", query = "SELECT p FROM Point p WHERE p.updateTime = :updateTime")})
public class Point implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "POINT_ID")
    private Integer pointId;
    @Basic(optional = false)
    @Column(name = "PRIVATE")
    private short private1;
    @Basic(optional = false)
    @Column(name = "TYPE")
    private String type;
    @Basic(optional = false)
    @Column(name = "X")
    private double x;
    @Basic(optional = false)
    @Column(name = "Y")
    private double y;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "Z")
    private Double z;
    @Basic(optional = false)
    @Column(name = "UPDATE_TIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updateTime;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "pointId")
    private Collection<MultiPolygonGeometry> multiPolygonGeometryCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "pointId")
    private Collection<MultiLineStringGeometry> multiLineStringGeometryCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "pointId")
    private Collection<PolygonGeometry> polygonGeometryCollection;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "point")
    private File file;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "pointId")
    private Collection<LineStringGeometry> lineStringGeometryCollection;

    public Point() {
    }

    public Point(Integer pointId) {
        this.pointId = pointId;
    }

    public Point(Integer pointId, short private1, String type, double x, double y, Date updateTime) {
        this.pointId = pointId;
        this.private1 = private1;
        this.type = type;
        this.x = x;
        this.y = y;
        this.updateTime = updateTime;
    }

    public Integer getPointId() {
        return pointId;
    }

    public void setPointId(Integer pointId) {
        this.pointId = pointId;
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

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public Double getZ() {
        return z;
    }

    public void setZ(Double z) {
        this.z = z;
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

    @XmlTransient
    public Collection<MultiLineStringGeometry> getMultiLineStringGeometryCollection() {
        return multiLineStringGeometryCollection;
    }

    public void setMultiLineStringGeometryCollection(Collection<MultiLineStringGeometry> multiLineStringGeometryCollection) {
        this.multiLineStringGeometryCollection = multiLineStringGeometryCollection;
    }

    @XmlTransient
    public Collection<PolygonGeometry> getPolygonGeometryCollection() {
        return polygonGeometryCollection;
    }

    public void setPolygonGeometryCollection(Collection<PolygonGeometry> polygonGeometryCollection) {
        this.polygonGeometryCollection = polygonGeometryCollection;
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
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
        hash += (pointId != null ? pointId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Point)) {
            return false;
        }
        Point other = (Point) object;
        if ((this.pointId == null && other.pointId != null) || (this.pointId != null && !this.pointId.equals(other.pointId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.r_terai.gisapp.entity.Point[ pointId=" + pointId + " ]";
    }
    
}
