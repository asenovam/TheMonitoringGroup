/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.monitoringgroup.controllers;

import com.monitoringgroup.entities.ReportEntity;
import com.monitoringgroup.converters.ReportConverter;
import com.monitoringgroup.dao.ReportDao;
import com.monitoringgroup.dao.UserDao;
import com.monitoringgroup.entities.UserEntity;
import com.monitoringgroup.user.ReportingFormBean;
import java.util.List;
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
@Controller
public class ReportController {

    @Autowired
    private UserDao userDao;

    @Autowired
    private ReportDao reportDao;

    @Autowired
    private ReportConverter reportConverter;

    @GetMapping("/report")
    public String loadInitialReport(Model model, String email, String name) {
        ReportingFormBean reportingFormBean = new ReportingFormBean();

        if (email != null) {
            reportingFormBean.setEmail(email);
        }
        if (name != null) {
            reportingFormBean.setName(name);
        }

        System.out.println("Name from report: " + reportingFormBean.getName());

        model.addAttribute("reportingForm", reportingFormBean);

        return "redirect:reportOne?email=" + email + "&name=" + name;
    }

    @GetMapping("/reportOne")
    public String reportOneLoad(@ModelAttribute ReportingFormBean reportingFormBean, Model model,
            String email, String name) {

        if (name != null && email != null) {
            reportingFormBean.setName(name);
            reportingFormBean.setEmail(email);
        }

        System.out.println("Name: " + reportingFormBean.getName());
        System.out.println("Name: " + reportingFormBean.getName());
        System.out.println("Name: " + reportingFormBean.getName());
        model.addAttribute("reportingForm", reportingFormBean);

        return "reportOne";
    }

    @PostMapping("/reportOne")
    public String reportOneSubmit(@ModelAttribute ReportingFormBean reportingFormBean, Model model) {
        if (reportingFormBean.getEmail() == null || reportingFormBean.getEmail().isEmpty()
                || reportingFormBean.getPhoneNumber() == null || reportingFormBean.getPhoneNumber().isEmpty()) {
            reportingFormBean.setErrorMessage("Fill all mandatory fields!");

            model.addAttribute("reportingForm", reportingFormBean);
            return "reportOne";
        }

        System.out.println("reportOne POST Name: " + reportingFormBean.getName());

        model.addAttribute("reportingForm", reportingFormBean);

        return "confirmation";
    }


    @GetMapping("/viewreport")
    public String vuewReport(Model model, Long reportid) {
        List<ReportEntity> reports = reportDao.findByUid(reportid);

        ReportEntity reportEntity = reports != null && !reports.isEmpty() ? reports.get(0) : null;
        model.addAttribute("report", reportEntity);

        return "vewreport";
    }

}
