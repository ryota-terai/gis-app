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
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
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
@Table(name = "POST_INFORMATION_VIEW")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PostInformationView.findAll", query = "SELECT p FROM PostInformationView p"),
    @NamedQuery(name = "PostInformationView.findByPointId", query = "SELECT p FROM PostInformationView p WHERE p.pointId = :pointId"),
    @NamedQuery(name = "PostInformationView.findByPrivate1", query = "SELECT p FROM PostInformationView p WHERE p.private1 = :private1"),
    @NamedQuery(name = "PostInformationView.findByType", query = "SELECT p FROM PostInformationView p WHERE p.type = :type"),
    @NamedQuery(name = "PostInformationView.findByX", query = "SELECT p FROM PostInformationView p WHERE p.x = :x"),
    @NamedQuery(name = "PostInformationView.findByY", query = "SELECT p FROM PostInformationView p WHERE p.y = :y"),
    @NamedQuery(name = "PostInformationView.findByZ", query = "SELECT p FROM PostInformationView p WHERE p.z = :z"),
    @NamedQuery(name = "PostInformationView.findByApproved", query = "SELECT p FROM PostInformationView p WHERE p.approved = :approved"),
    @NamedQuery(name = "PostInformationView.findByUpdateTime", query = "SELECT p FROM PostInformationView p WHERE p.updateTime = :updateTime")})
public class PostInformationView implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "POINT_ID")
    private String pointId;
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
    @Lob
    @Column(name = "INFORMATION")
    private String information;
    @Column(name = "APPROVED")
    private Short approved;
    @Basic(optional = false)
    @Column(name = "UPDATE_TIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updateTime;

    public PostInformationView() {
    }

    public String getPointId() {
        return pointId;
    }

    public void setPointId(String pointId) {
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

    public String getInformation() {
        return information;
    }

    public void setInformation(String information) {
        this.information = information;
    }

    public Short getApproved() {
        return approved;
    }

    public void setApproved(Short approved) {
        this.approved = approved;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
    
}
