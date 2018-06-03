package com.cy.contractmanagement.Dao.Mappers.SqlProvider;

import org.apache.ibatis.jdbc.SQL;

import java.util.Map;

public class ContractProvider {
    public String findContract(Map<String, Object> map) {
        String number = (String) map.get("number");
        String name = (String) map.get("name");
        int status = (int) map.get("status");
        int classification = (int) map.get("classification");
        String leader = (String) map.get("leader");
        String startDate = (String) map.get("startDate");
        String endDate = (String) map.get("endDate");
        int isDelay = (int) map.get("isDelay");
        return new SQL() {{
            SELECT("*");
            FROM("contract_info");
            if (number != null && !number.isEmpty()) {
                WHERE("number like CONCAT(CONCAT('%',#{number}),'%')");
            }
            if (name != null && !name.isEmpty()) {
                WHERE("name like CONCAT(CONCAT('%',#{name}),'%')");
            }
            if (status != 999) {
                WHERE("status = #{status}");
            }
            if (classification != 999) {
                WHERE("classification = #{classification}");
            }
            if (leader != null && !leader.isEmpty()) {
                WHERE("leader like CONCAT(CONCAT('%',#{leader}),'%')");
            }
            if (startDate != null && !startDate.isEmpty()) {
                WHERE("#{startDate} <= filingTime");
            }
            if (endDate != null && !endDate.isEmpty()) {
                WHERE("#{endDate} >= filingTime");
            }
            if (isDelay != 999) {
                WHERE("isDelay = #{isDelay}");
            }
        }}.toString();
    }
}
