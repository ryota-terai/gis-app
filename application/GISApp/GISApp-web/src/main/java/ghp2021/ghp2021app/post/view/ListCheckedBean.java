/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ghp2021.ghp2021app.post.view;

import ghp2021.ghp2021app.ejb.PostInformationEJB;
import ghp2021.ghp2021entity.PostInformation;
import java.util.List;
import javax.inject.Inject;

/**
 *
 * @author r-terai
 */
public class ListCheckedBean {

    private List<PostInformation> postedInformation;

    @Inject
    private PostInformationEJB postInformationEJB;

    /**
     * Creates a new instance of DisasterInformationBean
     */
    public ListCheckedBean() {
    }

    public List<PostInformation> getPostedInformation() {
        return postedInformation;
    }

    public void load() {
        postedInformation = postInformationEJB.getApprovedInformation();

    }
}
