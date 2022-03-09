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
import javax.persistence.Lob;
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
@Table(name = "POINT_INFORMATION")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PointInformation.findAll", query = "SELECT p FROM PointInformation p"),
    @NamedQuery(name = "PointInformation.findByPointId", query = "SELECT p FROM PointInformation p WHERE p.pointInformationPK.pointId = :pointId"),
    @NamedQuery(name = "PointInformation.findByName", query = "SELECT p FROM PointInformation p WHERE p.pointInformationPK.name = :name"),
    @NamedQuery(name = "PointInformation.findByType", query = "SELECT p FROM PointInformation p WHERE p.type = :type"),
    @NamedQuery(name = "PointInformation.findByNumber", query = "SELECT p FROM PointInformation p WHERE p.number = :number"),
    @NamedQuery(name = "PointInformation.findByBoolean1", query = "SELECT p FROM PointInformation p WHERE p.boolean1 = :boolean1"),
    @NamedQuery(name = "PointInformation.findByUpdateTime", query = "SELECT p FROM PointInformation p WHERE p.updateTime = :updateTime")})
public class PointInformation implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected PointInformationPK pointInformationPK;
    @Basic(optional = false)
    @Column(name = "TYPE")
    private String type;
    @Lob
    @Column(name = "STRING")
    private String string;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "NUMBER")
    private Double number;
    @Column(name = "BOOLEAN")
    private Short boolean1;
    @Basic(optional = false)
    @Column(name = "UPDATE_TIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updateTime;
    @JoinColumn(name = "POINT_ID", referencedColumnName = "POINT_ID", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Point point;

    public PointInformation() {
    }

    public PointInformation(PointInformationPK pointInformationPK) {
        this.pointInformationPK = pointInformationPK;
    }

    public PointInformation(PointInformationPK pointInformationPK, String type, Date updateTime) {
        this.pointInformationPK = pointInformationPK;
        this.type = type;
        this.updateTime = updateTime;
    }

    public PointInformation(String pointId, String name) {
        this.pointInformationPK = new PointInformationPK(pointId, name);
    }

    public PointInformationPK getPointInformationPK() {
        return pointInformationPK;
    }

    public void setPointInformationPK(PointInformationPK pointInformationPK) {
        this.pointInformationPK = pointInformationPK;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getString() {
        return string;
    }

    public void setString(String string) {
        this.string = string;
    }

    public Double getNumber() {
        return number;
    }

    public void setNumber(Double number) {
        this.number = number;
    }

    public Short getBoolean1() {
        return boolean1;
    }

    public void setBoolean1(Short boolean1) {
        this.boolean1 = boolean1;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Point getPoint() {
        return point;
    }

    public void setPoint(Point point) {
        this.point = point;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pointInformationPK != null ? pointInformationPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PointInformation)) {
            return false;
        }
        PointInformation other = (PointInformation) object;
        if ((this.pointInformationPK == null && other.pointInformationPK != null) || (this.pointInformationPK != null && !this.pointInformationPK.equals(other.pointInformationPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.r_terai.gisapp.entity.PointInformation[ pointInformationPK=" + pointInformationPK + " ]";
    }
    
}
