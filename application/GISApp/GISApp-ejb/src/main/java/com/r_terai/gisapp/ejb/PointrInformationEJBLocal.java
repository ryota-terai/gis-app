/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.r_terai.gisapp.ejb;

import com.r_terai.gisapp.entity.ShelterInformationView;
import java.io.InputStream;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Ryota-Terai
 */
@Local
public interface PointrInformationEJBLocal {

    /**
     * セットアップ
     *
     * @param stream 地点情報(GeoJSON形式)
     */
    void setup(InputStream stream, boolean _private, String type);

    /**
     * 一覧を検索
     *
     * @param administrativeAreaCode 行政区域コード
     * @param p20_007 地震災害
     * @param p20_008 津波災害
     * @param p20_009 水害
     * @param p20_010 火山災害
     * @param p20_011 その他
     * @return 避難所一覧
     */
    List<ShelterInformationView> search(String administrativeAreaCode, String type, boolean p20_007, boolean p20_008, boolean p20_009, boolean p20_010, boolean p20_011, Boolean open);

    /**
     * 情報更新
     *
     * @param pointId 主キー
     * @param open 開設中
     * @param comment 備考
     */
    void upatePointInformation(String pointId, boolean open, String comment);

}
