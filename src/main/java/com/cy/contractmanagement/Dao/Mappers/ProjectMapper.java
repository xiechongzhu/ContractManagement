package com.cy.contractmanagement.Dao.Mappers;

import com.cy.contractmanagement.Dao.Project.ProjectInfo;
import com.cy.contractmanagement.Dao.Mappers.SqlProvider.ProjectProvider;
import org.apache.ibatis.annotations.*;

import java.util.List;
@Mapper
public interface ProjectMapper {

    @Select("select * from project_info")
    List<ProjectInfo> getAllProjectInfo();

    @Insert("INSERT INTO project_info(project_name,contract_number,project_status,project_classification,project_phases," +
            "update_time,partyA_unit,partyA_infpeople,partyB_unit,partyB_infpeople,project_planstarttime,project_planendtime) " +
            " values (#{info.project_name},#{info.contract_number},#{info.project_status},#{info.project_classification},#{info.project_phases},"+
            "#{info.update_time},#{info.partyA_unit},#{info.partyA_infpeople},"+
            "#{info.partyB_unit},#{info.partyB_infpeople},#{info.project_planstarttime},#{info.project_planendtime})")
    int insertProjectInfo(@Param("info") ProjectInfo info);

    @Update("UPDATE project_info set project_name=#{info.project_name},contract_number=#{info.contract_number},project_status=#{info.project_status}," +
            "project_classification=#{info.project_classification}, project_phases=#{info.project_phases},update_time=#{info.update_time}," +
            "partyA_unit=#{info.partyA_unit},partyA_infpeople=#{info.partyA_infpeople},partyB_unit=#{info.partyB_unit}," +
            "partyB_infpeople=#{info.partyB_infpeople},project_planstarttime=#{info.project_planstarttime}," +
            "project_planendtime=#{info.project_planendtime}  where id = #{info.id}")
    void updateProjectInfo(@Param("info") ProjectInfo info);

    @SelectProvider(type = ProjectProvider.class, method = "findProjectInfo")
    List<ProjectInfo> findProjectInfo(ProjectInfo info);

    @Select("select * from project_info where id = #{id}")
    ProjectInfo getSingleProject(@Param("id") int id);

}
