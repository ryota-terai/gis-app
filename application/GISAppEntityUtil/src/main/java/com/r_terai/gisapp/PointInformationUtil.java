/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.r_terai.gisapp;

import com.r_terai.gisapp.entity.PointInformation;
import com.r_terai.gisapp.entity.PointInformationPK;
import java.util.Date;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

/**
 *
 * @author r-terai
 */
public class PointInformationUtil {

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
