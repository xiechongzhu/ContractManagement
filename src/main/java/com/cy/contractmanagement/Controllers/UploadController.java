package com.cy.contractmanagement.Controllers;

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
    protected String makeProjectAlertDirectory() throws Exception {
        File f = new File("");
        File dir = new File(f.getAbsolutePath(), "ProjectAlertFile");
        if(!dir.exists()) {
            dir.mkdirs();
        }
        return  dir.getAbsolutePath();
    }

    @PostMapping("/upload-project-alert")
    public ResponseEntity uploadProjectAlertFile(@RequestParam("file") MultipartFile multipartFile) throws Exception {
        String fileExt = FileUtility.getFileExtension(multipartFile.getOriginalFilename());
        String fileName = FileUtility.getUuidString() + "." + fileExt;
        String absFileName = makeProjectAlertDirectory() + "/" + fileName;
        File f = new File(absFileName);
        if(!f.createNewFile()) {
            return ResponseEntity.badRequest().body(null);
        }
        FileOutputStream fileOutputStream = new FileOutputStream(f);
        try {
            fileOutputStream.write(multipartFile.getBytes());
            return  ResponseEntity.ok().body(fileName);
        }catch (Exception ex) {
            return ResponseEntity.badRequest().body(null);
        }finally {
            fileOutputStream.close();
        }
    }
}
