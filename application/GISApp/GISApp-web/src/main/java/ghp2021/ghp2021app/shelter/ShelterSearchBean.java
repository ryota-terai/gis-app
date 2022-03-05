/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ghp2021.ghp2021app.shelter;

import ghp2021.ghp2021app.ejb.ShelterInformationEJB;
import ghp2021.ghp2021entity.ShelterInformation;
import ghp2021.ghp2021entity.ShelterInformationExt;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import org.primefaces.PrimeFaces;

/**
 *
 * @author Ryota-Terai
 */
public class ShelterSearchBean implements Serializable {

    /**
     * 行政区域コード
     */
    private String administrativeAreaCode;

    /**
     * 地震災害
     */
    private boolean p20_007;

    /**
     * 津波災害
     */
    private boolean p20_008;

    /**
     * 水害
     */
    private boolean p20_009;

    /**
     * 火山災害
     */
    private boolean p20_010;

    /**
     * その他
     */
    private boolean p20_011;

    /**
     * 指定なし
     */
    private boolean p20_012;

    private List<ShelterSearchResult> shelters;

    private ShelterSearchResult selectedShelter;

    @Inject
    private ShelterInformationEJB searchEJB;

    /**
     * Creates a new instance of ShelterSearchBean
     */
    public ShelterSearchBean() {
        FacesContext context = FacesContext.getCurrentInstance();
        ExternalContext exContext = context.getExternalContext();
        administrativeAreaCode = exContext.getInitParameter("GHP2021App_areaCode");
        this.p20_007 = true;
        this.p20_008 = true;
        this.p20_009 = true;
        this.p20_010 = true;
        this.p20_011 = true;
        this.p20_012 = true;
    }

    @PostConstruct
    public void init() {
        shelters = new ArrayList();
    }

    public String getAdministrativeAreaCode() {
        return administrativeAreaCode;
    }

    public void setAdministrativeAreaCode(String administrativeAreaCode) {
        this.administrativeAreaCode = administrativeAreaCode;
    }

    public boolean isP20_007() {
        return p20_007;
    }

    public void setP20_007(boolean p20_007) {
        this.p20_007 = p20_007;
    }

    public boolean isP20_008() {
        return p20_008;
    }

    public void setP20_008(boolean p20_008) {
        this.p20_008 = p20_008;
    }

    public boolean isP20_009() {
        return p20_009;
    }

    public void setP20_009(boolean p20_009) {
        this.p20_009 = p20_009;
    }

    public boolean isP20_010() {
        return p20_010;
    }

    public void setP20_010(boolean p20_010) {
        this.p20_010 = p20_010;
    }

    public boolean isP20_011() {
        return p20_011;
    }

    public void setP20_011(boolean p20_011) {
        this.p20_011 = p20_011;
    }

    public boolean isP20_012() {
        return p20_012;
    }

    public void setP20_012(boolean p20_012) {
        this.p20_012 = p20_012;
    }

    public List<ShelterSearchResult> getShelters() {
        return shelters;
    }

    public ShelterSearchResult getSelectedShelter() {
        return selectedShelter;
    }

    public void setSelectedShelter(ShelterSearchResult selectedShelter) {
        this.selectedShelter = selectedShelter;
    }

    public void search() {
        List<ShelterInformation> shelterInformationList = searchEJB.search(administrativeAreaCode, p20_007, p20_008, p20_009, p20_010, p20_011, null);
        shelters.clear();
        for (ShelterInformation shelterInformation : shelterInformationList) {
            ShelterInformationExt ext = shelterInformation.getShelterInformationExt();
            ShelterSearchResult result = new ShelterSearchResult(shelterInformation.getGeom(),
                    shelterInformation.getAdministrativeAreaCode(),
                    shelterInformation.getName(),
                    shelterInformation.getAddress(),
                    shelterInformation.getType(),
                    shelterInformation.getLatitude(),
                    shelterInformation.getLongitude(),
                    shelterInformation.getP20007() != 0,
                    shelterInformation.getP20008() != 0,
                    shelterInformation.getP20009() != 0,
                    shelterInformation.getP20010() != 0,
                    shelterInformation.getP20011() != 0,
                    shelterInformation.getP20012() != 0,
                    (int) Math.ceil(Math.random() * 100),
                    (int) Math.ceil(Math.random() * 100),
                    ext != null ? ext.getOpen() == 1 : false,
                    ext != null ? ext.getComment() : null);

            shelters.add(result);
        }
    }

    public void saveShelter() {
        searchEJB.upateShelterInformationExt(selectedShelter.getGeom(), selectedShelter.isOpen(), selectedShelter.getComment());
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("避難所情報を更新しました"));

        PrimeFaces.current().executeScript("PF('manageShelterDialog').hide()");
        PrimeFaces.current().ajax().update("messages", "form2:tbl");
    }
}
