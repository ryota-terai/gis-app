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
@Table(name = "SHELTER_INFORMATION")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ShelterInformation.findAll", query = "SELECT s FROM ShelterInformation s"),
    @NamedQuery(name = "ShelterInformation.findByPointId", query = "SELECT s FROM ShelterInformation s WHERE s.pointId = :pointId"),
    @NamedQuery(name = "ShelterInformation.findByPrivate1", query = "SELECT s FROM ShelterInformation s WHERE s.private1 = :private1"),
    @NamedQuery(name = "ShelterInformation.findByType", query = "SELECT s FROM ShelterInformation s WHERE s.type = :type"),
    @NamedQuery(name = "ShelterInformation.findByX", query = "SELECT s FROM ShelterInformation s WHERE s.x = :x"),
    @NamedQuery(name = "ShelterInformation.findByY", query = "SELECT s FROM ShelterInformation s WHERE s.y = :y"),
    @NamedQuery(name = "ShelterInformation.findByZ", query = "SELECT s FROM ShelterInformation s WHERE s.z = :z"),
    @NamedQuery(name = "ShelterInformation.findByP20005", query = "SELECT s FROM ShelterInformation s WHERE s.p20005 = :p20005"),
    @NamedQuery(name = "ShelterInformation.findByP20006", query = "SELECT s FROM ShelterInformation s WHERE s.p20006 = :p20006"),
    @NamedQuery(name = "ShelterInformation.findByP20007", query = "SELECT s FROM ShelterInformation s WHERE s.p20007 = :p20007"),
    @NamedQuery(name = "ShelterInformation.findByP20008", query = "SELECT s FROM ShelterInformation s WHERE s.p20008 = :p20008"),
    @NamedQuery(name = "ShelterInformation.findByP20009", query = "SELECT s FROM ShelterInformation s WHERE s.p20009 = :p20009"),
    @NamedQuery(name = "ShelterInformation.findByP20010", query = "SELECT s FROM ShelterInformation s WHERE s.p20010 = :p20010"),
    @NamedQuery(name = "ShelterInformation.findByP20011", query = "SELECT s FROM ShelterInformation s WHERE s.p20011 = :p20011"),
    @NamedQuery(name = "ShelterInformation.findByP20012", query = "SELECT s FROM ShelterInformation s WHERE s.p20012 = :p20012"),
    @NamedQuery(name = "ShelterInformation.findByOpen", query = "SELECT s FROM ShelterInformation s WHERE s.open = :open"),
    @NamedQuery(name = "ShelterInformation.findByUpdateTime", query = "SELECT s FROM ShelterInformation s WHERE s.updateTime = :updateTime")})
public class ShelterInformation implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
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
    @Lob
    @Column(name = "P20_001")
    private String p20001;
    @Lob
    @Column(name = "P20_002")
    private String p20002;
    @Lob
    @Column(name = "P20_003")
    private String p20003;
    @Lob
    @Column(name = "P20_004")
    private String p20004;
    @Column(name = "P20_005")
    private Double p20005;
    @Column(name = "P20_006")
    private Double p20006;
    @Column(name = "P20_007")
    private Double p20007;
    @Column(name = "P20_008")
    private Double p20008;
    @Column(name = "P20_009")
    private Double p20009;
    @Column(name = "P20_010")
    private Double p20010;
    @Column(name = "P20_011")
    private Double p20011;
    @Column(name = "P20_012")
    private Double p20012;
    @Column(name = "OPEN")
    private Short open;
    @Lob
    @Column(name = "COMMENT")
    private String comment;
    @Column(name = "UPDATE_TIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updateTime;

    public ShelterInformation() {
    }

    public ShelterInformation(Integer pointId) {
        this.pointId = pointId;
    }

    public ShelterInformation(Integer pointId, short private1, String type, double x, double y) {
        this.pointId = pointId;
        this.private1 = private1;
        this.type = type;
        this.x = x;
        this.y = y;
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

    public String getP20001() {
        return p20001;
    }

    public void setP20001(String p20001) {
        this.p20001 = p20001;
    }

    public String getP20002() {
        return p20002;
    }

    public void setP20002(String p20002) {
        this.p20002 = p20002;
    }

    public String getP20003() {
        return p20003;
    }

    public void setP20003(String p20003) {
        this.p20003 = p20003;
    }

    public String getP20004() {
        return p20004;
    }

    public void setP20004(String p20004) {
        this.p20004 = p20004;
    }

    public Double getP20005() {
        return p20005;
    }

    public void setP20005(Double p20005) {
        this.p20005 = p20005;
    }

    public Double getP20006() {
        return p20006;
    }

    public void setP20006(Double p20006) {
        this.p20006 = p20006;
    }

    public Double getP20007() {
        return p20007;
    }

    public void setP20007(Double p20007) {
        this.p20007 = p20007;
    }

    public Double getP20008() {
        return p20008;
    }

    public void setP20008(Double p20008) {
        this.p20008 = p20008;
    }

    public Double getP20009() {
        return p20009;
    }

    public void setP20009(Double p20009) {
        this.p20009 = p20009;
    }

    public Double getP20010() {
        return p20010;
    }

    public void setP20010(Double p20010) {
        this.p20010 = p20010;
    }

    public Double getP20011() {
        return p20011;
    }

    public void setP20011(Double p20011) {
        this.p20011 = p20011;
    }

    public Double getP20012() {
        return p20012;
    }

    public void setP20012(Double p20012) {
        this.p20012 = p20012;
    }

    public Short getOpen() {
        return open;
    }

    public void setOpen(Short open) {
        this.open = open;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
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
        if (!(object instanceof ShelterInformation)) {
            return false;
        }
        ShelterInformation other = (ShelterInformation) object;
        if ((this.pointId == null && other.pointId != null) || (this.pointId != null && !this.pointId.equals(other.pointId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.r_terai.gisapp.entity.ShelterInformation[ pointId=" + pointId + " ]";
    }
    
}
