/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.r_terai.gisapp.ejb;

import com.r_terai.gisapp.entity.File;
import com.r_terai.gisapp.entity.PostInformationView;
import com.r_terai.gisapp.GISAppEntityUtil;
import com.r_terai.java.util.Logger;
import java.util.List;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Ryota-Terai
 */
@Stateless
@LocalBean
@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
public class PostInformationEJB implements PostInformationEJBLocal {

    @PersistenceContext(unitName = "GISAppEntity")
    private EntityManager em;

    private static final Logger logger = new Logger(PostInformationEJB.class.getName());

    @Override
    public void postInformation(String latitude, String longtitude, String information, byte[] file) {
        GISAppEntityUtil.PointUtil.persist(em, "post_information", longtitude, latitude, information, file);
    }

    @Override
    public List<PostInformationView> getUncheckedInformation() {
        return GISAppEntityUtil.PostInformationViewUtil.getInformation(em, false);
    }

    @Override
    public List<PostInformationView> getApprovedInformation() {
        return GISAppEntityUtil.PostInformationViewUtil.getInformation(em, true);
    }

    @Override
    public PostInformationView getPostInformation(int pointId) {
        return em.find(PostInformationView.class, pointId);
    }

    @Override
    public byte[] getPicture(int pointId) {
        File file = em.find(File.class, pointId);
        if (file != null) {
            return file.getFile();
        } else {
            return null;
        }
    }

    @Override
    public void confirm(PostInformationView information) {
        GISAppEntityUtil.PostInformationViewUtil.merge(em, information);
    }


    @Override
    public void delete(int pointId) {
        GISAppEntityUtil.PostInformationViewUtil.remove(em, pointId);
    }

}
