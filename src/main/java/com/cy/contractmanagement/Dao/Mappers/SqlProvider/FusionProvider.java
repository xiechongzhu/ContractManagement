package com.cy.contractmanagement.Dao.Mappers.SqlProvider;

import org.apache.ibatis.jdbc.SQL;

import java.util.Map;

public class FusionProvider {
    public String findFusionInfo(Map<String, Object> map) {
        String version = (String)map.get("version");
        int platform = (int)map.get("platform");
        return new SQL() {{
            SELECT("*");
            FROM("fusion_info");
            if(!version.isEmpty()) {
                WHERE("version like CONCAT(CONCAT('%',#{version}),'%')");
            }
            switch (platform) {
                case 1:
                case 2:
                    WHERE("platform = #{platform}");
                    break;
                default:
            }
        }}.toString();
    }
}
