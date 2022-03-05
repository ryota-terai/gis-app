/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ghp2021.ghp2021entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
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
@Table(name = "SHELTER_INFORMATION_EXT")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ShelterInformationExt.findAll", query = "SELECT s FROM ShelterInformationExt s"),
    @NamedQuery(name = "ShelterInformationExt.findByGeom", query = "SELECT s FROM ShelterInformationExt s WHERE s.geom = :geom"),
    @NamedQuery(name = "ShelterInformationExt.findByOpen", query = "SELECT s FROM ShelterInformationExt s WHERE s.open = :open"),
    @NamedQuery(name = "ShelterInformationExt.findByComment", query = "SELECT s FROM ShelterInformationExt s WHERE s.comment = :comment")})
public class ShelterInformationExt implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "GEOM")
    private String geom;
    @Basic(optional = false)
    @Column(name = "OPEN")
    private short open;
    @Column(name = "COMMENT")
    private String comment;
    @JoinColumn(name = "GEOM", referencedColumnName = "GEOM", insertable = false, updatable = false)
    @OneToOne(optional = false)
    private ShelterInformation shelterInformation;

    public ShelterInformationExt() {
    }

    public ShelterInformationExt(String geom) {
        this.geom = geom;
    }

    public ShelterInformationExt(String geom, short open) {
        this.geom = geom;
        this.open = open;
    }

    public String getGeom() {
        return geom;
    }

    public void setGeom(String geom) {
        this.geom = geom;
    }

    public short getOpen() {
        return open;
    }

    public void setOpen(short open) {
        this.open = open;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public ShelterInformation getShelterInformation() {
        return shelterInformation;
    }

    public void setShelterInformation(ShelterInformation shelterInformation) {
        this.shelterInformation = shelterInformation;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (geom != null ? geom.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ShelterInformationExt)) {
            return false;
        }
        ShelterInformationExt other = (ShelterInformationExt) object;
        if ((this.geom == null && other.geom != null) || (this.geom != null && !this.geom.equals(other.geom))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ghp2021.ghp2021entity.ShelterInformationExt[ geom=" + geom + " ]";
    }
    
}
