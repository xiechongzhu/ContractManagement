package com.cy.contractmanagement.Controllers;

import com.cy.contractmanagement.Dao.Contract.ContractInfo;
import com.cy.contractmanagement.Dao.Mappers.ContractMapper;
import com.cy.contractmanagement.Utiliy.JqGridResultBuilder;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/contract")
public class ContractController {
    @Autowired
    ContractMapper contractMapper;

    @GetMapping("")
    public String GetContractHtml() {
        return "contract";
    }

    @PostMapping("/add")
    @ResponseBody
    public void addContract(@RequestParam("number") String number,
                            @RequestParam("name") String name,
                            @RequestParam("status") int status,
                            @RequestParam("classification") int classification,
                            @RequestParam("leader") String leader,
                            @RequestParam("money") float money,
                            @RequestParam("needInvoice") int needInvoice,
                            @RequestParam("filingTime") String filingTime,
                            @RequestParam("cdNumber") String cdNumber,
                            @RequestParam("requirementTimePlan") String requirementTimePlan,
                            @RequestParam("requirementTimeReal") String requirementTimeReal,
                            @RequestParam("requirementPayMoney") float requirementPayMoney,
                            @RequestParam("designTimePlan") String designTimePlan,
                            @RequestParam("designTimeReal") String designTimeReal,
                            @RequestParam("designPayMoney") float designPayMoney,
                            @RequestParam("testTimePlan") String testTimePlan,
                            @RequestParam("testTimeReal") String testTimeReal,
                            @RequestParam("testPayMoney") float testPayMoney,
                            @RequestParam("acceptanceTimePlan") String acceptanceTimePlan,
                            @RequestParam("acceptanceTimeReal") String acceptanceTimeReal,
                            @RequestParam("acceptancePayMoney") float acceptancePayMoney,
                            @RequestParam("isDelay") int isDelay
                            ) throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date dateFilingTime = sdf.parse(filingTime);
        Date dateRequirementTimePlan = sdf.parse(requirementTimePlan);
        Date dateRequirementTimeReal = sdf.parse(requirementTimeReal);
        Date dateDesignTimePlan = sdf.parse(designTimePlan);
        Date dateDesignTimeReal = sdf.parse(designTimeReal);
        Date dateTestTimePlan = sdf.parse(testTimePlan);
        Date dateTestTimeReal = sdf.parse(testTimeReal);
        Date dateAcceptanceTimePlan = sdf.parse(acceptanceTimePlan);
        Date dateAcceptanceTimeReal = sdf.parse(acceptanceTimeReal);
        contractMapper.insertContract(number, name, status, classification,
                leader, money, needInvoice, dateFilingTime, cdNumber, dateRequirementTimePlan,
                dateRequirementTimeReal, requirementPayMoney, dateDesignTimePlan, dateDesignTimeReal,
                designPayMoney, dateTestTimePlan, dateTestTimeReal, testPayMoney, dateAcceptanceTimePlan,
                dateAcceptanceTimeReal, acceptancePayMoney, isDelay);
    }

    @GetMapping("/get")
    @ResponseBody
    public Map<String, Object> findContract(@RequestParam("rows") int rows,
                                          @RequestParam("page") int page,
                                          @RequestParam(value = "number", required = false, defaultValue = "") String number,
                                          @RequestParam(value = "name", required = false, defaultValue = "") String name,
                                          @RequestParam(value = "status", required = false, defaultValue = "999") int status,
                                          @RequestParam(value = "classification", required = false, defaultValue = "999") int classification,
                                          @RequestParam(value = "leader", required = false, defaultValue = "") String leader,
                                          @RequestParam(value = "startDate", required = false, defaultValue = "") String startDate,
                                          @RequestParam(value = "endDate", required = false, defaultValue = "") String endDate,
                                          @RequestParam(value = "isDelay", required = false, defaultValue = "999") int isDelay) {
        Page<ContractInfo> pageInfo = PageHelper.startPage(page, rows, true);
        List<ContractInfo> contractList = contractMapper.findContract(number, name, status, classification,
                leader, startDate, endDate, isDelay);
        return JqGridResultBuilder.builde(rows, page, pageInfo.getTotal(), contractList);
    }
}
