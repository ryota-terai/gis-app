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
@Table(name = "GEOJSON_FILE_QUEUE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "GeojsonFileQueue.findAll", query = "SELECT g FROM GeojsonFileQueue g"),
    @NamedQuery(name = "GeojsonFileQueue.findById", query = "SELECT g FROM GeojsonFileQueue g WHERE g.id = :id"),
    @NamedQuery(name = "GeojsonFileQueue.findByPrivate1", query = "SELECT g FROM GeojsonFileQueue g WHERE g.private1 = :private1"),
    @NamedQuery(name = "GeojsonFileQueue.findByType", query = "SELECT g FROM GeojsonFileQueue g WHERE g.type = :type")})
public class GeojsonFileQueue implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "PRIVATE")
    private short private1;
    @Basic(optional = false)
    @Column(name = "TYPE")
    private String type;
    @Basic(optional = false)
    @Lob
    @Column(name = "GEOJSON")
    private String geojson;

    public GeojsonFileQueue() {
    }

    public GeojsonFileQueue(Integer id) {
        this.id = id;
    }

    public GeojsonFileQueue(Integer id, short private1, String type, String geojson) {
        this.id = id;
        this.private1 = private1;
        this.type = type;
        this.geojson = geojson;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getGeojson() {
        return geojson;
    }

    public void setGeojson(String geojson) {
        this.geojson = geojson;
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
        if (!(object instanceof GeojsonFileQueue)) {
            return false;
        }
        GeojsonFileQueue other = (GeojsonFileQueue) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.r_terai.gisapp.entity.GeojsonFileQueue[ id=" + id + " ]";
    }
    
}
