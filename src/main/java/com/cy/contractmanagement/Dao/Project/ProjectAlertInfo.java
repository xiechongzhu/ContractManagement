package com.cy.contractmanagement.Dao.Project;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class ProjectAlertInfo {
    private long id;
    private String projectName;
    private String alertNumber;
    private String confirmNumber;
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date alertDate;
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date confirmDate;
    private String alertFile;
    private String confirmFile;
    private double effort;

    public double getEffort() {
        return effort;
    }

    public void setEffort(double effort) {
        this.effort = effort;
    }

    public String getAlertFile() {
        return alertFile;
    }

    public void setAlertFile(String alertFile) {
        this.alertFile = alertFile;
    }

    public String getConfirmFile() {
        return confirmFile;
    }

    public void setConfirmFile(String confirmFile) {
        this.confirmFile = confirmFile;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getAlertNumber() {
        return alertNumber;
    }

    public void setAlertNumber(String alertNumber) {
        this.alertNumber = alertNumber;
    }

    public String getConfirmNumber() {
        return confirmNumber;
    }

    public void setConfirmNumber(String confirmNumber) {
        this.confirmNumber = confirmNumber;
    }

    public Date getAlertDate() {
        return alertDate;
    }

    public void setAlertDate(Date alertDate) {
        this.alertDate = alertDate;
    }

    public Date getConfirmDate() {
        return confirmDate;
    }

    public void setConfirmDate(Date confirmDate) {
        this.confirmDate = confirmDate;
    }
}
