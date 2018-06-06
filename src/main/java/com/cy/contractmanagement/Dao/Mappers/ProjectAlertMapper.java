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

    @SelectProvider(type = ProjectAlertProvider.class, method = "findProjectAlertByContractId")
    List<ProjectAlertInfo> findAlert(@Param("contractId") long contractId);

    @Insert("insert into project_alerts(contractId, alertNumber, confirmNumber," +
            "alertDate, confirmDate, alertFile, confirmFile) values(#{contractId}, #{alertNumber}," +
            "#{confirmNumber}, #{alertDate}, #{confirmDate}, #{alertFile}, #{confirmFile})")
    int insertAlert(@Param("contractId") long contractId, @Param("alertNumber") String alertNumber,
                    @Param("confirmNumber") String confirmNumber,
                    @Param("alertDate") Date alertDate, @Param("confirmDate") Date confirmDate,
                    @Param("alertFile") String alertFile, @Param("confirmFile") String confirmFile);

    @SelectProvider(type = ProjectAlertProvider.class, method = "findProjectAlertById")
    ProjectAlertInfo getSingleAlert(@Param("id") long id);

    @Update("update project_alerts set contractId=#{contractId}, alertNumber=#{alertNumber}," +
            "alertFile=#{alertFile}, alertDate=#{alertDate}, confirmNumber=#{confirmNumber}," +
            "confirmFile=#{confirmFile}, confirmDate=#{confirmDate}" +
            " where id = #{id}")
    void modifyProductAlert(@Param("id") long id, @Param("contractId") long contractId,
                            @Param("alertNumber") String alertNumber, @Param("alertFile") String alertFile,
                            @Param("alertDate") Date alertDate,@Param("confirmNumber") String confirmNumber,
                            @Param("confirmFile") String confirmFile,@Param("confirmDate") Date confirmDate);
}


