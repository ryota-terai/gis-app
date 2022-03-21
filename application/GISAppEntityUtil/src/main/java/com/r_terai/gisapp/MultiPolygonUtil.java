/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.r_terai.gisapp;

import com.r_terai.gisapp.entity.Information;
import com.r_terai.gisapp.entity.MultiPolygon;
import com.r_terai.gisapp.entity.MultiPolygonGeometry;
import com.r_terai.gisapp.entity.Point;
import com.r_terai.java.util.Logger;
import java.util.List;
import javax.persistence.EntityManager;

/**
 *
 * @author r-terai
 */
public class MultiPolygonUtil {

    public static void initialize(EntityManager em, String type) {
        List<MultiPolygon> polygons = em.createNamedQuery("MultiPolygon.findByType", MultiPolygon.class)
                .setParameter("type", type)
                .getResultList();
        for (MultiPolygon polygon : polygons) {
            remove(em, polygon.getMultiPolygonId());
        }
    }

    public static void remove(EntityManager em, int polygonId) {
        List<Information> info = em.createNativeQuery("SELECT * FROM INFORMATION WHERE ID_TYPE = ?1 AND ID = ?2", Information.class)
                .setParameter(1, GISAppEntityUtil.ID_TYPE_MULTI_POLYGON)
                .setParameter(2, polygonId)
                .getResultList();
        for (Information pointInfo : info) {
            em.remove(pointInfo);
        }
        List<MultiPolygonGeometry> geometries = em.createNamedQuery("MultiPolygonGeometry.findByMultiPolygonId", MultiPolygonGeometry.class)
                .setParameter("multiPolygonId", polygonId)
                .getResultList();
        for (MultiPolygonGeometry geometry : geometries) {
            Point point = em.find(Point.class, geometry.getPointId().getPointId());
            if (point != null) {
                GISAppEntityUtil.logger.log(Logger.Level.INFO, "point={}", point.toString());
                em.remove(point);
            }
            em.remove(geometry);
        }
        MultiPolygon polygon = em.find(MultiPolygon.class, polygonId);
        em.remove(polygon);
    }

}
