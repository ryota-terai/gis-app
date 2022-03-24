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
@Table(name = "A33_LINE_STRING_VIEW")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "A33LineStringView.findAll", query = "SELECT a FROM A33LineStringView a"),
    @NamedQuery(name = "A33LineStringView.findByLineStringId", query = "SELECT a FROM A33LineStringView a WHERE a.lineStringId = :lineStringId"),
    @NamedQuery(name = "A33LineStringView.findByPrivate1", query = "SELECT a FROM A33LineStringView a WHERE a.private1 = :private1"),
    @NamedQuery(name = "A33LineStringView.findByType", query = "SELECT a FROM A33LineStringView a WHERE a.type = :type"),
    @NamedQuery(name = "A33LineStringView.findByUpdateTime", query = "SELECT a FROM A33LineStringView a WHERE a.updateTime = :updateTime")})
public class A33LineStringView implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "LINE_STRING_ID")
    private int lineStringId;
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
    @Lob
    @Column(name = "A33_001")
    private String a33001;
    @Lob
    @Column(name = "A33_002")
    private String a33002;
    @Lob
    @Column(name = "A33_003")
    private String a33003;
    @Lob
    @Column(name = "A33_004")
    private String a33004;
    @Lob
    @Column(name = "A33_005")
    private String a33005;
    @Lob
    @Column(name = "A33_006")
    private String a33006;
    @Lob
    @Column(name = "A33_007")
    private String a33007;
    @Lob
    @Column(name = "A33_008")
    private String a33008;

    public A33LineStringView() {
    }

    public int getLineStringId() {
        return lineStringId;
    }

    public void setLineStringId(int lineStringId) {
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

    public String getA33001() {
        return a33001;
    }

    public void setA33001(String a33001) {
        this.a33001 = a33001;
    }

    public String getA33002() {
        return a33002;
    }

    public void setA33002(String a33002) {
        this.a33002 = a33002;
    }

    public String getA33003() {
        return a33003;
    }

    public void setA33003(String a33003) {
        this.a33003 = a33003;
    }

    public String getA33004() {
        return a33004;
    }

    public void setA33004(String a33004) {
        this.a33004 = a33004;
    }

    public String getA33005() {
        return a33005;
    }

    public void setA33005(String a33005) {
        this.a33005 = a33005;
    }

    public String getA33006() {
        return a33006;
    }

    public void setA33006(String a33006) {
        this.a33006 = a33006;
    }

    public String getA33007() {
        return a33007;
    }

    public void setA33007(String a33007) {
        this.a33007 = a33007;
    }

    public String getA33008() {
        return a33008;
    }

    public void setA33008(String a33008) {
        this.a33008 = a33008;
    }
    
}
