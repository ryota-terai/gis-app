/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ghp2021.ghp2021app.post.post;

import ghp2021.ghp2021app.ejb.PostInformationEJB;
import java.io.IOException;
import java.io.InputStream;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import org.primefaces.model.file.UploadedFile;

/**
 *
 * @author Ryota-Terai
 */
public class PostBean {

    /**
     * 緯度
     */
    private String latitude;

    /**
     * 経度
     */
    private String longitude;

    /**
     * 投稿内容
     */
    private String information;

    /**
     * ファイル
     */
    private UploadedFile file;

    @Inject
    private PostInformationEJB postInformationEJB;

    public PostBean() {
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getInformation() {
        return information;
    }

    public void setInformation(String information) {
        this.information = information;
    }

    public UploadedFile getFile() {
        return file;
    }

    public void setFile(UploadedFile file) {
        this.file = file;
    }

    public String postDisaster() throws IOException {
        byte[] buffer = null;
        if (file != null) {
            FacesMessage message = new FacesMessage("Successful", file.getFileName() + " is uploaded.");
            FacesContext.getCurrentInstance().addMessage(null, message);

            long size = file.getSize();
            InputStream stream = file.getInputStream();
            buffer = new byte[(int) size];
            stream.read(buffer, 0, (int) size);
            stream.close();
        }
        postInformationEJB.postDisasterInformation(latitude, longitude, information, buffer);
        return "disasterPosted?faces-redirect=true";
    }
}
