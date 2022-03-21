/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.r_terai.gisapp;

import com.r_terai.gisapp.entity.Information;
import com.r_terai.gisapp.entity.InformationPK;
import com.r_terai.gisapp.entity.PostInformationView;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;

/**
 *
 * @author r-terai
 */
public class PostInformationViewUtil {

    public static List<PostInformationView> getInformation(EntityManager em, boolean approved) {
        return em.createNamedQuery("PostInformationView.findByApproved", PostInformationView.class).setParameter("approved", approved ? (short) 1 : (short) 0).getResultList();
    }

    public static void merge(EntityManager em, PostInformationView information) {
        Information info = em.find(Information.class, new InformationPK(GISAppEntityUtil.ID_TYPE_POINT, information.getPointId(), GISAppEntityUtil.INFORMATION));
        if (info == null) {
            info = new Information(GISAppEntityUtil.ID_TYPE_POINT, information.getPointId(), GISAppEntityUtil.INFORMATION);
            info.setUpdateTime(new Date());
            info.setType(GISAppEntityUtil.INFORMATION_TYPE_STRING);
            info.setString(information.getInformation());
            em.persist(info);
        } else {
            info.setUpdateTime(new Date());
            info.setType(GISAppEntityUtil.INFORMATION_TYPE_STRING);
            info.setString(information.getInformation());
            em.merge(info);
        }
        info = em.find(Information.class, new InformationPK(GISAppEntityUtil.ID_TYPE_POINT, information.getPointId(), GISAppEntityUtil.APPROVED));
        if (info == null) {
            info = new Information(GISAppEntityUtil.ID_TYPE_POINT, information.getPointId(), GISAppEntityUtil.APPROVED);
            info.setUpdateTime(new Date());
            info.setType(GISAppEntityUtil.INFORMATION_TYPE_BOOLEAN);
            info.setBoolean1((short) 1);
            em.persist(info);
        } else {
            info.setUpdateTime(new Date());
            info.setType(GISAppEntityUtil.INFORMATION_TYPE_BOOLEAN);
            info.setBoolean1((short) 1);
            em.merge(info);
        }
    }

}
