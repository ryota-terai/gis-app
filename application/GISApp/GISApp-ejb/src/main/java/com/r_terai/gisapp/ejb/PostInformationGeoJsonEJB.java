/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.r_terai.gisapp.ejb;

import com.mapbox.geojson.Feature;
import com.mapbox.geojson.FeatureCollection;
import com.mapbox.geojson.Point;
import com.r_terai.gisapp.entity.File;
import com.r_terai.gisapp.entity.PostInformationView;
import com.r_terai.gisapp.GISAppEntityUtil;
import com.r_terai.java.util.Logger;
import java.util.ArrayList;
import java.util.List;
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
@TransactionAttribute(TransactionAttributeType.MANDATORY)
public class PostInformationGeoJsonEJB {

    @PersistenceContext(unitName = "GISAppEntity")
    private EntityManager em;

    private static final Logger LOG = new Logger(PostInformationGeoJsonEJB.class.getName());

    public FeatureCollection getDisasterInformationGeoJson() {
        List<PostInformationView> approvedInformation = GISAppEntityUtil.PostInformationViewUtil.getInformation(em, true);

        List<Feature> features = new ArrayList();

        for (PostInformationView information : approvedInformation) {
            Point point = Point.fromLngLat(information.getX(), information.getY());
            Feature feature = Feature.fromGeometry(point);
            feature.addStringProperty("comment", information.getInformation().replace("\n", ""));
            feature.addNumberProperty("id", information.getPointId());

            File file = em.find(File.class, information.getPointId());
            feature.addBooleanProperty("picture", file != null);
            features.add(feature);
        }
        return FeatureCollection.fromFeatures(features);
    }
}
