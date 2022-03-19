/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.r_terai.gisapp;

import com.r_terai.gisapp.entity.ShelterInformation;
import com.r_terai.gisapp.entity.ShelterInformationView;
import com.r_terai.java.util.Logger;
import java.util.List;
import javax.persistence.EntityManager;

/**
 *
 * @author r-terai
 */
public class ShelterInformationUtil {

    public static List<ShelterInformation> search(EntityManager em, String administrativeAreaCode, String type) {
        List<ShelterInformation> shelters = em.createNativeQuery("SELECT * FROM SHELTER_INFORMATION WHERE P20_001 like ?1 AND TYPE = ?2", ShelterInformation.class)
                .setParameter(1, (administrativeAreaCode == null ? "" : administrativeAreaCode) + "%")
                .setParameter(2, type)
                .getResultList();
        return shelters;
    }

    public static void copy(EntityManager em) {
        List<ShelterInformation> list = em.createNamedQuery("ShelterInformation.findAll", ShelterInformation.class)
                .getResultList();
        for (ShelterInformation shelter : list) {
            em.remove(shelter);
        }
        List<ShelterInformationView> viewList = em.createNamedQuery("ShelterInformationView.findAll", ShelterInformationView.class)
                .getResultList();
        for (ShelterInformationView shelter : viewList) {
            ShelterInformation entity = new ShelterInformation();
            entity.setComment(shelter.getComment());
            entity.setOpen(shelter.getOpen());
            entity.setP20001(shelter.getP20001());
            entity.setP20002(shelter.getP20002());
            entity.setP20003(shelter.getP20003());
            entity.setP20004(shelter.getP20004());
            entity.setP20005(shelter.getP20005());
            entity.setP20006(shelter.getP20006());
            entity.setP20007(shelter.getP20007());
            entity.setP20008(shelter.getP20008());
            entity.setP20009(shelter.getP20009());
            entity.setP20010(shelter.getP20010());
            entity.setP20011(shelter.getP20011());
            entity.setP20012(shelter.getP20012());
            entity.setPointId(shelter.getPointId());
            entity.setPrivate1(shelter.getPrivate1());
            entity.setType(shelter.getType());
            entity.setUpdateTime(shelter.getUpdateTime());
            entity.setX(shelter.getX());
            entity.setY(shelter.getY());
            entity.setZ(shelter.getZ());

            em.persist(entity);
            GISAppEntityUtil.logger.log(Logger.Level.INFO, "point={}", entity.toString());
        }
    }

}
