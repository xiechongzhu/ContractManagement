package com.cy.contractmanagement.Controllers;

import com.cy.contractmanagement.Dao.Contract.ContractInfo;
import com.cy.contractmanagement.Dao.Mappers.ProjectAlertMapper;
import com.cy.contractmanagement.Dao.Project.ProjectAlertInfo;
import com.cy.contractmanagement.Utiliy.JqGridResultBuilder;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.text.ParseException;
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
                                            @RequestParam(value = "contractId", required = false, defaultValue = "-1") long contractId) {
        Page<ContractInfo> pageInfo = PageHelper.startPage(page, rows, true);
        List<ProjectAlertInfo> alertInfoList = projectAlertMapper.findAlert(contractId);
        return JqGridResultBuilder.builde(rows, page, pageInfo.getTotal(), alertInfoList);
    }

    @PostMapping("/alert/add")
    @ResponseBody
    public void addProjectAlert(@RequestParam("contractId") long contractId,
                                @RequestParam("alertNumber") String alertNumber,
                                @RequestParam("alertFile") String alertFile,
                                @RequestParam("alertDate") String alertDate,
                                @RequestParam("confirmNumber") String confirmNumber,
                                @RequestParam("confirmFile") String confirmFile,
                                @RequestParam("confirmDate") String confirmDate) throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date dateAlert = alertDate.isEmpty() ? null : sdf.parse(alertDate);
        Date dateConfirm = confirmDate.isEmpty() ? null : sdf.parse(confirmDate);
        projectAlertMapper.insertAlert(contractId, alertNumber, confirmNumber, dateAlert,
                dateConfirm, alertFile, confirmFile);
    }

    @GetMapping("/alert/get/{id}")
    @ResponseBody
    public ProjectAlertInfo getSingleAlert(@PathVariable("id") long id) {
        return projectAlertMapper.getSingleAlert(id);
    }

    @PostMapping("/alert/modify/{id}")
    @ResponseBody
    public void modifyProjectAlert(@PathVariable("id") long id, @RequestParam("contractId") long contractId,
                                   @RequestParam("alertNumber") String alertNumber,
                                   @RequestParam("alertFile") String alertFile, @RequestParam("alertDate") String alertDate,
                                   @RequestParam("confirmNumber") String confirmNumber, @RequestParam("confirmFile") String confirmFile,
                                   @RequestParam("confirmDate") String confirmDate) throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date dateAlert = alertDate.isEmpty() ? null : sdf.parse(alertDate);
        Date dateConfirm = confirmDate.isEmpty() ? null : sdf.parse(confirmDate);
        projectAlertMapper.modifyProductAlert(id, contractId, alertNumber, alertFile, dateAlert,
                confirmNumber, confirmFile, dateConfirm);
    }
}
