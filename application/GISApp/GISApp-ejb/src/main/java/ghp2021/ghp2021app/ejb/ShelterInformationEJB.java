/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ghp2021.ghp2021app.ejb;

import com.mapbox.geojson.Feature;
import com.mapbox.geojson.FeatureCollection;
import com.mapbox.geojson.Geometry;
import com.mapbox.geojson.Point;
import ghp2021.ghp2021entity.ShelterInformation;
import ghp2021.ghp2021entity.ShelterInformationExt;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Ryota-Terai
 */
@Stateless
@LocalBean
@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
public class ShelterInformationEJB implements ShelterInformationEJBLocal {

    @PersistenceContext(unitName = "GHP2021Entity")
    private EntityManager em;

    private static final Logger LOG = Logger.getLogger(ShelterInformationEJB.class.getName());

    @Override
    public void setup(InputStream stream) {
        InputStreamReader inputStreamReader = new InputStreamReader(stream);
        Stream<String> streamOfString = new BufferedReader(inputStreamReader).lines();
        String streamToString = streamOfString.collect(Collectors.joining());

        FeatureCollection featureCollection = FeatureCollection.fromJson(streamToString);

        for (Feature feature : featureCollection.features()) {
            ShelterInformation shelterInformation = new ShelterInformation();
            shelterInformation.setAdministrativeAreaCode(feature.getStringProperty("P20_001"));
            shelterInformation.setName(feature.getStringProperty("P20_002"));
            shelterInformation.setAddress(feature.getStringProperty("P20_003"));
            shelterInformation.setType(feature.getStringProperty("P20_004"));
            Geometry geometry = feature.geometry();
            shelterInformation.setLatitude(((Point) geometry).latitude());
            shelterInformation.setLongitude(((Point) geometry).longitude());
            shelterInformation.setGeom("Point(" + shelterInformation.getLongitude() + " " + shelterInformation.getLatitude() + ")");
            shelterInformation.setP20007(feature.getNumberProperty("P20_007").shortValue());
            shelterInformation.setP20008(feature.getNumberProperty("P20_008").shortValue());
            shelterInformation.setP20009(feature.getNumberProperty("P20_009").shortValue());
            shelterInformation.setP20010(feature.getNumberProperty("P20_010").shortValue());
            shelterInformation.setP20011(feature.getNumberProperty("P20_011").shortValue());
            shelterInformation.setP20012(feature.getNumberProperty("P20_012").shortValue());
            em.persist(shelterInformation);
        }
    }

    @Override
    public List<ShelterInformation> search(String administrativeAreaCode, boolean p20_007, boolean p20_008, boolean p20_009, boolean p20_010, boolean p20_011, Boolean open) {
//        List<ShelterInformation> shelters = em.createNamedQuery("ShelterInformation.findByAdministrativeAreaCode", ShelterInformation.class)
//                .setParameter("administrativeAreaCode", administrativeAreaCode)
//                .getResultList();

        List<ShelterInformation> shelters = em.createNativeQuery("SELECT * FROM SHELTER_INFORMATION WHERE ADMINISTRATIVE_AREA_CODE like ?1", ShelterInformation.class)
                .setParameter(1, (administrativeAreaCode == null ? "" : administrativeAreaCode) + "%")
                .getResultList();

        List<ShelterInformation> shelters2 = new ArrayList();

        for (ShelterInformation shelter : shelters) {
            ShelterInformationExt ext = em.find(ShelterInformationExt.class, shelter.getGeom());
            shelter.setShelterInformationExt(ext);
            boolean check = false;
            if (open == null) {
                check = true;
            } else if (ext != null) {
                if ((ext.getOpen() != 0) == open) {
                    check = true;
                }
            } else if (open == false) {
                check = true;
            }

            if (check) {
                if (shelter.getP20012() != 0) {
                    shelters2.add(shelter);
                } else if (p20_007 && shelter.getP20007() != 0) {
                    shelters2.add(shelter);
                } else if (p20_008 && shelter.getP20008() != 0) {
                    shelters2.add(shelter);
                } else if (p20_009 && shelter.getP20009() != 0) {
                    shelters2.add(shelter);
                } else if (p20_010 && shelter.getP20010() != 0) {
                    shelters2.add(shelter);
                } else if (p20_011 && shelter.getP20011() != 0) {
                    shelters2.add(shelter);
                }
            }
        }
        return shelters2;
    }

    @Override
    public void upateShelterInformationExt(String geom, boolean open, String comment) {

        ShelterInformationExt ext2 = em.find(ShelterInformationExt.class, geom);
        if (ext2 == null) {
            ShelterInformationExt ext = new ShelterInformationExt();
            ext.setGeom(geom);
            ext.setOpen(open ? (short) 1 : (short) 0);
            ext.setComment(comment);
            em.persist(ext);
        } else {
            ext2.setOpen(open ? (short) 1 : (short) 0);
            ext2.setComment(comment);
            em.merge(ext2);
        }
    }
}
