/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.r_terai.gisapp;

import java.util.Date;

/**
 *
 * @author r-terai
 */
public class topPageBean {

    private Date now;

    /**
     * Creates a new instance of topPageBean
     */
    public topPageBean() {
        now = new Date();
    }

    public Date getNow() {
        return now;
    }

}
