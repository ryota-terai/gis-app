/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.r_terai.gisapp.ejb;

import com.r_terai.gisapp.GISAppEntityUtil;
import com.r_terai.gisapp.entity.TimerSetting;
import com.r_terai.java.util.Logger;
import com.r_terai.java.util.Logger.Level;
import com.r_terai.java.util.Util;
import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.ejb.Timeout;
import javax.ejb.Timer;
import javax.ejb.TimerConfig;
import javax.ejb.TimerService;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

/**
 *
 * @author r-terai
 */
@Startup
@Singleton
public class OvserverTimer {

    @Resource
    TimerService timer;

    @PersistenceContext(unitName = "GISAppEntity")
    private EntityManager em;

    private static final Logger logger = new Logger(OvserverTimer.class.getName());

    @PostConstruct
    public void initialize() {
        try {
            String application = Util.getApplicationName();
            String module = Util.getModuleName();

            TimerSetting setting = (TimerSetting) em.createNativeQuery("SELECT * FROM TIMER_SETTING WHERE APPLICATION = ?1 AND MODULE = ?2 AND CLASS = ?3", TimerSetting.class)
                    .setParameter(1, application)
                    .setParameter(2, module)
                    .setParameter(3, this.getClass().getName())
                    .getSingleResult();

            TimerConfig timerConfig = new TimerConfig();
            timerConfig.setInfo(this.getClass().getName());
            timerConfig.setPersistent(false);

            if (setting.getInterval() > 0) {
                timer.createIntervalTimer(setting.getTimeout(), setting.getInterval(), timerConfig);
            } else {
                timer.createSingleActionTimer(setting.getTimeout(), timerConfig);
            }
        } catch (NamingException | NoResultException ex) {
            logger.log(Level.SEVERE, null, ex);
        }
    }

    @Timeout
    public void timeout(Timer timer) {
        try {
            GISAppEntityUtil.ObserverTargetUtil.kick(em);
        } catch (NamingException ex) {
            logger.log(Level.SEVERE, null, ex);
        }

    }

}
