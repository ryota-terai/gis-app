/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.r_terai.gisapp;

import com.r_terai.gisapp.entity.GeojsonFileLocation;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

/**
 *
 * @author r-terai
 */
public class GeojsonFileLocationUtil {

    public static GeojsonFileLocation searchStoredGeoJson(EntityManager em, String type, String areaCode) {
        GeojsonFileLocation location;
        try {
            location = (GeojsonFileLocation) em.createNativeQuery("SELECT * FROM GEOJSON_FILE_LOCATION WHERE TYPE = ?1 AND (AREA_CODE like ?2 OR AREA_CODE IS NULL)", GeojsonFileLocation.class)
                    .setParameter(1, type)
                    .setParameter(2, (areaCode == null ? "" : areaCode) + "%")
                    .getSingleResult();
        } catch (NoResultException ex) {
            return null;
        }
        return location;
    }

}
