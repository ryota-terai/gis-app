/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.r_terai.gisapp.faces.shelter;

import com.r_terai.gisapp.ejb.PointInformationEJB;
import java.io.IOException;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import org.primefaces.event.FileUploadEvent;

/**
 *
 * @author r-terai
 */
public class ShelterSetupBean {

    @Inject
    private PointInformationEJB shelterEJB;

    /**
     * Creates a new instance of ShelterSetupBean
     */
    public ShelterSetupBean() {
    }

    public void handleFileUpload(FileUploadEvent event) throws IOException {
        shelterEJB.setup(event.getFile().getInputStream(), false, "shelter");

        FacesMessage message = new FacesMessage("Successful", event.getFile().getFileName() + " is uploaded.");
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    public void initialize() {
        shelterEJB.initialize("shelter");

        FacesMessage message = new FacesMessage("Successful", "Initialized.");
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
}
