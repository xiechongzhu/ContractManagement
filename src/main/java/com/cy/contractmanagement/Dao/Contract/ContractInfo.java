package com.cy.contractmanagement.Dao.Contract;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class ContractInfo {
    private static Map<Integer, String> statusDescMap;
    private static Map<Integer, String> classificationDescMap;
    private static Map<Integer, String> needInvoiceDescMap;
    private static Map<Integer, String> isDelayDescMap;

    static {
        if (statusDescMap == null) {
            statusDescMap = new HashMap<>();
            statusDescMap.put(0, "正常");
            statusDescMap.put(1, "超期");
            statusDescMap.put(2, "已验收");
        }
        if (classificationDescMap == null) {
            classificationDescMap = new HashMap<>();
            classificationDescMap.put(0, "非密");
            classificationDescMap.put(1, "秘密");
        }
        if (needInvoiceDescMap == null) {
            needInvoiceDescMap = new HashMap<>();
            needInvoiceDescMap.put(0, "否");
            needInvoiceDescMap.put(1, "是");
        }

        isDelayDescMap = needInvoiceDescMap;
    }

    private int id;                     //数据库id,自增长
    private String number;              //合同编号
    private String name;                //合同名称
    private int status;                 //合同状态
    private int classification;         //合同密级
    private String leader;              //负责人
    private float money;                //合同金额(万元)
    private int needInvoice;            //是否提供发票
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date filingTime;            //归档交付时间
    private String cdNumber;            //刻盘编号
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date requirementTimePlan;   //需求阶段合同计划评审时间
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date requirementTimeReal;   //需求阶段实际评审时间
    private float requirementPayMoney;  //需求阶段结束付款(万元)
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date designTimePlan;        //设计阶段合同计划评审时间
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date designTimeReal;        //设计阶段实际评审时间
    private float designPayMoney;       //设计阶段结束付款(万元)
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date testTimePlan;          //测试阶段合同计划评审时间
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date testTimeReal;          //测试阶段实际评审时间
    private float testPayMoney;         //测试阶段结束付款(万元)
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date acceptanceTimePlan;    //验收阶段合同计划评审时间
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date acceptanceTimeReal;    //验收阶段实际评审时间
    private float acceptancePayMoney;   //验收阶段结束付款(万元)
    private int isDelay;                //是否延期

    public int getId() {
        return id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getClassification() {
        return classification;
    }

    public void setClassification(int classification) {
        this.classification = classification;
    }

    public String getLeader() {
        return leader;
    }

    public void setLeader(String leader) {
        this.leader = leader;
    }

    public float getMoney() {
        return money;
    }

    public void setMoney(float money) {
        this.money = money;
    }

    public int getNeedInvoice() {
        return needInvoice;
    }

    public void setNeedInvoice(int needInvoice) {
        this.needInvoice = needInvoice;
    }

    public Date getFilingTime() {
        return filingTime;
    }

    public void setFilingTime(Date filingTime) {
        this.filingTime = filingTime;
    }

    public String getCdNumber() {
        return cdNumber;
    }

    public void setCdNumber(String cdNumber) {
        this.cdNumber = cdNumber;
    }

    public Date getRequirementTimePlan() {
        return requirementTimePlan;
    }

    public void setRequirementTimePlan(Date requirementTimePlan) {
        this.requirementTimePlan = requirementTimePlan;
    }

    public Date getRequirementTimeReal() {
        return requirementTimeReal;
    }

    public void setRequirementTimeReal(Date requirementTimeReal) {
        this.requirementTimeReal = requirementTimeReal;
    }

    public float getRequirementPayMoney() {
        return requirementPayMoney;
    }

    public void setRequirementPayMoney(float requirementPayMoney) {
        this.requirementPayMoney = requirementPayMoney;
    }

    public Date getDesignTimePlan() {
        return designTimePlan;
    }

    public void setDesignTimePlan(Date designTimePlan) {
        this.designTimePlan = designTimePlan;
    }

    public Date getDesignTimeReal() {
        return designTimeReal;
    }

    public void setDesignTimeReal(Date designTimeReal) {
        this.designTimeReal = designTimeReal;
    }

    public float getDesignPayMoney() {
        return designPayMoney;
    }

    public void setDesignPayMoney(float designPayMoney) {
        this.designPayMoney = designPayMoney;
    }

    public Date getTestTimePlan() {
        return testTimePlan;
    }

    public void setTestTimePlan(Date testTimePlan) {
        this.testTimePlan = testTimePlan;
    }

    public Date getTestTimeReal() {
        return testTimeReal;
    }

    public void setTestTimeReal(Date testTimeReal) {
        this.testTimeReal = testTimeReal;
    }

    public float getTestPayMoney() {
        return testPayMoney;
    }

    public void setTestPayMoney(float testPayMoney) {
        this.testPayMoney = testPayMoney;
    }

    public Date getAcceptanceTimePlan() {
        return acceptanceTimePlan;
    }

    public void setAcceptanceTimePlan(Date acceptanceTimePlan) {
        this.acceptanceTimePlan = acceptanceTimePlan;
    }

    public Date getAcceptanceTimeReal() {
        return acceptanceTimeReal;
    }

    public void setAcceptanceTimeReal(Date acceptanceTimeReal) {
        this.acceptanceTimeReal = acceptanceTimeReal;
    }

    public float getAcceptancePayMoney() {
        return acceptancePayMoney;
    }

    public void setAcceptancePayMoney(float acceptancePayMoney) {
        this.acceptancePayMoney = acceptancePayMoney;
    }

    public int getIsDelay() {
        return isDelay;
    }

    public void setIsDelay(int isDelay) {
        this.isDelay = isDelay;
    }

    public String getStatusText() {
        return statusDescMap.get(status);
    }

    public String getClassificationText() {
        return classificationDescMap.get(classification);
    }

    public String getNeedInvoiceText() {
        return needInvoiceDescMap.get(needInvoice);
    }

    public String getIsDelayText() {
        return isDelayDescMap.get(isDelay);
    }
}
