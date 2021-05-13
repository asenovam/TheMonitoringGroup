/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.monitoringgroup.converters;

import com.monitoringgroup.entities.ReportEntity;
import com.monitoringgroup.user.ReportingFormBean;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import org.springframework.stereotype.Component;

/**
 *
 * @author mar_9
 */
@Component
public class ReportConverter {

    public void convert(ReportingFormBean reportingFormBean, ReportEntity reportEntity) {
        reportEntity.setMessage(reportingFormBean.getMessage());

        reportEntity.setEmail(reportingFormBean.getEmail());

        reportEntity.setFiles(reportingFormBean.getFiles());

        reportEntity.setPhoneNumber(reportingFormBean.getPhoneNumber());

        reportEntity.setReportSubmitionDate(new Date());

        long id = UUID.randomUUID().getMostSignificantBits() & Long.MAX_VALUE;
        reportEntity.setUid(id);
    }

}
