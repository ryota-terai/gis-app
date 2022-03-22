/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.r_terai.gisapp;

import com.r_terai.gisapp.entity.Information;
import com.r_terai.gisapp.entity.LineString;
import com.r_terai.gisapp.entity.LineStringGeometry;
import com.r_terai.gisapp.entity.Point;
import com.r_terai.java.util.Logger;
import java.util.List;
import javax.persistence.EntityManager;

/**
 *
 * @author r-terai
 */
public class LineStringUtility {

    public static void initialize(EntityManager em, String type) {
        List<LineString> polygons = em.createNamedQuery("LineString.findByType", LineString.class)
                .setParameter("type", type)
                .getResultList();
        for (LineString lineString : polygons) {
            GISAppEntityUtil.logger.log(Logger.Level.INFO, "LineString={}", lineString.toString());
            remove(em, lineString.getLineStringId());
        }
    }

    public static void remove(EntityManager em, int id) {
        List<Information> info = em.createNativeQuery("SELECT * FROM INFORMATION WHERE ID_TYPE = ?1 AND ID = ?2", Information.class)
                .setParameter(1, GISAppEntityUtil.ID_TYPE_LINE_STRING)
                .setParameter(2, id)
                .getResultList();
        for (Information pointInfo : info) {
            em.remove(pointInfo);
        }
        List<LineStringGeometry> geometries = em.createNamedQuery("LineStringGeometry.findByLineStringId", LineStringGeometry.class)
                .setParameter("lineStringId", id)
                .getResultList();
        for (LineStringGeometry geometry : geometries) {
            Point point = em.find(Point.class, geometry.getPointId().getPointId());
            if (point != null) {
                em.remove(point);
            }
            em.remove(geometry);
        }
        LineString polygon = em.find(LineString.class, id);
        em.remove(polygon);
    }

}
