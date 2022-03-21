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
import com.r_terai.gisapp.entity.Information;
import com.r_terai.java.util.Logger;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;

/**
 *
 * @author r-terai
 */
public class PointUtil {

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
        Information info = new Information(GISAppEntityUtil.ID_TYPE_POINT, point.getPointId(), GISAppEntityUtil.INFORMATION);
        info.setUpdateTime(new Date());
        info.setType(GISAppEntityUtil.INFORMATION_TYPE_STRING);
        info.setString(information);
        em.persist(info);
        info = new Information(GISAppEntityUtil.ID_TYPE_POINT, point.getPointId(), GISAppEntityUtil.APPROVED);
        info.setUpdateTime(new Date());
        info.setType(GISAppEntityUtil.INFORMATION_TYPE_BOOLEAN);
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
            remove(em, point.getPointId());
        }
    }

    public static void remove(EntityManager em, int pointId) {
        List<Information> info = em.createNativeQuery("SELECT * FROM INFORMATION WHERE ID_TYPE = ?1 AND ID = ?2", Information.class)
                .setParameter(1, GISAppEntityUtil.ID_TYPE_POINT)
                .setParameter(2, pointId)
                .getResultList();
        for (Information pointInfo : info) {
            em.remove(pointInfo);
        }
        File file = em.find(File.class, pointId);
        if (file != null) {
            em.remove(file);
        }
        Point point = em.find(Point.class, pointId);
        if (point != null) {
            GISAppEntityUtil.logger.log(Logger.Level.INFO, "point={}", point.toString());
            em.remove(point);
        }
    }

}
