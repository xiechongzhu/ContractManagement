package com.cy.contractmanagement.Dao.Contract;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * 项目管理实体类
 */
@Service
public class ProjectInfo {


    private int id;// 自增长ID
    private String project_name;// 项目名称
    private String contract_number; //合同编号
    private int project_status; //项目状态 0:正常,1暂停 2超期3结项
    private int project_classification; //项目密级
    private int project_phases; //评审阶段0 需求阶段，1设计阶段,2测试阶段,3验收阶段

    private int project_phasesstauts; //阶段状态 0：未评审，1.评审通过，待修改，3.评审通过，已修改
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date update_time; // 更新时间
    private String partyA_unit; //甲方单位
    private String partyA_infpeople; //甲方接口人
    private String partyB_unit; //乙方单位
    private String partyB_infpeople;//乙方接口人
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date project_planstarttime; //项目计划开始时间
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date project_planendtime; //项目计划结束时间
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date project_realendtime;//项目结项时间


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProject_name() {
        return project_name;
    }

    public void setProject_name(String project_name) {
        this.project_name = project_name;
    }

    public String getContract_number() {
        return contract_number;
    }

    public void setContract_number(String contract_number) {
        this.contract_number = contract_number;
    }

    public int getProject_status() {
        return project_status;
    }

    public void setProject_status(int project_status) {
        this.project_status = project_status;
    }

    public int getProject_classification() {
        return project_classification;
    }

    public void setProject_classification(int project_classification) {
        this.project_classification = project_classification;
    }

    public int getProject_phases() {
        return project_phases;
    }

    public void setProject_phases(int project_phases) {
        this.project_phases = project_phases;
    }

    public int getProject_phasesstauts() {
        return project_phasesstauts;
    }

    public void setProject_phasesstauts(int project_phasesstauts) {
        this.project_phasesstauts = project_phasesstauts;
    }

    public Date getUpdate_time() {
        return update_time;
    }

    public void setUpdate_time(Date update_time) {
        this.update_time = update_time;
    }

    public String getPartyA_unit() {
        return partyA_unit;
    }

    public void setPartyA_unit(String partyA_unit) {
        this.partyA_unit = partyA_unit;
    }

    public String getPartyA_infpeople() {
        return partyA_infpeople;
    }

    public void setPartyA_infpeople(String partyA_infpeople) {
        this.partyA_infpeople = partyA_infpeople;
    }

    public String getPartyB_unit() {
        return partyB_unit;
    }

    public void setPartyB_unit(String partyB_unit) {
        this.partyB_unit = partyB_unit;
    }

    public String getPartyB_infpeople() {
        return partyB_infpeople;
    }

    public void setPartyB_infpeople(String partyB_infpeople) {
        this.partyB_infpeople = partyB_infpeople;
    }

    public Date getProject_planstarttime() {
        return project_planstarttime;
    }

    public void setProject_planstarttime(Date project_planstarttime) {
        this.project_planstarttime = project_planstarttime;
    }

    public Date getProject_planendtime() {
        return project_planendtime;
    }

    public void setProject_planendtime(Date project_planendtime) {
        this.project_planendtime = project_planendtime;
    }

    public Date getProject_realendtime() {
        return project_realendtime;
    }

    public void setProject_realendtime(Date project_realendtime) {
        this.project_realendtime = project_realendtime;
    }

}
