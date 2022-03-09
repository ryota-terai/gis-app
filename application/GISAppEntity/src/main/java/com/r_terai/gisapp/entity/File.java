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
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author r-terai
 */
@Entity
@Table(name = "FILE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "File.findAll", query = "SELECT f FROM File f"),
    @NamedQuery(name = "File.findByPointId", query = "SELECT f FROM File f WHERE f.pointId = :pointId")})
public class File implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "POINT_ID")
    private String pointId;
    @Basic(optional = false)
    @Lob
    @Column(name = "FILE")
    private byte[] file;
    @JoinColumn(name = "POINT_ID", referencedColumnName = "POINT_ID", insertable = false, updatable = false)
    @OneToOne(optional = false)
    private Point point;

    public File() {
    }

    public File(String pointId) {
        this.pointId = pointId;
    }

    public File(String pointId, byte[] file) {
        this.pointId = pointId;
        this.file = file;
    }

    public String getPointId() {
        return pointId;
    }

    public void setPointId(String pointId) {
        this.pointId = pointId;
    }

    public byte[] getFile() {
        return file;
    }

    public void setFile(byte[] file) {
        this.file = file;
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
        hash += (pointId != null ? pointId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof File)) {
            return false;
        }
        File other = (File) object;
        if ((this.pointId == null && other.pointId != null) || (this.pointId != null && !this.pointId.equals(other.pointId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.r_terai.gisapp.entity.File[ pointId=" + pointId + " ]";
    }
    
}
