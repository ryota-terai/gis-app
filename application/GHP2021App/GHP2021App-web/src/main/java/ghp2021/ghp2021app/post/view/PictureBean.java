/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ghp2021.ghp2021app.post.view;

import ghp2021.ghp2021app.ejb.PostInformationEJB;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.Serializable;
import javax.inject.Inject;

/**
 *
 * @author r-terai
 */
public class PictureBean implements Serializable {

    private long id;
    @Inject
    private PostInformationEJB postInformationEJB;

    /**
     * Creates a new instance of PictureBean
     */
    public PictureBean() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
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
