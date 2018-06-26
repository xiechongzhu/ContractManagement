package com.cy.contractmanagement.Controllers;

import com.cy.contractmanagement.Dao.Project.ProjectInfo;
import com.cy.contractmanagement.Dao.Mappers.ProjectMapper;
import com.cy.contractmanagement.Excel.ProjectExcelOperator;
import com.cy.contractmanagement.Utiliy.FileUtility;
import com.cy.contractmanagement.Utiliy.JqGridResultBuilder;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/project")
public class ProjectController {
    @Autowired
    ProjectMapper projectMapper;

    @Autowired
    ProjectExcelOperator projectExcelOperator;
    @Autowired
    ProjectInfo info;

    /* 返回页面 */
    @GetMapping("")
    public String GetContractHtml() {
        return "project";
    }

    @GetMapping("/get-all")
    @ResponseBody
    public List<ProjectInfo> getAllProjectInfo() {

        List<ProjectInfo>   list = projectMapper.getAllProjectInfo();
        return list;
    }

    /* 获取符合查询条件的所有项目信息 */
    @GetMapping("/get")
    @ResponseBody
    public Map<String, Object> findProject(@RequestParam("rows") int rows,
                                            @RequestParam("page") int page,
                                            @RequestParam(value = "project_name", required = false, defaultValue = "") String project_name,
                                            @RequestParam(value = "contract_number", required = false, defaultValue = "") String contract_number,
                                            @RequestParam(value = "project_status", required = false, defaultValue = "-1") int project_status,
                                           @RequestParam(value = "project_classification", required = false, defaultValue = "-1") int project_classification,
                                           @RequestParam(value = "party_infpeople", required = false, defaultValue = "") String party_infpeople,
                                            @RequestParam(value = "project_planstarttime", required = false, defaultValue = "") String project_planstarttime,
                                            @RequestParam(value = "project_planendtime", required = false, defaultValue = "") String project_planendtime) throws Exception {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date dateproject_planstarttime = null;
        if(!project_planstarttime.isEmpty())
        {
             sdf.parse(project_planstarttime);
            dateproject_planstarttime = sdf.parse(project_planstarttime);
        }

        Date dateproject_planendtime = null;
        if(!project_planendtime.isEmpty()){
            dateproject_planendtime =  sdf.parse(project_planendtime);
        }

        Page<ProjectInfo> pageInfo = PageHelper.startPage(page, rows, true);

        info.setProject_name(project_name);
        info.setContract_number(contract_number);
        info.setProject_status(project_status);
        info.setProject_classification(project_classification);
        info.setPartyA_infpeople(party_infpeople);
        info.setPartyB_infpeople(party_infpeople);
        info.setProject_planstarttime(dateproject_planstarttime);
        info.setProject_planendtime(dateproject_planendtime);
        List<ProjectInfo> projectList = projectMapper.findProjectInfo(info);

        return JqGridResultBuilder.builde(rows, page, pageInfo.getTotal(), projectList);
    }

    /* 获取单个项目信息 */
    @GetMapping("/get/{id}")
    @ResponseBody
    public ProjectInfo getSingleContract(@PathVariable("id") int id) {
        ProjectInfo projectInfo = projectMapper.getSingleProject(id);
        return projectInfo;
    }


    /* 添加项目信息 */
    @PostMapping("/add")
    @ResponseBody
    public void addProject(@RequestParam("project_name") String project_name,
                           @RequestParam("contract_number") String contract_number,
                           @RequestParam("project_status") int project_status,
                           @RequestParam("project_classification") int project_classification,
                           @RequestParam("project_phases") int project_phases,
                       //    @RequestParam("project_phasesstauts") int project_phasesstauts,
                           @RequestParam("partyA_unit") String partyA_unit,
                           @RequestParam("partyA_infpeople") String partyA_infpeople,
                           @RequestParam("partyB_unit") String partyB_unit,
                           @RequestParam("partyB_infpeople") String partyB_infpeople,
                           @RequestParam("project_planstarttime") String project_planstarttime,
                           @RequestParam("project_planendtime") String project_planendtime
    ) throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date dateproject_planstarttime = sdf.parse(project_planstarttime);
        Date dateproject_planendtime = sdf.parse(project_planendtime);

