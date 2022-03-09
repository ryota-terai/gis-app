/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.r_terai.gisapp.ejb;

import com.r_terai.gisapp.entity.PostInformationView;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Ryota-Terai
 */
@Local
public interface PostInformationEJBLocal {

    /**
     * 災害情報を投稿する
     *
     * @param latitude 緯度
     * @param longtitude 経度
     * @param information 情報
     * @param file 画像ファイル
     */
    public void postInformation(String latitude, String longtitude, String information, byte[] file);

    /**
     * 未確認の投稿情報を取得する
     *
     * @return 未確認の投稿情報
     */
    public List<PostInformationView> getUncheckedInformation();

    /**
     * 確認済みの投稿情報を取得する
     *
     * @return 確認済みの投稿情報
     */
    public List<PostInformationView> getApprovedInformation();

    /**
     * 投稿情報を取得する
     *
     * @param id ID
     * @return IDに合致する投稿情報
     */
    public PostInformationView getPostInformation(String point_id);
    
    /**
     * 投稿画像取得
     * @param id 投稿ID
     * @return 画像
     */
    public byte[] getPicture(String point_id);

    public void confirm(PostInformationView information);

    public void delete(String pointId);

}
