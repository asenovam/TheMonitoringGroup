/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.monitoringgroup;

/**
 *
 * @author mar_9
 */

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ApplicationStartUp {
    
    public static void main(String[] args) {
         SpringApplication.run(ApplicationStartUp.class, args);
         
        //WebApplicationContext context = ContextLoader.getCurrentWebApplicationContext();
        //RssFeedDao rssFeedDao = context.getBean(RssFeedDao.class);

        
    }
}
