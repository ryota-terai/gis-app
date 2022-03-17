/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.r_terai.gisapp;

import com.r_terai.gisapp.entity.ShelterInformationView;
import java.util.List;
import javax.persistence.EntityManager;

/**
 *
 * @author r-terai
 */
public class ShelterInformationViewUtil {

    public static List<ShelterInformationView> search(EntityManager em, String administrativeAreaCode, String type) {
        List<ShelterInformationView> shelters = em.createNativeQuery("SELECT * FROM SHELTER_INFORMATION_VIEW WHERE P20_001 like ?1 AND TYPE = ?2", ShelterInformationView.class)
                .setParameter(1, (administrativeAreaCode == null ? "" : administrativeAreaCode) + "%")
                .setParameter(2, type)
                .getResultList();
        return shelters;
    }

}
