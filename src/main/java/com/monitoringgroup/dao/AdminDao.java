/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.monitoringgroup.dao;

import com.monitoringgroup.entities.AdminUser;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 *
 * @author mar_9
 */
public interface AdminDao extends MongoRepository<AdminUser, String> {
    
    public AdminUser findByEmail(String email);
    
   // @Query("{'address.country': ?0}")
  // List<Person> findByCountry(final String country);
}