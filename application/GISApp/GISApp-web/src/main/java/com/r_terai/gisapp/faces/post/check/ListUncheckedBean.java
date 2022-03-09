/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.r_terai.gisapp.faces.post.check;

import com.r_terai.gisapp.ejb.PostInformationEJB;
import com.r_terai.gisapp.entity.PostInformationView;
import java.util.List;
import javax.inject.Inject;

/**
 *
 * @author r-terai
 */
public class ListUncheckedBean {

    private List<PostInformationView> postedInformation;

    @Inject
    private PostInformationEJB postInformationEJB;

    /**
     * Creates a new instance of CheckDisasterInformationBean
     */
    public ListUncheckedBean() {
    }

    public void load() {
        postedInformation = postInformationEJB.getUncheckedInformation();
    }

    public List<PostInformationView> getPostedInformation() {
        return postedInformation;
    }

}
