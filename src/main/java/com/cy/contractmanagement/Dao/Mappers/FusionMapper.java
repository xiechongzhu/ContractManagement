package com.cy.contractmanagement.Dao.Mappers;

import com.cy.contractmanagement.Dao.Asset.FusionInfo;
import com.cy.contractmanagement.Dao.Mappers.SqlProvider.FusionProvider;
import org.apache.ibatis.annotations.*;

import java.util.Date;
import java.util.List;

@Mapper
public interface FusionMapper {
    @SelectProvider(type = FusionProvider.class, method = "findFusionInfo")
    List<FusionInfo> getFusionList(@Param("version") String version,
                                   @Param("platform") int platform);

    @Insert("insert into fusion_info(version, platform, uploadDate, fileName, md5) values (" +
            "#{version}, #{platform}, #{uploadDate}, #{fileName}, #{md5})")
    long insertFusionInfo(@Param("version") String version, @Param("platform") String platform,
                          @Param("uploadDate") Date uploadDate, @Param("fileName") String fileName,
                          @Param("md5") String md5);
}
