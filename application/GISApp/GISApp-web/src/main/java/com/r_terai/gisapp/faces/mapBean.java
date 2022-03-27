/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.r_terai.gisapp.faces;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.model.SelectItem;

/**
 *
 * @author r-terai
 */
public class mapBean {

    private String areaCode;
    private List<SelectItem> prefectures;

    /**
     * Creates a new instance of mapBean
     */
    public mapBean() {
    }

    @PostConstruct
    public void init() {
        prefectures = new ArrayList<>();

        prefectures.add(new SelectItem("01", "北海道"));
        prefectures.add(new SelectItem("02", "青森県"));
        prefectures.add(new SelectItem("03", "岩手県"));
        prefectures.add(new SelectItem("04", "宮城県"));
        prefectures.add(new SelectItem("05", "秋田県"));
        prefectures.add(new SelectItem("06", "山形県"));
        prefectures.add(new SelectItem("07", "福島県"));
        prefectures.add(new SelectItem("08", "茨城県"));
        prefectures.add(new SelectItem("09", "栃木県"));
        prefectures.add(new SelectItem("10", "群馬県"));
        prefectures.add(new SelectItem("11", "埼玉県"));
        prefectures.add(new SelectItem("12", "千葉県"));
        prefectures.add(new SelectItem("13", "東京都"));
        prefectures.add(new SelectItem("14", "神奈川県"));
        prefectures.add(new SelectItem("15", "新潟県"));
        prefectures.add(new SelectItem("16", "富山県"));
        prefectures.add(new SelectItem("17", "石川県"));
        prefectures.add(new SelectItem("18", "福井県"));
        prefectures.add(new SelectItem("19", "山梨県"));
        prefectures.add(new SelectItem("20", "長野県"));
        prefectures.add(new SelectItem("21", "岐阜県"));
        prefectures.add(new SelectItem("22", "静岡県"));
        prefectures.add(new SelectItem("23", "愛知県"));
        prefectures.add(new SelectItem("24", "三重県"));
        prefectures.add(new SelectItem("25", "滋賀県"));
        prefectures.add(new SelectItem("26", "京都府"));
        prefectures.add(new SelectItem("27", "大阪府"));
        prefectures.add(new SelectItem("28", "兵庫県"));
        prefectures.add(new SelectItem("29", "奈良県"));
        prefectures.add(new SelectItem("30", "和歌山県"));
        prefectures.add(new SelectItem("31", "鳥取県"));
        prefectures.add(new SelectItem("32", "島根県"));
        prefectures.add(new SelectItem("33", "岡山県"));
        prefectures.add(new SelectItem("34", "広島県"));
        prefectures.add(new SelectItem("35", "山口県"));
        prefectures.add(new SelectItem("36", "徳島県"));
        prefectures.add(new SelectItem("37", "香川県"));
        prefectures.add(new SelectItem("38", "愛媛県"));
        prefectures.add(new SelectItem("39", "高知県"));
        prefectures.add(new SelectItem("40", "福岡県"));
        prefectures.add(new SelectItem("41", "佐賀県"));
        prefectures.add(new SelectItem("42", "長崎県"));
        prefectures.add(new SelectItem("43", "熊本県"));
        prefectures.add(new SelectItem("44", "大分県"));
        prefectures.add(new SelectItem("45", "宮崎県"));
        prefectures.add(new SelectItem("46", "鹿児島県"));
        prefectures.add(new SelectItem("47", "沖縄県"));
    }

    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }

    public List<SelectItem> getPrefectures() {
        return prefectures;
    }

    public void setPrefectures(List<SelectItem> prefectures) {
        this.prefectures = prefectures;
    }

    public String go() {
        if (areaCode != null) {
            String positionUrl = new String();
            switch (areaCode) {
                case "01":
                    positionUrl = "&lat=43.063940637499996&lon=141.347906782";
                    break;
                case "02":
                    positionUrl = "&lat=40.824338&lon=140.740087";
                    break;
                case "03":
                    positionUrl = "&lat=39.703647&lon=141.152592";
                    break;
                case "04":
                    positionUrl = "&lat=38.268803&lon=140.871846";
                    break;
                case "05":
                    positionUrl = "&lat=39.718058&lon=140.10325";
                    break;
                case "06":
                    positionUrl = "&lat=38.240457&lon=140.363278";
                    break;
                case "07":
                    positionUrl = "&lat=37.749957&lon=140.467734";
                    break;
                case "08":
                    positionUrl = "&lat=36.34145&lon=140.446735";
                    break;
                case "09":
                    positionUrl = "&lat=36.565689&lon=139.883528";
                    break;
                case "10":
                    positionUrl = "&lat=36.391192&lon=139.060947";
                    break;
                case "11":
                    positionUrl = "&lat=35.856907&lon=139.648854";
                    break;
                case "12":
                    positionUrl = "&lat=35.604588&lon=140.123184";
                    break;
                case "13":
                    positionUrl = "&lat=35.689568&lon=139.691717";
                    break;
                case "14":
                    positionUrl = "&lat=35.44771&lon=139.642536";
                    break;
                case "15":
                    positionUrl = "&lat=37.902238&lon=139.023531";
                    break;
                case "16":
                    positionUrl = "&lat=36.69519&lon=137.211341";
                    break;
                case "17":
                    positionUrl = "&lat=36.594652&lon=136.625725";
                    break;
                case "18":
                    positionUrl = "&lat=36.065244&lon=136.221791";
                    break;
                case "19":
                    positionUrl = "&lat=35.663935&lon=138.568379";
                    break;
                case "20":
                    positionUrl = "&lat=36.65131&lon=138.180991";
                    break;
                case "21":
                    positionUrl = "&lat=35.391199&lon=136.722168";
                    break;
                case "22":
                    positionUrl = "&lat=34.976906&lon=138.383023";
                    break;
                case "23":
                    positionUrl = "&lat=35.180198&lon=136.906739";
                    break;
                case "24":
                    positionUrl = "&lat=34.730268&lon=136.508594";
                    break;
                case "25":
                    positionUrl = "&lat=35.004394&lon=135.868292";
                    break;
                case "26":
                    positionUrl = "&lat=35.021279&lon=135.755635";
                    break;
                case "27":
                    positionUrl = "&lat=34.686394&lon=135.519994";
                    break;
                case "28":
                    positionUrl = "&lat=34.691304&lon=135.182995";
                    break;
                case "29":
                    positionUrl = "&lat=34.685231&lon=135.832883";
                    break;
                case "30":
                    positionUrl = "&lat=34.225994&lon=135.16745";
                    break;
                case "31":
                    positionUrl = "&lat=35.503704&lon=134.238174";
                    break;
                case "32":
                    positionUrl = "&lat=35.472212&lon=133.05053";
                    break;
                case "33":
                    positionUrl = "&lat=34.661759&lon=133.934894";
                    break;
                case "34":
                    positionUrl = "&lat=34.396271&lon=132.459369";
                    break;
                case "35":
                    positionUrl = "&lat=34.185859&lon=131.471401";
                    break;
                case "36":
                    positionUrl = "&lat=34.065728&lon=134.559484";
                    break;
                case "37":
                    positionUrl = "&lat=34.34016&lon=134.04339";
                    break;
                case "38":
                    positionUrl = "&lat=33.841646&lon=132.766103";
                    break;
                case "39":
                    positionUrl = "&lat=33.559753&lon=133.531115";
                    break;
                case "40":
                    positionUrl = "&lat=33.606261&lon=130.418114";
                    break;
                case "41":
                    positionUrl = "&lat=33.249322&lon=130.298799";
                    break;
                case "42":
                    positionUrl = "&lat=32.744836&lon=129.873514";
                    break;
                case "43":
                    positionUrl = "&lat=32.790374&lon=130.741134";
                    break;
                case "44":
                    positionUrl = "&lat=33.238128&lon=131.612605";
                    break;
                case "45":
                    positionUrl = "&lat=31.910975&lon=131.423863";
                    break;
                case "46":
                    positionUrl = "&lat=31.560185&lon=130.558141";
                    break;
                case "47":
                    positionUrl = "&lat=26.212365&lon=127.680975";
                    break;
            }
            return "map?faces-redirect=true&areaCode=" + areaCode + positionUrl;
        } else {
            return "map?faces-redirect=true";
        }
    }

}
