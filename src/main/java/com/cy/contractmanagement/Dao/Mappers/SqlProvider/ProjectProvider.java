package com.cy.contractmanagement.Dao.Mappers.SqlProvider;

import com.cy.contractmanagement.Dao.Contract.ProjectInfo;
import org.apache.ibatis.jdbc.SQL;

public class ProjectProvider {

    public String findProjectInfo(final ProjectInfo info) {

        String sql =new SQL() {{
            SELECT("*");
            FROM("project_info");
            if (info.getProject_name() != null && !info.getProject_name().isEmpty()) {
                WHERE("project_name like CONCAT(CONCAT('%',#{project_name}),'%')");
            }

            if (info.getContract_number() != null && !info.getContract_number().isEmpty()) {
                WHERE("contract_number like CONCAT(CONCAT('%',#{contract_number}),'%')");
            }

            if (info.getProject_status() != -1) {
                WHERE("project_status like CONCAT(CONCAT('%',#{project_status}),'%')");
            }

            if (info.getProject_classification() != -1) {
                WHERE("project_classification like CONCAT(CONCAT('%',#{project_classification}),'%')");
            }

            if ((info.getPartyA_infpeople() != null && !info.getPartyA_infpeople().isEmpty() || (info.getPartyB_infpeople() != null && !info.getPartyB_infpeople().isEmpty()))) {
                WHERE("partyA_infpeople like CONCAT(CONCAT('%',#{partyA_infpeople}),'%') or partyB_infpeople like CONCAT(CONCAT('%',#{partyB_infpeople}),'%')");
            }

            if (info.getProject_planstarttime() != null && !info.getProject_planstarttime().equals("") && info.getProject_planendtime() != null && !info.getProject_planstarttime().equals("")) {
                WHERE("#{project_planstarttime} <= project_planstarttime  and #{project_planendtime} >= project_planstarttime");
            }

        }}.toString();


        return sql;
    }
}
