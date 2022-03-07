/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.r_terai.gisapp.ejb;

import com.google.gson.JsonPrimitive;
import com.mapbox.geojson.Feature;
import com.mapbox.geojson.FeatureCollection;
import com.mapbox.geojson.Geometry;
import com.mapbox.geojson.Point;
import com.r_terai.gisapp.entity.PointInformation;
import com.r_terai.gisapp.entity.ShelterInformation;
import com.r_terai.gisapp.entity.ShelterInformationExt;
import com.rterai.java.util.Util;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Date;
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

    @PersistenceContext(unitName = "GISAppEntity")
    private EntityManager em;

    private static final Logger LOG = Logger.getLogger(ShelterInformationEJB.class.getName());

    @Override
    public void setup(InputStream stream) {
        InputStreamReader inputStreamReader = new InputStreamReader(stream);
        Stream<String> streamOfString = new BufferedReader(inputStreamReader).lines();
        String streamToString = streamOfString.collect(Collectors.joining());

        FeatureCollection featureCollection = FeatureCollection.fromJson(streamToString);

        for (Feature feature : featureCollection.features()) {
            com.r_terai.gisapp.entity.Point point = new com.r_terai.gisapp.entity.Point();
            point.setPointId(Util.makeId(32, true));
            point.setPrivate1((short) 0);
            Geometry geometry = feature.geometry();
            point.setX(((Point) geometry).longitude());
            point.setY(((Point) geometry).latitude());
            point.setUpdateTime(new Date());
            em.persist(point);

            for (String key : feature.properties().keySet()) {
                if (!feature.getProperty(key).isJsonNull()) {
                    PointInformation info = new PointInformation(point.getPointId(), key);
                    JsonPrimitive property = feature.getProperty(key).getAsJsonPrimitive();
                    info.setUpdateTime(new Date());

                    if (property.isString()) {
                        info.setType("String");
                        info.setString((String) feature.getStringProperty(key));
                        em.persist(info);
                    } else if (property.isNumber()) {
                        info.setType("Number");
                        info.setNumber(feature.getNumberProperty(key).doubleValue());
                        em.persist(info);
                    } else if (property.isBoolean()) {
                        info.setType("Boolean");
                        info.setBoolean1(feature.getBooleanProperty(key) ? (short) 1 : (short) 0);
                        em.persist(info);
                    } else {
                        System.out.println("Type=" + property.getClass().toGenericString());
                    }
                }
            }
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
