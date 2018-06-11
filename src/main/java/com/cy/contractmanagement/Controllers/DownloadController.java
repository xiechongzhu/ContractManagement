package com.cy.contractmanagement.Controllers;

import com.cy.contractmanagement.Utiliy.FileUtility;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.io.File;
import java.io.FileInputStream;

@Controller
public class DownloadController {
    @GetMapping("/download-project-alert/{name}")
    public ResponseEntity downloadProjectAlertFile(@PathVariable("name") String fileName) throws Exception {
        String absFileName = FileUtility.makeProjectAlertDirectory() + "/" + fileName;
        FileInputStream stream = new FileInputStream(new File(absFileName));
        byte[] buffer = new byte[stream.available()];
        stream.read(buffer);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "attachment;filename=" + fileName);
        return ResponseEntity.ok().headers(headers).body(buffer);
    }

    @GetMapping("/view-project-alert/{name}")
    public ResponseEntity viewProjectAlertFile(@PathVariable("name") String fileName) throws Exception {
        String absFileName = FileUtility.makeProjectAlertDirectory() + "/" + fileName;
        FileInputStream stream = new FileInputStream(new File(absFileName));
        byte[] buffer = new byte[stream.available()];
        stream.read(buffer);
        HttpHeaders headers = new HttpHeaders();
        headers.add("content-type", "application/pdf");
        return ResponseEntity.ok().headers(headers).body(buffer);
    }

    @GetMapping("/word-template/{templateName}")
    public ResponseEntity getWordTemplate(@PathVariable("templateName") String templateName) throws Exception {
        String fileName = null;
        switch(templateName) {
            case "requisition":
                fileName = "技术通知单.doc";
                break;
            case "confirm":
                fileName = "变更申请分析及确认单.doc";
                break;
            default:
        }
        String absFileName = FileUtility.getWordTemplateDirectory() + "/" + fileName;
        FileInputStream stream = new FileInputStream(new File(absFileName));
        byte[] buffer = new byte[stream.available()];
        stream.read(buffer);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "attachment;filename=" +
                new String(fileName.getBytes("gb2312"), "ISO8859-1"));
        return ResponseEntity.ok().headers(headers).body(buffer);
    }

    @GetMapping("/download-fusion/{fileName}")
    public  ResponseEntity getFusion(@PathVariable("fileName") String fileName) throws Exception {
        String absFileName = FileUtility.makeFusionDirectory() + "/" + fileName;
        FileInputStream stream = new FileInputStream(new File(absFileName));
        byte[] buffer = new byte[stream.available()];
        stream.read(buffer);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "attachment;filename=" + fileName);
        return ResponseEntity.ok().headers(headers).body(buffer);
    }

    @GetMapping("/download-plugin/{fileName}")
    public ResponseEntity getPlugin(@PathVariable("fileName") String fileName) throws Exception {
        String absFileName = FileUtility.makePluginsDirectory() + "/" + fileName;
        FileInputStream stream = new FileInputStream(new File(absFileName));
        byte[] buffer = new byte[stream.available()];
        stream.read(buffer);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "attachment;filename=" + fileName);
        return ResponseEntity.ok().headers(headers).body(buffer);
    }
}
