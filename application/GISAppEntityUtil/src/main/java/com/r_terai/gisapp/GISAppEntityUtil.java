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
import com.r_terai.gisapp.entity.A48MultiPolygonView;
import com.r_terai.gisapp.entity.A48PolygonView;
import com.r_terai.gisapp.entity.GeojsonFileLocation;
import com.r_terai.gisapp.entity.Information;
import com.r_terai.gisapp.entity.LineString;
import com.r_terai.gisapp.entity.LineStringGeometry;
import com.r_terai.gisapp.entity.MultiLineString;
import com.r_terai.gisapp.entity.MultiLineStringGeometry;
import com.r_terai.gisapp.entity.MultiPolygon;
import com.r_terai.gisapp.entity.MultiPolygonGeometry;
import com.r_terai.gisapp.entity.Point;
import com.r_terai.gisapp.entity.Polygon;
import com.r_terai.gisapp.entity.PolygonGeometry;
import com.r_terai.java.util.Logger;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javax.persistence.EntityManager;

/**
 *
 * @author r-terai
 */
public class GISAppEntityUtil {

    public static final String ID_TYPE_POINT = "POINT_ID";
    public static final String ID_TYPE_POLYGON = "POLYGON_ID";
    public static final String ID_TYPE_MULTI_POLYGON = "MULTI_POLYGON_ID";
    public static final String ID_TYPE_LINE_STRING = "LINE_STRING_ID";
    public static final String ID_TYPE_MULTI_LINE_STRING = "MULTI_LINE_STRING_ID";

    public static final String INFORMATION_TYPE_STRING = "String";
    public static final String INFORMATION_TYPE_BOOLEAN = "Boolean";
    public static final String INFORMATION_TYPE_NUMBER = "Number";
    public static final String COMMENT = "COMMENT";
    public static final String OPEN = "OPEN";
    public static final String APPROVED = "APPROVED";
    public static final String INFORMATION = "INFORMATION";

    static final Logger logger = new Logger(GISAppEntityUtil.class.getName());

    public static void persist(EntityManager em, FeatureCollection featureCollection, boolean _private, String type, boolean expand) {
        for (Feature feature : featureCollection.features()) {
            Geometry geometry = feature.geometry();
            if (geometry instanceof com.mapbox.geojson.Point) {
                Point point = persistPoint(em, geometry, _private, type);
                persistProperties(em, feature, GISAppEntityUtil.ID_TYPE_POINT, point.getPointId());
                GISAppEntityUtil.logger.log(Logger.Level.INFO, "point={}", point.toString());
            } else if (geometry instanceof com.mapbox.geojson.Polygon) {
                Polygon polygon = persistPolygon(em, geometry, _private, type, expand);
                persistProperties(em, feature, GISAppEntityUtil.ID_TYPE_POLYGON, polygon.getPolygonId());
                GISAppEntityUtil.logger.log(Logger.Level.INFO, "polygon={}", polygon.toString());
            } else if (geometry instanceof com.mapbox.geojson.MultiPolygon) {
                MultiPolygon polygon = persistMultiPolygon(em, geometry, _private, type, expand);
                persistProperties(em, feature, GISAppEntityUtil.ID_TYPE_MULTI_POLYGON, polygon.getMultiPolygonId());
                GISAppEntityUtil.logger.log(Logger.Level.INFO, "MultiPolygon={}", polygon.toString());
            } else if (geometry instanceof com.mapbox.geojson.LineString) {
                LineString lineString = persistLineString(em, geometry, _private, type, expand);
                persistProperties(em, feature, GISAppEntityUtil.ID_TYPE_LINE_STRING, lineString.getLineStringId());
                GISAppEntityUtil.logger.log(Logger.Level.INFO, "LineString={}", lineString.toString());
            } else if (geometry instanceof com.mapbox.geojson.MultiLineString) {
                MultiLineString multiLineString = persistMultiLineString(em, geometry, _private, type, expand);
                persistProperties(em, feature, GISAppEntityUtil.ID_TYPE_MULTI_LINE_STRING, multiLineString.getMultiLineStringId());
                GISAppEntityUtil.logger.log(Logger.Level.INFO, "MultiLineString={}", multiLineString.toString());
            }
        }
    }

