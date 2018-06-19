package com.cy.contractmanagement.Excel;

import com.cy.contractmanagement.Dao.Contract.ContractInfo;
import com.cy.contractmanagement.Dao.Mappers.ContractMapper;
import com.cy.contractmanagement.Utiliy.FileUtility;
import org.apache.poi.ss.util.CellRangeAddress;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class ContractExcelOperator {
    private static final List<String> title1 = Arrays.asList(new String[]{"", "",
            "", "", "", "", "", "", "", "需求阶段", "", "", "设计阶段", "", "", "测试阶段", "", "",
            "验收阶段", "", "", "", ""});
    private static final List<String> title2 = Arrays.asList(new String[]{
            "合同编号", "合同名称", "合同状态", "密级", "负责人", "金额(万元)", "是否提供发票",
            "归档交付时间", "刻盘编号",
            "合同时间", "评审时间", "付款金额(万元)",
            "合同时间", "评审时间", "付款金额(万元)",
            "合同时间", "评审时间", "付款金额(万元)",
            "合同时间", "评审时间", "付款金额(万元)",
            "未付金额(万元)"/*, "是否延期"*/
    });
    @Autowired
    ContractMapper contractMapper;
    @Autowired
    ExcelExport excelExport;
    @Autowired
    ExcelImport excelImport;

    public String exportExcelFile() throws Exception {
        String filename = FileUtility.getTempFileName(".xls");
        //ExcelExport exp = new ExcelExport();
        excelExport.clearTitle();
        excelExport.addTitle(title1);
        excelExport.addTitle(title2);

        List<ContractInfo> contractInfos = contractMapper.getAllContracts();
        List<List<Object>> dataList = new ArrayList<>();
        for (ContractInfo contractInfo : contractInfos) {
            List<Object> rowList = Arrays.asList(new Object[]{contractInfo.getNumber(),
                    contractInfo.getName(), contractInfo.getStatusText(),
                    contractInfo.getClassificationText(), contractInfo.getLeader(),
                    contractInfo.getMoney(), contractInfo.getNeedInvoiceText(),
                    contractInfo.getFilingTime(), contractInfo.getCdNumber(),
                    contractInfo.getRequirementTimePlan(), contractInfo.getRequirementTimeReal(),
                    contractInfo.getRequirementPayMoney(), contractInfo.getDesignTimePlan(),
                    contractInfo.getDesignTimeReal(), contractInfo.getDesignPayMoney(),
                    contractInfo.getTestTimePlan(), contractInfo.getTestTimeReal(),
                    contractInfo.getTestPayMoney(), contractInfo.getAcceptanceTimePlan(),
                    contractInfo.getAcceptanceTimeReal(), contractInfo.getAcceptancePayMoney(),
                    contractInfo.getMoney() - contractInfo.getRequirementPayMoney() -
                            contractInfo.getDesignPayMoney() - contractInfo.getTestPayMoney() -
                            contractInfo.getAcceptancePayMoney()/*,
                    contractInfo.getIsDelayText()*/});
            dataList.add(rowList);
        }
        excelExport.setData(dataList);

        excelExport.setMerge(Arrays.asList(new CellRangeAddress[]{
                new CellRangeAddress(0, 0, 9, 11),
                new CellRangeAddress(0, 0, 12, 14),
                new CellRangeAddress(0, 0, 15, 17),
                new CellRangeAddress(0, 0, 18, 20)
        }));

        excelExport.exportToXls(filename);
        return filename;
    }

    public void importFromExcel(String fileName) throws Exception {
        List<ArrayList<String>> resultList = excelImport.parseExcelData(fileName, 2, /*22*/21);
        //System.out.println(resultList);
        //List<ContractInfo> contractInfoList = new ArrayList<>();
        for (int i = 0; i < resultList.size(); ++i) {
            ArrayList<String> result = resultList.get(i);
            ContractInfo contractInfo = new ContractInfo();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            contractInfo.setNumber(result.get(0));
            contractInfo.setName(result.get(1));
            switch (result.get(2)) {
                case "正常":
                    contractInfo.setStatus(0);
                    break;
                case "超期":
                    contractInfo.setStatus(1);
                    break;
                case "已验收":
                    contractInfo.setStatus(2);
                    break;
                default:
                    throw (new Exception(String.format("第%d行数据项目状态字段填写有误", i + 1)));
            }
            switch (result.get(3)) {
                case "非密":
                    contractInfo.setClassification(0);
                    break;
                case "秘密":
                    contractInfo.setClassification(1);
                    break;
                default:
                    throw (new Exception(String.format("第%d行数据项目密级字段填写有误", i + 1)));
            }
            contractInfo.setLeader(result.get(4));
            contractInfo.setMoney(Float.valueOf(result.get(5)));
            switch (result.get(6)) {
                case "否":
                    contractInfo.setNeedInvoice(0);
                    break;
                case "是":
                    contractInfo.setNeedInvoice(1);
                    break;
                default:
                    throw (new Exception(String.format("第%d行数据是否提供发票字段填写有误", i + 1)));
            }
            contractInfo.setFilingTime(sdf.parse(result.get(7)));
            contractInfo.setCdNumber(result.get(8));
            contractInfo.setRequirementTimePlan(sdf.parse(result.get(9)));
            contractInfo.setRequirementTimeReal(sdf.parse(result.get(10)));
            contractInfo.setRequirementPayMoney(Float.valueOf(result.get(11)));
            contractInfo.setDesignTimePlan(sdf.parse(result.get(12)));
            contractInfo.setDesignTimeReal(sdf.parse(result.get(13)));
            contractInfo.setDesignPayMoney(Float.valueOf(result.get(14)));
            contractInfo.setTestTimePlan(sdf.parse(result.get(15)));
            contractInfo.setTestTimeReal(sdf.parse(result.get(16)));
            contractInfo.setTestPayMoney(Float.valueOf(result.get(17)));
            contractInfo.setAcceptanceTimePlan(sdf.parse(result.get(18)));
            contractInfo.setAcceptanceTimeReal(sdf.parse(result.get(19)));
            contractInfo.setAcceptancePayMoney(Float.valueOf(result.get(20)));
            /*switch (result.get(21)) {
                case "否":
                    contractInfo.setIsDelay(0);
                    break;
                case "是":
                    contractInfo.setIsDelay(1);
                    break;
                default:
                    throw (new Exception(String.format("第%d行数据是否延期字段填写有误", i + 1)));
            }*/
            contractInfo.setIsDelay(0);
            contractMapper.insertContractByClass(contractInfo);
        }
    }
}
