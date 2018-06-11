package com.cy.contractmanagement.Controllers;

import com.cy.contractmanagement.Dao.Asset.FusionInfo;
import com.cy.contractmanagement.Dao.Asset.PluginInfo;
import com.cy.contractmanagement.Dao.Contract.ContractInfo;
import com.cy.contractmanagement.Dao.Mappers.PluginsMapper;
import com.cy.contractmanagement.Dao.Mappers.SqlProvider.PluginsProvider;
import com.cy.contractmanagement.Utiliy.FileUtility;
import com.cy.contractmanagement.Utiliy.JqGridResultBuilder;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import sun.plugin2.main.server.Plugin;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/asset/plugins")
public class PluginsController {
    @Autowired
    PluginsMapper pluginsMapper;

    @GetMapping("")
    public String getPluginsPage() {
        return "plugins";
    }

    @GetMapping("/get")
    @ResponseBody
    public Map<String, Object> getPlugins(@RequestParam("rows") int rows,
                                          @RequestParam("page") int page,
                                          @RequestParam(value = "name", required = false, defaultValue = "") String name,
                                          @RequestParam(value = "version", required = false, defaultValue = "") String version,
                                          @RequestParam(value = "platform", required = false, defaultValue = "999") int platform,
                                          @RequestParam(value = "fusionVersion", required = false, defaultValue = "") String fusionVersion) {
        Page pageInfo = PageHelper.startPage(page, rows, true);
        List<PluginInfo> fusionInfoList = pluginsMapper.getPluginInfoList(name, version, platform, fusionVersion);
        return JqGridResultBuilder.builde(rows, page, pageInfo.getTotal(), fusionInfoList);
    }

    @GetMapping("/get-all")
    @ResponseBody
    public List<PluginInfo> getAllPlugins() {
        return pluginsMapper.getAllPlugins();
    }

    @PostMapping("/add")
    @ResponseBody
    public void insertPlugin(@RequestParam("name") String name,
                             @RequestParam("version") String version,
                             @RequestParam("platform") int platform,
                             @RequestParam("fusionVersion") String fusionVersion,
                             @RequestParam("fileName") String fileName) throws Exception {
        String absFileName = FileUtility.makePluginsDirectory() + "/" + fileName;
        String md5 = FileUtility.calcFileMd5(absFileName);
        pluginsMapper.insertPlugin(name, version, platform, fusionVersion, new Date(), fileName, md5);
    }
}
