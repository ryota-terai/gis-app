/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.r_terai.gisapp.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author r-terai
 */
@Entity
@Table(name = "GEOJSON_FILE_LOCATION")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "GeojsonFileLocation.findAll", query = "SELECT g FROM GeojsonFileLocation g"),
    @NamedQuery(name = "GeojsonFileLocation.findById", query = "SELECT g FROM GeojsonFileLocation g WHERE g.id = :id"),
    @NamedQuery(name = "GeojsonFileLocation.findByType", query = "SELECT g FROM GeojsonFileLocation g WHERE g.type = :type"),
    @NamedQuery(name = "GeojsonFileLocation.findByAreaCode", query = "SELECT g FROM GeojsonFileLocation g WHERE g.areaCode = :areaCode")})
public class GeojsonFileLocation implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "TYPE")
    private String type;
    @Column(name = "AREA_CODE")
    private String areaCode;
    @Basic(optional = false)
    @Lob
    @Column(name = "LOCATION")
    private String location;

    public GeojsonFileLocation() {
    }

    public GeojsonFileLocation(Integer id) {
        this.id = id;
    }

    public GeojsonFileLocation(Integer id, String type, String location) {
        this.id = id;
        this.type = type;
        this.location = location;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof GeojsonFileLocation)) {
            return false;
        }
        GeojsonFileLocation other = (GeojsonFileLocation) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.r_terai.gisapp.entity.GeojsonFileLocation[ id=" + id + " ]";
    }
    
}
