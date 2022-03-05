/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ghp2021.ghp2021entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
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
@Table(name = "SHELTER_INFORMATION")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ShelterInformation.findAll", query = "SELECT s FROM ShelterInformation s"),
    @NamedQuery(name = "ShelterInformation.findByGeom", query = "SELECT s FROM ShelterInformation s WHERE s.geom = :geom"),
    @NamedQuery(name = "ShelterInformation.findByAdministrativeAreaCode", query = "SELECT s FROM ShelterInformation s WHERE s.administrativeAreaCode = :administrativeAreaCode"),
    @NamedQuery(name = "ShelterInformation.findByName", query = "SELECT s FROM ShelterInformation s WHERE s.name = :name"),
    @NamedQuery(name = "ShelterInformation.findByAddress", query = "SELECT s FROM ShelterInformation s WHERE s.address = :address"),
    @NamedQuery(name = "ShelterInformation.findByType", query = "SELECT s FROM ShelterInformation s WHERE s.type = :type"),
    @NamedQuery(name = "ShelterInformation.findByLatitude", query = "SELECT s FROM ShelterInformation s WHERE s.latitude = :latitude"),
    @NamedQuery(name = "ShelterInformation.findByLongitude", query = "SELECT s FROM ShelterInformation s WHERE s.longitude = :longitude"),
    @NamedQuery(name = "ShelterInformation.findByP20007", query = "SELECT s FROM ShelterInformation s WHERE s.p20007 = :p20007"),
    @NamedQuery(name = "ShelterInformation.findByP20008", query = "SELECT s FROM ShelterInformation s WHERE s.p20008 = :p20008"),
    @NamedQuery(name = "ShelterInformation.findByP20009", query = "SELECT s FROM ShelterInformation s WHERE s.p20009 = :p20009"),
    @NamedQuery(name = "ShelterInformation.findByP20010", query = "SELECT s FROM ShelterInformation s WHERE s.p20010 = :p20010"),
    @NamedQuery(name = "ShelterInformation.findByP20011", query = "SELECT s FROM ShelterInformation s WHERE s.p20011 = :p20011"),
    @NamedQuery(name = "ShelterInformation.findByP20012", query = "SELECT s FROM ShelterInformation s WHERE s.p20012 = :p20012")})
public class ShelterInformation implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "GEOM")
    private String geom;
    @Basic(optional = false)
    @Column(name = "ADMINISTRATIVE_AREA_CODE")
    private String administrativeAreaCode;
    @Basic(optional = false)
    @Column(name = "NAME")
    private String name;
    @Basic(optional = false)
    @Column(name = "ADDRESS")
    private String address;
    @Basic(optional = false)
    @Column(name = "TYPE")
    private String type;
    @Basic(optional = false)
    @Column(name = "LATITUDE")
    private double latitude;
    @Basic(optional = false)
    @Column(name = "LONGITUDE")
    private double longitude;
    @Basic(optional = false)
    @Column(name = "P20_007")
    private short p20007;
    @Basic(optional = false)
    @Column(name = "P20_008")
    private short p20008;
    @Basic(optional = false)
    @Column(name = "P20_009")
    private short p20009;
    @Basic(optional = false)
    @Column(name = "P20_010")
    private short p20010;
    @Basic(optional = false)
    @Column(name = "P20_011")
    private short p20011;
    @Basic(optional = false)
    @Column(name = "P20_012")
    private short p20012;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "shelterInformation")
    private ShelterInformationExt shelterInformationExt;

    public ShelterInformation() {
    }

    public ShelterInformation(String geom) {
        this.geom = geom;
    }

    public ShelterInformation(String geom, String administrativeAreaCode, String name, String address, String type, double latitude, double longitude, short p20007, short p20008, short p20009, short p20010, short p20011, short p20012) {
        this.geom = geom;
        this.administrativeAreaCode = administrativeAreaCode;
        this.name = name;
        this.address = address;
        this.type = type;
        this.latitude = latitude;
        this.longitude = longitude;
        this.p20007 = p20007;
        this.p20008 = p20008;
        this.p20009 = p20009;
        this.p20010 = p20010;
        this.p20011 = p20011;
        this.p20012 = p20012;
    }

    public String getGeom() {
        return geom;
    }

    public void setGeom(String geom) {
        this.geom = geom;
    }

    public String getAdministrativeAreaCode() {
        return administrativeAreaCode;
    }

    public void setAdministrativeAreaCode(String administrativeAreaCode) {
        this.administrativeAreaCode = administrativeAreaCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public short getP20007() {
        return p20007;
    }

    public void setP20007(short p20007) {
        this.p20007 = p20007;
    }

    public short getP20008() {
        return p20008;
    }

    public void setP20008(short p20008) {
        this.p20008 = p20008;
    }

    public short getP20009() {
        return p20009;
    }

    public void setP20009(short p20009) {
        this.p20009 = p20009;
    }

    public short getP20010() {
        return p20010;
    }

    public void setP20010(short p20010) {
        this.p20010 = p20010;
    }

    public short getP20011() {
        return p20011;
    }

    public void setP20011(short p20011) {
        this.p20011 = p20011;
    }

    public short getP20012() {
        return p20012;
    }

    public void setP20012(short p20012) {
        this.p20012 = p20012;
    }

    public ShelterInformationExt getShelterInformationExt() {
        return shelterInformationExt;
    }

    public void setShelterInformationExt(ShelterInformationExt shelterInformationExt) {
        this.shelterInformationExt = shelterInformationExt;
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
        if (!(object instanceof ShelterInformation)) {
            return false;
        }
        ShelterInformation other = (ShelterInformation) object;
        if ((this.geom == null && other.geom != null) || (this.geom != null && !this.geom.equals(other.geom))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ghp2021.ghp2021entity.ShelterInformation[ geom=" + geom + " ]";
    }
    
}
