/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.monitoringgroup.dao;

import com.monitoringgroup.entities.ReportEntity;
import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 *
 * @author mar_9
 */
public interface ReportDao extends MongoRepository<ReportEntity, String> {
     public List<ReportEntity> findByUid(long uid);
     
     public List<ReportEntity> findByEmail(String userEmail);
     
     // @Query("{'address.country': ?0}")
     // List<Person> findByCountry(final String country);
}
