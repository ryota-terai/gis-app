/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.r_terai.gisapp;

import com.google.gson.JsonPrimitive;
import com.mapbox.geojson.Feature;
import com.mapbox.geojson.FeatureCollection;
import com.mapbox.geojson.Geometry;
import com.r_terai.gisapp.entity.File;
import com.r_terai.gisapp.entity.Point;
import com.r_terai.gisapp.entity.PointInformation;
import com.r_terai.java.util.Logger;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;

/**
 *
 * @author r-terai
 */
public class PointUtil {

    public static void persist(EntityManager em, FeatureCollection featureCollection, boolean _private, String type) {
        for (Feature feature : featureCollection.features()) {
            Point point = new Point();
            //                point.setPointId(Util.makeId(32, true));
            point.setPrivate1((short) 0);
            Geometry geometry = feature.geometry();
            point.setX(((com.mapbox.geojson.Point) geometry).longitude());
            point.setY(((com.mapbox.geojson.Point) geometry).latitude());
            point.setPrivate1(_private ? (short) 1 : 0);
            point.setType(type);
            point.setUpdateTime(new Date());
            em.persist(point);
            em.flush();
            for (String key : feature.properties().keySet()) {
                if (!feature.getProperty(key).isJsonNull()) {
                    PointInformation info = new PointInformation(point.getPointId(), key);
                    info.setUpdateTime(new Date());
                    JsonPrimitive property = feature.getProperty(key).getAsJsonPrimitive();
                    if (property.isString()) {
                        info.setType(GISAppEntityUtil.POINT_INFORMATION_TYPE_STRING);
                        info.setString((String) feature.getStringProperty(key));
                        em.persist(info);
                    } else if (property.isNumber()) {
                        info.setType(GISAppEntityUtil.POINT_INFORMATION_TYPE_NUMBER);
                        info.setNumber(feature.getNumberProperty(key).doubleValue());
                        em.persist(info);
                    } else if (property.isBoolean()) {
                        info.setType(GISAppEntityUtil.POINT_INFORMATION_TYPE_BOOLEAN);
                        info.setBoolean1(feature.getBooleanProperty(key) ? (short) 1 : (short) 0);
                        em.persist(info);
                    } else {
                        GISAppEntityUtil.logger.log(Logger.Level.INFO, "Type={}", property.getClass().toGenericString());
                    }
                }
            }
            GISAppEntityUtil.logger.log(Logger.Level.INFO, "point={}", point.toString());
        }
    }

    public static void persist(EntityManager em, String type, String longtitude, String latitude, String information, byte[] file) throws NumberFormatException {
        Point point = new Point();
        //            point.setPointId(Util.makeId(32, true));
        point.setPrivate1((short) 0);
        point.setX(Double.parseDouble(longtitude));
        point.setY(Double.parseDouble(latitude));
        point.setType(type);
        Date now = new Date();
        point.setUpdateTime(now);
        em.persist(point);
        em.flush();
        PointInformation info = new PointInformation(point.getPointId(), GISAppEntityUtil.INFORMATION);
        info.setUpdateTime(new Date());
        info.setType(GISAppEntityUtil.POINT_INFORMATION_TYPE_STRING);
        info.setString(information);
        em.persist(info);
        info = new PointInformation(point.getPointId(), GISAppEntityUtil.APPROVED);
        info.setUpdateTime(new Date());
        info.setType(GISAppEntityUtil.POINT_INFORMATION_TYPE_BOOLEAN);
        info.setBoolean1((short) 0);
        em.persist(info);
        if (file != null) {
            File fileEntity = new File();
            fileEntity.setPointId(point.getPointId());
            fileEntity.setFile(file);
            em.persist(fileEntity);
        }
    }

    public static void initialize(EntityManager em, String type) {
        List<Point> points = em.createNamedQuery("Point.findByType", Point.class).setParameter("type", type).getResultList();
        for (Point point : points) {
            List<PointInformation> info = em.createNamedQuery("PointInformation.findByPointId", PointInformation.class).setParameter("pointId", point.getPointId()).getResultList();
            for (PointInformation pointInfo : info) {
                em.remove(pointInfo);
            }
            File file = em.find(File.class, point.getPointId());
            if (file != null) {
                em.remove(file);
            }
            GISAppEntityUtil.logger.log(Logger.Level.INFO, "point={}", point.toString());
            em.remove(point);
        }
    }

    public static void remove(EntityManager em, int pointId) {
        List<PointInformation> info = em.createNamedQuery("PointInformation.findByPointId", PointInformation.class).setParameter("pointId", pointId).getResultList();
        for (PointInformation pointInfo : info) {
            em.remove(pointInfo);
        }
        File file = em.find(File.class, pointId);
        if (file != null) {
            em.remove(file);
        }
        Point point = em.find(Point.class, pointId);
        if (point != null) {
            em.remove(point);
        }
    }

}
