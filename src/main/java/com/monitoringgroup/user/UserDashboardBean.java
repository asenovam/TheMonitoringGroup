/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.monitoringgroup.user;

import com.monitoringgroup.entities.ReportEntity;
import java.util.List;

/**
 *
 * @author mar_9
 */
public class UserDashboardBean {
    
    private String name;
    private List<ReportEntity> reports;
    private int nextPage;
    private boolean showNextBtn;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<ReportEntity> getReports() {
        return reports;
    }

    public void setReports(List<ReportEntity> reports) {
        this.reports = reports;
    }

    public int getNextPage() {
        return nextPage;
    }

    public void setNextPage(int nextPage) {
        this.nextPage = nextPage;
    }

    public boolean isShowNextBtn() {
        return showNextBtn;
    }

    public void setShowNextBtn(boolean showNextBtn) {
        this.showNextBtn = showNextBtn;
    }
    
    
}
