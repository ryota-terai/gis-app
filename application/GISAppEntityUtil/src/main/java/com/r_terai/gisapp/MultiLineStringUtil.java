/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.r_terai.gisapp;

import com.r_terai.gisapp.entity.Information;
import com.r_terai.gisapp.entity.MultiLineString;
import com.r_terai.gisapp.entity.MultiLineStringGeometry;
import com.r_terai.gisapp.entity.Point;
import com.r_terai.java.util.Logger;
import java.util.List;
import javax.persistence.EntityManager;

/**
 *
 * @author r-terai
 */
public class MultiLineStringUtil {

    public static void initialize(EntityManager em, String type) {
        List<MultiLineString> polygons = em.createNamedQuery("MultiLineString.findByType", MultiLineString.class)
                .setParameter("type", type)
                .getResultList();
        for (MultiLineString multiLineString : polygons) {
            GISAppEntityUtil.logger.log(Logger.Level.INFO, "MultiLineString={}", multiLineString.toString());
            remove(em, multiLineString.getMultiLineStringId());
        }
    }

    public static void remove(EntityManager em, int id) {
        List<Information> info = em.createNativeQuery("SELECT * FROM INFORMATION WHERE ID_TYPE = ?1 AND ID = ?2", Information.class)
                .setParameter(1, GISAppEntityUtil.ID_TYPE_MULTI_LINE_STRING)
                .setParameter(2, id)
                .getResultList();
        for (Information pointInfo : info) {
            em.remove(pointInfo);
        }
        List<MultiLineStringGeometry> geometries = em.createNamedQuery("MultiLineStringGeometry.findByMultiLineStringId", MultiLineStringGeometry.class)
                .setParameter("multiLineStringId", id)
                .getResultList();
        for (MultiLineStringGeometry geometry : geometries) {
            Point point = em.find(Point.class, geometry.getPointId().getPointId());
            if (point != null) {
                em.remove(point);
            }
            em.remove(geometry);
        }
        MultiLineString polygon = em.find(MultiLineString.class, id);
        em.remove(polygon);
    }

}
