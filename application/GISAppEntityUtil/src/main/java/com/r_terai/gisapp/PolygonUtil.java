/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.r_terai.gisapp;

import com.r_terai.gisapp.entity.Information;
import com.r_terai.gisapp.entity.Point;
import com.r_terai.gisapp.entity.Polygon;
import com.r_terai.gisapp.entity.PolygonGeometry;
import com.r_terai.java.util.Logger;
import java.util.List;
import javax.persistence.EntityManager;

/**
 *
 * @author r-terai
 */
public class PolygonUtil {

    public static void initialize(EntityManager em, String type) {
        List<Polygon> polygons = em.createNamedQuery("Polygon.findByType", Polygon.class)
                .setParameter("type", type)
                .getResultList();
        for (Polygon polygon : polygons) {
            GISAppEntityUtil.logger.log(Logger.Level.INFO, "Polygon={}", polygon.toString());
            remove(em, polygon.getPolygonId());
        }
    }

    public static void remove(EntityManager em, int polygonId) {
        List<Information> info = em.createNativeQuery("SELECT * FROM INFORMATION WHERE ID_TYPE = ?1 AND ID = ?2", Information.class)
                .setParameter(1, GISAppEntityUtil.ID_TYPE_POLYGON)
                .setParameter(2, polygonId)
                .getResultList();
        for (Information pointInfo : info) {
            em.remove(pointInfo);
        }
        List<PolygonGeometry> geometries = em.createNamedQuery("PolygonGeometry.findByPolygonId", PolygonGeometry.class)
                .setParameter("polygonId", polygonId)
                .getResultList();
        for (PolygonGeometry geometry : geometries) {
            Point point = em.find(Point.class, geometry.getPointId().getPointId());
            if (point != null) {
                em.remove(point);
            }
            em.remove(geometry);
        }
        Polygon polygon = em.find(Polygon.class, polygonId);
        em.remove(polygon);
    }

}
