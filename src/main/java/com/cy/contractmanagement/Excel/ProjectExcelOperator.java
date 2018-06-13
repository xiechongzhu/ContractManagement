package com.cy.contractmanagement.Excel;


import com.cy.contractmanagement.Dao.Project.ProjectInfo;
import com.cy.contractmanagement.Dao.Mappers.ProjectMapper;
import com.cy.contractmanagement.Utiliy.FileUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class ProjectExcelOperator {

    private  static List<String> title = Arrays.asList(new String[]{
            "项目名称", "'合同编号'", "项目状态","密级", "评审阶段", "阶段状态", "更新时间", "甲方单位",
             "甲方接口人", "乙方单位", "乙方接口人", "计划开始时间", "计划结束时间", "结项时间"
    });



    @Autowired
    ProjectMapper projectMapper;
    @Autowired
    ExcelExport excelExport;
    @Autowired
    ExcelImport excelImport;

    public String exportExcelFile() throws Exception {
        String filename = FileUtility.getTempFileName(".xls");

        List<List<String>> titleList  =  new ArrayList<>();
        titleList.add(title);
        excelExport.setTitle(titleList);

        List<ProjectInfo> projectInfos = projectMapper.getAllProjectInfo();
        List<List<Object>> dataList = new ArrayList<>();
        for (ProjectInfo info : projectInfos) {

            String project_status ="";
            switch (info.getProject_status()) {
                case 0:
                    project_status="正常";
                    break;
                case 1:
                    project_status="暂停";
                    break;
                case 2:
                    project_status="超期";
                    break;
                case 3:
                    project_status="结项";
                    break;
            }

            String project_classification ="";
            switch (info.getProject_classification()) {
                case 0:
                    project_classification="非密";
                    break;
                case 1:
                    project_classification="秘密";
                    break;
            }

            String project_phases ="";
            switch (info.getProject_phases()) {
                case 0:
                    project_phases="需求阶段";
                    break;
                case 1:
                    project_phases="设计阶段";
                    break;
                case 2:
                    project_phases="测试阶段";
                    break;
                case 3:
                    project_phases="验收阶段";
                    break;
            }

            String project_phasesstauts ="";
            switch (info.getProject_phasesstauts()) {
                case 0:
                    project_phasesstauts="未评审";
                    break;
                case 1:
                    project_phasesstauts = "已评审";
                    break;
                case 2:
                    project_phasesstauts ="已关闭";
                    break;
            }

            List<Object> rowList = Arrays.asList(new Object[]{
                    info.getProject_name(),
                    info.getContract_number(),
                    project_status,
                    project_classification,
                    project_phases,
                    project_phasesstauts,
                    info.getUpdate_time(),
                    info.getPartyA_unit(),
                    info.getPartyA_infpeople(),
                    info.getPartyB_unit(),
                    info.getPartyB_infpeople(),
                    info.getProject_planstarttime(),
                    info.getProject_planendtime(),
                    info.getProject_realendtime()});
            dataList.add(rowList);
        }
        excelExport.setData(dataList);

        excelExport.exportToXls(filename);


        return filename;
    }

    public void importFromExcel(String fileName) throws Exception {
        List<ArrayList<String>> resultList = excelImport.parseExcelData(fileName, 1, 14);

        for (int i = 0; i < resultList.size(); ++i) {
            ArrayList<String> result = resultList.get(i);
            ProjectInfo info = new ProjectInfo();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            info.setProject_name(result.get(0));
            info.setContract_number(result.get(1));

            switch (result.get(2)) {
                case "正常":
                    info.setProject_status(0);
                    break;
                case "暂停":
                    info.setProject_status(1);
                    break;
                case "超期":
                    info.setProject_status(2);
                    break;
                case "结项":
                    info.setProject_status(3);
                    break;
                default:
                    throw (new Exception(String.format("第%d行数据项目状态字段填写有误", i + 1)));
            }


            switch (result.get(3)) {
                case "非密":
                    info.setProject_classification(0);
                    break;
                case "秘密":
                    info.setProject_classification(1);
                    break;
            }

            switch (result.get(4)) {
                case "需求阶段":
                    info.setProject_phases(0);
                    break;
                case "设计阶段":
                    info.setProject_phases(1);
                    break;
                case "测试阶段":
                    info.setProject_phases(2);
                    break;
                case "验收阶段":
                    info.setProject_phases(3);
                    break;
                default:
                    throw (new Exception(String.format("第%d行数据项目状态字段填写有误", i + 1)));
            }

            switch (result.get(5)) {
                case "未评审":
                    info.setProject_phasesstauts(0);
                    break;
                case "已评审":
                    info.setProject_phasesstauts(1);
                    break;
                case "已关闭":
                    info.setProject_phasesstauts(2);
                    break;
                default:
                    throw (new Exception(String.format("第%d行数据项目状态字段填写有误", i + 1)));
            }

            info.setUpdate_time(sdf.parse(result.get(6)));
            info.setPartyA_unit(result.get(7));
            info.setPartyA_infpeople(result.get(8));
            info.setPartyB_unit(result.get(9));
            info.setPartyB_infpeople(result.get(10));
            info.setProject_planstarttime(sdf.parse(result.get(11)));
            info.setProject_planendtime(sdf.parse(result.get(12)));
            info.setProject_realendtime(sdf.parse(result.get(13)));

            projectMapper.insertProjectInfo(info);
        }
    }
}
