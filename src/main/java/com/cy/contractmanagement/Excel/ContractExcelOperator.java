package com.cy.contractmanagement.Excel;

import com.cy.contractmanagement.Dao.Contract.ContractInfo;
import com.cy.contractmanagement.Dao.Mappers.ContractMapper;
import com.cy.contractmanagement.Utiliy.TempFileNameBuilder;
import org.apache.poi.ss.util.CellRangeAddress;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class ContractExcelOperator{
    @Autowired
    ContractMapper contractMapper;

    private static final List<String> title1 = Arrays.asList(new String[]{"","",
    "","","","","","","","需求阶段","","","设计阶段","","","测试阶段","","",
    "验收阶段","","","",""});
    private static final List<String> title2 = Arrays.asList(new String[]{
            "合同编号","合同名称","合同状态","密级","负责人","金额(万元)","是否提供发票",
            "归档交付时间","刻盘编号",
            "合同时间","评审时间","付款金额(万元)",
            "合同时间","评审时间","付款金额(万元)",
            "合同时间","评审时间","付款金额(万元)",
            "合同时间","评审时间","付款金额(万元)",
            "未付金额(万元)","是否延期"
    });

    public String exportExcelFile() throws Exception {
        String filename = TempFileNameBuilder.getTempXlsFileName();
        ExcelExport exp = new ExcelExport();
        exp.addTitle(title1);
        exp.addTitle(title2);

        List<ContractInfo> contractInfos = contractMapper.getAllContracts();
        List<List<Object>> dataList = new ArrayList<>();
        for(ContractInfo contractInfo : contractInfos) {
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
                            contractInfo.getAcceptancePayMoney(),
                    contractInfo.getIsDelayText()});
            dataList.add(rowList);
        }
        exp.setData(dataList);

        exp.setMerge(Arrays.asList(new CellRangeAddress[]{
                new CellRangeAddress(0, 0, 9, 11),
                new CellRangeAddress(0, 0, 12, 14),
                new CellRangeAddress(0, 0, 15, 17),
                new CellRangeAddress(0, 0, 18, 20)
        }));

        exp.exportToXls(filename);
        return filename;
    }
}