    private static MultiLineString persistMultiLineString(EntityManager em, Geometry geometry, boolean _private, String type, boolean expand) {
        MultiLineString multiLineString = new MultiLineString();
        multiLineString.setPrivate1(_private ? (short) 1 : 0);
        multiLineString.setType(type);
        multiLineString.setUpdateTime(new Date());
        em.persist(multiLineString);
        em.flush();
        if (expand) {
            int multiLineStringId = multiLineString.getMultiLineStringId(), lineStringIndex = 0, pointIndex = 0;
            for (List<com.mapbox.geojson.Point> lineStringArray : ((com.mapbox.geojson.MultiLineString) geometry).coordinates()) {
                for (com.mapbox.geojson.Point pointArray : lineStringArray) {
                    Point point = persistPoint(em, pointArray, _private, type);
                    GISAppEntityUtil.logger.log(Logger.Level.INFO, "point={}", point.toString());

                    MultiLineStringGeometry multiLineStringGeometry = new MultiLineStringGeometry(multiLineStringId, lineStringIndex, pointIndex);
                    multiLineStringGeometry.setPointId(point);
                    multiLineStringGeometry.setUpdateTime(new Date());

                    em.persist(multiLineStringGeometry);
                    GISAppEntityUtil.logger.log(Logger.Level.INFO, "MultiLineStringGeometry={}", multiLineStringGeometry.toString());

                    pointIndex++;
                }
                lineStringIndex++;
                pointIndex = 0;
            }
        } else {
            multiLineString.setMultiLineString(geometry.toJson());
            em.merge(multiLineString);
        }
        return multiLineString;

    }

    private static LineString persistLineString(EntityManager em, Geometry geometry, boolean _private, String type, boolean expand) {
        LineString lineString = new LineString();
        lineString.setPrivate1(_private ? (short) 1 : 0);
        lineString.setType(type);
        lineString.setUpdateTime(new Date());
        em.persist(lineString);
        em.flush();
        if (expand) {
            int lineStringId = lineString.getLineStringId(), pointIndex = 0;
            for (com.mapbox.geojson.Point lineStringPoint : ((com.mapbox.geojson.LineString) geometry).coordinates()) {
                Point point = persistPoint(em, lineStringPoint, _private, type);
                GISAppEntityUtil.logger.log(Logger.Level.INFO, "point={}", point.toString());
                LineStringGeometry lineStringGeometry = new LineStringGeometry(lineStringId, pointIndex);
                lineStringGeometry.setPointId(point);
                lineStringGeometry.setUpdateTime(new Date());
                em.persist(lineStringGeometry);
                GISAppEntityUtil.logger.log(Logger.Level.INFO, "lineStringGeometry={}", lineStringGeometry.toString());
                pointIndex++;
            }
        } else {
            lineString.setLineString(geometry.toJson());
            em.merge(lineString);
        }
        return lineString;

    }

    private static MultiPolygon persistMultiPolygon(EntityManager em, Geometry geometry, boolean _private, String type, boolean expand) {
        MultiPolygon polygon = new MultiPolygon();
        polygon.setPrivate1(_private ? (short) 1 : 0);
        polygon.setType(type);
        polygon.setUpdateTime(new Date());
        em.persist(polygon);
        em.flush();
        if (expand) {
            int polygonId = polygon.getMultiPolygonId(), multiPolygonIndex = 0, polygonIndex = 0, pointIndex = 0;
            for (List<List<com.mapbox.geojson.Point>> polygonArray : ((com.mapbox.geojson.MultiPolygon) geometry).coordinates()) {
                for (List<com.mapbox.geojson.Point> pointArray : polygonArray) {
                    for (com.mapbox.geojson.Point polygonPoint : pointArray) {
                        Point point = persistPoint(em, polygonPoint, _private, type);
                        GISAppEntityUtil.logger.log(Logger.Level.INFO, "point={}", point.toString());

                        MultiPolygonGeometry polygonGeometry = new MultiPolygonGeometry(polygonId, multiPolygonIndex, polygonIndex, pointIndex);
                        polygonGeometry.setPointId(point);
                        polygonGeometry.setUpdateTime(new Date());

                        em.persist(polygonGeometry);
                        GISAppEntityUtil.logger.log(Logger.Level.INFO, "MultiPolygonGeometry={}", polygonGeometry.toString());

                        pointIndex++;
                    }
                    polygonIndex++;
                    pointIndex = 0;
                }
                multiPolygonIndex++;
                polygonIndex = 0;
                pointIndex = 0;
            }
        } else {
            polygon.setMultiPolygon(geometry.toJson());
            em.merge(polygon);
        }
        return polygon;
    }

