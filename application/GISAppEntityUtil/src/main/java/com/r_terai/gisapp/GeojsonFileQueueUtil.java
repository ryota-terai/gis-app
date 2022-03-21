/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.r_terai.gisapp;

import com.r_terai.gisapp.entity.GeojsonFileQueue;
import java.util.List;
import javax.persistence.EntityManager;

/**
 *
 * @author r-terai
 */
public class GeojsonFileQueueUtil {

    public static void persist(EntityManager em, String geojson, boolean _private, String type, boolean expand) {
        GeojsonFileQueue file = new GeojsonFileQueue();
        file.setPrivate1(_private ? (short) 1 : 0);
        file.setType(type);
        file.setGeojson(geojson);
        file.setExpand(expand ? (short) 1 : (short) 0);
        em.persist(file);
    }

    public static List<GeojsonFileQueue> search(EntityManager em) {
        List<GeojsonFileQueue> queue = em.createNamedQuery("GeojsonFileQueue.findAll", GeojsonFileQueue.class)
                .getResultList();
        return queue;
    }

}
