/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rterai.gisapp;

import com.google.gson.JsonPrimitive;
import com.mapbox.geojson.Feature;
import com.mapbox.geojson.FeatureCollection;
import com.mapbox.geojson.Geometry;
import com.r_terai.gisapp.entity.Point;
import com.r_terai.gisapp.entity.PointInformation;
import com.r_terai.gisapp.entity.PointInformationPK;
import com.r_terai.gisapp.entity.PointInformationView;
import com.rterai.java.util.Util;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

/**
 *
 * @author r-terai
 */
public class GISAppEntityUtil {

    private static final String POINT_INFORMATION_TYPE_STRING = "String";
    private static final String POINT_INFORMATION_TYPE_BOOLEAN = "Boolean";
    private static final String POINT_INFORMATION_TYPE_NUMBER = "Number";
    private static final String COMMENT = "COMMENT";
    private static final String OPEN = "OPEN";

    private static final Logger LOG = Logger.getLogger(GISAppEntityUtil.class.getName());

    public static void persist(EntityManager em, FeatureCollection featureCollection, boolean _private, String type) {
        for (Feature feature : featureCollection.features()) {
            Point point = new Point();
            point.setPointId(Util.makeId(32, true));
            point.setPrivate1((short) 0);
            Geometry geometry = feature.geometry();
            point.setX(((com.mapbox.geojson.Point) geometry).longitude());
            point.setY(((com.mapbox.geojson.Point) geometry).latitude());
            point.setPrivate1(_private ? (short) 1 : 0);
            point.setType(type);
            point.setUpdateTime(new Date());
            em.persist(point);
            for (String key : feature.properties().keySet()) {
                if (!feature.getProperty(key).isJsonNull()) {
                    PointInformation info = new PointInformation(point.getPointId(), key);
                    JsonPrimitive property = feature.getProperty(key).getAsJsonPrimitive();
                    info.setUpdateTime(new Date());
                    if (property.isString()) {
                        info.setType(POINT_INFORMATION_TYPE_STRING);
                        info.setString((String) feature.getStringProperty(key));
                        em.persist(info);
                    } else if (property.isNumber()) {
                        info.setType(POINT_INFORMATION_TYPE_NUMBER);
                        info.setNumber(feature.getNumberProperty(key).doubleValue());
                        em.persist(info);
                    } else if (property.isBoolean()) {
                        info.setType(POINT_INFORMATION_TYPE_BOOLEAN);
                        info.setBoolean1(feature.getBooleanProperty(key) ? (short) 1 : (short) 0);
                        em.persist(info);
                    } else {
                        System.out.println("Type=" + property.getClass().toGenericString());
                    }
                }
            }
        }
    }

    public static List<PointInformationView> search(EntityManager em, String administrativeAreaCode, String type) {
        List<PointInformationView> shelters = em.createNativeQuery("SELECT * FROM POINT_INFORMATION_VIEW WHERE P20_001 like ?1 AND TYPE = ?2", PointInformationView.class)
                .setParameter(1, (administrativeAreaCode == null ? "" : administrativeAreaCode) + "%")
                .setParameter(2, type)
                .getResultList();
        return shelters;
    }

    public static void update(EntityManager em, String pointId, boolean open, String comment) {
        PointInformation info;
        try {
            info = (PointInformation) em.createNativeQuery("SELECT * FROM POINT_INFORMATION WHERE POINT_ID = ?1 AND NAME = ?2", PointInformation.class)
                    .setParameter(1, pointId)
                    .setParameter(2, OPEN)
                    .getSingleResult();
            info.setBoolean1(open ? (short) 1 : 0);
            info.setUpdateTime(new Date());
            em.merge(info);
        } catch (NoResultException ex) {
            PointInformationPK pk = new PointInformationPK(pointId, OPEN);
            info = new PointInformation(pk);
            info.setType(POINT_INFORMATION_TYPE_BOOLEAN);
            info.setBoolean1(open ? (short) 1 : 0);
            info.setUpdateTime(new Date());
            em.persist(info);
        }
        try {
            info = (PointInformation) em.createNativeQuery("SELECT * FROM POINT_INFORMATION WHERE POINT_ID = ?1 AND NAME = ?2", PointInformation.class)
                    .setParameter(1, pointId)
                    .setParameter(2, COMMENT)
                    .getSingleResult();
            info.setString(comment);
            info.setUpdateTime(new Date());
            em.merge(info);
        } catch (NoResultException ex) {
            PointInformationPK pk = new PointInformationPK(pointId, COMMENT);
            info = new PointInformation(pk);
            info.setType(POINT_INFORMATION_TYPE_STRING);
            info.setString(comment);
            info.setUpdateTime(new Date());
            em.persist(info);
        }
    }

}
