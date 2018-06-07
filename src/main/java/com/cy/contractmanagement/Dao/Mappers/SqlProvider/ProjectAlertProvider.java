package com.cy.contractmanagement.Dao.Mappers.SqlProvider;

import org.apache.ibatis.jdbc.SQL;

import java.util.Map;

public class ProjectAlertProvider {
    public String findProjectAlertByContractName(Map<String, Object> map) {
        String contractName = (String) map.get("contractName");
        int alertType = (int) map.get("alertType");
        int confirmType = (int) map.get("confirmType");
        return new SQL() {{
            SELECT("project_alerts.id as id, contract_info.name as contractName, " +
                    "alertNumber, confirmNumber, alertDate, confirmDate, alertFile, confirmFile");
            FROM("project_alerts");
            JOIN("contract_info on contractId = contract_info.id");
            if (!contractName.isEmpty()) {
                WHERE("contract_info.name like CONCAT(CONCAT('%',#{contractName}),'%')");
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
            SELECT("project_alerts.id as id, contract_info.name as contractName, " +
                    "alertNumber, confirmNumber, alertDate, confirmDate, alertFile, confirmFile");
            FROM("project_alerts");
            JOIN("contract_info on contractId = contract_info.id");
            WHERE("project_alerts.id=#{id}");
        }}.toString();
    }
}
