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
        //headers.add("content-type", "application/pdf");
        return ResponseEntity.ok().headers(headers).body(buffer);
    }

    @GetMapping("/view-project-alert/{name}")
    public ResponseEntity viewProjectAlertFile(@PathVariable("name") String fileName) throws Exception {
        String absFileName = FileUtility.makeProjectAlertDirectory() + "/" + fileName;
        FileInputStream stream = new FileInputStream(new File(absFileName));
        byte[] buffer = new byte[stream.available()];
        stream.read(buffer);
        HttpHeaders headers = new HttpHeaders();
        //headers.add("Content-Disposition", "attachment;filename="+fileName);
        headers.add("content-type", "application/pdf");
        return ResponseEntity.ok().headers(headers).body(buffer);
    }
}