        ProjectInfo info = new ProjectInfo();
        info.setProject_name(project_name);
        info.setContract_number(contract_number);
        info.setProject_status(project_status);
        info.setProject_classification(project_classification);
        info.setProject_phases(project_phases);
      //  info.setProject_phasesstauts(project_phasesstauts);
        info.setUpdate_time(new Date());
        info.setPartyA_unit(partyA_unit);
        info.setPartyA_infpeople(partyA_infpeople);
        info.setPartyB_unit(partyB_unit);
        info.setPartyB_infpeople(partyB_infpeople);
        info.setProject_planstarttime(dateproject_planstarttime);
        info.setProject_planendtime(dateproject_planendtime);

        projectMapper.insertProjectInfo(info);
    }


    /* 修改项目信息 */
    @PostMapping("/modify/{id}")
    @ResponseBody
    public void modifyContract(@PathVariable("id") int id,
                               @RequestParam("project_name") String project_name,
                               @RequestParam("contract_number") String contract_number,
                               @RequestParam("project_status") int project_status,
                               @RequestParam("project_classification") int project_classification,
                               @RequestParam("project_phases") int project_phases,
                             //  @RequestParam("project_phasesstauts") int project_phasesstauts,
                               @RequestParam("partyA_unit") String partyA_unit,
                               @RequestParam("partyA_infpeople") String partyA_infpeople,
                               @RequestParam("partyB_unit") String partyB_unit,
                               @RequestParam("partyB_infpeople") String partyB_infpeople,
                               @RequestParam("project_planstarttime") String project_planstarttime,
                               @RequestParam("project_planendtime") String project_planendtime
    ) throws Exception {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date dateproject_planstarttime = sdf.parse(project_planstarttime);
        Date dateproject_planendtime = sdf.parse(project_planendtime);

        ProjectInfo info = new ProjectInfo();
        info.setId(id);
        info.setProject_name(project_name);
        info.setContract_number(contract_number);
        info.setProject_status(project_status);
        info.setProject_classification(project_classification);
        info.setProject_phases(project_phases);
       // info.setProject_phasesstauts(project_phasesstauts);
        info.setUpdate_time(new Date());
        info.setPartyA_unit(partyA_unit);
        info.setPartyA_infpeople(partyA_infpeople);
        info.setPartyB_unit(partyB_unit);
        info.setPartyB_infpeople(partyB_infpeople);
        info.setProject_planstarttime(dateproject_planstarttime);
        info.setProject_planendtime(dateproject_planendtime);

        projectMapper.updateProjectInfo(info);
    }

    @GetMapping("/export")
    public ResponseEntity exportToExcel() throws Exception {
        String excelFile = projectExcelOperator.exportExcelFile();
        FileSystemResource resource = new FileSystemResource(excelFile);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
        headers.add("Content-Disposition", "attachment; filename=project.xls");
        headers.add("Pragma", "no-cache");
        headers.add("Expires", "0");

        return ResponseEntity
                .ok()
                .headers(headers)
                .contentLength(resource.contentLength())
                .contentType(MediaType.parseMediaType("application/octet-stream"))
                .body(new InputStreamResource(resource.getInputStream()));
    }

    @PostMapping("/import")
    @ResponseBody
    public void importFromExcel(@RequestParam("file") MultipartFile file) throws Exception {
        String tempXlsFile = FileUtility.getTempFileName(".xls");
        File f = new File(tempXlsFile);
        f.createNewFile();
        FileOutputStream stream = new FileOutputStream(f);
        stream.write(file.getBytes());
        stream.close();
        projectExcelOperator.importFromExcel(tempXlsFile);
    }

}