    private static Polygon persistPolygon(EntityManager em, Geometry geometry, boolean _private, String type, boolean expand) {
        Polygon polygon = new Polygon();
        polygon.setPrivate1(_private ? (short) 1 : 0);
        polygon.setType(type);
        polygon.setUpdateTime(new Date());
        em.persist(polygon);
        em.flush();
        if (expand) {
            int polygonId = polygon.getPolygonId(), polygonIndex = 0, pointIndex = 0;
            for (List<com.mapbox.geojson.Point> pointArray : ((com.mapbox.geojson.Polygon) geometry).coordinates()) {
                for (com.mapbox.geojson.Point PolygonPoint : pointArray) {
                    Point point = persistPoint(em, PolygonPoint, _private, type);
                    GISAppEntityUtil.logger.log(Logger.Level.INFO, "point={}", point.toString());

                    PolygonGeometry polygonGeometry = new PolygonGeometry(polygonId, polygonIndex, pointIndex);
                    polygonGeometry.setPointId(point);
                    polygonGeometry.setUpdateTime(new Date());

                    em.persist(polygonGeometry);
                    GISAppEntityUtil.logger.log(Logger.Level.INFO, "polygonGeometry={}", polygonGeometry.toString());

                    pointIndex++;
                }
                polygonIndex++;
                pointIndex = 0;
            }
        } else {
            polygon.setPolygon(geometry.toJson());
            em.merge(polygon);
        }
        return polygon;
    }

    private static Point persistPoint(EntityManager em, Geometry geometry, boolean _private, String type) {
        Point point = new Point();
        point.setX(((com.mapbox.geojson.Point) geometry).longitude());
        point.setY(((com.mapbox.geojson.Point) geometry).latitude());
        point.setPrivate1(_private ? (short) 1 : 0);
        point.setType(type);
        point.setUpdateTime(new Date());
        em.persist(point);
        em.flush();
        return point;
    }

    private static void persistProperties(EntityManager em, Feature feature, String type, int id) {
        for (String key : feature.properties().keySet()) {
            if (!feature.getProperty(key).isJsonNull()) {
                Information info = new Information(type, id, key);
                info.setUpdateTime(new Date());
                JsonPrimitive property = feature.getProperty(key).getAsJsonPrimitive();
                if (property.isString()) {
                    info.setType(GISAppEntityUtil.INFORMATION_TYPE_STRING);
                    info.setString((String) feature.getStringProperty(key));
                    em.persist(info);
                } else if (property.isNumber()) {
                    info.setType(GISAppEntityUtil.INFORMATION_TYPE_NUMBER);
                    info.setNumber(feature.getNumberProperty(key).doubleValue());
                    em.persist(info);
                } else if (property.isBoolean()) {
                    info.setType(GISAppEntityUtil.INFORMATION_TYPE_BOOLEAN);
                    info.setBoolean1(feature.getBooleanProperty(key) ? (short) 1 : (short) 0);
                    em.persist(info);
                } else {
                    GISAppEntityUtil.logger.log(Logger.Level.INFO, "Type={}", property.getClass().toGenericString());
                }
            }
        }
    }

