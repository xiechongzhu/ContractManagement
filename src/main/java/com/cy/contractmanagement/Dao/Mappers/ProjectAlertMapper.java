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

    @SelectProvider(type = ProjectAlertProvider.class, method = "findProjectAlertByProjectName")
    List<ProjectAlertInfo> findAlert(@Param("projectName") String projectName,
                                     @Param("alertType") int alertType,
                                     @Param("confirmType") int confirmType);

    @Insert("insert into project_alerts(projectId, alertNumber, confirmNumber," +
            "alertDate, confirmDate, alertFile, confirmFile, effort) values(#{projectId}, #{alertNumber}," +
            "#{confirmNumber}, #{alertDate}, #{confirmDate}, #{alertFile}, #{confirmFile}, #{effort})")
    int insertAlert(@Param("projectId") long projectId, @Param("alertNumber") String alertNumber,
                    @Param("confirmNumber") String confirmNumber,
                    @Param("alertDate") Date alertDate, @Param("confirmDate") Date confirmDate,
                    @Param("alertFile") String alertFile, @Param("confirmFile") String confirmFile,
                    @Param("effort") double effort);

    @SelectProvider(type = ProjectAlertProvider.class, method = "findProjectAlertById")
    ProjectAlertInfo getSingleAlert(@Param("id") long id);

    @Update("update project_alerts set projectId=#{projectId}, alertNumber=#{alertNumber}," +
            "alertFile=#{alertFile}, alertDate=#{alertDate}, confirmNumber=#{confirmNumber}," +
            "confirmFile=#{confirmFile}, confirmDate=#{confirmDate}, effort=#{effort}" +
            " where id = #{id}")
    void modifyProductAlert(@Param("id") long id, @Param("projectId") long projectId,
                            @Param("alertNumber") String alertNumber, @Param("alertFile") String alertFile,
                            @Param("alertDate") Date alertDate, @Param("confirmNumber") String confirmNumber,
                            @Param("confirmFile") String confirmFile, @Param("confirmDate") Date confirmDate,
                            @Param("effort") double effort);
}


