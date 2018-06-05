package com.cy.contractmanagement.Dao.Mappers;

import com.cy.contractmanagement.Dao.Mappers.SqlProvider.ProjectAlertProvider;
import com.cy.contractmanagement.Dao.Project.ProjectAlertInfo;
import org.apache.ibatis.annotations.*;

import java.util.Date;
import java.util.List;

@Mapper
public interface ProjectAlertMapper {
    @Select("select * from project_alerts")
    List<ProjectAlertInfo> getAllAlerts();

    @SelectProvider(type = ProjectAlertProvider.class, method = "findProjectAlert")
    List<ProjectAlertInfo> findAlert(@Param("contractId") long contractId);

    @Insert("insert into project_alerts(contractId, alertNumber, confirmNumber," +
            "alertDate, confirmDate, alertFile, confirmFile) values(#{contractId}, #{alertNumber}," +
            "#{confirmNumber}, #{alertDate}, #{confirmDate}, #{alertFile}, #{confirmFile})")
    int insertAlert(@Param("contractId") long contractId, @Param("alertNumber") String alertNumber,
                    @Param("confirmNumber") String confirmNumber,
                    @Param("alertDate") Date alertDate, @Param("confirmDate") Date confirmDate,
                    @Param("alertFile") String alertFile, @Param("confirmFile") String confirmFile);
}


