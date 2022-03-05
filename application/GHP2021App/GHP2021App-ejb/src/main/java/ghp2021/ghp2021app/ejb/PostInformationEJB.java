/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ghp2021.ghp2021app.ejb;

import ghp2021.ghp2021entity.File;
import ghp2021.ghp2021entity.PostInformation;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Ryota-Terai
 */
@Stateless
@LocalBean
@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
public class PostInformationEJB implements PostInformationEJBLocal {

    @PersistenceContext(unitName = "GHP2021Entity")
    private EntityManager em;

    private static final Logger LOG = Logger.getLogger(PostInformationEJB.class.getName());

    @Override
    public void postDisasterInformation(String latitude, String longtitude, String information, byte[] file) {
        PostInformation info = new PostInformation();
        Date now = new Date();
        info.setId(now.getTime());
        info.setTime(now);
        info.setLatitude(latitude);
        info.setLongitude(longtitude);
        info.setInformation(information);
        info.setApproved((short) 0);

        em.persist(info);

        if (file != null) {
            File fileEntity = new File();
            fileEntity.setId(info.getId());
            fileEntity.setFile(file);

            em.persist(fileEntity);
        }
    }

    @Override
    public List<PostInformation> getUncheckedInformation() {
        return em.createNamedQuery("PostInformation.findByApproved", PostInformation.class)
                .setParameter("approved", 0)
                .getResultList();
    }

    @Override
    public List<PostInformation> getApprovedInformation() {
        return em.createNamedQuery("PostInformation.findByApproved", PostInformation.class)
                .setParameter("approved", 1)
                .getResultList();
    }

    @Override
    public PostInformation getPostInformation(long id) {
        return em.find(PostInformation.class, id);
    }

    @Override
    public byte[] getPicture(long id) {
        File file = em.find(File.class, id);
        if (file != null) {
            return file.getFile();
        } else {
            return null;
        }
    }

    @Override
    public void confirm(PostInformation information) {
        information.setApproved(1);
        em.merge(information);
    }

    @Override
    public void delete(long id) {
        em.remove(getPostInformation(id));
        File file = em.find(File.class, id);
        if (file != null) {
            em.remove(file);
        }
    }
}
