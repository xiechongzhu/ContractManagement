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
        if(statusDescMap == null) {
            statusDescMap = new HashMap<Integer, String>();
            statusDescMap.put(0, "正常");
            statusDescMap.put(1, "超期");
            statusDescMap.put(2, "已验收");
        }
        if(classificationDescMap == null) {
            classificationDescMap = new HashMap<Integer, String>();
            classificationDescMap.put(0, "非密");
            classificationDescMap.put(1, "秘密");
        }
        if(needInvoiceDescMap == null) {
            needInvoiceDescMap = new HashMap<Integer, String>();
            needInvoiceDescMap.put(0, "否");
            needInvoiceDescMap.put(1, "是");
        }
        isDelayDescMap = needInvoiceDescMap;
    }

    public int getId() {
        return id;
    }

    public String getNumber() {
        return number;
    }

    public String getName() {
        return name;
    }

    public int getStatus() {
        return status;
    }

    public int getClassification() {
        return classification;
    }

    public String getLeader() {
        return leader;
    }

    public float getMoney() {
        return money;
    }

    public int getNeedInvoice() {
        return needInvoice;
    }

    public Date getFilingTime() {
        return filingTime;
    }

    public String getCdNumber() {
        return cdNumber;
    }

    public Date getRequirementTimePlan() {
        return requirementTimePlan;
    }

    public Date getRequirementTimeReal() {
        return requirementTimeReal;
    }

    public float getRequirementPayMoney() {
        return requirementPayMoney;
    }

    public Date getDesignTimePlan() {
        return designTimePlan;
    }

    public Date getDesignTimeReal() {
        return designTimeReal;
    }

    public float getDesignPayMoney() {
        return designPayMoney;
    }

    public Date getTestTimePlan() {
        return testTimePlan;
    }

    public Date getTestTimeReal() {
        return testTimeReal;
    }

    public float getTestPayMoney() {
        return testPayMoney;
    }

    public Date getAcceptanceTimePlan() {
        return acceptanceTimePlan;
    }

    public Date getAcceptanceTimeReal() {
        return acceptanceTimeReal;
    }

    public float getAcceptancePayMoney() {
        return acceptancePayMoney;
    }

    public int getIsDelay() {
        return isDelay;
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
}
