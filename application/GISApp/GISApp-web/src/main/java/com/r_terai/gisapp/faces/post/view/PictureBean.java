/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.r_terai.gisapp.faces.post.view;

import com.r_terai.gisapp.ejb.PostInformationEJB;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.Serializable;
import javax.inject.Inject;

/**
 *
 * @author r-terai
 */
public class PictureBean implements Serializable {

    private int id;
    @Inject
    private PostInformationEJB postInformationEJB;

    /**
     * Creates a new instance of PictureBean
     */
    public PictureBean() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public byte[] getPicture() throws IOException {
        byte byteArray[] = postInformationEJB.getPicture(id);
        if (byteArray == null) {
            byteArray = new byte[0];
        }
        ByteArrayInputStream is = new ByteArrayInputStream(byteArray);
        byte[] array = new byte[is.available()];
        is.read(array);
        return array;
    }

}