    public static FeatureCollection search(EntityManager em, String type, String areaCode) {
        List<Feature> features = new ArrayList();

        switch (type) {
//            case "A33":
//                features.addAll(searchA33(em, type, areaCode));
//                break;
            case "A48":
                features.addAll(searchA48(em, type, areaCode));
                break;
            default:
                features.addAll(searchMultiLineString(em, type, areaCode));
                features.addAll(searchLineString(em, type, areaCode));
                features.addAll(searchMultiPolygon(em, type, areaCode));
                features.addAll(searchPolygon(em, type, areaCode));
                features.addAll(searchPoint(em, type, areaCode));
                break;
        }

        return FeatureCollection.fromFeatures(features);
    }

//    private static List<Feature> searchA33(EntityManager em, String type, String areaCode) {
//        List<Feature> features = new ArrayList();
//        List<A33LineStringView> a33LineStringViews = em.createNativeQuery("SELECT * FROM A33_LINE_STRING_VIEW WHERE A33_003 like ?1", A33LineStringView.class)
//                .setParameter(1, (areaCode == null ? "" : areaCode) + "%")
//                .getResultList();
//        GISAppEntityUtil.logger.log(Logger.Level.INFO, "found {} A33LineStringView items", a33LineStringViews.size());
//        for (A33LineStringView a33LineStringView : a33LineStringViews) {
//            com.mapbox.geojson.LineString geom;
//            if (a33LineStringView.getLineString() != null) {
//                geom = com.mapbox.geojson.LineString.fromJson(a33LineStringView.getLineString());
//            } else {
//                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//            }
//            Feature feature = Feature.fromGeometry(geom);
//            feature.addStringProperty("A33_001", a33LineStringView.getA33001());
//            feature.addStringProperty("A33_002", a33LineStringView.getA33002());
//            feature.addStringProperty("A33_003", a33LineStringView.getA33003());
//            feature.addStringProperty("A33_004", a33LineStringView.getA33004());
//            feature.addStringProperty("A33_005", a33LineStringView.getA33005());
//            feature.addStringProperty("A33_006", a33LineStringView.getA33006());
//            feature.addStringProperty("A33_007", a33LineStringView.getA33007());
//            feature.addStringProperty("A33_008", a33LineStringView.getA33008());
//            features.add(feature);
//        }
//
//        List<A33MultiPolygonView> a33MultiPolygonViews = em.createNativeQuery("SELECT * FROM A33_MULTI_POLYGON_VIEW WHERE A33_003 like ?1", A33MultiPolygonView.class)
//                .setParameter(1, (areaCode == null ? "" : areaCode) + "%")
//                .getResultList();
//        GISAppEntityUtil.logger.log(Logger.Level.INFO, "found {} A33MultiPolygonView items", a33MultiPolygonViews.size());
//        for (A33MultiPolygonView a33MultiPolygonView : a33MultiPolygonViews) {
//            com.mapbox.geojson.MultiPolygon geom;
//            if (a33MultiPolygonView.getMultiPolygon() != null) {
//                geom = com.mapbox.geojson.MultiPolygon.fromJson(a33MultiPolygonView.getMultiPolygon());
//            } else {
//                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//            }
//            Feature feature = Feature.fromGeometry(geom);
//            feature.addStringProperty("A33_001", a33MultiPolygonView.getA33001());
//            feature.addStringProperty("A33_002", a33MultiPolygonView.getA33002());
//            feature.addStringProperty("A33_003", a33MultiPolygonView.getA33003());
//            feature.addStringProperty("A33_004", a33MultiPolygonView.getA33004());
//            feature.addStringProperty("A33_005", a33MultiPolygonView.getA33005());
//            feature.addStringProperty("A33_006", a33MultiPolygonView.getA33006());
//            feature.addStringProperty("A33_007", a33MultiPolygonView.getA33007());
//            feature.addStringProperty("A33_008", a33MultiPolygonView.getA33008());
//            features.add(feature);
//        }
//
//        List<A33PolygonView> a33PolygonViews = em.createNativeQuery("SELECT * FROM A33_POLYGON_VIEW WHERE A33_003 like ?1", A33PolygonView.class)
//                .setParameter(1, (areaCode == null ? "" : areaCode) + "%")
//                .getResultList();
//        GISAppEntityUtil.logger.log(Logger.Level.INFO, "found {} A33PolygonView items", a33PolygonViews.size());
//        for (A33PolygonView a33PolygonView : a33PolygonViews) {
//            com.mapbox.geojson.Polygon geom;
//            if (a33PolygonView.getPolygon() != null) {
//                geom = com.mapbox.geojson.Polygon.fromJson(a33PolygonView.getPolygon());
//            } else {
//                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//            }
//            Feature feature = Feature.fromGeometry(geom);
//            feature.addStringProperty("A33_001", a33PolygonView.getA33001());
//            feature.addStringProperty("A33_002", a33PolygonView.getA33002());
//            feature.addStringProperty("A33_003", a33PolygonView.getA33003());
//            feature.addStringProperty("A33_004", a33PolygonView.getA33004());
//            feature.addStringProperty("A33_005", a33PolygonView.getA33005());
//            feature.addStringProperty("A33_006", a33PolygonView.getA33006());
//            feature.addStringProperty("A33_007", a33PolygonView.getA33007());
//            feature.addStringProperty("A33_008", a33PolygonView.getA33008());
//            features.add(feature);
//        }
//
//        return features;
//    }
    private static List<Feature> searchA48(EntityManager em, String type, String areaCode) {
        List<Feature> features = new ArrayList();
        List<A48MultiPolygonView> a48MultiPolygonViews = em.createNativeQuery("SELECT * FROM A48_MULTI_POLYGON_VIEW WHERE A48_003 like ?1", A48MultiPolygonView.class)
                .setParameter(1, (areaCode == null ? "" : areaCode) + "%")
                .getResultList();
        GISAppEntityUtil.logger.log(Logger.Level.INFO, "found {} A48MultiPolygonView items", a48MultiPolygonViews.size());
        for (A48MultiPolygonView a48MultiPolygonView : a48MultiPolygonViews) {
            com.mapbox.geojson.MultiPolygon geom;
            if (a48MultiPolygonView.getMultiPolygon() != null) {
                geom = com.mapbox.geojson.MultiPolygon.fromJson(a48MultiPolygonView.getMultiPolygon());
            } else {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
            Feature feature = Feature.fromGeometry(geom);
            feature.addStringProperty("A48_001", a48MultiPolygonView.getA48001());
            feature.addStringProperty("A48_002", a48MultiPolygonView.getA48002());
            feature.addStringProperty("A48_003", a48MultiPolygonView.getA48003());
            feature.addNumberProperty("A48_004", a48MultiPolygonView.getA48004());
            feature.addStringProperty("A48_005", a48MultiPolygonView.getA48005());
            feature.addStringProperty("A48_006", a48MultiPolygonView.getA48006());
            feature.addNumberProperty("A48_007", a48MultiPolygonView.getA48007());
            feature.addStringProperty("A48_008", a48MultiPolygonView.getA48008());
            feature.addStringProperty("A48_009", a48MultiPolygonView.getA48009());
            feature.addStringProperty("A48_010", a48MultiPolygonView.getA48010());
            feature.addStringProperty("A48_011", a48MultiPolygonView.getA48011());
            feature.addNumberProperty("A48_012", a48MultiPolygonView.getA48012());
            feature.addStringProperty("A48_013", a48MultiPolygonView.getA48013());
            feature.addStringProperty("A48_014", a48MultiPolygonView.getA48014());
            features.add(feature);
        }

        List<A48PolygonView> a48PolygonViews = em.createNativeQuery("SELECT * FROM A48_POLYGON_VIEW WHERE A48_003 like ?1", A48PolygonView.class)
                .setParameter(1, (areaCode == null ? "" : areaCode) + "%")
                .getResultList();
        GISAppEntityUtil.logger.log(Logger.Level.INFO, "found {} A48PolygonView items", a48PolygonViews.size());
        for (A48PolygonView a48PolygonView : a48PolygonViews) {
            com.mapbox.geojson.Polygon geom;
            if (a48PolygonView.getPolygon() != null) {
                geom = com.mapbox.geojson.Polygon.fromJson(a48PolygonView.getPolygon());
            } else {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
            Feature feature = Feature.fromGeometry(geom);
            feature.addStringProperty("A48_001", a48PolygonView.getA48001());
            feature.addStringProperty("A48_002", a48PolygonView.getA48002());
            feature.addStringProperty("A48_003", a48PolygonView.getA48003());
            feature.addNumberProperty("A48_004", a48PolygonView.getA48004());
            feature.addStringProperty("A48_005", a48PolygonView.getA48005());
            feature.addStringProperty("A48_006", a48PolygonView.getA48006());
            feature.addNumberProperty("A48_007", a48PolygonView.getA48007());
            feature.addStringProperty("A48_008", a48PolygonView.getA48008());
            feature.addStringProperty("A48_009", a48PolygonView.getA48009());
            feature.addStringProperty("A48_010", a48PolygonView.getA48010());
            feature.addStringProperty("A48_011", a48PolygonView.getA48011());
            feature.addNumberProperty("A48_012", a48PolygonView.getA48012());
            feature.addStringProperty("A48_013", a48PolygonView.getA48013());
            feature.addStringProperty("A48_014", a48PolygonView.getA48014());
            features.add(feature);
        }

        return features;
    }

    private static List<Feature> searchMultiLineString(EntityManager em, String type, String areaCode) {
        List<Feature> features = new ArrayList();

        List<MultiLineString> multiLineStrings = em.createNamedQuery("MultiLineString.findByType", MultiLineString.class)
                .setParameter("type", type)
                .getResultList();
        for (MultiLineString lineString : multiLineStrings) {
            List<Information> informationList = em.createNativeQuery("SELECT * FROM INFORMATION WHERE ID_TYPE = ?1 AND ID = ?2", Information.class)
                    .setParameter(1, GISAppEntityUtil.ID_TYPE_MULTI_LINE_STRING)
                    .setParameter(2, lineString.getMultiLineStringId())
                    .getResultList();
            String area = getAreaCode(informationList);
            if (area == null || area.startsWith(areaCode)) {
                com.mapbox.geojson.MultiLineString geom;
                if (lineString.getMultiLineString() != null) {
                    geom = com.mapbox.geojson.MultiLineString.fromJson(lineString.getMultiLineString());
                } else {
                    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                }
                Feature feature = Feature.fromGeometry(geom);
                feature = setProperties(informationList, feature);
                features.add(feature);
            }
        }

        return features;
    }

    private static List<Feature> searchLineString(EntityManager em, String type, String areaCode) {
        List<Feature> features = new ArrayList();

        List<LineString> lineStrings = em.createNamedQuery("LineString.findByType", LineString.class)
                .setParameter("type", type)
                .getResultList();
        for (LineString lineString : lineStrings) {
            List<Information> informationList = em.createNativeQuery("SELECT * FROM INFORMATION WHERE ID_TYPE = ?1 AND ID = ?2", Information.class)
                    .setParameter(1, GISAppEntityUtil.ID_TYPE_LINE_STRING)
                    .setParameter(2, lineString.getLineStringId())
                    .getResultList();
            String area = getAreaCode(informationList);
            if (area == null || area.startsWith(areaCode)) {
                com.mapbox.geojson.LineString geom;
                if (lineString.getLineString() != null) {
                    geom = com.mapbox.geojson.LineString.fromJson(lineString.getLineString());
                } else {
                    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                }
                Feature feature = Feature.fromGeometry(geom);
                feature = setProperties(informationList, feature);
                features.add(feature);
            }
        }

        return features;
    }

    private static List<Feature> searchMultiPolygon(EntityManager em, String type, String areaCode) {
        List<Feature> features = new ArrayList();

        List<MultiPolygon> multiPolygons = em.createNamedQuery("MultiPolygon.findByType", MultiPolygon.class)
                .setParameter("type", type)
                .getResultList();
        for (MultiPolygon multiPolygon : multiPolygons) {
            List<Information> informationList = em.createNativeQuery("SELECT * FROM INFORMATION WHERE ID_TYPE = ?1 AND ID = ?2", Information.class)
                    .setParameter(1, GISAppEntityUtil.ID_TYPE_MULTI_POLYGON)
                    .setParameter(2, multiPolygon.getMultiPolygonId())
                    .getResultList();
            String area = getAreaCode(informationList);
            if (area == null || area.startsWith(areaCode)) {
                com.mapbox.geojson.MultiPolygon geom;
                if (multiPolygon.getMultiPolygon() != null) {
                    geom = com.mapbox.geojson.MultiPolygon.fromJson(multiPolygon.getMultiPolygon());
                } else {
                    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                }
                Feature feature = Feature.fromGeometry(geom);
                feature = setProperties(informationList, feature);
                features.add(feature);
            }
        }

        return features;
    }

    private static List<Feature> searchPolygon(EntityManager em, String type, String areaCode) {
        List<Feature> features = new ArrayList();

        List<Polygon> polygons = em.createNamedQuery("Polygon.findByType", Polygon.class)
                .setParameter("type", type)
                .getResultList();
        for (Polygon polygon : polygons) {
            List<Information> informationList = em.createNativeQuery("SELECT * FROM INFORMATION WHERE ID_TYPE = ?1 AND ID = ?2", Information.class)
                    .setParameter(1, GISAppEntityUtil.ID_TYPE_POLYGON)
                    .setParameter(2, polygon.getPolygonId())
                    .getResultList();
            String area = getAreaCode(informationList);
            if (area == null || area.startsWith(areaCode)) {
                com.mapbox.geojson.Polygon geom;
                if (polygon.getPolygon() != null) {
                    geom = com.mapbox.geojson.Polygon.fromJson(polygon.getPolygon());
                } else {
                    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                }
                Feature feature = Feature.fromGeometry(geom);
                feature = setProperties(informationList, feature);
                features.add(feature);
            }
        }

        return features;
    }

    private static List<Feature> searchPoint(EntityManager em, String type, String areaCode) {
        List<Feature> features = new ArrayList();

        List<Point> points = em.createNamedQuery("Point.findByType", Point.class)
                .setParameter("type", type)
                .getResultList();
        for (Point point : points) {
            List<Information> informationList = em.createNativeQuery("SELECT * FROM INFORMATION WHERE ID_TYPE = ?1 AND ID = ?2", Information.class)
                    .setParameter(1, GISAppEntityUtil.ID_TYPE_POINT)
                    .setParameter(2, point.getPointId())
                    .getResultList();
            String area = getAreaCode(informationList);
            if (area == null || area.startsWith(areaCode)) {
                com.mapbox.geojson.Point geom;
                if (point.getZ() != null) {
                    geom = com.mapbox.geojson.Point.fromLngLat(point.getX(), point.getY(), point.getZ());
                } else {
                    geom = com.mapbox.geojson.Point.fromLngLat(point.getX(), point.getY());
                }
                Feature feature = Feature.fromGeometry(geom);
                feature = setProperties(informationList, feature);
                features.add(feature);
            }
        }

        return features;
    }

    private static Feature setProperties(List<Information> informationList, Feature feature) {
        for (Information information : informationList) {
            switch (information.getType()) {
                case INFORMATION_TYPE_STRING:
                    feature.addStringProperty(information.getInformationPK().getName(), information.getString());
                    break;
                case INFORMATION_TYPE_BOOLEAN:
                    feature.addBooleanProperty(information.getInformationPK().getName(), information.getBoolean1() == (short) 1);
                    break;
                case INFORMATION_TYPE_NUMBER:
                    feature.addNumberProperty(information.getInformationPK().getName(), information.getNumber());
                    break;
            }
        }
        return feature;
    }

    private static String getAreaCode(List<Information> informationList) {
        List<String> areaCodeParam = new ArrayList();
        areaCodeParam.add("A33_003");
        areaCodeParam.add("A46-a_002");
        areaCodeParam.add("A46-b_002");
        areaCodeParam.add("A46-c_002");
        areaCodeParam.add("A49_002");
        areaCodeParam.add("A47_002");
        areaCodeParam.add("A40_002");
        areaCodeParam.add("A48_003");

        for (Information information : informationList) {
            if (areaCodeParam.contains(information.getInformationPK().getName())) {
                return information.getString();
            }
        }
        return null;
    }
}
