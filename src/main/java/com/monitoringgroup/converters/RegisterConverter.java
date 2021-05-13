/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.monitoringgroup.converters;

import com.monitoringgroup.entities.UserEntity;
import com.monitoringgroup.user.RegisterBean;
import java.util.UUID;
import org.springframework.stereotype.Component;

/**
 *
 * @author mar_9
 */

@Component
public class RegisterConverter {
    
    public void convert(RegisterBean registerBean, UserEntity user) {
        user.setEmail(registerBean.getEmail());
        user.setPassword(registerBean.getPassword());
        user.setFirstName(registerBean.getFirstName());
        user.setLastName(registerBean.getLastName());    
    }
}
