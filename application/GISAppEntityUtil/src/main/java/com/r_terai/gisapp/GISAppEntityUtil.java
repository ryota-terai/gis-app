/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.r_terai.gisapp;

import com.google.gson.JsonPrimitive;
import com.mapbox.geojson.Feature;
import com.mapbox.geojson.FeatureCollection;
import com.mapbox.geojson.Geometry;
import com.r_terai.gisapp.entity.File;
import com.r_terai.gisapp.entity.ObserverTarget;
import com.r_terai.gisapp.entity.Point;
import com.r_terai.gisapp.entity.PointInformation;
import com.r_terai.gisapp.entity.PointInformationPK;
import com.r_terai.gisapp.entity.PostInformationView;
import com.r_terai.gisapp.entity.ShelterInformationView;
import com.r_terai.java.util.Logger;
import com.r_terai.java.util.Logger.Level;
import com.r_terai.java.util.Util;
import java.util.Date;
import java.util.List;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

/**
 *
 * @author r-terai
 */
public class GISAppEntityUtil {

    private static final String POINT_INFORMATION_TYPE_STRING = "String";
    private static final String POINT_INFORMATION_TYPE_BOOLEAN = "Boolean";
    private static final String POINT_INFORMATION_TYPE_NUMBER = "Number";
    private static final String COMMENT = "COMMENT";
    private static final String OPEN = "OPEN";
    private static final String APPROVED = "APPROVED";
    private static final String INFORMATION = "INFORMATION";

    private static final Logger logger = new Logger(GISAppEntityUtil.class.getName());

    public static class PointUtil {

        public static void persist(EntityManager em, FeatureCollection featureCollection, boolean _private, String type) {
            for (Feature feature : featureCollection.features()) {
                Point point = new Point();
//                point.setPointId(Util.makeId(32, true));
                point.setPrivate1((short) 0);
                Geometry geometry = feature.geometry();
                point.setX(((com.mapbox.geojson.Point) geometry).longitude());
                point.setY(((com.mapbox.geojson.Point) geometry).latitude());
                point.setPrivate1(_private ? (short) 1 : 0);
                point.setType(type);
                point.setUpdateTime(new Date());
                em.persist(point);
                em.flush();

                for (String key : feature.properties().keySet()) {
                    if (!feature.getProperty(key).isJsonNull()) {
                        PointInformation info = new PointInformation(point.getPointId(), key);
                        info.setUpdateTime(new Date());
                        JsonPrimitive property = feature.getProperty(key).getAsJsonPrimitive();
                        if (property.isString()) {
                            info.setType(GISAppEntityUtil.POINT_INFORMATION_TYPE_STRING);
                            info.setString((String) feature.getStringProperty(key));
                            em.persist(info);
                        } else if (property.isNumber()) {
                            info.setType(GISAppEntityUtil.POINT_INFORMATION_TYPE_NUMBER);
                            info.setNumber(feature.getNumberProperty(key).doubleValue());
                            em.persist(info);
                        } else if (property.isBoolean()) {
                            info.setType(GISAppEntityUtil.POINT_INFORMATION_TYPE_BOOLEAN);
                            info.setBoolean1(feature.getBooleanProperty(key) ? (short) 1 : (short) 0);
                            em.persist(info);
                        } else {
                            GISAppEntityUtil.logger.log(Level.INFO, "Type={0}", property.getClass().toGenericString());
                        }
                    }
                }
            }
        }

        public static void persist(EntityManager em, String type, String longtitude, String latitude, String information, byte[] file) throws NumberFormatException {
            Point point = new Point();
//            point.setPointId(Util.makeId(32, true));
            point.setPrivate1((short) 0);
            point.setX(Double.parseDouble(longtitude));
            point.setY(Double.parseDouble(latitude));
            point.setType(type);
            Date now = new Date();
            point.setUpdateTime(now);
            em.persist(point);
            em.flush();

            PointInformation info = new PointInformation(point.getPointId(), GISAppEntityUtil.INFORMATION);
            info.setUpdateTime(new Date());
            info.setType(GISAppEntityUtil.POINT_INFORMATION_TYPE_STRING);
            info.setString(information);
            em.persist(info);
            info = new PointInformation(point.getPointId(), GISAppEntityUtil.APPROVED);
            info.setUpdateTime(new Date());
            info.setType(GISAppEntityUtil.POINT_INFORMATION_TYPE_BOOLEAN);
            info.setBoolean1((short) 0);
            em.persist(info);
            if (file != null) {
                File fileEntity = new File();
                fileEntity.setPointId(point.getPointId());
                fileEntity.setFile(file);
                em.persist(fileEntity);
            }
        }

    }

    public static class ShelterInformationViewUtil {

        public static List<ShelterInformationView> search(EntityManager em, String administrativeAreaCode, String type) {
            List<ShelterInformationView> shelters = em.createNativeQuery("SELECT * FROM SHELTER_INFORMATION_VIEW WHERE P20_001 like ?1 AND TYPE = ?2", ShelterInformationView.class).setParameter(1, (administrativeAreaCode == null ? "" : administrativeAreaCode) + "%").setParameter(2, type).getResultList();
            return shelters;
        }
    }

