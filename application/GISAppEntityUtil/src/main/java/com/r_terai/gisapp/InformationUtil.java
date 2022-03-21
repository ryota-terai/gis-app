/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.r_terai.gisapp;

import com.r_terai.gisapp.entity.Information;
import com.r_terai.gisapp.entity.InformationPK;
import java.util.Date;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

/**
 *
 * @author r-terai
 */
public class InformationUtil {

    public static void update(EntityManager em, String idType, int id, boolean open, String comment) {
        Information info;
        try {
            info = (Information) em.createNativeQuery("SELECT * FROM INFORMATION WHERE ID_TYPE = ?1 AND ID = ?2 AND NAME = ?3", Information.class)
                    .setParameter(1, idType)
                    .setParameter(2, id)
                    .setParameter(3, GISAppEntityUtil.OPEN)
                    .getSingleResult();
            info.setBoolean1(open ? (short) 1 : 0);
            info.setUpdateTime(new Date());
            em.merge(info);
        } catch (NoResultException ex) {
            InformationPK pk = new InformationPK(idType, id, GISAppEntityUtil.OPEN);
            info = new Information(pk);
            info.setType(GISAppEntityUtil.INFORMATION_TYPE_BOOLEAN);
            info.setBoolean1(open ? (short) 1 : 0);
            info.setUpdateTime(new Date());
            em.persist(info);
        }
        try {
            info = (Information) em.createNativeQuery("SELECT * FROM INFORMATION WHERE ID_TYPE = ?1 AND ID = ?2 AND NAME = ?3", Information.class)
                    .setParameter(1, idType)
                    .setParameter(1, id)
                    .setParameter(2, GISAppEntityUtil.COMMENT)
                    .getSingleResult();
            info.setString(comment);
            info.setUpdateTime(new Date());
            em.merge(info);
        } catch (NoResultException ex) {
            InformationPK pk = new InformationPK(idType, id, GISAppEntityUtil.COMMENT);
            info = new Information(pk);
            info.setType(GISAppEntityUtil.INFORMATION_TYPE_STRING);
            info.setString(comment);
            info.setUpdateTime(new Date());
            em.persist(info);
        }
    }

}
