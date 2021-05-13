/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.monitoringgroup.controllers;

import com.monitoringgroup.user.ReportingFormBean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 *
 * @author mar_9
 */
@Controller
public class HelpLinksController {
    
    @GetMapping("/disability")
    public String disability(Model model) {
        
        return "disability";
    }
    
    @GetMapping("/genderIdentify")
    public String genderIdentify(Model model) {
        
        return "genderIdentify";
    }
    
    
    @GetMapping("/race")
    public String race(Model model) {
        
        return "race";
    }
    
    
    @GetMapping("/sexualOrientation")
    public String sexualOrientation(Model model) {
        
        return "sexualOrientation";
    }
    
    
    @GetMapping("/religion")
    public String religion(Model model) {
        
        return "religion";
    }
    
    
    @GetMapping("/howworks")
    public String howworks(Model model) {
        
        return "howworks";
    }
    
    @GetMapping("/aboutUs")
    public String aboutUs(Model model) {
        
        return "aboutUs";
    }
    
    
}
