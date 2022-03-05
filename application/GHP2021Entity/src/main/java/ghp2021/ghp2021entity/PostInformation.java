/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ghp2021.ghp2021entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author r-terai
 */
@Entity
@Table(name = "POST_INFORMATION")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PostInformation.findAll", query = "SELECT p FROM PostInformation p"),
    @NamedQuery(name = "PostInformation.findById", query = "SELECT p FROM PostInformation p WHERE p.id = :id"),
    @NamedQuery(name = "PostInformation.findByTime", query = "SELECT p FROM PostInformation p WHERE p.time = :time"),
    @NamedQuery(name = "PostInformation.findByLatitude", query = "SELECT p FROM PostInformation p WHERE p.latitude = :latitude"),
    @NamedQuery(name = "PostInformation.findByLongitude", query = "SELECT p FROM PostInformation p WHERE p.longitude = :longitude"),
    @NamedQuery(name = "PostInformation.findByInformation", query = "SELECT p FROM PostInformation p WHERE p.information = :information"),
    @NamedQuery(name = "PostInformation.findByApproved", query = "SELECT p FROM PostInformation p WHERE p.approved = :approved")})
public class PostInformation implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID")
    private Long id;
    @Basic(optional = false)
    @Column(name = "TIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date time;
    @Column(name = "LATITUDE")
    private String latitude;
    @Column(name = "LONGITUDE")
    private String longitude;
    @Column(name = "INFORMATION")
    private String information;
    @Basic(optional = false)
    @Column(name = "APPROVED")
    private int approved;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "postInformation")
    private File file;

    public PostInformation() {
    }

    public PostInformation(Long id) {
        this.id = id;
    }

    public PostInformation(Long id, Date time, int approved) {
        this.id = id;
        this.time = time;
        this.approved = approved;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getInformation() {
        return information;
    }

    public void setInformation(String information) {
        this.information = information;
    }

    public int getApproved() {
        return approved;
    }

    public void setApproved(int approved) {
        this.approved = approved;
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
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
        if (!(object instanceof PostInformation)) {
            return false;
        }
        PostInformation other = (PostInformation) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ghp2021.ghp2021entity.PostInformation[ id=" + id + " ]";
    }
    
}
