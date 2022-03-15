/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.r_terai.gisapp.ejb;

import com.mapbox.geojson.FeatureCollection;
import com.r_terai.gisapp.entity.ShelterInformationView;
import com.r_terai.gisapp.PointInformationUtil;
import com.r_terai.gisapp.PointUtil;
import com.r_terai.gisapp.ShelterInformationViewUtil;
import com.r_terai.java.ee.common.entity.util.COMMONEntityUtil;
import com.r_terai.java.ee.LogInterceptor;
import com.r_terai.java.util.Logger;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.interceptor.Interceptors;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Ryota-Terai
 */
@Stateless
@LocalBean
@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
public class PointInformationEJB implements PointrInformationEJBLocal {

    @PersistenceContext(unitName = "GISAppEntity")
    private EntityManager em;

    @PersistenceContext(unitName = "COMMONEntity")
    private EntityManager common;

    private static final Logger logger = new Logger(PointInformationEJB.class.getName());

    @Override
    @Interceptors(LogInterceptor.class)
    public void setup(InputStream stream, boolean _private, String type) {
        InputStreamReader inputStreamReader = new InputStreamReader(stream);
        Stream<String> streamOfString = new BufferedReader(inputStreamReader).lines();
        String streamToString = streamOfString.collect(Collectors.joining());

        FeatureCollection featureCollection = FeatureCollection.fromJson(streamToString);

        PointUtil.persist(em, featureCollection, _private, type);
    }

    @Override
    public List<ShelterInformationView> search(String administrativeAreaCode, String type, boolean p20_007, boolean p20_008, boolean p20_009, boolean p20_010, boolean p20_011, Boolean open) {
        List<ShelterInformationView> shelters = ShelterInformationViewUtil.search(em, administrativeAreaCode, type);

        List<ShelterInformationView> shelters2 = new ArrayList();
        for (ShelterInformationView shelter : shelters) {
            boolean check = false;
            if (open == null) {
                check = true;
            } else if (shelter.getOpen() != null && (shelter.getOpen().intValue() == 1) == open) {
                check = true;
            } else if (shelter.getOpen() == null && open == false) {
                check = true;
            }
            if (check) {
                if (shelter.getP20012() != 0) {
                    shelters2.add(shelter);
                } else if (p20_007 && shelter.getP20007() != 0) {
                    shelters2.add(shelter);
                } else if (p20_008 && shelter.getP20008() != 0) {
                    shelters2.add(shelter);
                } else if (p20_009 && shelter.getP20009() != 0) {
                    shelters2.add(shelter);
                } else if (p20_010 && shelter.getP20010() != 0) {
                    shelters2.add(shelter);
                } else if (p20_011 && shelter.getP20011() != 0) {
                    shelters2.add(shelter);
                }
            }
        }
        try {
            COMMONEntityUtil.ObserverTargetUtil.kick(common);
        } catch (NamingException ex) {
            logger.log(Logger.Level.SEVERE, null, ex);
        }

        return shelters2;
    }

    @Override
    @Interceptors(LogInterceptor.class)
    public void upatePointInformation(int pointId, boolean open, String comment) {
        PointInformationUtil.update(em, pointId, open, comment);
    }

    @Override
    public void initialize(String type) {
        PointUtil.initialize(em, type);
    }

}