    public static class PointInformationUtil {

        public static void update(EntityManager em, int pointId, boolean open, String comment) {
            PointInformation info;
            try {
                info = (PointInformation) em.createNativeQuery("SELECT * FROM POINT_INFORMATION WHERE POINT_ID = ?1 AND NAME = ?2", PointInformation.class).setParameter(1, pointId).setParameter(2, GISAppEntityUtil.OPEN).getSingleResult();
                info.setBoolean1(open ? (short) 1 : 0);
                info.setUpdateTime(new Date());
                em.merge(info);
            } catch (NoResultException ex) {
                PointInformationPK pk = new PointInformationPK(pointId, GISAppEntityUtil.OPEN);
                info = new PointInformation(pk);
                info.setType(GISAppEntityUtil.POINT_INFORMATION_TYPE_BOOLEAN);
                info.setBoolean1(open ? (short) 1 : 0);
                info.setUpdateTime(new Date());
                em.persist(info);
            }
            try {
                info = (PointInformation) em.createNativeQuery("SELECT * FROM POINT_INFORMATION WHERE POINT_ID = ?1 AND NAME = ?2", PointInformation.class).setParameter(1, pointId).setParameter(2, GISAppEntityUtil.COMMENT).getSingleResult();
                info.setString(comment);
                info.setUpdateTime(new Date());
                em.merge(info);
            } catch (NoResultException ex) {
                PointInformationPK pk = new PointInformationPK(pointId, GISAppEntityUtil.COMMENT);
                info = new PointInformation(pk);
                info.setType(GISAppEntityUtil.POINT_INFORMATION_TYPE_STRING);
                info.setString(comment);
                info.setUpdateTime(new Date());
                em.persist(info);
            }
        }

    }

    public static class PostInformationViewUtil {

        public static List<PostInformationView> getInformation(EntityManager em, boolean approved) {
            return em.createNamedQuery("PostInformationView.findByApproved", PostInformationView.class)
                    .setParameter("approved", approved ? (short) 1 : (short) 0)
                    .getResultList();
        }

        public static void merge(EntityManager em, PostInformationView information) {
            PointInformation info = em.find(PointInformation.class, new PointInformationPK(information.getPointId(), GISAppEntityUtil.INFORMATION));
            if (info == null) {
                info = new PointInformation(information.getPointId(), GISAppEntityUtil.INFORMATION);
                info.setUpdateTime(new Date());
                info.setType(GISAppEntityUtil.POINT_INFORMATION_TYPE_STRING);
                info.setString(information.getInformation());
                em.persist(info);
            } else {
                info.setUpdateTime(new Date());
                info.setType(GISAppEntityUtil.POINT_INFORMATION_TYPE_STRING);
                info.setString(information.getInformation());
                em.merge(info);
            }
            info = em.find(PointInformation.class, new PointInformationPK(information.getPointId(), GISAppEntityUtil.APPROVED));
            if (info == null) {
                info = new PointInformation(information.getPointId(), GISAppEntityUtil.APPROVED);
                info.setUpdateTime(new Date());
                info.setType(GISAppEntityUtil.POINT_INFORMATION_TYPE_BOOLEAN);
                info.setBoolean1((short) 1);
                em.persist(info);
            } else {
                info.setUpdateTime(new Date());
                info.setType(GISAppEntityUtil.POINT_INFORMATION_TYPE_BOOLEAN);
                info.setBoolean1((short) 1);
                em.merge(info);
            }
        }

        public static void remove(EntityManager em, int pointId) {
            Point point = em.find(Point.class, pointId);
            if (point != null) {
                em.remove(point);
            }

            List<PointInformation> info = em.createNamedQuery("PointInformation.findByPointId", PointInformation.class)
                    .setParameter("pointId", pointId)
                    .getResultList();
            for (PointInformation pointInfo : info) {
                em.remove(pointInfo);
            }

            File file = em.find(File.class, pointId);
            if (file != null) {
                em.remove(file);
            }
        }

    }

    public static class ObserverTargetUtil {

        private static void persist(EntityManager em, String application, String module, String _class, String method) {
            ObserverTarget target = new ObserverTarget();
            target.setApplication(application);
            target.setModule(module);
            target.setClass1(_class);
            target.setMethod(method);
            target.setUpdateTime(new Date());
            em.persist(target);
        }

        public static void kick(EntityManager em) throws NamingException {
            String application = Util.getApplicationName();
            String module = Util.getModuleName();
            StackTraceElement[] elems = Thread.currentThread().getStackTrace();

            String className = elems[2].getClassName();
            String methodName = elems[2].getMethodName();

            persist(em, application, module, className, methodName);
            logger.log(Logger.Level.INFO, "Application={};Module={};Class={};Method={}", application, module, className, methodName);
        }
    }
}
