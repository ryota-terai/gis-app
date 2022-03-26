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
@Table(name = "A48_MULTI_POLYGON_VIEW")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "A48MultiPolygonView.findAll", query = "SELECT a FROM A48MultiPolygonView a"),
    @NamedQuery(name = "A48MultiPolygonView.findByMultiPolygonId", query = "SELECT a FROM A48MultiPolygonView a WHERE a.multiPolygonId = :multiPolygonId"),
    @NamedQuery(name = "A48MultiPolygonView.findByPrivate1", query = "SELECT a FROM A48MultiPolygonView a WHERE a.private1 = :private1"),
    @NamedQuery(name = "A48MultiPolygonView.findByType", query = "SELECT a FROM A48MultiPolygonView a WHERE a.type = :type"),
    @NamedQuery(name = "A48MultiPolygonView.findByUpdateTime", query = "SELECT a FROM A48MultiPolygonView a WHERE a.updateTime = :updateTime"),
    @NamedQuery(name = "A48MultiPolygonView.findByA48004", query = "SELECT a FROM A48MultiPolygonView a WHERE a.a48004 = :a48004"),
    @NamedQuery(name = "A48MultiPolygonView.findByA48007", query = "SELECT a FROM A48MultiPolygonView a WHERE a.a48007 = :a48007"),
    @NamedQuery(name = "A48MultiPolygonView.findByA48012", query = "SELECT a FROM A48MultiPolygonView a WHERE a.a48012 = :a48012")})
public class A48MultiPolygonView implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "MULTI_POLYGON_ID")
    private int multiPolygonId;
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
    @Lob
    @Column(name = "A48_001")
    private String a48001;
    @Lob
    @Column(name = "A48_002")
    private String a48002;
    @Lob
    @Column(name = "A48_003")
    private String a48003;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "A48_004")
    private Double a48004;
    @Lob
    @Column(name = "A48_005")
    private String a48005;
    @Lob
    @Column(name = "A48_006")
    private String a48006;
    @Column(name = "A48_007")
    private Double a48007;
    @Lob
    @Column(name = "A48_008")
    private String a48008;
    @Lob
    @Column(name = "A48_009")
    private String a48009;
    @Lob
    @Column(name = "A48_010")
    private String a48010;
    @Lob
    @Column(name = "A48_011")
    private String a48011;
    @Column(name = "A48_012")
    private Double a48012;
    @Lob
    @Column(name = "A48_013")
    private String a48013;
    @Lob
    @Column(name = "A48_014")
    private String a48014;

    public A48MultiPolygonView() {
    }

    public int getMultiPolygonId() {
        return multiPolygonId;
    }

    public void setMultiPolygonId(int multiPolygonId) {
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

    public String getA48001() {
        return a48001;
    }

    public void setA48001(String a48001) {
        this.a48001 = a48001;
    }

    public String getA48002() {
        return a48002;
    }

    public void setA48002(String a48002) {
        this.a48002 = a48002;
    }

    public String getA48003() {
        return a48003;
    }

    public void setA48003(String a48003) {
        this.a48003 = a48003;
    }

    public Double getA48004() {
        return a48004;
    }

    public void setA48004(Double a48004) {
        this.a48004 = a48004;
    }

    public String getA48005() {
        return a48005;
    }

    public void setA48005(String a48005) {
        this.a48005 = a48005;
    }

    public String getA48006() {
        return a48006;
    }

    public void setA48006(String a48006) {
        this.a48006 = a48006;
    }

    public Double getA48007() {
        return a48007;
    }

    public void setA48007(Double a48007) {
        this.a48007 = a48007;
    }

    public String getA48008() {
        return a48008;
    }

    public void setA48008(String a48008) {
        this.a48008 = a48008;
    }

    public String getA48009() {
        return a48009;
    }

    public void setA48009(String a48009) {
        this.a48009 = a48009;
    }

    public String getA48010() {
        return a48010;
    }

    public void setA48010(String a48010) {
        this.a48010 = a48010;
    }

    public String getA48011() {
        return a48011;
    }

    public void setA48011(String a48011) {
        this.a48011 = a48011;
    }

    public Double getA48012() {
        return a48012;
    }

    public void setA48012(Double a48012) {
        this.a48012 = a48012;
    }

    public String getA48013() {
        return a48013;
    }

    public void setA48013(String a48013) {
        this.a48013 = a48013;
    }

    public String getA48014() {
        return a48014;
    }

    public void setA48014(String a48014) {
        this.a48014 = a48014;
    }
    
}
