package com.cy.contractmanagement.Dao.Asset;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class PluginInfo {
    String name;
    String version;
    int platform;
    int fusionVersion;
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    Date uploadDate;
    String fileName;
    String md5;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public int getPlatform() {
        return platform;
    }

    public void setPlatform(int platform) {
        this.platform = platform;
    }

    public int getFusionVersion() {
        return fusionVersion;
    }

    public void setFusionVersion(int fusionVersion) {
        this.fusionVersion = fusionVersion;
    }

    public Date getUploadDate() {
        return uploadDate;
    }

    public void setUploadDate(Date uploadDate) {
        this.uploadDate = uploadDate;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getMd5() {
        return md5;
    }

    public void setMd5(String md5) {
        this.md5 = md5;
    }
}
