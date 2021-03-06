/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.r_terai.gisapp.rest;

import com.mapbox.geojson.FeatureCollection;
import com.r_terai.gisapp.ejb.GeoJsonEJB;
import com.r_terai.gisapp.entity.GeojsonFileLocation;
import com.r_terai.java.util.Logger;
import java.net.URI;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.NotSupportedException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * REST Web Service
 *
 * @author r-terai
 */
@TransactionManagement(TransactionManagementType.BEAN)
@Path("gml")
public class GmlResource {

    @Context
    private UriInfo context;

    @Resource
    UserTransaction tx;

    @EJB(name = "GeoJsonEJB")
    GeoJsonEJB geoJsonEJB;

    private static final Logger LOG = Logger.getLogger(GmlResource.class.getName());

    /**
     * Creates a new instance of RestResource
     */
    public GmlResource() {
    }

    @GET
    @Path(value = "/geoJson")
    @Produces(MediaType.APPLICATION_JSON)
    public Response search(@QueryParam("areaCode") final String areaCode,
            @QueryParam("type") final String type) {
        FeatureCollection geoJson = null;

        GeojsonFileLocation location = null;

        try {
            tx.begin();
            location = geoJsonEJB.searchStoredGeoJson(type, areaCode);
            tx.commit();
        } catch (HeuristicMixedException | HeuristicRollbackException | SecurityException | IllegalStateException | NotSupportedException | SystemException | RollbackException ex) {
            try {
                tx.rollback();
                LOG.log(Logger.Level.SEVERE, null, ex);
                throw new WebApplicationException();
            } catch (IllegalStateException | SecurityException | SystemException ex1) {
                LOG.log(Logger.Level.SEVERE, null, ex1);
                throw new WebApplicationException();
            }
        }
        if (location != null) {
            return Response.seeOther(URI.create(location.getLocation())).build();
        }

        try {
            tx.begin();
            geoJson = geoJsonEJB.search(type, areaCode);

            tx.commit();
        } catch (HeuristicMixedException | HeuristicRollbackException | SecurityException | IllegalStateException | NotSupportedException | SystemException | RollbackException ex) {
            try {
                tx.rollback();
                LOG.log(Logger.Level.SEVERE, null, ex);
                throw new WebApplicationException();
            } catch (IllegalStateException | SecurityException | SystemException ex1) {
                LOG.log(Logger.Level.SEVERE, null, ex1);
                throw new WebApplicationException();
            }
        }
        return Response.ok(geoJson != null ? geoJson.toJson() : null, MediaType.APPLICATION_JSON_TYPE).build();
    }

}
