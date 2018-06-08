package com.cy.contractmanagement.Dao.Mappers.SqlProvider;

import org.apache.ibatis.jdbc.SQL;

import java.util.Map;

public class PluginsProvider {
    public String findPlugins(Map<String, Object> map) {
        String name = (String) map.get("name");
        String version = (String) map.get("version");
        int platform = (int) map.get("platform");
        String fusionVersion = (String) map.get("fusionVersion");
        return new SQL() {{
            SELECT("*");
            FROM("plugin_info");
            if(!name.isEmpty()) {
                WHERE("name like CONCAT(CONCAT('%',#{name}),'%')");
            }
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
            if(!fusionVersion.isEmpty()) {
                WHERE("fusionVersion like CONCAT(CONCAT('%',#{fusionVersion}),'%')");
            }
        }}.toString();
    }
}
