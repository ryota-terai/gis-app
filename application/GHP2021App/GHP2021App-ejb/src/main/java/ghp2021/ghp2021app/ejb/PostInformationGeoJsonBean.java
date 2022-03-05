/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ghp2021.ghp2021app.ejb;

import com.mapbox.geojson.Feature;
import com.mapbox.geojson.FeatureCollection;
import com.mapbox.geojson.Point;
import ghp2021.ghp2021entity.File;
import ghp2021.ghp2021entity.PostInformation;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
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
public class PostInformationGeoJsonBean {

    @PersistenceContext(unitName = "GHP2021Entity")
    private EntityManager em;

    private static final Logger LOG = Logger.getLogger(PostInformationGeoJsonBean.class.getName());

    public FeatureCollection getDisasterInformationGeoJson() {
        List<PostInformation> approvedInformation = em.createNamedQuery("PostInformation.findByApproved", PostInformation.class)
                .setParameter("approved", 1)
                .getResultList();

        List<Feature> features = new ArrayList();

        for (PostInformation information : approvedInformation) {
            if (information.getLongitude().length() > 0 && information.getLatitude().length() > 0) {
                Point point = Point.fromLngLat(Double.parseDouble(information.getLongitude()), Double.parseDouble(information.getLatitude()));
                Feature feature = Feature.fromGeometry(point);
                feature.addStringProperty("comment", information.getInformation().replace("\n", ""));
                feature.addStringProperty("id", information.getId().toString());

                File file = em.find(File.class, information.getId());
                feature.addBooleanProperty("picture", file != null);
                features.add(feature);
            }
        }
        return FeatureCollection.fromFeatures(features);
    }

}
