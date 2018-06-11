package com.cy.contractmanagement.Dao.Mappers.SqlProvider;

import org.apache.ibatis.jdbc.SQL;

import java.util.Map;

public class ProjectAlertProvider {
    public String findProjectAlertByProjectName(Map<String, Object> map) {
        String projectName = (String) map.get("projectName");
        int alertType = (int) map.get("alertType");
        int confirmType = (int) map.get("confirmType");
        return new SQL() {{
            SELECT("project_alerts.id as id, project_info.project_name as projectName, " +
                    "alertNumber, confirmNumber, alertDate, confirmDate, alertFile, confirmFile, effort");
            FROM("project_alerts");
            JOIN("project_info on projectId = project_info.id");
            if (!projectName.isEmpty()) {
                WHERE("project_info.project_name like CONCAT(CONCAT('%',#{projectName}),'%')");
            }
            switch (alertType) {
                case 0:
                    WHERE("alertNumber = ''");
                    break;
                case 1:
                    WHERE("alertNumber != ''");
                    break;
                default:
            }
            switch (confirmType) {
                case 0:
                    WHERE("confirmNumber = ''");
                    break;
                case 1:
                    WHERE("confirmNumber != ''");
                    break;
                default:
            }
        }}.toString();
    }

    public String findProjectAlertById(Map<String, Object> map) {
        long id = (long) map.get("id");
        return new SQL() {{
            SELECT("project_alerts.id as id, project_info.project_name as projectName, " +
                    "alertNumber, confirmNumber, alertDate, confirmDate, alertFile, confirmFile, effort");
            FROM("project_alerts");
            JOIN("project_info on projectId = project_info.id");
            WHERE("project_alerts.id=#{id}");
        }}.toString();
    }
}
