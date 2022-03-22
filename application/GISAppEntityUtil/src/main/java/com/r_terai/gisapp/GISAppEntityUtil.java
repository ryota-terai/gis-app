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
import java.util.Date;
import java.util.List;
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

}
