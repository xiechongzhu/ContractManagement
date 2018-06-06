package com.cy.contractmanagement.Dao.Mappers.SqlProvider;

import org.apache.ibatis.jdbc.SQL;

import java.util.Map;

public class ProjectAlertProvider {
    public String findProjectAlertByContractId(Map<String, Object> map) {
        long contractId = (long) map.get("contractId");
        return new SQL() {{
            SELECT("project_alerts.id as id, contract_info.name as contractName, " +
                    "alertNumber, confirmNumber, alertDate, confirmDate, alertFile, confirmFile");
            FROM("project_alerts");
            JOIN("contract_info on contractId = contract_info.id");
            if (contractId >= 0) {
                WHERE("contract_info.id=#{contractId}");

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
