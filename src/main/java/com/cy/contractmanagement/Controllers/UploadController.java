package com.cy.contractmanagement.Controllers;

import com.cy.contractmanagement.Utiliy.FileConvert;
import com.cy.contractmanagement.Utiliy.FileUtility;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;

@Controller
public class UploadController {

    @PostMapping("/upload-project-alert")
    public ResponseEntity uploadProjectAlertFile(@RequestParam("file") MultipartFile multipartFile) throws Exception {
        String fileExt = FileUtility.getFileExtension(multipartFile.getOriginalFilename());
        String uuid = FileUtility.getUuidString();
        String fileName = uuid + "." + fileExt;
        String absFileName = FileUtility.makeProjectAlertDirectory() + "/" + fileName;
        File f = new File(absFileName);
        if (!f.createNewFile()) {
            return ResponseEntity.badRequest().body(null);
        }
        FileOutputStream fileOutputStream = new FileOutputStream(f);
        try {
            fileOutputStream.write(multipartFile.getBytes());
            fileOutputStream.close();
            if (fileExt.equals("doc") || fileExt.equals("docx")) {
                String pdfFileName = FileUtility.makeProjectAlertDirectory() + "/" + uuid + ".pdf";
                FileConvert.wordToPdf(absFileName, pdfFileName);
            }
            return ResponseEntity.ok().body(fileName);
        } catch (Exception ex) {
            ex.printStackTrace();
            fileOutputStream.close();
            return ResponseEntity.badRequest().body(null);
        }
    }

    @PostMapping("/upload-fusion")
    public ResponseEntity uploadFusion(@RequestParam("file") MultipartFile multipartFile) throws Exception {
        String fileExt = FileUtility.getFileExtension(multipartFile.getOriginalFilename());
        String uuid = FileUtility.getUuidString();
        String fileName = uuid + "." + fileExt;
        String absFileName = FileUtility.makeFusionDirectory() + "/" + fileName;
        File f = new File(absFileName);
        if (!f.createNewFile()) {
            return ResponseEntity.badRequest().body(null);
        }
        FileOutputStream fileOutputStream = new FileOutputStream(f);
        try {
            fileOutputStream.write(multipartFile.getBytes());
            return ResponseEntity.ok().body(fileName);
        } catch (Exception ex) {
            ex.printStackTrace();
            return ResponseEntity.badRequest().body(null);
        } finally {
            fileOutputStream.close();
        }
    }
}
