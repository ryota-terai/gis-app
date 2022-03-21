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
@Table(name = "INFORMATION")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Information.findAll", query = "SELECT i FROM Information i"),
    @NamedQuery(name = "Information.findByIdType", query = "SELECT i FROM Information i WHERE i.informationPK.idType = :idType"),
    @NamedQuery(name = "Information.findById", query = "SELECT i FROM Information i WHERE i.informationPK.id = :id"),
    @NamedQuery(name = "Information.findByName", query = "SELECT i FROM Information i WHERE i.informationPK.name = :name"),
    @NamedQuery(name = "Information.findByType", query = "SELECT i FROM Information i WHERE i.type = :type"),
    @NamedQuery(name = "Information.findByNumber", query = "SELECT i FROM Information i WHERE i.number = :number"),
    @NamedQuery(name = "Information.findByBoolean1", query = "SELECT i FROM Information i WHERE i.boolean1 = :boolean1"),
    @NamedQuery(name = "Information.findByUpdateTime", query = "SELECT i FROM Information i WHERE i.updateTime = :updateTime")})
public class Information implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected InformationPK informationPK;
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

    public Information() {
    }

    public Information(InformationPK informationPK) {
        this.informationPK = informationPK;
    }

    public Information(InformationPK informationPK, String type, Date updateTime) {
        this.informationPK = informationPK;
        this.type = type;
        this.updateTime = updateTime;
    }

    public Information(String idType, int id, String name) {
        this.informationPK = new InformationPK(idType, id, name);
    }

    public InformationPK getInformationPK() {
        return informationPK;
    }

    public void setInformationPK(InformationPK informationPK) {
        this.informationPK = informationPK;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (informationPK != null ? informationPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Information)) {
            return false;
        }
        Information other = (Information) object;
        if ((this.informationPK == null && other.informationPK != null) || (this.informationPK != null && !this.informationPK.equals(other.informationPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.r_terai.gisapp.entity.Information[ informationPK=" + informationPK + " ]";
    }
    
}
