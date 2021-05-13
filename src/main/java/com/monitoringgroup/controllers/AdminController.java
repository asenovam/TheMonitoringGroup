/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.monitoringgroup.controllers;

import com.monitoringgroup.dao.AdminDao;
import com.monitoringgroup.dao.ReportDao;
import com.monitoringgroup.dao.UserDao;
import com.monitoringgroup.entities.AdminUser;
import com.monitoringgroup.entities.ReportEntity;
import com.monitoringgroup.entities.UserEntity;
import com.monitoringgroup.user.LoginBean;
import com.monitoringgroup.user.UserDashboardBean;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author mar_9
 */
@Controller
public class AdminController {
    
    @Autowired
    private AdminDao adminDao;
    
    @Autowired
    private ReportDao reportDao;
    
    // adminlogin
    
    @GetMapping("/adminlogin")
    public String greeting(Model model) {
        model.addAttribute("login", new LoginBean());
        return "adminlogin";
    }

    @PostMapping("/adminhome")
    public String greetingSubmit(@ModelAttribute LoginBean login, Model model, RedirectAttributes attr) {
        // shte imame logika koqto vima potrebitel ot DB and ako passwod e OK logva dashoard, ako li ne error page 
        
        System.out.println("email admn : " + login.getEmail());
        AdminUser user = adminDao.findByEmail(login.getEmail());
        
        if (user == null) {
            login.setErrorMessage("No user with nickname: " + login.getEmail());
            model.addAttribute("login", login);
            return "adminlogin";
        }
        // TODO's : ADD A CHECK FOR PASSOWRD MATCH - IF DOES NOT MATCH THE PASSWORD SHOW ERROR MESSGAE ON LOGIN PAGHE 
        attr.addAttribute("email", login.getEmail());
        
        return "redirect:/adminuserdashboard";
    }
    
    @GetMapping("/adminvewreport")
    public String vuewReport(Model model, Long reportid, String email) {
        List<ReportEntity> reports = reportDao.findByUid(reportid);
     
        ReportEntity reportEntity = reports != null && !reports.isEmpty() ? reports.get(0) : null;
        model.addAttribute("report", reportEntity);
        
        return "adminvewreport";
    }
    
    @GetMapping("/adminuserdashboard")
    public String loadUserDashoboard(Model model, String email,  Integer page, Integer size) {
        populateUserDashboard(model, email, page, size);
        
        return "adminuserdashboard";
    }
    
    private void populateUserDashboard(
            Model model, String email, Integer page, Integer size) {
        AdminUser user = adminDao.findByEmail(email);
        
         // contains he user's information - categories and the number of new news related to each category
        UserDashboardBean userDashboardBean = new UserDashboardBean();
        userDashboardBean.setName(user.getFirstName() + " " + user.getLastName());
        
        System.out.println(" user email : " + user.getEmail());
        List<ReportEntity> reports = reportDao.findAll();
         System.out.println(" reports : " + reports);
         
        Comparator<ReportEntity> reportComparator = new Comparator<ReportEntity>() {
            public int compare(ReportEntity e1, ReportEntity e2) {
                return (e1.getReportSubmitionDate() != null && e2.getReportSubmitionDate() != null)
                        ? e2.getReportSubmitionDate().compareTo(e1.getReportSubmitionDate()) : -1;
            }
        };
        
        Collections.sort(reports, reportComparator);
        
        List<ReportEntity> reportsRange = new ArrayList<>();
        if (page == null) {
            page = 0;
        }
        if (size == null) {
            size = 10;
        }
        int startIndex = page.intValue() * size;
        
        int endIndex = startIndex + size;
        if (reports.size() > startIndex) {
            if (reports.size() > endIndex) {
                reportsRange.addAll(reports.subList(
                        0, endIndex ));
                userDashboardBean.setShowNextBtn(true);
            } else {
                reportsRange.addAll(
                        reports.subList(
                                0, reports.size() - 1));
                userDashboardBean.setShowNextBtn(false);
            }
        } else {
            reportsRange.addAll(reports);
        }
        
        userDashboardBean.setReports(reportsRange);
        userDashboardBean.setNextPage(page + 1);
        
        model.addAttribute("userDashboard", userDashboardBean);
    }
    
    
    //adminuserdashboard
}
