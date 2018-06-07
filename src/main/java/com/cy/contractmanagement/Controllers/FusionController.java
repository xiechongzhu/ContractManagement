package com.cy.contractmanagement.Controllers;

import com.cy.contractmanagement.Dao.Asset.FusionInfo;
import com.cy.contractmanagement.Dao.Contract.ContractInfo;
import com.cy.contractmanagement.Dao.Mappers.FusionMapper;
import com.cy.contractmanagement.Dao.Project.ProjectAlertInfo;
import com.cy.contractmanagement.Utiliy.FileUtility;
import com.cy.contractmanagement.Utiliy.JqGridResultBuilder;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import javafx.scene.chart.ValueAxis;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/asset/fusion")
public class FusionController {
    @Autowired
    FusionMapper fusionMapper;

    @GetMapping("")
    public String getFusionPage() {
        return "fusion";
    }

    @GetMapping("/get")
    @ResponseBody
    public Map<String, Object> getFusionInfo(@RequestParam("rows") int rows,
                                             @RequestParam("page") int page,
                                             @RequestParam(value = "version", required = false, defaultValue = "") String version,
                                             @RequestParam(value = "platform", required = false, defaultValue = "999") int platform
                                          ) {
        Page<ContractInfo> pageInfo = PageHelper.startPage(page, rows, true);
        List<FusionInfo> fusionInfoList = fusionMapper.getFusionList(version, platform);
        return JqGridResultBuilder.builde(rows, page, pageInfo.getTotal(), fusionInfoList);

    }

    @PostMapping("/add")
    @ResponseBody
    public void addFusionInfo(@RequestParam("version") String version, @RequestParam("platform") String platform,
                              @RequestParam("fileName") String fileName) throws Exception {
        String absFileName = FileUtility.makeFusionDirectory() + "/" + fileName;
        String md5 = FileUtility.calcFileMd5(absFileName);
        fusionMapper.insertFusionInfo(version, platform, new Date(), fileName, md5);
    }
}
