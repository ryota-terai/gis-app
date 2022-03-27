/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.r_terai.gisapp.ejb;

import com.mapbox.geojson.FeatureCollection;
import com.r_terai.gisapp.GISAppEntityUtil;
import com.r_terai.gisapp.GeojsonFileLocationUtil;
import com.r_terai.gisapp.entity.GeojsonFileLocation;
import com.r_terai.java.util.Logger;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author r-terai
 */
@Stateless
@LocalBean
@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
public class GeoJsonEJB implements GeoJsonEJBLocal {

    @PersistenceContext(unitName = "GISAppEntity")
    private EntityManager em;

    private static final Logger logger = new Logger(GeoJsonEJB.class.getName());

    @Override
    public FeatureCollection search(String type, String areaCode) {
        return GISAppEntityUtil.search(em, type, areaCode);
    }

    @Override
    public GeojsonFileLocation searchStoredGeoJson(String type, String areaCode) {
        return GeojsonFileLocationUtil.searchStoredGeoJson(em, type, areaCode);
    }
}
