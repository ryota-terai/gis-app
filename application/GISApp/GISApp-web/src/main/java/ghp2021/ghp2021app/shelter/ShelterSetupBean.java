/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ghp2021.ghp2021app.shelter;

import ghp2021.ghp2021app.ejb.ShelterInformationEJB;
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
    private ShelterInformationEJB shelterEJB;

    /**
     * Creates a new instance of ShelterSetupBean
     */
    public ShelterSetupBean() {
    }

    public void handleFileUpload(FileUploadEvent event) throws IOException {
        shelterEJB.setup(event.getFile().getInputStream());

        FacesMessage message = new FacesMessage("Successful", event.getFile().getFileName() + " is uploaded.");
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
}
