/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.r_terai.gisapp.ejb;

import com.mapbox.geojson.FeatureCollection;
import com.r_terai.gisapp.GISAppEntityUtil;
import com.r_terai.gisapp.GeojsonFileQueueUtil;
import com.r_terai.gisapp.entity.GeojsonFileQueue;
import com.r_terai.java.ee.common.entity.util.COMMONEntityUtil;
import com.r_terai.java.util.Logger;
import com.r_terai.java.ee.common.TimerEJB;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.ejb.Timer;
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
    public void timeout(Timer timer) {
        try {
            COMMONEntityUtil.ObserverTargetUtil.kick(super.em, 200, (new Date()).toString(), false);
        } catch (NamingException ex) {
            logger.log(Logger.Level.SEVERE, null, ex);
        }

        List<GeojsonFileQueue> queue = GeojsonFileQueueUtil.search(gis);

        if (!queue.isEmpty()) {
            InputStream stream = null;
            File tempFile = null;
            try {
                GeojsonFileQueue file = queue.get(0);

                tempFile = new File(file.getGeojson());
                stream = Files.newInputStream(tempFile.toPath());
                InputStreamReader inputStreamReader = new InputStreamReader(stream);
                Stream<String> streamOfString = new BufferedReader(inputStreamReader).lines();
                String streamToString = streamOfString.collect(Collectors.joining());

                FeatureCollection featureCollection = FeatureCollection.fromJson(streamToString);
                GISAppEntityUtil.persist(gis, featureCollection, (file.getPrivate1() == (short) 1), file.getType(), file.getExpand() == 1);
                gis.remove(file);
            } catch (IOException ex) {
                logger.log(Logger.Level.SEVERE, null, ex);
            } finally {
                try {
                    stream.close();
                    tempFile.delete();
                } catch (IOException ex) {
                    logger.log(Logger.Level.SEVERE, null, ex);
                }
            }
        }
    }

}
