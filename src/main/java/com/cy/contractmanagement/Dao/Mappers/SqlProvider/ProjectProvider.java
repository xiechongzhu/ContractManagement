package com.cy.contractmanagement.Dao.Mappers.SqlProvider;

import com.cy.contractmanagement.Dao.Contract.ProjectInfo;
import org.apache.ibatis.jdbc.SQL;

public class ProjectProvider {

    public String findProjectInfo(final ProjectInfo info) {
        return new SQL() {{
            SELECT("*");
            FROM("projcet_info");
            if (info.getProject_name() != null && !info.getProject_name().isEmpty()) {
                WHERE("project_name like CONCAT(CONCAT('%',#{project_name}),'%')");
            }
            if (info.getContract_number() != null && !info.getContract_number().isEmpty()) {
                WHERE("contract_name like CONCAT(CONCAT('%',#{contract_name}),'%')");
            }

            if (info.getProject_planstarttime() != null) {
                WHERE("#{getProject_planstarttime} <= getProject_planstarttime");
            }
            if (info.getProject_planendtime() != null) {
                WHERE("#{getProject_planendtime} >= getProject_planendtime");
            }
        }}.toString();
    }
}
