/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.r_terai.gisapp.ejb;

import com.mapbox.geojson.FeatureCollection;
import com.r_terai.gisapp.GeojsonFileQueueUtil;
import com.r_terai.gisapp.PointUtil;
import com.r_terai.gisapp.entity.GeojsonFileQueue;
import com.r_terai.java.ee.LogInterceptor;
import com.r_terai.java.ee.common.entity.util.COMMONEntityUtil;
import com.r_terai.java.util.Logger;
import com.rterai.java.ee.common.TimerEJB;
import java.util.Date;
import java.util.List;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.ejb.Timer;
import javax.interceptor.Interceptors;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author r-terai
 */
@Startup
@Singleton
public class GeoJsonSetupTimer extends TimerEJB {

    @PersistenceContext(unitName = "GISAppEntity")
    private EntityManager gis;

    @Override
    @Interceptors(LogInterceptor.class)
    public void timeout(Timer timer) {
        try {
            COMMONEntityUtil.ObserverTargetUtil.kick(super.em, 200, (new Date()).toString(), false);
        } catch (NamingException ex) {
            logger.log(Logger.Level.SEVERE, null, ex);
        }

        List<GeojsonFileQueue> queue = GeojsonFileQueueUtil.search(gis);

        GeojsonFileQueue file = queue.get(0);
        if (file != null) {
            FeatureCollection featureCollection = FeatureCollection.fromJson(file.getGeojson());
            PointUtil.persist(gis, featureCollection, (file.getPrivate1() == (short) 1), file.getType());
            gis.remove(file);
        }
    }

}
