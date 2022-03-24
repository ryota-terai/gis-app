/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.r_terai.gisapp.ejb;

import com.mapbox.geojson.FeatureCollection;
import javax.ejb.Local;

/**
 *
 * @author r-terai
 */
@Local
public interface GeoJsonEJBLocal {

    public FeatureCollection search(String type, String areaCode);
}
