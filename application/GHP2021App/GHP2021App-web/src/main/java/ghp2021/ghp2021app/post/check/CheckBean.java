/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ghp2021.ghp2021app.post.check;

import ghp2021.ghp2021app.ejb.PostInformationEJB;
import ghp2021.ghp2021entity.PostInformation;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.Date;
import javax.inject.Inject;

/**
 *
 * @author r-terai
 */
public class CheckBean implements Serializable {

    private long id;

    private boolean admin;

    private PostInformation postInformation;

    @Inject
    private PostInformationEJB postInformationEJB;

    /**
     * Creates a new instance of CheckDisasterInformationBean
     */
    public CheckBean() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    public PostInformation getPostInformation() {
        return postInformation;
    }

    public void load() {
        this.postInformation = postInformationEJB.getPostInformation(id);
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

    public String confirm() {
        postInformation.setTime(new Date());

        postInformationEJB.confirm(postInformation);

        return "/post/check/index?faces-redirect=true";
    }

    public String delete() {
        postInformationEJB.delete(id);

        return "/post/check/index?faces-redirect=true";
    }
}
