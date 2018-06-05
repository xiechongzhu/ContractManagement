package com.cy.contractmanagement.Dao.Mappers;

import com.cy.contractmanagement.Dao.Contract.ProjectInfo;
import com.cy.contractmanagement.Dao.Mappers.SqlProvider.ProjectProvider;
import org.apache.ibatis.annotations.*;

import java.util.List;
@Mapper
public interface ProjectMapper {
    @Select("select * from project_info")
    List<ProjectInfo> getAllContracts();

    @SelectProvider(type = ProjectProvider.class, method = "findProjectInfo")
    List<ProjectInfo> findProjectInfo(ProjectInfo info);

    @Select("SELECT * FROM project_info WHERE project_name = #{project_name}")
    ProjectInfo findByName(@Param("name") String name);
    @Insert("INSERT INTO project_info(name, age) VALUES(#{name}, #{age})")
    int insert(@Param("name") String name, @Param("age") Integer age);
    @Update("UPDATE project_info SET age=#{age} WHERE name=#{name}")
    void update(ProjectInfo user);


}
