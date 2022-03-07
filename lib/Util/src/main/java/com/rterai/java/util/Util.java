/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rterai.java.util;

import java.util.Date;

/**
 *
 * @author r-terai
 */
public class Util {

    public static String makeId(int length, boolean date) {
        StringBuilder sb = new StringBuilder();
        if (date) {
            sb.append((new Date()).getTime());
        }
        while (sb.length() < length) {
            sb.append(Double.hashCode(Math.random()));
        }
        return sb.toString().substring(0, length);
    }

}
