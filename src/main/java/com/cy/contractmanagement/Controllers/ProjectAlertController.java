package com.cy.contractmanagement.Controllers;

import com.cy.contractmanagement.Dao.Project.ProjectInfo;
import com.cy.contractmanagement.Dao.Mappers.ProjectAlertMapper;
import com.cy.contractmanagement.Dao.Project.ProjectAlertInfo;
import com.cy.contractmanagement.Utiliy.JqGridResultBuilder;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/project")
public class ProjectAlertController {
    @Autowired
    ProjectAlertMapper projectAlertMapper;

    @GetMapping("/alert")
    public String getAlertPage() {
        return "project-alert";
    }

    @GetMapping("/alert/get")
    @ResponseBody
    public Map<String, Object> getAllAlerts(@RequestParam("rows") int rows,
                                            @RequestParam("page") int page,
                                            @RequestParam(value = "projectName", required = false, defaultValue = "") String projectName,
                                            @RequestParam(value = "alertType", required = false, defaultValue = "999") int alertType,
                                            @RequestParam(value = "confirmType", required = false, defaultValue = "999") int confirmType) {
        Page<ProjectInfo> pageInfo = PageHelper.startPage(page, rows, true);
        List<ProjectAlertInfo> alertInfoList = projectAlertMapper.findAlert(projectName, alertType, confirmType);
        return JqGridResultBuilder.builde(rows, page, pageInfo.getTotal(), alertInfoList);
    }

    @PostMapping("/alert/add")
    @ResponseBody
    public void addProjectAlert(@RequestParam("projectId") long projectId,
                                @RequestParam("alertNumber") String alertNumber,
                                @RequestParam("alertFile") String alertFile,
                                @RequestParam("alertDate") String alertDate,
                                @RequestParam("confirmNumber") String confirmNumber,
                                @RequestParam("confirmFile") String confirmFile,
                                @RequestParam("confirmDate") String confirmDate,
                                @RequestParam("effort") String effort) throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date dateAlert = alertDate.isEmpty() ? null : sdf.parse(alertDate);
        Date dateConfirm = confirmDate.isEmpty() ? null : sdf.parse(confirmDate);
        projectAlertMapper.insertAlert(projectId, alertNumber, confirmNumber, dateAlert,
                dateConfirm, alertFile, confirmFile, effort.isEmpty() ? 0 : Double.parseDouble(effort));
    }

    @GetMapping("/alert/get/{id}")
    @ResponseBody
    public ProjectAlertInfo getSingleAlert(@PathVariable("id") long id) {
        return projectAlertMapper.getSingleAlert(id);
    }

    @PostMapping("/alert/modify/{id}")
    @ResponseBody
    public void modifyProjectAlert(@PathVariable("id") long id, @RequestParam("projectId") long projectId,
                                   @RequestParam("alertNumber") String alertNumber,
                                   @RequestParam("alertFile") String alertFile, @RequestParam("alertDate") String alertDate,
                                   @RequestParam("confirmNumber") String confirmNumber, @RequestParam("confirmFile") String confirmFile,
                                   @RequestParam("confirmDate") String confirmDate,
                                   @RequestParam("effort") String effort) throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date dateAlert = alertDate.isEmpty() ? null : sdf.parse(alertDate);
        Date dateConfirm = confirmDate.isEmpty() ? null : sdf.parse(confirmDate);
        projectAlertMapper.modifyProductAlert(id, projectId, alertNumber, alertFile, dateAlert,
                confirmNumber, confirmFile, dateConfirm, effort.isEmpty() ? 0 : Double.parseDouble(effort));
    }
}
