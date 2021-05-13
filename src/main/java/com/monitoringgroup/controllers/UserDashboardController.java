/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.monitoringgroup.controllers;

import com.monitoringgroup.entities.UserEntity;
import com.monitoringgroup.dao.ReportDao;
import com.monitoringgroup.dao.UserDao;
import com.monitoringgroup.entities.ReportEntity;
import com.monitoringgroup.user.UserDashboardBean;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

/**
 *
 * @author mar_9
 */
@Controller// says that this class makes the connection between back and and front end 
public class UserDashboardController {
    
    @Autowired
    private UserDao userDao;
    
    @Autowired
    private ReportDao reportDao;
    
    // Getmapping says it is cathing the GETHTTP method from called from the front end with unique URI - userdashboard 
    @GetMapping("/userdashboard")
    public String loadUserDashoboard(Model model, String email) {
        // the method will populate the user's both names and all reports and will render the html called userdashboard.html
        populateUserDashboard(model, email);
        
        return "userdashboard";
    }
    
    private void populateUserDashboard(
            Model model, String email) {
        
        UserEntity user = userDao.findByEmail(email);
        
         // contains he user's information - categories and the number of new news related to each category
        UserDashboardBean userDashboardBean = new UserDashboardBean();
        userDashboardBean.setName(user.getFirstName() + " " + user.getLastName());
        
        System.out.println(" user email : " + user.getEmail());
        List<ReportEntity> reports = reportDao.findByEmail(user.getEmail());
         System.out.println(" reports : " + reports);
     //   reports.stream().forEach(r -> System.out.println(r.getName()));
        
        userDashboardBean.setReports(reports);
        
        model.addAttribute("userDashboard", userDashboardBean);
    }
    
}
