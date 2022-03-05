/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ghp2021.ghp2021app.shelter;

/**
 *
 * @author Ryota-Terai
 */
public class ShelterSearchResult {

    private final String geom;
    private final String administrativeAreaCode;
    private final String name;
    private final String address;
    private final String type;
    private final double latitude;
    private final double longitude;
    private final boolean p20007;
    private final boolean p20008;
    private final boolean p20009;
    private final boolean p20010;
    private final boolean p20011;
    private final boolean p20012;
    private final int numberOfEvacuus;
    private final int numberOfNonEvacuees;
    private boolean open;
    private String comment;

    public ShelterSearchResult(String geom, String administrativeAreaCode, String name, String address, String type, double latitude, double longitude, boolean p20007, boolean p20008, boolean p20009, boolean p20010, boolean p20011, boolean p20012, int numberOfEvacuus, int numberOfNonEvacuees, boolean open, String comment) {
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
        this.numberOfEvacuus = numberOfEvacuus;
        this.numberOfNonEvacuees = numberOfNonEvacuees;
        this.open = open;
        this.comment = comment;
    }

    public String getGeom() {
        return geom;
    }

    public String getAdministrativeAreaCode() {
        return administrativeAreaCode;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getType() {
        return type;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public boolean isP20007() {
        return p20007;
    }

    public boolean isP20008() {
        return p20008;
    }

    public boolean isP20009() {
        return p20009;
    }

    public boolean isP20010() {
        return p20010;
    }

    public boolean isP20011() {
        return p20011;
    }

    public boolean isP20012() {
        return p20012;
    }

    public int getNumberOfEvacuus() {
        return numberOfEvacuus;
    }

    public int getNumberOfNonEvacuees() {
        return numberOfNonEvacuees;
    }

    public boolean isOpen() {
        return open;
    }

    public String getComment() {
        return comment;
    }

    public void setOpen(boolean open) {
        this.open = open;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

}
