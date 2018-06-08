package com.cy.contractmanagement.Dao.Mappers;

import com.cy.contractmanagement.Dao.Asset.PluginInfo;
import com.cy.contractmanagement.Dao.Mappers.SqlProvider.PluginsProvider;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.SelectProvider;

import java.util.Date;
import java.util.List;

@Mapper
public interface PluginsMapper {
    @SelectProvider(type = PluginsProvider.class, method = "findPlugins")
    List<PluginInfo> getPluginInfoList(@Param("name") String name, @Param("version") String version,
                                       @Param("platform") int platform,
                                       @Param("fusionVersion") String fusionVersion);

    @Insert("insert into plugin_info(name, version, platform, fusionVersion, uploadDate, fileName, md5)" +
            "values(#{name}, #{version}, #{platform}, #{fusionVersion}, #{uploadDate}, #{fileName}, #{md5})")
    long insertPlugin(@Param("name") String name, @Param("version") String version,
                      @Param("platform") int platform, @Param("fusionVersion") String fusionVersion,
                      @Param("uploadDate") Date uploadDate, @Param("fileName") String fileName,
                      @Param("md5") String md5);
}
